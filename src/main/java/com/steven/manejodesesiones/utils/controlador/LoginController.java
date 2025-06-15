package com.steven.manejodesesiones.utils.controlador;

import com.steven.manejodesesiones.utils.dto.CredencialesDTO;
import com.steven.manejodesesiones.utils.Usuario;
import com.steven.manejodesesiones.utils.servicio.AuthService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private AuthService authService = new AuthService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(CredencialesDTO credenciales) {
        Usuario usuario = authService.login(credenciales.getEmail(), credenciales.getPassword());

        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Usuario no autorizado o credenciales inválidas")
                    .build();
        }
    }
}
