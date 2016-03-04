package com.example.dam.actividadesrestretrofit.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dam.actividadesrestretrofit.R;
import com.example.dam.actividadesrestretrofit.pojo.Actividad;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ActividadViewHolder> {

    private ArrayList<Actividad> actividads;

    public Adaptador(ArrayList<Actividad> actividads) {
        this.actividads = actividads;
    }

    @Override
    public ActividadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        ActividadViewHolder pvh = new ActividadViewHolder(itemView);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ActividadViewHolder holder, int position) {
        Actividad p = actividads.get(position);
        holder.bindActividad(p);
    }

    @Override
    public int getItemCount() {
        return actividads.size();
    }

    public static class ActividadViewHolder extends RecyclerView.ViewHolder{
        public TextView contenido, tipo, fecha, lugar;
        public ActividadViewHolder(View itemView) {
            super(itemView);
            contenido = (TextView) itemView.findViewById(R.id.txContenido);
            tipo = (TextView) itemView.findViewById(R.id.txTipo);
            fecha = (TextView) itemView.findViewById(R.id.txFecha);
            lugar = (TextView) itemView.findViewById(R.id.txLugar);
        }
        public void bindActividad(Actividad ke) {
            contenido.setText(ke.getDescripcion());
            if (ke.getTipo().compareTo("complementaria")==0){
                tipo.setText("C");
            }else{
                tipo.setText("E");
            }
            fecha.setText(ke.getFechai() + " -> " + ke.getFechaf());
            lugar.setText(ke.getLugari() + " -> " + ke.getLugarf());
        }
    }
}
