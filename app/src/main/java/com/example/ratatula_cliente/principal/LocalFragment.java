package com.example.ratatula_cliente.principal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ratatula_cliente.Api.GraphQL.GraphQLCallback;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLOperations;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLResponse;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.ListResponse;
import com.example.ratatula_cliente.Api.Models.Producto;
import com.example.ratatula_cliente.Api.Models.Promocion;
import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.Session.AppData;
import com.example.ratatula_cliente.adapters.LocalesAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class LocalFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LocalFragment() {
    }

    public static LocalFragment newInstance(String param1, String param2) {
        LocalFragment fragment = new LocalFragment();
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
    }

    private OkHttpGraphQLClient client;
    private List<Usuario> usuarios;
    private RecyclerView recyclerView;
    private LocalesAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        client = new OkHttpGraphQLClient();
        View view= inflater.inflate(R.layout.fragment_local, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewLocales);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LocalesAdapter(usuarios,getContext());
        recyclerView.setAdapter(adapter);

        // Llamar al método para obtener los locales
        obtenerLocales();

        return view;
    }

    private void obtenerLocales() {
        String query = GraphQLOperations.GET_LOCALES;

        Type responseType = new TypeToken<GraphQLResponse<ListResponse>>() {}.getType();

        client.sendRequest(query, null, responseType, new GraphQLCallback<GraphQLResponse<ListResponse>>() {
            @Override
            public void onSuccess(GraphQLResponse<ListResponse> result) {
                // Asegúrate de acceder a la lista de locales de forma segura
                usuarios = result.data.obtenerLocales;

                // Ahora debes actualizar el RecyclerView en el hilo principal
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Aquí actualizas el RecyclerView
                        if (usuarios != null && !usuarios.isEmpty()) {
                            AppData.getInstance().setLocales(usuarios);
                            ObtenerProductos();
                            // Actualizar el RecyclerView con los nuevos datos
                            LocalesAdapter adapter = new LocalesAdapter(usuarios,getContext());
                            RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewLocales);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getContext(), "No se encontraron locales", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Log.e("UsuarioFragment", "Error al obtener locales: " + e.getMessage());
                Toast.makeText(getContext(), "Error al cargar locales", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ObtenerProductos(){

        String query = GraphQLOperations.GET_PRODUCTOS;

        Type responseType = new TypeToken<GraphQLResponse<ListResponse>>() {}.getType();

        client.sendRequest(query, null, responseType, new GraphQLCallback<GraphQLResponse<ListResponse>>() {
            @Override
            public void onSuccess(GraphQLResponse<ListResponse> result) {
                // Asegúrate de acceder a la lista de locales de forma segura
                List<Producto> productos = result.data.obtenerProductos;

                // Ahora debes actualizar el RecyclerView en el hilo principal
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (productos != null && !productos.isEmpty()) {
                            AppData.getInstance().setProductos(productos);
                            Log.e("Productos", "Todo salio bien ");
                            ObtenerPromos();
                        } else {
                            Toast.makeText(getContext(), "No se encontraron PRODUCTOS", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Log.e("Productos", "Error al obtener locales: " + e.getMessage());
                Toast.makeText(getContext(), "Error al cargar locales", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ObtenerPromos(){
        String query = GraphQLOperations.GET_PROMOS;

        Type responseType = new TypeToken<GraphQLResponse<ListResponse>>() {}.getType();

        client.sendRequest(query, null, responseType, new GraphQLCallback<GraphQLResponse<ListResponse>>() {
            @Override
            public void onSuccess(GraphQLResponse<ListResponse> result) {
                // Asegúrate de acceder a la lista de locales de forma segura
                List<Promocion> promos = result.data.obtenerPromociones;

                // Ahora debes actualizar el RecyclerView en el hilo principal
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (promos != null && !promos.isEmpty()) {
                            AppData.getInstance().setPromociones(promos);
                            Log.e("Promo", "Todo salio bien ");

                        } else {
                            Toast.makeText(getContext(), "No se encontraron PROMO", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Log.e("Promo", "Error al obtener locales: " + e.getMessage());
                Toast.makeText(getContext(), "Error al cargar locales", Toast.LENGTH_SHORT).show();
            }
        });
    }


}