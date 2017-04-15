package co.edu.com.compumovil.gr09_20171.lab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Actualizar_Perfil extends Fragment implements View.OnClickListener {
   private InfoUsuario infoUsuario;
    private EditText username, email, age;
    private Fragment perfil;

    public Actualizar_Perfil() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoUsuario = (InfoUsuario) getArguments().getSerializable("dat");


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_actualizar__perfil, container, false);
        username = (EditText) view.findViewById(R.id.Act_perfil_User);
        email = (EditText) view.findViewById(R.id.Act_perfil_email);
        age = (EditText) view.findViewById(R.id.Act_perfil_edad);
        Button actualizar = (Button) view.findViewById(R.id.Act_perfil_actu);
        username.setText(infoUsuario.getName());
        email.setText(infoUsuario.getEmail());
        age.setText(infoUsuario.getAge());
        actualizar.setOnClickListener(this);
        return view;
    }



    private void updateU() {
        final String u = username.getText().toString().toUpperCase();
        final String e = email.getText().toString().toUpperCase();
        final String a = age.getText().toString().toUpperCase();
        final FragmentManager fragmentManager = getFragmentManager();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(infoUsuario.getUrl()).build();

        //Creating Rest Services
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.updateUser(infoUsuario.getUsername(),
                u, infoUsuario.getPassword(), e, a, infoUsuario.getPhoto(), new Callback<Usuario>() {
                    @Override
                    public void success(Usuario usuario, Response response) {
                        infoUsuario.setName(u);
                        infoUsuario.setEmail(e);
                        infoUsuario.setAge(a);
                        perfil=new Perfil();
                        Bundle args=new Bundle();
                        args.putSerializable("dat",infoUsuario);
                        perfil.setArguments(args);
                        fragmentManager.beginTransaction().replace(R.id.fragment_content, perfil).commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }


    @Override
    public void onClick(View v) {
updateU();
    }
}
