package com.example.proyectojakartaee.service;

import com.example.proyectojakartaee.model.Cliente;

import java.util.List;

public interface ClienteService {
    void agregarCliente(int id,String nombre,String  edad,String correo,String telefono,String direccion);

    Cliente buscarCliente(int id);
    void eliminarCliente(int id);
    void modificarCliente(int id,String nombre,String  edad,String correo,String telefono,String direccion);

    List<Cliente> listarClientes();

}
