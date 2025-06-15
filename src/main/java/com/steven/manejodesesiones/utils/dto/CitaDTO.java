package com.steven.manejodesesiones.utils.dto;

import java.time.LocalDate;

public class CitaDTO {
    private Long id;
    private Long idMascota;
    private LocalDate fecha;
    private String hora;
    private String motivo;
    private String indicaciones;
    private String veterinario;
    private String estado;

    public CitaDTO() {
    }

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
