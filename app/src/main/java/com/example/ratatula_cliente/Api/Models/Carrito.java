package com.example.ratatula_cliente.Api.Models;

import java.util.List;

public class Carrito {
    public String id;
    public String idUsuario;
    public List<carpro> productos;
    public List<carpromo> promociones;
    public float total;
    public String idLocal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<carpro> getProductos() {
        return productos;
    }

    public void setProductos(List<carpro> productos) {
        this.productos = productos;
    }

    public List<carpromo> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<carpromo> promociones) {
        this.promociones = promociones;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }
}
