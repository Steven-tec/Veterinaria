// Paquete donde se encuentra el DTO (Data Transfer Object) de las citas médicas
package com.steven.manejodesesiones.utils.dto;

import java.sql.Date;
import java.time.LocalDate;

// Clase que representa una cita médica como objeto de transferencia de datos (DTO)
public class CitaDTO {
    // Identificador único de la cita
    private Long id;
    // Identificador de la mascota asociada a la cita
    private Long idMascota;
    // Fecha de la cita (usando LocalDate para manejo moderno de fechas)
    private LocalDate fecha;
    // Hora de la cita (en formato String, por ejemplo "14:30")
    private String hora;
    // Motivo o razón por la que se agenda la cita
    private String motivo;
    // Indicaciones o recomendaciones médicas después de la cita
    private String indicaciones;
    // Nombre del veterinario que atenderá o atendió la cita
    private String veterinario;
    // Estado de la cita (por ejemplo: "ACTIVA", "CANCELADA", "REALIZADA")
    private String estado;

    // Constructor vacío (necesario para frameworks y serialización)
    public CitaDTO() {
    }

    // Constructor con todos los atributos para instanciar un objeto de forma completa
    public CitaDTO(Long id, Long idMascota, LocalDate fecha, String hora, String motivo, String indicaciones, String veterinario, String estado) {
        this.id = id;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.indicaciones = indicaciones;
        this.veterinario = veterinario;
        this.estado = estado;
    }

    // Métodos getter y setter para acceder y modificar cada atributo del DTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}

