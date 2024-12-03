package com.example.ratatula_cliente.Api.Models;

import java.util.List;

public class Usuario {
    public String id;
    public String nombre;
    public String apellido;
    public String correo;
    public String rol;
    public String estado;
    public String telefono;
    public String fechaRegistro;
    public String descripcion;
    public List<String> categoria;
    public Horario horarios;
    public String idCarrito;

    // Clase interna para los horarios
    public static class Horario {
        public String apertura;
        public String cierre;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<String> categoria) {
        this.categoria = categoria;
    }

    public Horario getHorarios() {
        return horarios;
    }

    public void setHorarios(Horario horarios) {
        this.horarios = horarios;
    }
}
