package co.edu.com.compumovil.gr09_20171.lab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import co.edu.com.compumovil.gr09_20171.lab3.Otros.InfoUsuario;
import co.edu.com.compumovil.gr09_20171.lab3.POJO.RestInterface;
import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Actualizar_Perfil extends Fragment implements View.OnClickListener {
    private InfoUsuario infoUsuario;
    private EditText username, email, age;
    private Fragment perfil;
    FragmentManager fragmentManager = getFragmentManager();

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
        boolean res = false;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(infoUsuario.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RestInterface restInterface = retrofit.create(RestInterface.class);
        Call<Usuario> call = restInterface.updateUser(infoUsuario.getUsername(), u, infoUsuario.getPassword()
                , e, a, infoUsuario.getPhoto());


        try {

            Usuario data = call.execute().body();
            infoUsuario.getBDdata(infoUsuario.getUsername());
            if(infoUsuario.getEmail().equals(e)||infoUsuario.getName().equals(u)||infoUsuario.getAge().equals(a)) {
                Toast.makeText(getContext().getApplicationContext(), "Updated ok ", Toast.LENGTH_SHORT).show();
                perfil=new Perfil();
                Bundle args=new Bundle();
                args.putSerializable("dat",infoUsuario);
                perfil.setArguments(args);
                fragmentManager.beginTransaction().replace(R.id.fragment_content, perfil).commit();
            }else{
                Toast.makeText(getContext().getApplicationContext(),"Update error",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


        @Override
        public void onClick (View v){
updateU();
        }
    }
