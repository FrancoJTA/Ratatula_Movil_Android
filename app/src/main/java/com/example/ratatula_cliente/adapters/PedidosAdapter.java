package com.example.ratatula_cliente.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatula_cliente.Api.Models.Pedido;
import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.principal.LocalDetailActivity;
import com.example.ratatula_cliente.principal.PedidoInfoActivity;

import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.LocalViewHolder> {

    private List<Pedido> pedidos;
    private Context context;

    public PedidosAdapter(List<Pedido> pedidos, Context context) {
        this.pedidos = pedidos;
        this.context = context;
    }

    @Override
    public PedidosAdapter.LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.local_item, parent, false); // Aquí usas el layout para cada ítem
        return new PedidosAdapter.LocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PedidosAdapter.LocalViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PedidoInfoActivity.class);

                intent.putExtra("ID_PEDIDO", pedido.getId());
                context.startActivity(intent);
            }
        });
        holder.nombreTextView.setText(pedido.getId());
        holder.correoTextView.setText(pedido.getEstado());
    }

    @Override
    public int getItemCount() {
        return pedidos != null ? pedidos.size() : 0;
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