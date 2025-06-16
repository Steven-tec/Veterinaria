package com.steven.manejodesesiones.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHash {

    // Método estático para hashear (encriptar) una contraseña en texto plano
    public static String hashPassword(String plainPassword) {
        // Genera un hash seguro usando BCrypt con un salt aleatorio
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Método estático para verificar si una contraseña en texto plano coincide con un hash
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        // Compara la contraseña en texto plano con el hash almacenado y retorna true si coinciden
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
