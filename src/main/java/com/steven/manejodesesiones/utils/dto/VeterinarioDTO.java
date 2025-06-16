// Paquete que contiene la clase DTO para representar un veterinario
package com.steven.manejodesesiones.utils.dto;

// Clase que representa los datos básicos de un veterinario
public class VeterinarioDTO {
    // Identificador único del veterinario
    private Long id;
    // Nombre completo del veterinario
    private String nombre;

    // Constructor vacío necesario para frameworks y serialización
    public VeterinarioDTO() {}

    // Constructor que inicializa todos los atributos del veterinario
    public VeterinarioDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Método getter para obtener el id del veterinario
    public Long getId() { return id; }
    // Método setter para asignar el id del veterinario
    public void setId(Long id) { this.id = id; }

    // Método getter para obtener el nombre del veterinario
    public String getNombre() { return nombre; }
    // Método setter para asignar el nombre del veterinario
    public void setNombre(String nombre) { this.nombre = nombre; }
}
