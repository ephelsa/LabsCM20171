package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.udea.compumovil.gr09_20171.lab4.Model.PostEvent;
import co.edu.udea.compumovil.gr09_20171.lab4.R;

public class CreateEvent extends Fragment {
    final public static String EVENTS_REFERENCE = "Events";

    public CreateEvent(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create_event, container, false);
        return view;

        /*FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference eventReference=database.getReference(EVENTS_REFERENCE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostEvent event=new PostEvent("klkls","Julian",title.getText().toString(),"12/12/12","MEdellin",cont.getText().toString(),"null");
                eventReference.push().setValue(event);

            }
        });*/
    }
}