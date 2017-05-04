package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by leonardo on 17/04/17.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Datos> datosList;
    private Context context = null;

    Adaptador(List<Datos> datosList) {
        this.datosList = datosList;
    }

    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Adaptador.ViewHolder holder, final int position) {
        holder.foto.setImageResource(datosList.get(position).getIdPhoto());
        holder.nombre.setText(datosList.get(position).getNombre());
        holder.edad.setText(datosList.get(position).getEdad());

        /*
        * Aquí están definidos los clics en cada card.
        * */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Nombre: " + datosList.get(position).getNombre(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return datosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*
        * Aquí se declaran y definen los elementos de la vista por si se necesitan más datos.        *
        * */
        CardView card;
        ImageView foto;
        TextView nombre, edad;


        public ViewHolder(View itemView) {
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.cv);
            foto = (ImageView) itemView.findViewById(R.id.person_photo);
            nombre = (TextView) itemView.findViewById(R.id.person_name);
            edad = (TextView) itemView.findViewById(R.id.person_age);
        }
    }

}
