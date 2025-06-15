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

</head>
<body>
<div class="form-container">
    <h2>Registrar Mascota</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error-message"><%= error %></p>
    <%
        }
    %>

    <form method="post" action="registrarMascota">
        Nombre Usuario: <input type="text" name="nombre" required><br>
        Nombre Mascota: <input type="text" name="nombreMascota" required><br>
        Especie: <input type="text" name="especie"><br>
        Raza: <input type="text" name="raza"><br>
        Edad: <input type="number" name="edad" min="0"><br>
        <input type="submit" value="Registrar">
    </form>
</div>
</body>
</html>

