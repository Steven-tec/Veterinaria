// Paquete donde se encuentra esta clase de configuración
package com.steven.manejodesesiones.utils.config;

// Importa la anotación que define el path base para todos los recursos REST
import jakarta.ws.rs.ApplicationPath;
// Importa la clase base para aplicaciones JAX-RS
import jakarta.ws.rs.core.Application;

/**
 * Clase de configuración para la aplicación JAX-RS (RESTful Web Services).
 * Esta clase extiende jakarta.ws.rs.core.Application y permite establecer
 * la ruta base para todos los servicios REST del sistema.
 */
@ApplicationPath("/api") // Define el path base de la API REST. Todas las rutas REST comenzarán con /api
public class ApplicationConfig extends Application {
    // No es necesario implementar ningún método aquí a menos que se necesite configuración adicional,
    // como el registro manual de clases o recursos.
}
