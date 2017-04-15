package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Perfil extends Fragment implements View.OnClickListener {
    private InfoUsuario infoUsuario;
private Fragment perfil;
    public Perfil() {
        //Vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoUsuario = (InfoUsuario) getArguments().getSerializable("dat");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_perfil, container, false);
        Button actualizar = (Button) view.findViewById(R.id.Button_perfil_actualizar);
        ImageView foto = (ImageView) view.findViewById(R.id.imageView_perfil_foto);
        TextView textView_perfil_nombre = (TextView) view.findViewById(R.id.textView_perfil_nombre);
        TextView textView_perfil_email = (TextView) view.findViewById(R.id.textView_perfil_email);
        TextView textView_perfil_edad = (TextView) view.findViewById(R.id.textView_perfil_edad);
        actualizar.setOnClickListener(this);
        textView_perfil_nombre.setText(infoUsuario.getUsername());
        textView_perfil_email.setText(infoUsuario.getEmail());
        textView_perfil_edad.setText(infoUsuario.getAge());

        return view;
    }


    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()) {
            case R.id.Button_perfil_actualizar:
                /*Intent mainIntent = new Intent().setClass(
                        getActivity(), Actualizar_Perfil.class);
                startActivity(mainIntent);*/
                perfil=new Actualizar_Perfil();
                Bundle args=new Bundle();
                args.putSerializable("dat",infoUsuario);
                perfil.setArguments(args);
                fragmentManager.beginTransaction().replace(R.id.fragment_content, perfil).commit();
                break;
        }
    }

    public void loadbd2(String u){

    }
}
