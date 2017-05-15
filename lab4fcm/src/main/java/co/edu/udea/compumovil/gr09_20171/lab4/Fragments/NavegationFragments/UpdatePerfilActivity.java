package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.udea.compumovil.gr09_20171.lab4.R;

public class UpdatePerfilActivity extends Fragment implements View.OnClickListener {
    private EditText username, email, age;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }};
        FirebaseUser user=firebaseAuth.getCurrentUser();


            View view = inflater.inflate(R.layout.activity_update_perfil, container, false);
        username = (EditText) view.findViewById(R.id.Upd_perfil_User);
        email = (EditText) view.findViewById(R.id.Upd_perfil_email);
        age = (EditText) view.findViewById(R.id.Upd_perfil_edad);
        Button actualizar = (Button) view.findViewById(R.id.Upd_perfil_act);
        username.setText(user.getDisplayName());
        email.setText(user.getEmail());
        age.setText("");
        actualizar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
