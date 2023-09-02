package com.example.proyectojakartaee.model;


public class Cliente {
    private int id;
    private String nombre;
    private String edad;
    private String correo;
    private String telefono;
    private String direccion;

    public Cliente(int id, String nombre, String  edad, String correo,String telefono, String direccion){
        this.id=id;
        this.nombre=nombre;
        this.edad=edad;
        this.correo=correo;
        this.telefono=telefono;
        this.direccion=direccion;
    }

    public int getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setId(int id){
        this.id=id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getEdad(){
        return edad;

    }
    public void setEdad(String edad){
        this.edad=edad;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    public String getDireccion(){
        return correo;
    }
    public void setDireccion(String correo){
        this.correo=correo;
    }
}
