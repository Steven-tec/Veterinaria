<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Verificación</title>
    <link rel="stylesheet" href="css/verify.css">
</head>
<body>
<div class="container">
    <h2>Verificación de Cuenta</h2>

    <%
        String error = request.getParameter("error");
        if ("1".equals(error)) {
    %>
    <p class="error">El código de verificación es incorrecto. Intenta nuevamente.</p>
    <%
    } else if ("2".equals(error)) {
    %>
    <p class="error">Ocurrió un error inesperado. Por favor, intenta más tarde.</p>
    <%
        }
    %>

    <form method="post" action="verify" class="formulario">
        <label for="codigo">Código de verificación:</label>
        <input type="text" id="codigo" name="codigo" required>

        <input type="hidden" name="email" value="<%= request.getParameter("email") %>">

        <div class="botones">
            <input type="submit" value="Verificar">
            <a class="btn-secundario" href="resend-code?email=<%= request.getParameter("email") %>">Reenviar Código</a>
        </div>
    </form>
</div>
</body>
</html>
