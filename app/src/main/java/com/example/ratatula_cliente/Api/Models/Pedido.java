package com.example.ratatula_cliente.Api.Models;

import java.util.List;

public class Pedido {
    public String id;
    public String idCliente;
    public String idLocal;
    public List<carpro> productos;
    public List<carpromo> promociones;
    public int tiempoPreparacion;
    public String estado;
    public String estadoVenta;
    public float total;
    public String fechaCreacion;
    public String urlqr;
    public Boolean qr;
    public String transaccionID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
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

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUrlqr() {
        return urlqr;
    }

    public void setUrlqr(String urlqr) {
        this.urlqr = urlqr;
    }

    public Boolean getQr() {
        return qr;
    }

    public void setQr(Boolean qr) {
        this.qr = qr;
    }

    public String getTransaccionID() {
        return transaccionID;
    }

    public void setTransaccionID(String transaccionID) {
        this.transaccionID = transaccionID;
    }
}
