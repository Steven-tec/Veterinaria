package com.steven.manejodesesiones.utils.dto;

public class MascotaDTO {
    private Long id;
    private Long idUsuario;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;

    public MascotaDTO() {
    }

    public MascotaDTO(Long id, Long idUsuario, String nombre, String especie, String raza, int edad) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
    }

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
