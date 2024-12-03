package com.example.ratatula_cliente.principal.PedidosCoso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatula_cliente.Api.GraphQL.OkHttpGraphQLClient;
import com.example.ratatula_cliente.Api.Models.CarritoProducto;
import com.example.ratatula_cliente.Api.Models.Pedido;
import com.example.ratatula_cliente.R;
import com.example.ratatula_cliente.adapters.CarritoAdapter;
import com.example.ratatula_cliente.adapters.PedidosAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PedidoActualFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidoActualFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PedidoActualFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedidoActualFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PedidoActualFragment newInstance(String param1, String param2) {
        PedidoActualFragment fragment = new PedidoActualFragment();
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
    private List<Pedido> pedidos;
    private RecyclerView recyclerView;
    private PedidosAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_pedido_actual, container, false);
        return view;
    }
}