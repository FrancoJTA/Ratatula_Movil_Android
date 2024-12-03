package com.example.ratatula_cliente.principal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.ratatula_cliente.adapters.ProductoAdapter;
import com.example.ratatula_cliente.adapters.PromoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LocalDetailActivity extends AppCompatActivity {
    private String local_id;
    private Usuario local;
    private List<Producto> productos;
    private List<Promocion> promocions;
    private OkHttpGraphQLClient client;
    private CarritoFragment fragment ;
    private TextView nombre, descripcion;

    private FloatingActionButton fab;

    private RecyclerView productos_rec, promo_rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detail);
        client = new OkHttpGraphQLClient();
        Intent intent =getIntent();
        local_id=intent.getStringExtra("LOCAL_ID");
        local = AppData.getInstance().getLocales().stream()
                .filter(usuario -> usuario.getId().equals(local_id))
                .findFirst()
                .orElse(null);

        init();
        ObtenerProductos();
        ObtenerPromos();
    }
    public void init(){
        fragment = new CarritoFragment();

        productos_rec=findViewById(R.id.product_recycler);
        promo_rec = findViewById(R.id.promos_recyler);
        productos_rec.setLayoutManager(new LinearLayoutManager(this));
        promo_rec.setLayoutManager(new LinearLayoutManager(this));
        nombre=findViewById(R.id.nombre);
        descripcion=findViewById(R.id.descripcion);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> showLocalDetailFragment());

        nombre.setText(local.getNombre());
        descripcion.setText(local.getDescripcion());
    }
    private void showLocalDetailFragment() {
        // Crear una nueva instancia del fragmento

        if (fragment != null && fragment.isVisible()) {

            getSupportFragmentManager().beginTransaction()
                    .remove(fragment)
                    .commit();
        } else {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }


    private void ObtenerProductos(){

        String query = GraphQLOperations.GET_PRODUCTOS_LOCAL;

        Type responseType = new TypeToken<GraphQLResponse<ListResponse>>() {}.getType();
        String variables = "{ \"idLocal\": \"" + local_id + "\" }";

        client.sendRequest(query, variables, responseType, new GraphQLCallback<GraphQLResponse<ListResponse>>() {
            @Override
            public void onSuccess(GraphQLResponse<ListResponse> result) {
                if (result != null && result.data != null && result.data.obtenerProductosLocal != null) {
                    productos = result.data.obtenerProductosLocal;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (productos != null) {
                                ProductoAdapter productoAdapter = new ProductoAdapter(productos,LocalDetailActivity.this);
                                productos_rec.setLayoutManager(new LinearLayoutManager(LocalDetailActivity.this));
                                productos_rec.setAdapter(productoAdapter);
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("LocalDetailActivity", "Error al obtener productos: " + e.getMessage());
                Toast.makeText(LocalDetailActivity.this, "Error al cargar productos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ObtenerPromos(){
        String query = GraphQLOperations.GET_PROMOS_LOCAL;

        Type responseType = new TypeToken<GraphQLResponse<ListResponse>>() {}.getType();
        String variables = "{ \"idLocal\": \"" + local_id + "\" }";

        client.sendRequest(query, variables, responseType, new GraphQLCallback<GraphQLResponse<ListResponse>>() {
            @Override
            public void onSuccess(GraphQLResponse<ListResponse> result) {
                if (result != null && result.data != null && result.data.obtenerPromocionesLocal != null) {
                    promocions = result.data.obtenerPromocionesLocal;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (promocions != null) {
                                PromoAdapter promoAdapter = new PromoAdapter(promocions,LocalDetailActivity.this);
                                promo_rec.setLayoutManager(new LinearLayoutManager(LocalDetailActivity.this));
                                promo_rec.setAdapter(promoAdapter);
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("LocalDetailActivity", "Error al obtener promociones: " + e.getMessage());
                Toast.makeText(LocalDetailActivity.this, "Error al cargar promociones", Toast.LENGTH_SHORT).show();
            }
        });
    }
}