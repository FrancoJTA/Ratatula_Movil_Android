package com.example.ratatula_cliente.principal;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLCallback;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLOperations;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLResponse;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.Pedido;
import com.example.ratatula_cliente.Api.Models.ResponseOBJ;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.Session.AppData;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PedidoInfoActivity extends AppCompatActivity {
    private String pedido_id;
    private Pedido pedido;
    private OkHttpGraphQLClient client;
    private ImageView qr;

    private Button downloadButton;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_info);
        client = new OkHttpGraphQLClient();
        Intent intent =getIntent();
        pedido_id=intent.getStringExtra("ID_PEDIDO");

        init();
        loadPedido();
    }

    private void loadPedido() {
        String mutation = GraphQLOperations.GET_PEDIDO;

        // Definir las variables de la mutation
        String variables = "{ \"obtenerPedidoPorIdId\": \"" +pedido_id + "\"  }";

        Type responseType = new TypeToken<GraphQLResponse<ResponseOBJ>>() {}.getType();
        // Realizar la solicitud GraphQL
        client.sendRequest(mutation, variables, responseType, new GraphQLCallback<GraphQLResponse<ResponseOBJ>>() {
            @Override
            public void onSuccess(GraphQLResponse<ResponseOBJ> result) {
                pedido =result.data.obtenerPedidoPorID;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(PedidoInfoActivity.this)
                                .load(pedido.urlqr)
                                .into(qr);

                    }
                });
            }

            @Override
            public void onError(Exception e) {
                // En caso de error al hacer la solicitud

                Log.d("fadsfkasjf", "Mierda ");
            }
        });

    }

    public void init(){
        downloadButton = findViewById(R.id.btn_download);
        downloadButton.setOnClickListener(v -> downloadImage());
        qr = findViewById(R.id.QR_img);

    }

    private void downloadImage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pedido.urlqr));

            // Define where to save the downloaded file
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setAllowedOverRoaming(false);
            String fileName = "downloaded_image.jpg"; // Cambia el nombre si es necesario
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

            // Get the system's DownloadManager service and enqueue the request
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
        }else {
            // Informar al usuario que el almacenamiento no está disponible
            Toast.makeText(this, "No se puede acceder al almacenamiento", Toast.LENGTH_SHORT).show();
        }
    }
}