package com.example.prueba_repo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_repo.Adaptadores.Adapter_tema;
import com.example.prueba_repo.Entidades.Tema;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Configuraciones_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Configuraciones_Fragment extends Fragment {

    private Window window;
    private Adapter_tema adapter_tema;
    RecyclerView recyclerViewTemas;
    ArrayList<Tema> listaTemas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Configuraciones_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Configuraciones_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Configuraciones_Fragment newInstance(String param1, String param2) {
        Configuraciones_Fragment fragment = new Configuraciones_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        this.window = getActivity().getWindow();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuraciones, container, false);
        recyclerViewTemas = view.findViewById(R.id.recyclerView);
        listaTemas = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarDatos();
        return view;
    }

    public void cargarLista(){
        listaTemas.add(new Tema("Unidades Distancia",R.drawable.distancia));
        listaTemas.add(new Tema("Modo Oscuro",R.drawable.modo_oscuro));
        listaTemas.add(new Tema("Tamaño Letra",R.drawable.tama_letra));
        listaTemas.add(new Tema("Politica de privacidad",R.drawable.politica_privacidad));
        listaTemas.add(new Tema("Acerca de",R.drawable.acerca_de));
        listaTemas.add(new Tema("Eliminar cuenta",R.drawable.eliminar_cuenta));
        listaTemas.add(new Tema("Comentarios",R.drawable.comentario));
    }
    public void mostrarDatos(){
        recyclerViewTemas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_tema = new Adapter_tema(getContext(), listaTemas);
        recyclerViewTemas.setAdapter(adapter_tema);

        adapter_tema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = listaTemas.get(recyclerViewTemas.getChildAdapterPosition(v)).getNombre();
                Toast.makeText(getContext(), "Seleccionó el tema: " + nombre, Toast.LENGTH_SHORT).show();
            }
        });
    }
}