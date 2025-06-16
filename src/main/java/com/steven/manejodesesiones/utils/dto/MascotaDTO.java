// Paquete donde se encuentra la clase DTO para representar una mascota
package com.steven.manejodesesiones.utils.dto;

// Clase que representa los datos de una mascota como objeto de transferencia de datos (DTO)
public class MascotaDTO {

    // Identificador único de la mascota
    private Long id;

    // Identificador del usuario (dueño) al que pertenece la mascota
    private Long idUsuario;

    // Nombre de la mascota
    private String nombre;

    // Especie de la mascota (por ejemplo, "Perro", "Gato")
    private String especie;

    // Raza de la mascota (por ejemplo, "Labrador", "Siames")
    private String raza;

    // Edad de la mascota en años
    private int edad;

    // Constructor vacío requerido por frameworks y para instanciación sin parámetros
    public MascotaDTO() {
    }

    // Constructor completo para crear una instancia con todos los campos definidos
    public MascotaDTO(Long id, Long idUsuario, String nombre, String especie, String raza, int edad) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
    }

    // Métodos getter y setter para acceder y modificar cada campo del DTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

