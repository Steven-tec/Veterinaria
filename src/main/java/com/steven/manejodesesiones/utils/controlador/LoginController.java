// Paquete donde se encuentra el controlador REST para el login
package com.steven.manejodesesiones.utils.controlador;

// Importa el DTO que contiene las credenciales (email y contraseña)
import com.steven.manejodesesiones.utils.dto.CredencialesDTO;
// Importa la clase Usuario (modelo)
import com.steven.manejodesesiones.utils.Usuario;
// Importa el servicio que contiene la lógica de autenticación
import com.steven.manejodesesiones.utils.servicio.AuthService;

// Importaciones de Jakarta RESTful Web Services (JAX-RS)
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Define el path base para este recurso REST, accesible desde /api/login
@Path("/login")
public class LoginController {

    // Instancia del servicio de autenticación
    private AuthService authService = new AuthService();

    // Maneja peticiones POST que consumen y producen JSON
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // El método acepta datos JSON como entrada
    @Produces(MediaType.APPLICATION_JSON) // El método devuelve datos JSON como salida
    public Response login(CredencialesDTO credenciales) {
        // Llama al servicio de autenticación con los datos proporcionados
        Usuario usuario = authService.login(credenciales.getEmail(), credenciales.getPassword());

        // Si las credenciales son válidas y el usuario existe
        if (usuario != null) {
            // Retorna una respuesta HTTP 200 (OK) con el usuario como cuerpo en formato JSON
            return Response.ok(usuario).build();
        } else {
            // Si las credenciales no son válidas, retorna HTTP 401 (Unauthorized)
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Usuario no autorizado o credenciales inválidas") // Mensaje de error
                    .build();
        }
    }
}
