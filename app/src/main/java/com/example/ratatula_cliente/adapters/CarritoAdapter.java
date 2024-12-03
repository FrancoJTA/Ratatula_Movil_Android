package com.example.ratatula_cliente.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatula_cliente.Api.GraphQL.GraphQLCallback;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLOperations;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLResponse;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.CarritoProducto;
import com.example.ratatula_cliente.Api.Models.Producto;
import com.example.ratatula_cliente.Api.Models.ResponseOBJ;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.Session.AppData;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.LocalViewHolder> {
    private List<CarritoProducto> carritoProductos;
    private Context context;

    public CarritoAdapter(List<CarritoProducto> carritoProductos, Context context) {
        this.carritoProductos = carritoProductos;
        this.context = context;
    }

    @Override
    public CarritoAdapter.LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carrito_item, parent, false); // Aquí usas el layout para cada ítem
        return new CarritoAdapter.LocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarritoAdapter.LocalViewHolder holder, int position) {
        CarritoProducto carritoProducto = carritoProductos.get(position);
        holder.nombreTextView.setText(carritoProducto.getNombre());
        holder.stocktextView.setText(carritoProducto.getCantidad()+"");
        holder.addstock.setOnClickListener(v -> agregaraCarrito(carritoProducto.getId()));
    }

    private void agregaraCarrito(String id) {
        // Definir la mutation de GraphQL
        String mutation = GraphQLOperations.QUITAR_PRODUCT_CARRITO;

        // Definir las variables de la mutation
        String variables = "{ \"idUsuario\": \"" + AppData.getInstance().getUsuario().getId() + "\", \"idProducto\": \"" + id + "\" }";

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
        return carritoProductos != null ? carritoProductos.size() : 0;
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