<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Registro de Administrador</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background: #e6f5ec;
    }

    .container {
      margin-top: 50px;
      max-width: 600px;
      background-color: #ffffff;
      padding: 30px;
      border-radius: 15px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    .btn-success {
      background-color: #28a745;
      border: none;
    }

    .btn-volver {
      background-color: #6c757d;
      color: #fff;
      margin-top: 10px;
    }

    .form-group label {
      font-weight: bold;
    }

    .error-message {
      color: red;
      font-size: 0.9rem;
    }

    .valid-message {
      color: green;
      font-size: 0.9rem;
    }
  </style>
  <script>
    function bloquearNumeros(e) {
      const char = String.fromCharCode(e.which);
      const regex = /^[a-zA-Z\s]+$/;
      if (!regex.test(char)) {
        e.preventDefault();
      }
    }

    function validarFormulario() {
      const password = document.getElementById("password").value;
      const confirmar = document.getElementById("confirmar").value;
      const mensajeError = document.getElementById("mensajeError");

      if (password !== confirmar) {
        mensajeError.innerText = "Las contraseñas no coinciden.";
        return false;
      }

      const requisitos = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      if (!requisitos.test(password)) {
        mensajeError.innerText = "La contraseña debe tener al menos 8 caracteres, una letra y un número.";
        return false;
      }

      mensajeError.innerText = "";
      return true;
    }

    function validarRequisitos() {
      const password = document.getElementById("password").value;
      const requisitos = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      const requisitosMsg = document.getElementById("requisitosMsg");

      if (!requisitos.test(password)) {
        requisitosMsg.innerText = "Mínimo 8 caracteres, una letra y un número.";
        requisitosMsg.className = "error-message";
      } else {
        requisitosMsg.innerText = "Contraseña válida.";
        requisitosMsg.className = "valid-message";
      }
    }

    window.onload = function () {
      document.getElementById("nombre").addEventListener("keypress", bloquearNumeros);
      document.getElementById("apellido").addEventListener("keypress", bloquearNumeros);
    }
  </script>
</head>
<body>
<div class="container">
  <h3 class="text-center text-success">Registro de Administrador</h3>
  <form action="RegistrarAdministradorServlet" method="post" onsubmit="return validarFormulario();">
    <div class="form-group">
      <label for="nombre">Nombre:</label>
      <input type="text" class="form-control" name="nombre" id="nombre" required>
    </div>
    <div class="form-group">
      <label for="apellido">Apellido:</label>
      <input type="text" class="form-control" name="apellido" id="apellido" required>
    </div>
    <div class="form-group">
      <label for="correo">Correo Electrónico:</label>
      <input type="email" class="form-control" name="correo" id="correo" required>
    </div>
    <div class="form-group">
      <label for="password">Contraseña:</label>
      <input type="password" class="form-control" name="password" id="password" required onkeyup="validarRequisitos();">
      <small id="requisitosMsg" class="form-text text-muted"></small>
    </div>
    <div class="form-group">
      <label for="confirmar">Confirmar Contraseña:</label>
      <input type="password" class="form-control" id="confirmar" required>
    </div>

    <p id="mensajeError" class="error-message"></p>

    <div class="text-center">
      <button type="submit" class="btn btn-success">Registrar Administrador</button>
    </div>
  </form>

  <!-- Botón para volver al CRUD -->
  <div class="text-center">
    <a href="crudUsuarios.jsp" class="btn btn-volver">Volver al CRUD</a>
  </div>
</div>
</body>
</html>


