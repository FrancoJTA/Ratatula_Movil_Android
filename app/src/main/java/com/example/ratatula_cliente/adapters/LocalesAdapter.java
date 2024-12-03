package com.example.ratatula_cliente.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.principal.HolderActivity;
import com.example.ratatula_cliente.principal.LocalDetailActivity;

import java.util.List;

public class LocalesAdapter extends RecyclerView.Adapter<LocalesAdapter.LocalViewHolder> {

    private List<Usuario> usuarios;
    private Context context;

    public LocalesAdapter(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @Override
    public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.local_item, parent, false); // Aquí usas el layout para cada ítem
        return new LocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LocalViewHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LocalDetailActivity.class);

                intent.putExtra("LOCAL_ID", usuario.getId());
                context.startActivity(intent);
            }
        });
        holder.nombreTextView.setText(usuario.getNombre());
        holder.correoTextView.setText(usuario.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
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