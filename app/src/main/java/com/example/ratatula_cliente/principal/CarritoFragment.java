package com.example.ratatula_cliente.principal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatula_cliente.Api.GraphQL.GraphQLCallback;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLOperations;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLResponse;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.CarritoProducto;
import com.example.ratatula_cliente.Api.Models.ListResponse;
import com.example.ratatula_cliente.Api.Models.Producto;
import com.example.ratatula_cliente.Api.Models.ResponseOBJ;
import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.Api.Models.carpro;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.Session.AppData;
import com.example.ratatula_cliente.Start.StartActivity;
import com.example.ratatula_cliente.adapters.CarritoAdapter;
import com.example.ratatula_cliente.adapters.LocalesAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarritoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public CarritoFragment() {
    }


    public static CarritoFragment newInstance(String param1, String param2) {
        CarritoFragment fragment = new CarritoFragment();
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
    private List<CarritoProducto> carritoProducto;
    private RecyclerView recyclerView;
    private CarritoAdapter adapter;
    private Spinner spinner;
    private Button pedir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);
        recyclerView=view.findViewById(R.id.listaCarritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CarritoAdapter(carritoProducto,getContext());
        recyclerView.setAdapter(adapter);

        client = new OkHttpGraphQLClient();

        pedir = view.findViewById(R.id.pedir);
        pedir.setOnClickListener(v -> RealizarPedido());


        spinner = view.findViewById(R.id.spinner);
        List<String> options = new ArrayList<>();
        options.add("Efectivo");
        options.add("QR");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        obtenerCarrito();

        return view;
    }

    private void RealizarPedido() {
        String spin_item=spinner.getSelectedItem().toString();
        boolean qr=false;
        if(spin_item.equals("QR")){
            qr=true;
        }
        // Definir la mutation de GraphQL
        String mutation = GraphQLOperations.CONFIRM_CARRITO;

        // Definir las variables de la mutation
        String variables = "{ \"idUsuario\": \"" + AppData.getInstance().getUsuario().getId() + "\", \"qr\": " + qr + " }";

        Type responseType = new TypeToken<GraphQLResponse<ResponseOBJ>>() {}.getType();
        // Realizar la solicitud GraphQL
        client.sendRequest(mutation, variables, responseType, new GraphQLCallback<GraphQLResponse<ResponseOBJ>>() {
            @Override
            public void onSuccess(GraphQLResponse<ResponseOBJ> result) {
                String id = result.data.confirmarCarrito.id;
                Intent intent=new Intent(getContext(),PedidoInfoActivity.class);
                intent.putExtra("ID_PEDIDO",id);
                startActivity(intent);
            }

            @Override
            public void onError(Exception e) {
                // En caso de error al hacer la solicitud

                Log.d("fadsfkasjf", "Mierda ");
            }
        });
    }

    private void obtenerCarrito() {
        String query = GraphQLOperations.GET_CARRITO;

        Type responseType = new TypeToken<GraphQLResponse<ResponseOBJ>>() {}.getType();
        Log.d("IDDDDD", AppData.getInstance().getUsuario().getId());
        String variables = "{ \"idUsuario\": \"" +AppData.getInstance().getUsuario().getId()+ "\" }";
        client.sendRequest(query, variables, responseType, new GraphQLCallback<GraphQLResponse<ResponseOBJ>>() {
            @Override
            public void onSuccess(GraphQLResponse<ResponseOBJ> result) {
                // Asegúrate de acceder a la lista de locales de forma segura
                List<carpro> carproapi = result.data.obtenerCarrito.productos;
                List<Producto> todosLosProductos = AppData.getInstance().getProductos();
                carritoProducto=new ArrayList<>();

                for (carpro productoCarrito : carproapi) {
                    for (Producto producto : todosLosProductos) {
                        if (productoCarrito.idProducto.equals(producto.id)) {
                            CarritoProducto productoConCantidad = new CarritoProducto();
                            productoConCantidad.id = producto.id;
                            productoConCantidad.nombre = producto.nombre;
                            productoConCantidad.cantidad = productoCarrito.cantidad;
                            carritoProducto.add(productoConCantidad);
                            break;
                        }
                    }
                }

                // Ahora debes actualizar el RecyclerView en el hilo principal
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Aquí actualizas el RecyclerView
                        if (carritoProducto != null && !carritoProducto.isEmpty()) {
                            // Actualizar el RecyclerView con los nuevos datos
                            CarritoAdapter adapter = new CarritoAdapter(carritoProducto,getContext());
                            RecyclerView recyclerView = getView().findViewById(R.id.listaCarritos);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getContext(), "No se encontraron carrito cosas", Toast.LENGTH_SHORT).show();
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
}