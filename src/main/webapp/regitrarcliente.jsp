<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <title>Registrar Usuario</title>
  <link rel="stylesheet" href="css/registarCliente.css" />
  <script>
    function soloLetras(e) {
      const key = e.key;
      const letras = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]$/;
      if (!letras.test(key)) {
        e.preventDefault();
      }
    }

    function validarFormulario() {
      const nombre = document.getElementById("nombre").value.trim();
      const apellido = document.getElementById("apellido").value.trim();
      const soloLetrasRegex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;

      document.getElementById("error-nombre").textContent = "";
      document.getElementById("error-apellido").textContent = "";
      document.getElementById("mensaje-password").textContent = "";
      document.getElementById("error-confirm-password").textContent = "";

      let valido = true;

      if (!soloLetrasRegex.test(nombre)) {
        document.getElementById("error-nombre").textContent = "El nombre solo debe contener letras y espacios.";
        valido = false;
      }
      if (!soloLetrasRegex.test(apellido)) {
        document.getElementById("error-apellido").textContent = "El apellido solo debe contener letras y espacios.";
        valido = false;
      }

      const password = document.getElementById("password").value;
      const confirmPassword = document.getElementById("confirm_password").value;

      if (password.length < 8) {
        document.getElementById("mensaje-password").textContent = "La contraseña debe tener al menos 8 caracteres.";
        valido = false;
      }

      if (password !== confirmPassword) {
        document.getElementById("error-confirm-password").textContent = "Las contraseñas no coinciden.";
        valido = false;
      }

      return valido;
    }

    function validarLongitudPassword() {
      const passwordInput = document.getElementById("password");
      const mensajePassword = document.getElementById("mensaje-password");
      if (passwordInput.value.length < 8) {
        mensajePassword.textContent = "La contraseña debe tener al menos 8 caracteres.";
      } else {
        mensajePassword.textContent = "";
      }
    }
  </script>
</head>
<body>
<div class="form-container">
  <h2>Registrar Usuario</h2>

  <%-- Mostrar mensajes de error --%>
  <%
    String error = (String) request.getAttribute("error");
    if ("nombre_invalido".equals(error)) {
  %>
  <p style="color:red;">Nombre o apellido inválido. Solo letras y espacios.</p>
  <% } else if ("password_corta".equals(error)) { %>
  <p style="color:red;">La contraseña es demasiado corta. Debe tener al menos 8 caracteres.</p>
  <% } else if ("password_no_coincide".equals(error)) { %>
  <p style="color:red;">Las contraseñas no coinciden.</p>
  <% } else if ("email_duplicado".equals(error)) { %>
  <p style="color:red;">El correo electrónico ya está registrado.</p>
  <% } else if ("error_generico".equals(error)) { %>
  <p style="color:red;">Error al registrar usuario. Intente nuevamente.</p>
  <% } %>

  <form method="post" action="register" onsubmit="return validarFormulario();">
    Nombre:
    <input type="text" id="nombre" name="nombre" onkeypress="soloLetras(event)" required />
    <div id="error-nombre" style="color: red; font-size: 0.9em; margin-bottom: 10px;"></div>

    Apellido:
    <input type="text" id="apellido" name="apellido" onkeypress="soloLetras(event)" required />
    <div id="error-apellido" style="color: red; font-size: 0.9em; margin-bottom: 10px;"></div>
    <br />

    Email:
    <input type="email" name="email" required /><br />

    Password:
    <input type="password" id="password" name="password" oninput="validarLongitudPassword()" required />
    <div id="mensaje-password" style="color: red; font-size: 0.9em; margin-bottom: 5px;"></div>
    <div style="font-size: 0.85em; color: #555; margin-bottom: 15px;">
      La contraseña debe tener al menos 8 caracteres, incluir mayúsculas, minúsculas, números y caracteres especiales.
    </div>

    Confirmar Password:
    <input type="password" id="confirm_password" name="confirm_password" required />
    <div id="error-confirm-password" style="color: red; font-size: 0.9em; margin-bottom: 15px;"></div>

    <!-- Eliminado select de rol -->

    <input type="submit" value="Registrar" />

    <div style="margin-top: 15px;">
      <a href="index.jsp" style="
        display: inline-block;
        padding: 8px 20px;
        background-color: #6c7277;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        margin-top: 10px;
      ">Volver al Inicio</a>
    </div>
  </form>
</div>
</body>
</html>



