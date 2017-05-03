package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;
import co.edu.udea.compumovil.gr09_20171.lab4.R;

public class EventsViewActivity extends Fragment {
private RecyclerView recyclerView;
    List<PostEvent> events;

    public EventsViewActivity(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_events_view, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        events=new ArrayList<>();

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        return view;
    }

}
