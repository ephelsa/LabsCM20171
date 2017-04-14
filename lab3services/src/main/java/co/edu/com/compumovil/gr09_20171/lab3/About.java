package co.edu.com.compumovil.gr09_20171.lab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class About extends Fragment {

    public About () {
        // Vacío
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_about, container, false);

        TextView materiaTitulo = (TextView) view.findViewById(R.id.about_materia);
        TextView materiaContent = (TextView) view.findViewById(R.id.about_materia_content);
        TextView cursoTitulo = (TextView) view.findViewById(R.id.about_curso);
        TextView cursoContent = (TextView) view.findViewById(R.id.about_curso_content);

        materiaTitulo.setText(R.string.materia_titulo);
        materiaContent.setText(R.string.materia_content);
        cursoTitulo.setText(R.string.curso_titulo);
        cursoContent.setText(R.string.curso_content);

        return view;
    }

}
