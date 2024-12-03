package com.example.ratatula_cliente.principal.PedidosCoso;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PedidoActualFragment();  // Cambia estos fragmentos por los correctos
            case 1:
                return new HistorialPedidosFragment();
            default:
                return new PedidoActualFragment();  // Devuelve un fragmento por defecto
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // Número de pestañas
    }
}