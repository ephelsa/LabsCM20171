package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;
import co.edu.udea.compumovil.gr09_20171.lab4.R;

public class EventViewInfoActivity extends Fragment {
    private PostEvent event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = (PostEvent) getArguments().getSerializable("data");
    }

    public EventViewInfoActivity(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_event_view_info, container, false);
        ImageView eventPhoto=(ImageView)view.findViewById(R.id.InImagen);
        TextView eventname=(TextView)view.findViewById(R.id.InTitle);
        RatingBar eventScore=(RatingBar)view.findViewById(R.id.InScore);
        TextView eventDate=(TextView)view.findViewById(R.id.InFecha);
        TextView eventLocation=(TextView)view.findViewById(R.id.InLocation);
        TextView eventInfo=(TextView)view.findViewById(R.id.InInfo);

        Glide.with(getActivity()).load(event.getPhoto()).into(eventPhoto);
        eventname.setText(event.getTitle());
        eventScore.setRating(event.getScore());
        eventDate.setText(event.getFecha());
        eventLocation.setText(event.getLocation());
        eventInfo.setText(event.getInfo());


        return view;
    }
}
