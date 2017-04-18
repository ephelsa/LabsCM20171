package co.edu.com.compumovil.gr09_20171.lab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Principal extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Datos> datosList;

    public Principal() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_principal, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        iniciar();

        return view;

    }

    private void iniciar() {

        //Los datosList.add se borran.
        int imagen = 0; // Hay que conservar esto. Dado que aquí pondrémos la dirección de cada img.
        datosList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            datosList.add(new Datos(R.mipmap.ic_launcher_round, "Nombre" + i, "Edad" + i));  // Datos de la base de datos.
        }

        initializeAdapter();
    }

    private void initializeAdapter() {
        Adaptador adaptador = new Adaptador(datosList);
        recyclerView.setAdapter(adaptador);

    }
}
