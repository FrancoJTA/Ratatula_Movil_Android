package com.example.ratatula_cliente.Start;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ratatula_cliente.R;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartActivity.this,AccesActivity.class));
                overridePendingTransition(R.anim.static_animation,R.anim.zoom_out);
                finish();
            }
        },2500);
    }
}