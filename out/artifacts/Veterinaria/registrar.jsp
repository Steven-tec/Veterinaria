<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Mascota</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
<h2>Registro de Mascota</h2>
<form action="registro" method="post">
    Nombre: <input type="text" name="nombre"><br>
    Especie: <input type="text" name="especie"><br>
    Edad: <input type="number" name="edad"><br>
    <input type="submit" value="Registrar">
</form>
<a href="index.jsp">Volver</a>
</body>
</html>
