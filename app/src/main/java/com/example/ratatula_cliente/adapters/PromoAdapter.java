package com.example.ratatula_cliente.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatula_cliente.Api.Models.Promocion;
import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.principal.LocalDetailActivity;

import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.LocalViewHolder> {

    private List<Promocion> promocions;
    private Context context;

    public PromoAdapter(List<Promocion> promocions, Context context) {
        this.promocions = promocions;
        this.context = context;
    }

    @Override
    public PromoAdapter.LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.local_item, parent, false); // Aquí usas el layout para cada ítem
        return new PromoAdapter.LocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PromoAdapter.LocalViewHolder holder, int position) {
        Promocion promocion = promocions.get(position);
        holder.nombreTextView.setText(promocion.getNombre());
        holder.correoTextView.setText(promocion.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return promocions != null ? promocions.size() : 0;
    }

    public static class LocalViewHolder extends RecyclerView.ViewHolder {
        public CardView carta;
        public TextView nombreTextView;
        public TextView correoTextView;

        public LocalViewHolder(View itemView) {
            super(itemView);
            carta = itemView.findViewById(R.id.item_card);
            nombreTextView = itemView.findViewById(R.id.name);
            correoTextView = itemView.findViewById(R.id.descripcion);
        }
    }
}