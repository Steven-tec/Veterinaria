<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String msg = request.getParameter("msg");
    if (msg != null) {
%>
<p style="color:green; text-align:center; font-weight:bold;"><%= msg %></p>
<% } %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Mascota</title>
    <link rel="stylesheet" href="css/RegistarMascota.css">

    <script>
        // Función para evitar ingreso de números en los campos especificados
        function evitarNumeros(e) {
            const char = String.fromCharCode(e.which);
            // Permitir solo letras, espacios y teclas especiales (backspace, delete, flechas)
            const regex = /[a-zA-Z\s]/;
            if (!regex.test(char) && !esTeclaEspecial(e)) {
                e.preventDefault();
            }
        }

        // Permitir teclas especiales como backspace, flechas, delete
        function esTeclaEspecial(e) {
            const teclasEspeciales = [8, 9, 13, 37, 38, 39, 40, 46]; // backspace, tab, enter, flechas, delete
            return teclasEspeciales.includes(e.keyCode);
        }

        // Validar edad mínimo 1 antes de enviar el formulario
        function validarEdad(event) {
            const edadInput = document.getElementsByName('edad')[0];
            const edad = parseInt(edadInput.value, 10);
            if (!isNaN(edad) && edad < 1) {
                alert('La edad debe ser un número entero mayor o igual a 1.');
                event.preventDefault();
                edadInput.focus();
            }
        }

        window.onload = function() {
            // Asignar evento keypress a los inputs que no deben aceptar números
            const campos = ['nombre', 'nombreMascota', 'especie', 'raza'];
            campos.forEach(function(id) {
                const input = document.getElementsByName(id)[0];
                if (input) {
                    input.addEventListener('keypress', evitarNumeros);
                }
            });

            // Asignar validación al formulario
            const form = document.querySelector('form');
            form.addEventListener('submit', validarEdad);
        }
    </script>
</head>
<body>
<div class="form-container">
    <h2>Registrar Mascota</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error-message" style="color:red; font-weight:bold;"><%= error %></p>
    <%
        }
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
    <p style="color:green; font-weight:bold;"><%= mensaje %></p>
    <%
        }
    %>

    <form method="post" action="registrarMascota">
        Nombre Usuario: <input type="text" name="nombre" required><br>
        Nombre Mascota: <input type="text" name="nombreMascota" required><br>
        Especie: <input type="text" name="especie"><br>
        Raza: <input type="text" name="raza"><br>
        Edad: <input type="number" name="edad" min="1"><br>
        <input type="submit" value="Registrar">
    </form>

    <br>

    <button type="button" onclick="window.location.href='index.jsp'"
            style="
            background-color: #555555;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        ">
        Volver al inicio
    </button>

</div>
</body>
</html>
