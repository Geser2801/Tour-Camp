package com.rodr.tourcamp.Adapter_Maps;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rodr.tourcamp.Activities.Activity_Principal;
import com.rodr.tourcamp.Activities.MapsActivity;
import com.rodr.tourcamp.Contexto.AppFragments;
import com.rodr.tourcamp.R;

import java.util.List;

public class ReciclerAdapterMaps extends RecyclerView.Adapter<ReciclerAdapterMaps.MapsViewHolder> {
    /*private int imgMaps;
    private String txtMaps;
    private String txtMapsDescripcion;*/

    public static Intent intent; // VARIABLE DE TIPO INTENT....

    private List<Maps> items; // LISTA DE ARREGLO DE TIPO MAPS (CLASE)...

    // CLASE DE ADAPTACION DE LOS DATOS QUE CONTIENE EL RECYCLER...
    public static class MapsViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgMaps; // IMAGEN...
        public TextView txtMaps; // TITULO...
        public TextView txtMapsDescripcion; // DESCRIPCION...

        public MapsViewHolder(View view) {
            super(view);
            // INSTACIAMIENTO DE MIS OBJETOS....
            imgMaps = (ImageView) view.findViewById(R.id.img_Maps);
            txtMaps = (TextView) view.findViewById(R.id.txt_Maps);
            txtMapsDescripcion = (TextView) view.findViewById(R.id.txt_Maps_Descripcion);

            // METODO O EVENTO ONCLICKLISTENER....
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition(); // OBTENDO UNA POSICION EN LA CLASE ADAPTER POSTION...

                    switch (position){ // EVALUAMOS LA POSICION PARA DESPUES PASARLELO A LA CLASE MAPSACTIVITY...
                        case 0:
                            // PARA EL CASO 0 SI ES EL CASO 0....
                            //Toast.makeText(AppFragments.context, "" + position, Toast.LENGTH_SHORT).show();
                            intent = new Intent(AppFragments.context, MapsActivity.class); // NOS DIRIGIMOS A MAPS ACTIVITY..
                            intent.putExtra("valor", "0"); // LOS DATOS SE LO MADAMOS A MapsActivity Y LO EVALUA ESA CLASE...
                            view.getContext().startActivity(intent); // ENTRAMOS A MapsActivity....
                            break;
                        case 1:
                            //Toast.makeText(AppFragments.context, "" + position, Toast.LENGTH_SHORT).show();
                            intent = new Intent(AppFragments.context, MapsActivity.class);
                            intent.putExtra("valor", "1");
                            view.getContext().startActivity(intent);
                            break;
                        case 2:
                            //Toast.makeText(AppFragments.context, "" + position, Toast.LENGTH_SHORT).show();
                            intent = new Intent(AppFragments.context, MapsActivity.class);
                            intent.putExtra("valor", "2");
                            view.getContext().startActivity(intent);
                            break;
                        case 3:
                            //Toast.makeText(AppFragments.context, "" + position, Toast.LENGTH_SHORT).show();
                            intent = new Intent(AppFragments.context, MapsActivity.class);
                            intent.putExtra("valor", "3");
                            view.getContext().startActivity(intent);
                            break;
                        case 4:
                            //Toast.makeText(AppFragments.context, "" + position, Toast.LENGTH_SHORT).show();
                            intent = new Intent(AppFragments.context, MapsActivity.class);
                            intent.putExtra("valor", "4");
                            view.getContext().startActivity(intent);
                            break;
                    }
                }
            });
        }
    }

    // OBTENEMOS EL TAMAÑO DEL RECYCLER Y DEL MAPS....
    public ReciclerAdapterMaps(List<Maps> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // CREAMOS EL ADAPTADOR O VIEW PARA VISUALIZAR LOS DATOS EN EL RECYCLER EN EL DISEÑO....
    @Override
    public MapsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_maps_calakmul_sitios, viewGroup, false);
        return new MapsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MapsViewHolder viewHolder, int i) {

        // OBTENEMOS LOS DATOS EN LA CLASE MAPS DONDE OBTUVIMOS LOS GETTERS...
        viewHolder.imgMaps.setImageResource(items.get(i).getImgMaps());
        viewHolder.txtMaps.setText(items.get(i).getTxtMaps());
        viewHolder.txtMapsDescripcion.setText(items.get(i).getTxtMapsDescripcion());
    }
}
