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

import com.example.ratatula_cliente.Api.GraphQL.GraphQLService;
import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AccesActivity extends AppCompatActivity {

    private Button login_btn,regist_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acces);
        init();
    }
    public void init(){
        login_btn=findViewById(R.id.login_button);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        regist_btn=findViewById(R.id.registra_button);

        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar(view);
            }
        });
    }



    public void login(View view){
        startActivity(new Intent(AccesActivity.this,LoginActivity.class));
    }

    public void registrar(View view){
        startActivity(new Intent(AccesActivity.this,RegisterActivity.class));

    }

    public void executeQuery() {
    }
}