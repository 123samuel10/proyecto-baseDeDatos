package com.example.proyectojakartaee.service.serviceImpl;

import com.example.proyectojakartaee.ConexionDB;
import com.example.proyectojakartaee.model.Cliente;
import com.example.proyectojakartaee.service.ClienteService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteServiceImpl implements ClienteService {
    List<Cliente> clientes=new ArrayList<>();

    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();

    }
    @Override
    public void agregarCliente(int id, String nombre, String edad, String correo, String telefono,String direccion) {

        clientes.add(new Cliente(id,nombre,edad,correo,telefono,direccion));
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("""
            INSERT INTO cliente(id,nombre,edad,correo,telefono,direccion) VALUES (?, ?, ?,?,?,?)""")) {
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3,edad);
            preparedStatement.setString(4,correo);
            preparedStatement.setString(5,telefono);
            preparedStatement.setString(6,direccion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void buscarCliente(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        clientes.stream()
                .filter(cliente -> cliente.getId()==id)
                .forEach(cliente -> mostrarDatosCliente(cliente));
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT FROM cliente WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void mostrarDatosCliente(Cliente cliente) {
        JOptionPane.showMessageDialog(null,"ID: " + cliente.getId() + "NOMBRE: "+cliente.getNombre()+" EDAD: "+cliente.getEdad()+" CORREO: "+cliente.getCorreo()+" TELEFONO: "+cliente.getTelefono()+" DIRECCION: "+cliente.getDireccion());
    }

    @Override
    public void eliminarCliente(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        clientes.removeIf(customer -> customer.getId()==id);

        // Eliminar el cliente desde la base de datos
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM cliente WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modificarCliente(int id, String nombre, String edad, String correo,String telefono, String direccion) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getId()==id) {
                cliente.setNombre(nombre);
                cliente.setCorreo(correo);
                cliente.setEdad(edad);
                cliente.setTelefono(telefono);
                cliente.setDireccion(direccion);
            }
        }
        // Modificar el cliente en la base de datos
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE cliente SET nombre = ?, edad = ?, correo = ?, telefono = ?, direccion = ? WHERE id = ?")) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, edad);
            preparedStatement.setString(3, correo);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, direccion);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listar() {
        clientes.stream().forEach(cliente -> JOptionPane.showMessageDialog(null,"ID: " + cliente.getId() + "NOMBRE: "+cliente.getNombre()+" EDAD: "+cliente.getEdad()+" CORREO: "+cliente.getCorreo()+" TELEFONO: "+cliente.getTelefono() +" DIRECCION: "+cliente.getDireccion()));
    }


}
