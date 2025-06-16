<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Pet Sitting - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
 
    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">


    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">

    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>

  <div class="wrap">
	  <div class="container">
		  <div class="row">
			  <div class="col-md-6 d-flex align-items-center">
				  <p class="mb-0 phone pl-md-2">
					  <a href="#" class="mr-2"><span class="fa fa-phone mr-1"></span> +00 1234 567</a>
					  <a href="#"><span class="fa fa-paper-plane mr-1"></span> petsiting@email.com</a>
				  </p>
			  </div>
			  <div class="col-md-6 d-flex justify-content-md-end">
				  <div class="social-media">
					  <p class="mb-0 d-flex">
						  <a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-facebook"><i class="sr-only">Facebook</i></span></a>
						  <a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-twitter"><i class="sr-only">Twitter</i></span></a>
						  <a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-instagram"><i class="sr-only">Instagram</i></span></a>
						  <a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-dribbble"><i class="sr-only">Dribbble</i></span></a>
					  </p>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	  <div class="container">
		  <a class="navbar-brand me-3" href="index.jsp">
			  <i class="fa fa-paw mr-2" style="color: #9f41d5;"></i> Clínica de mascotas
		  </a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
			  <span class="fa fa-bars"></span> Menú
		  </button>
		  <div class="collapse navbar-collapse" id="ftco-nav">
			  <ul class="navbar-nav ml-auto">
				  <li class="nav-item active"><a href="index.jsp" class="nav-link">Inicio</a></li>
				  <li class="nav-item"><a href="vet.jsp" class="nav-link">Nuestro Equipo</a></li>
				  <li class="nav-item"><a href="services.jsp" class="nav-link">Servicios</a></li>
				  <li class="nav-item"><a href="registrarCita.jsp" class="nav-link">Registrar citas</a></li>
				  <li class="nav-item"><a href="RegistrarMascota.jsp" class="nav-link">Registro de Mascotas</a></li>
				  <li class="nav-item"><a href="contact.jsp" class="nav-link">Contacto</a></li>
				  <li class="nav-item"><a href="login.jsp" class="nav-link">Cerrar sesion</a></li>
				  <li class="nav-item"><a href="LoginAdmin.jsp" class="nav-link">Admin loging</a></li>
			  </ul>
		  </div>
	  </div>
  </nav>
  <!-- FIN del nav -->

  <section class="hero-wrap hero-wrap-2" style="background-image: url('img/bg_2.jpg');" data-stellar-background-ratio="0.5">
	  <div class="overlay"></div>
	  <div class="container">
		  <div class="row no-gutters slider-text align-items-end">
			  <div class="col-md-9 ftco-animate pb-5">
				  <p class="breadcrumbs mb-2"><span class="mr-2"><a href="index.jsp">Inicio <i class="ion-ios-arrow-forward"></i></a></span> <span>Precios <i class="ion-ios-arrow-forward"></i></span></p>
				  <h1 class="mb-0 bread">Precios</h1>
			  </div>
		  </div>
	  </div>
  </section>

  <!-- ... Mantienes todo igual hasta antes del primer <section> -->

  <section class="ftco-section bg-light">
	  <div class="container">
		  <div class="row justify-content-center pb-5 mb-3">
			  <div class="col-md-7 heading-section text-center ftco-animate">
				  <h2>Registrar nueva mascota</h2>
				  <p>Complete la información para registrar a su mascota en nuestra clínica.</p>
			  </div>
		  </div>
		  <div class="row justify-content-center">
			  <div class="col-md-8 ftco-animate">
				  <form action="registrarMascota.jsp" method="post" class="appointment">
					  <div class="row">
						  <div class="col-md-6">
							  <div class="form-group">
								  <input type="text" name="nombre" class="form-control" placeholder="Nombre de la mascota" required>
							  </div>
						  </div>
						  <div class="col-md-6">
							  <div class="form-group">
								  <select name="tipo" class="form-control" required>
									  <option value="" disabled selected>Tipo de mascota</option>
									  <option value="Perro">Perro</option>
									  <option value="Gato">Gato</option>
									  <option value="Ave">Ave</option>
									  <option value="Otro">Otro</option>
								  </select>
							  </div>
						  </div>
						  <div class="col-md-6">
							  <div class="form-group">
								  <input type="text" name="raza" class="form-control" placeholder="Raza" required>
							  </div>
						  </div>
						  <div class="col-md-6">
							  <div class="form-group">
								  <input type="number" name="edad" class="form-control" placeholder="Edad (años)" min="0" required>
							  </div>
						  </div>
						  <div class="col-md-12">
							  <div class="form-group">
								  <textarea name="observaciones" class="form-control" rows="4" placeholder="Observaciones adicionales"></textarea>
							  </div>
						  </div>
						  <div class="col-md-12">
							  <div class="form-group">
								  <input type="submit" value="Registrar mascota" class="btn btn-primary py-3 px-4">
							  </div>
						  </div>
					  </div>
				  </form>
			  </div>
		  </div>
	  </div>
  </section>

  <!-- El resto del footer, scripts y carga se mantienen igual -->

  <footer class="footer">
	  <div class="container">
		  <div class="row">
			  <div class="col-md-6 col-lg-3 mb-4 mb-md-0">
				  <h2 class="footer-heading">Petsitting</h2>
				  <p>Un pequeño río llamado Duden pasa por su lugar y lo abastece con la necesaria regelialia.</p>
				  <ul class="ftco-footer-social p-0">
					  <li class="ftco-animate"><a href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><span class="fa fa-twitter"></span></a></li>
					  <li class="ftco-animate"><a href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><span class="fa fa-facebook"></span></a></li>
					  <li class="ftco-animate"><a href="#" data-toggle="tooltip" data-placement="top" title="Instagram"><span class="fa fa-instagram"></span></a></li>
				  </ul>
			  </div>
			  <div class="col-md-6 col-lg-3 mb-4 mb-md-0">
				  <h2 class="footer-heading">Últimas noticias</h2>
				  <div class="block-21 mb-4 d-flex">
					  <a class="img mr-4 rounded" style="background-image: url(img/image_1.jpg);"></a>
					  <div class="text">
						  <h3 class="heading"><a href="#">Incluso el todopoderoso Pointing no tiene control sobre</a></h3>
						  <div class="meta">
							  <div><a href="#"><span class="icon-calendar"></span> 7 de abril, 2020</a></div>
							  <div><a href="#"><span class="icon-person"></span> Admin</a></div>
							  <div><a href="#"><span class="icon-chat"></span> 19</a></div>
						  </div>
					  </div>
				  </div>
				  <div class="block-21 mb-4 d-flex">
					  <a class="img mr-4 rounded" style="background-image: url(img/image_2.jpg);"></a>
					  <div class="text">
						  <h3 class="heading"><a href="#">Incluso el todopoderoso Pointing no tiene control sobre</a></h3>
						  <div class="meta">
							  <div><a href="#"><span class="icon-calendar"></span> 7 de abril, 2020</a></div>
							  <div><a href="#"><span class="icon-person"></span> Admin</a></div>
							  <div><a href="#"><span class="icon-chat"></span> 19</a></div>
						  </div>
					  </div>
				  </div>
			  </div>
			  <div class="col-md-6 col-lg-3 pl-lg-5 mb-4 mb-md-0">
				  <h2 class="footer-heading">Enlaces rápidos</h2>
				  <ul class="list-unstyled">
					  <li><a href="#" class="py-2 d-block">Sobre nosotros</a></li>
					  <li><a href="#" class="py-2 d-block">Servicios</a></li>
					  <li><a href="#" class="py-2 d-block">Proyectos</a></li>
					  <li><a href="#" class="py-2 d-block">Contacto</a></li>
				  </ul>
			  </div>
			  <div class="col-md-6 col-lg-3 mb-4 mb-md-0">
				  <h2 class="footer-heading">Información</h2>
				  <div class="block-23 mb-3">
					  <ul>
						  <li><span class="icon fa fa-map-marker"></span><span class="text">203 Fake St. Ciudad, País</span></li>
						  <li><a href="#"><span class="icon fa fa-phone"></span><span class="text">+2 392 3929 210</span></a></li>
						  <li><a href="#"><span class="icon fa fa-paper-plane"></span><span class="text">info@petsitting.com</span></a></li>
					  </ul>
				  </div>
			  </div>
		  </div>
		  <div class="row mt-5">
			  <div class="col-md-12 text-center">
				  <p>© 2025 Todos los derechos reservados | Hecho con ❤️ por Clinica de Mascotas</p>
			  </div>
		  </div>
	  </div>
  </footer>




  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/jquery.timepicker.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>


    
  </body>
</html>