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

    private TextView textView_perfil_nombre;
    private TextView textView_perfil_email;
    private TextView textView_perfil_edad;

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

        return view;
    }
}
