package com.steven.manejodesesiones.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // URL de conexión a la base de datos MariaDB, con host, puerto y nombre de la base de datos
    private static final String URL = "jdbc:mariadb://localhost:3306/veterinaria_db";
    // Usuario para acceder a la base de datos
    private static final String USER = "root";
    // Contraseña para acceder a la base de datos (vacía en este caso)
    private static final String PASSWORD = "";

    // Bloque estático que se ejecuta al cargar la clase
    static {
        try {
            // Carga el driver JDBC para MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Si no se encuentra el driver, imprime la traza del error
            e.printStackTrace();
        }
    }

    // Método estático para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        // Retorna una nueva conexión usando la URL, usuario y contraseña definidas
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
