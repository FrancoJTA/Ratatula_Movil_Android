package com.example.ratatula_cliente.Session;

import com.example.ratatula_cliente.Api.Models.Producto;
import com.example.ratatula_cliente.Api.Models.Promocion;
import com.example.ratatula_cliente.Api.Models.Usuario;

import java.util.List;

public class AppData {

    // Instancia única de la clase (Singleton)
    private static AppData instance;

    private List<Usuario> locales;
    private List<Producto> productos;
    private List<Promocion> promociones;
    private Usuario usuario;
    private String token;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    // Constructor privado para evitar instanciación externa
    private AppData() {}

    // Obtener la instancia del Singleton
    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    // Guardar y obtener la lista de locales
    public void setLocales(List<Usuario> locales) {
        this.locales = locales;
    }

    public List<Usuario> getLocales() {
        return locales;
    }

    // Guardar y obtener los datos del usuario
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Guardar y obtener el token
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}