package co.edu.udea.compumovil.gr09_20171.lab2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Perfil extends Fragment {

    private Login login;


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

        View view = inflater.inflate(R.layout.activity_perfil, container, false);
        TextView textView_perfil_nombre = (TextView) view.findViewById(R.id.textView_perfil_nombre);
        TextView textView_perfil_email = (TextView) view.findViewById(R.id.textView_perfil_email);
        TextView textView_perfil_edad = (TextView) view.findViewById(R.id.textView_perfil_edad);

        textView_perfil_nombre.setText("Aquí va el usuario");
        textView_perfil_email.setText("Aquí va el email");
        textView_perfil_edad.setText("Aquí va el edad");

        return view;
    }
}
