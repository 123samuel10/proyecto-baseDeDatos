package com.example.proyectojakartaee.controllers;

import java.io.*;

import com.example.proyectojakartaee.service.serviceImpl.ClienteServiceImpl;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class ClienteController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    ClienteServiceImpl clienteService = new ClienteServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        try {
            // Agregar el cliente utilizando el service
            clienteService.agregarCliente(id, nombre, edad, correo, telefono, direccion);

            // Redirigir de nuevo a la página principal o a donde desees
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente, por ejemplo, redirigir a una página de error
           // response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }


    public void destroy() {
    }
}