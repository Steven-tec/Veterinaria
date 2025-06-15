<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Registrar Cita Médica</title>
  <link rel="stylesheet" href="css/Registrarcita.css">
</head>
<body>
<div class="form-container">
  <h2>Registrar Cita Médica</h2>
  <form method="post" action="registrarCita">
    ID Mascota: <input type="number" name="idMascota" required><br>
    Fecha: <input type="date" name="fecha" required><br>
    Hora: <input type="time" name="hora" required><br>
    Motivo: <input type="text" name="motivo"><br>
    Indicaciones: <textarea name="indicaciones" rows="3"></textarea><br>
    Veterinario: <input type="text" name="veterinario"><br>
    <input type="submit" value="Registrar">
  </form>
</div>
</body>
</html>

