package co.edu.udea.compumovil.gr09_20171.lab4.Fragments.NavegationFragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.udea.compumovil.gr09_20171.lab4.Model.User;
import co.edu.udea.compumovil.gr09_20171.lab4.R;

public class PerfilActivity extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private ImageView foto;
    private TextView textView_perfil_nombre;
    private TextView textView_perfil_email;
    private TextView textView_perfil_edad;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();

    public PerfilActivity() {
        //Vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        View view = inflater.inflate(R.layout.activity_perfil, container, false);

        Button actualizar = (Button) view.findViewById(R.id.Button_perfil_actualizar);
        foto = (ImageView) view.findViewById(R.id.imageView_perfil_foto);
        textView_perfil_nombre = (TextView) view.findViewById(R.id.textView_perfil_nombre);
        textView_perfil_email = (TextView) view.findViewById(R.id.textView_perfil_email);
        textView_perfil_edad = (TextView) view.findViewById(R.id.textView_perfil_edad);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            final String userId = user.getUid();
            database.getReference("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user1=dataSnapshot.getValue(User.class);
                    textView_perfil_nombre.setText(user1.getUsername());
                    textView_perfil_email.setText(user1.getEmail());
                    textView_perfil_edad.setText(user1.getAge());
                    Glide.with(getActivity()).load(user1.getPhotoUrl()).into(foto);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }


}
