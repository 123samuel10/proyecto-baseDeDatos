package com.example.proyectojakartaee.service;

public interface ClienteService {
    void agregarCliente(int id,String nombre,String  edad,String correo,String telefono,String direccion);
    void buscarCliente(int id);
    void eliminarCliente(int id);
    void modificarCliente(int id,String nombre,String  edad,String correo,String telefono,String direccion);
    void listar();

}
