package com.example.catalogo_videojuegos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogo_videojuegos.API.Juego_Interface;

import java.util.List;

public class JuegoAdapter extends RecyclerView.Adapter<JuegoAdapter.JuegoViewHolder> {

    private List<Juego_Interface> juegos;
    private Context context;

    public static class JuegoViewHolder extends RecyclerView.ViewHolder {

        public TextView no;
        public TextView es;
        public TextView pl;
        public TextView cl;
        public TextView fe;

        public JuegoViewHolder(View view) {

            super(view);
            no = view.findViewById(R.id.nombre);
            es = view.findViewById(R.id.estudio);
            pl = view.findViewById(R.id.plataforma);
            cl = view.findViewById(R.id.clasi);
            fe = view.findViewById(R.id.fechalan);

        }

    }

    public JuegoAdapter(List<Juego_Interface> juegos, Context context){
        this.juegos = juegos;
        this.context = context;
    }

    @Override
    public JuegoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_juego,parent,false);
        return new JuegoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull JuegoAdapter.JuegoViewHolder holder, int position) {

        Juego_Interface ac = juegos.get(position);
        holder.no.setText(ac.getNombre());
        holder.es.setText(ac.getEstudio());
        holder.pl.setText(ac.getPlataforma());
        holder.cl.setText(ac.getClasi());
        holder.fe.setText(ac.getFechalan());

    }

    @Override
    public int getItemCount() {

        return juegos.size();

    }

}