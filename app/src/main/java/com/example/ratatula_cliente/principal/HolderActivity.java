package com.example.ratatula_cliente.principal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.ratatula_cliente.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HolderActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        setupNavegacion();
    }

    private void setupNavegacion() {
        bottomNavigationView = findViewById(R.id.bottomNav);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.theframe);
        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.getNavController()
        );
    }
}