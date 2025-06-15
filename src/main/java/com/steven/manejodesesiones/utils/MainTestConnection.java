package com.steven.manejodesesiones.utils;

import java.sql.Connection;

public class MainTestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Conexión exitosa");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
