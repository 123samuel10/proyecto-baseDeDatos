package com.example.proyectojakartaee;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static String url = "jdbc:mysql://localhost:3306/tienda";
    private static String user = "root";
    private static String password = "";
    private static Connection connection;


    public static Connection getInstance() throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection(url,user,password);
            System.out.printf("conectadaaaaaaaa");
        }
        return connection;
    }
}
