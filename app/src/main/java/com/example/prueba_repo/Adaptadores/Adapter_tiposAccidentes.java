package com.example.prueba_repo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prueba_repo.Entidades.tiposAccidentes;
import com.example.prueba_repo.R;


import org.w3c.dom.Text;

import java.util.List;

public class Adapter_tiposAccidentes extends BaseAdapter {

   private Context contexto;
   private List<tiposAccidentes> tiposAccidentesList;

   public Adapter_tiposAccidentes (Context context,List<tiposAccidentes> tiposAccidentesList){

       this.contexto = context ;
       this.tiposAccidentesList = tiposAccidentesList;

   }


    @Override
    public int getCount() {
        return tiposAccidentesList.size();
    }

    @Override
    public Object getItem(int position) {
        return tiposAccidentesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista = inflate.inflate(R.layout.item_spinner,null);

        ImageView imagen =(ImageView) vista.findViewById(R.id.iv_tipoaccidente);
        TextView descripcion =(TextView) vista.findViewById(R.id.tv_tipoaccidente);

        imagen.setImageResource(tiposAccidentesList.get(position).getImagen_accidente());
        descripcion.setText(tiposAccidentesList.get(position).getNombre_accidente());

        return vista;
    }
}
