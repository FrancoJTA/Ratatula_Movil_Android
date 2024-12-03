package com.example.ratatula_cliente.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatula_cliente.Api.GraphQL.GraphQLCallback;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLOperations;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLResponse;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.Producto;
import com.example.ratatula_cliente.Api.Models.ResponseOBJ;
import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.Session.AppData;
import com.example.ratatula_cliente.principal.LocalDetailActivity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.LocalViewHolder> {

    private List<Producto> productos;
    private Context context;

    public ProductoAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @Override
    public ProductoAdapter.LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto_item, parent, false); // Aquí usas el layout para cada ítem
        return new ProductoAdapter.LocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoAdapter.LocalViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.nombreTextView.setText(producto.getNombre());
        holder.correoTextView.setText(producto.getDescripcion());
        holder.addstock.setOnClickListener(v -> agregaraCarrito(producto.getId()));
    }

    private void agregaraCarrito(String id) {
        // Definir la mutation de GraphQL
        String mutation = GraphQLOperations.ADD_PRODUCT_CARRITO;

        // Definir las variables de la mutation
        String variables = "{ \"idUsuario\": \"" + AppData.getInstance().getUsuario().getId() + "\", \"producto\": { \"idProducto\": \"" + id + "\" , \"cantidad\": " + 1 + " } }";

        OkHttpGraphQLClient client = new OkHttpGraphQLClient();
        Type responseType = new TypeToken<GraphQLResponse<ResponseOBJ>>() {}.getType();
        // Realizar la solicitud GraphQL
        client.sendRequest(mutation, variables, responseType, new GraphQLCallback<GraphQLResponse<ResponseOBJ>>() {
            @Override
            public void onSuccess(GraphQLResponse<ResponseOBJ> result) {
                Log.d("Good", "wazaaaaaaaaaaaaaaaaaa");
            }

            @Override
            public void onError(Exception e) {
                // En caso de error al hacer la solicitud

                Log.d("fadsfkasjf", "Mierda ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos != null ? productos.size() : 0;
    }

    public static class LocalViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTextView;
        public TextView correoTextView;
        public TextView stocktextView;
        public ImageButton addstock;
        public LocalViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.name);
            correoTextView = itemView.findViewById(R.id.descripcion);
            stocktextView = itemView.findViewById(R.id.stock);
            addstock = itemView.findViewById(R.id.agregar);

        }
    }
}
