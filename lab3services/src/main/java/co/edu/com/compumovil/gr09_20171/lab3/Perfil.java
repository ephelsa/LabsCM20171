package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public Perfil() {
        //Vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoUsuario= (InfoUsuario) getArguments().getSerializable("dat");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_perfil, container, false);
        Button actualizar=(Button)view.findViewById(R.id.Button_perfil_actualizar);
        ImageView foto = (ImageView) view.findViewById(R.id.imageView_perfil_foto);
        TextView textView_perfil_nombre = (TextView) view.findViewById(R.id.textView_perfil_nombre);
        TextView textView_perfil_email = (TextView) view.findViewById(R.id.textView_perfil_email);
        TextView textView_perfil_edad = (TextView) view.findViewById(R.id.textView_perfil_edad);
actualizar.setOnClickListener(this);
        //try {
        /*    Bitmap img=admin.getFotoUser(getUsuario());
            if(img!=null) {
                foto.setImageBitmap(img);
            }*/
            textView_perfil_nombre.setText(infoUsuario.getUsername());
            textView_perfil_email.setText(infoUsuario.getEmail());
            textView_perfil_edad.setText(infoUsuario.getAge());
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/

        return view;
    }

   /* private String getUsuario () throws IOException {
        final String filename = "registro.txt";
        String usuario = "";
        BufferedReader lector;
        lector = new BufferedReader(
                new InputStreamReader(
                        getActivity().openFileInput(filename)));
        usuario = lector.readLine();
        return usuario;
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Button_perfil_actualizar:
                Intent mainIntent = new Intent().setClass(
                        getActivity(), Actualizar_Perfil.class);
                startActivity(mainIntent);
                break;
        }
    }
}
