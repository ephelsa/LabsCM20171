package co.edu.udea.compumovil.gr09_20171.lab4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;

/**
 * Created by julian on 3/05/17.
 */

public class Adapter extends  RecyclerView.Adapter<Adapter.EventsViewHolder>{

    List<PostEvent> events;

    public Adapter(List<PostEvent> events) {
        this.events = events;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        EventsViewHolder holder=new EventsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
PostEvent event=events.get(position);
        holder.title.setText(event.getTitle());
        holder.descri.setText(event.getInfo());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {
TextView title,descri;
        public EventsViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.eventTitle);
            descri=(TextView)itemView.findViewById(R.id.eventDescri);
        }
    }
}
