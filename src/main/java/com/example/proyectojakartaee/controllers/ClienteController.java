package com.example.proyectojakartaee.controllers;
import java.io.*;
import java.util.List;
import com.example.proyectojakartaee.model.Cliente;
import com.example.proyectojakartaee.service.serviceImpl.ClienteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class ClienteController extends HttpServlet {
    ClienteServiceImpl clienteService = new ClienteServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        // Verificar si el parámetro "id" está presente y no es una cadena vacía
        String idParameter = request.getParameter("id");
        if (idParameter != null && !idParameter.isEmpty()) {
            int id = Integer.parseInt(idParameter);
            String nombre = request.getParameter("nombre");
            String edad = request.getParameter("edad");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");

            try {
                clienteService.agregarCliente(id, nombre, edad, correo, telefono, direccion);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (Exception e) {

            }
        }
        eliminar(request, response);
        modificar(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("buscar".equals(action)){
            String idBuscarParameter = request.getParameter("idBuscar");
            Cliente clienteEncontrado = null;
            if (idBuscarParameter != null && !idBuscarParameter.isEmpty()) {
                int idBuscar = Integer.parseInt(idBuscarParameter);
                clienteEncontrado = clienteService.buscarCliente(idBuscar);
            }
            // Construye una respuesta en formato HTML y envíala
            String htmlResponse = estructuraBuscar(clienteEncontrado);
            response.setContentType("text/html");
            response.getWriter().write(htmlResponse);
        }
        listar(request,response);

    }
        private String estructuraBuscar(Cliente clienteEncontrado) {
            if (clienteEncontrado != null) {
                // Construye la respuesta HTML si se encuentra un cliente
                return "<p>ID: " + clienteEncontrado.getId() + "</p>" +
                        "<p>Nombre: " + clienteEncontrado.getNombre() + "</p>" +
                        "<p>Edad: " + clienteEncontrado.getEdad() + "</p>" +
                        "<p>Correo: " + clienteEncontrado.getCorreo() + "</p>" +
                        "<p>Teléfono: " + clienteEncontrado.getTelefono() + "</p>" +
                        "<p>Dirección: " + clienteEncontrado.getDireccion() + "</p>";
            } else {
                // Construye la respuesta HTML si no se encuentra el cliente
                return "<p>Cliente no encontrado.</p>";
            }
    }

    public void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idEliminarParameter = request.getParameter("idEliminar");
        if (idEliminarParameter != null && !idEliminarParameter.isEmpty()) {
            int idEliminar = Integer.parseInt(idEliminarParameter);
            try {
                clienteService.eliminarCliente(idEliminar);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void modificar(HttpServletRequest request, HttpServletResponse response) {
        // Obtener los parámetros del formulario
        String idModificarParameter = request.getParameter("idModificar2");
        if (idModificarParameter != null && !idModificarParameter.isEmpty()) {
            int idModificar = Integer.parseInt(idModificarParameter);
            String nuevoNombre = request.getParameter("nombre2");
            String nuevaEdad = request.getParameter("edad2");
            String nuevoCorreo = request.getParameter("correo2");
            String nuevoTelefono = request.getParameter("telefono2");
            String nuevaDireccion = request.getParameter("direccion2");
            try {
                // Modificar el cliente utilizando el service
                clienteService.modificarCliente(idModificar, nuevoNombre, nuevaEdad, nuevoCorreo, nuevoTelefono, nuevaDireccion);
                // Redirigir a la página principal o a donde desees después de la modificación
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar la excepción adecuadamente, por ejemplo, redirigir a una página de error
                // response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        }
    }
    public void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            List<Cliente> clientes = clienteService.listarClientes();
            // Construye una respuesta en formato HTML y envíala
            String htmlResponse = estructuraListar(clientes);
            response.setContentType("text/html");
            response.getWriter().write(htmlResponse);
        }
    }
    private String estructuraListar(List<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            // Construye la respuesta HTML si hay clientes para listar
            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<tr>")
                    .append("<th>ID</th>")
                    .append("<th>Nombre</th>")
                    .append("<th>Edad</th>")
                    .append("<th>Correo</th>")
                    .append("<th>Teléfono</th>")
                    .append("<th>Dirección</th>")
                    .append("</tr>");

            for (Cliente cliente : clientes) {
                htmlBuilder.append("<tr>")
                        .append("<td>").append(cliente.getId()).append("</td>")
                        .append("<td>").append(cliente.getNombre()).append("</td>")
                        .append("<td>").append(cliente.getEdad()).append("</td>")
                        .append("<td>").append(cliente.getCorreo()).append("</td>")
                        .append("<td>").append(cliente.getTelefono()).append("</td>")
                        .append("<td>").append(cliente.getDireccion()).append("</td>")
                        .append("</tr>");
            }
            return htmlBuilder.toString();
        } else {
            // Construye la respuesta HTML si no hay clientes para listar
            return "<tr><td colspan='6'>No hay clientes para listar.</td></tr>";
        }
    }
}