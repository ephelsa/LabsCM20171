package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.lab4.Adapter;
import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;
import co.edu.udea.compumovil.gr09_20171.lab4.R;

public class EventsViewActivity extends Fragment {
    private RecyclerView recyclerView;
    private List<PostEvent> events;
    Fragment createEvent;

    public EventsViewActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_events_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        events = new ArrayList<>();
        final Adapter adapter;


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new Adapter(events);

        recyclerView.setAdapter(adapter);
        database.getReference("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                events.removeAll(events);
                for (DataSnapshot snapshop :
                        dataSnapshot.getChildren()) {
                    PostEvent event = snapshop.getValue(PostEvent.class);
                    events.add(event);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       final FragmentManager fragmentManager = getFragmentManager();
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                createEvent = new CreateEvent();
                fragmentManager.beginTransaction().replace(R.id.fragment_content, createEvent).commit();
            }
        });

        return view;
    }

}
