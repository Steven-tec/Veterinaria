// Paquete donde se encuentra la clase DTO para las credenciales de acceso
package com.steven.manejodesesiones.utils.dto;

// Clase que representa las credenciales de inicio de sesión (DTO)
public class CredencialesDTO {
    // Campo para el correo electrónico del usuario
    private String email;
    // Campo para la contraseña del usuario
    private String password;

    // Métodos getter y setter para acceder y modificar el campo 'email'
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Métodos getter y setter para acceder y modificar el campo 'password'
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

