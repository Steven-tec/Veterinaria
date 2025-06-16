package com.steven.manejodesesiones.utils;

import java.util.Random;

public class GenerateCode {
    // Método estático que genera un código numérico de 6 dígitos en formato String
    public static String generateCode() {
        Random random = new Random();
        // Genera un número entero aleatorio entre 100000 y 999999 (6 dígitos)
        int code = 100000 + random.nextInt(900000);
        // Convierte el número a String y lo retorna
        return String.valueOf(code);
    }
}
