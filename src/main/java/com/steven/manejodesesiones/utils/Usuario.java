package com.steven.manejodesesiones.utils;

import java.time.LocalDateTime;

public class Usuario {
    // Identificador único del usuario
    private Long idUsuario;
    // Nombre del usuario
    private String nombre;
    // Apellido del usuario
    private String apellido;
    // Correo electrónico del usuario
    private String email;
    // Rol asignado al usuario (ejemplo: ADMIN, CLIENTE, etc.)
    private String rol;
    // Indica si el usuario ha sido verificado (por ejemplo, confirmación por email)
    private boolean verificado;
    // Fecha y hora en que se registró el usuario
    private LocalDateTime fechaRegistro;
    // Fecha y hora de la última actualización de datos del usuario
    private LocalDateTime fechaActualizacion;
    // Indica si el usuario está activo (habilitado) o no
    private boolean activo;

    // Getters y setters para cada propiedad

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean isVerificado() { return verificado; }
    public void setVerificado(boolean verificado) { this.verificado = verificado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}


