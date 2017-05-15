package co.edu.udea.compumovil.gr09_20171.lab4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments.CreateEvent;
import co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments.EventViewInfoActivity;
import co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments.EventsViewActivity;
import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;

/**
 * Created by julian on 3/05/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.EventsViewHolder> {

    List<PostEvent> events;
    private Context context = null;


    public Adapter(List<PostEvent> events) {
        this.events = events;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_events, parent, false);
        EventsViewHolder holder = new EventsViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        final PostEvent event = events.get(events.size()-1-position);
        Glide.with(context).load(event.getPhoto()).into(holder.imageView);
        holder.title.setText(event.getTitle());
        if(event.getInfo().length()>=60) {
            holder.descri.setText(event.getInfo().substring(0, 60) + "...");
        }else{
            holder.descri.setText(event.getInfo());
        }
        holder.ratingBar.setRating(event.getScore());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                Fragment enventview  = new EventViewInfoActivity();
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",event);
                enventview.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.fragment_content, enventview).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {
        TextView title, descri;
        ImageView imageView;
        CardView cv;
        RatingBar ratingBar;


        public EventsViewHolder(View itemView) {
            super(itemView);
            ratingBar=(RatingBar)itemView.findViewById(R.id.score);
            cv = (CardView) itemView.findViewById(R.id.cv);
            imageView = (ImageView) itemView.findViewById(R.id.eventImg);
            title = (TextView) itemView.findViewById(R.id.eventTitle);
            descri = (TextView) itemView.findViewById(R.id.eventDescri);
        }
    }
}
