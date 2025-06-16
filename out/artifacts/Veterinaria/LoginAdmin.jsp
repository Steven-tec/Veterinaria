<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <title>Login Administrador</title>
  <link rel="stylesheet" href="css/loginAdmin.css">

  <!-- Bootstrap CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />


</head>
<body>

<div class="login-box shadow">
  <h2>Login Administrador</h2>
  <form action="LoginAdminServlet" method="post" class="needs-validation" novalidate>
    <div class="mb-3">
      <input
              type="email"
              name="email"
              placeholder="Correo"
              required
              class="form-control"
      />
      <div class="invalid-feedback">
        Por favor ingrese un correo válido.
      </div>
    </div>

    <div class="mb-4">
      <input
              type="password"
              name="password"
              placeholder="Contraseña"
              required
              class="form-control"
              minlength="8"
      />
      <div class="invalid-feedback">
        Por favor ingrese una contraseña (mínimo 8 caracteres).
      </div>
    </div>

    <button type="submit" class="btn btn-custom">Ingresar</button>
  </form>

  <div class="mt-3 text-center">
    <a href="index.jsp" class="btn btn-outline-secondary">Volver al Inicio</a>
  </div>
</div>

<!-- Bootstrap JS (para validación si quieres usar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
  // Validación nativa Bootstrap (opcional)
  (() => {
    'use strict';
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  })();
</script>

</body>
</html>

