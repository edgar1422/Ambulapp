package com.example.Ambulapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Ambulapp.Entidades.Tema;
import com.example.Ambulapp.R;

import java.util.ArrayList;

public class Adapter_tema extends RecyclerView.Adapter<Adapter_tema.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<Tema> model;

    //Listener
    private View.OnClickListener listener;

    public Adapter_tema(Context context, ArrayList<Tema>model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listaconfiguraciones,parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre();
        int imagen = model.get(position).getImagenId();
        holder.nombre.setText(nombre);
        holder.imagen.setImageResource(imagen);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.titulo_tema);
            imagen = itemView.findViewById(R.id.imagen_configuracion);
        }
    }
}
