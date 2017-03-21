package co.edu.udea.compumovil.gr09_20171.lab2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.*;


public class Perfil extends Fragment {

    public Perfil() {
        //Vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), "compumovil", null, 1);

        View view = inflater.inflate(R.layout.activity_perfil, container, false);

        ImageView foto = (ImageView) view.findViewById(R.id.imageView_perfil_foto);
        TextView textView_perfil_nombre = (TextView) view.findViewById(R.id.textView_perfil_nombre);
        TextView textView_perfil_email = (TextView) view.findViewById(R.id.textView_perfil_email);
        TextView textView_perfil_edad = (TextView) view.findViewById(R.id.textView_perfil_edad);

        try {
            textView_perfil_nombre.setText(getUsuario());
            textView_perfil_email.setText(admin.getEmailUser(getUsuario()));
            textView_perfil_edad.setText(admin.getEdadUser(getUsuario()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private String getUsuario () throws IOException {
        final String filename = "registro.txt";
        String usuario = "";
        BufferedReader lector;
        lector = new BufferedReader(
                new InputStreamReader(
                        getActivity().openFileInput(filename)));
        usuario = lector.readLine();
        return usuario;
    }
}
