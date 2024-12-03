package com.example.ratatula_cliente.Api.Models;

import java.util.List;

public class Promocion {
    public String id;
    public String nombre;
    public String descripcion;
    public String precioReal;
    public String precioPromo;
    public List<PromoProduct> productos;
    public String imagen;
    public String idLocal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecioReal() {
        return precioReal;
    }

    public void setPrecioReal(String precioReal) {
        this.precioReal = precioReal;
    }

    public String getPrecioPromo() {
        return precioPromo;
    }

    public void setPrecioPromo(String precioPromo) {
        this.precioPromo = precioPromo;
    }

    public List<PromoProduct> getProductos() {
        return productos;
    }

    public void setProductos(List<PromoProduct> productos) {
        this.productos = productos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }
}
