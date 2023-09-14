package com.example.proyectojakartaee;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static String url = "jdbc:mysql://localhost:3306/tienda";
    private static String user = "root";
    private static String password = "";
    private static Connection connection;


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }


}
