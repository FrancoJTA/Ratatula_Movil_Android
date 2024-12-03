package com.example.ratatula_cliente.Start;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ratatula_cliente.Api.GraphQL.GraphQLCallback;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLOperations;
import com.example.ratatula_cliente.Api.GraphQL.GraphQLResponse;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.ListResponse;
import com.example.ratatula_cliente.Api.Models.ResponseOBJ;
import com.example.ratatula_cliente.Api.Models.Usuario;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.Session.AppData;
import com.example.ratatula_cliente.principal.HolderActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public void init(){

        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);

        login=findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    // si esta vacio hacer algo
                    return;
                }
                autenticarUsuario(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void autenticarUsuario(String correo, String password) {
        // Definir la mutation de GraphQL
        String mutation = GraphQLOperations.AUTENTICAR_USUARIO;

        // Definir las variables de la mutation
        String variables = "{ \"input\": { \"correo\": \"" + correo + "\", \"password\": \"" + password + "\" } }";

        OkHttpGraphQLClient client = new OkHttpGraphQLClient();
        Type responseType = new TypeToken<GraphQLResponse<ResponseOBJ>>() {}.getType();
        // Realizar la solicitud GraphQL
        client.sendRequest(mutation, variables, responseType, new GraphQLCallback<GraphQLResponse<ResponseOBJ>>() {
            @Override
            public void onSuccess(GraphQLResponse<ResponseOBJ> result) {
                if (result != null && result.data != null && result.data.autenticarUsuario != null) {
                    // Obtener el token de la respuesta
                    String token = result.data.autenticarUsuario.token;
                    // Guardar el token en el Singleton
                    AppData.getInstance().setToken(token);
                    obtenerDatosUsuario(token);
                } else {
                    // Si la autenticación falla
                    Log.d("fadsfkasjf", "Carajo ");
                }
            }

            @Override
            public void onError(Exception e) {
                // En caso de error al hacer la solicitud

                Log.d("fadsfkasjf", "Mierda ");
            }
        });
    }

    private void obtenerDatosUsuario(String token) {
        // Definir la query de GraphQL
        String query = GraphQLOperations.GET_USUARIO;

        // Definir las variables de la query (si es necesario)
        String variables = "{ \"token\": \"" + token + "\" }";

        OkHttpGraphQLClient client = new OkHttpGraphQLClient();

        Type responseType = new TypeToken<GraphQLResponse<ResponseOBJ>>() {}.getType();
        client.sendRequest(query, variables, responseType, new GraphQLCallback<GraphQLResponse<ResponseOBJ>>() {
            @Override
            public void onSuccess(GraphQLResponse<ResponseOBJ> result) {
                if (result != null && result.data != null && result.data.obtenerUsuario != null)  {
                    Usuario user= result.data.obtenerUsuario;
                    AppData.getInstance().setUsuario(user);
                    Intent intent = new Intent(LoginActivity.this, HolderActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza esta actividad para que no pueda volver atrás con el botón "Atrás"
                } else {
                    Log.e("LoginActivity", "Error: No se obtuvieron los datos del usuario");
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("LoginActivity", "Error al obtener datos del usuario: " + e.getMessage());
            }
        });
    }
}