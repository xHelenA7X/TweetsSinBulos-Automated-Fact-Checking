<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>TweetCheck. Es lo que verdaderamente está pasando.</title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

		<!-- CSS -->
		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="css/style.css"/>
		<link rel="icon" type="image/png" href="./img/pinocho-logo.png" />
		
		<!-- JS -->
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    </head>
	<body>
        <input type="hidden" name="idTweet" value="${idTweet}">
		<!-- Header -->
		<header id="header" class="transparent-nav">
			<div class="container">

				<div class="navbar-header">
					<!-- Logo -->
					<div class="navbar-brand">
						<a class="logo" href="https://www.ua.es/" target="_blank">
							<img src="./img/logo.png" alt="logo">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Mobile toggle -->
					<button class="navbar-toggle">
						<span></span>
					</button>
					<!-- /Mobile toggle -->
				</div>

				<!-- Navigation -->
				<nav id="nav">
					<ul class="main-menu nav navbar-nav navbar-right">
						<li><a href="index.html">Inicio</a></li>
						<li><a href="#">Cómo usar</a></li>
						<li><a href="#">Desarrolladora</a></li>
						<li><a href="blog.html">Blog</a></li>
						<li><a href="contact.html">Contact</a></li>
					</ul>
				</nav>
				<!-- /Navigation -->

			</div>
		</header>
			<!-- Call To Action -->
			<div id="cta" class="section">

					<!-- Backgound Image -->
					<div class="bg-image bg-parallax overlay" style="background-image:url(./img/libros.jpg)"></div>
					<!-- /Backgound Image -->
		
					<!-- container -->
					<div class="container">
		
						<!-- row -->
						<div class="row">
		
							<div class="col-md-6">
                                <h2 class="white-text">Resultados</h2>
								<a class="main-button icon-button" href="#">Si lo prefieres, consulta los resultados en modo texto.</a>
							</div>
		
						</div>
						<!-- /row -->
		
					</div>
					<!-- /container -->
		
				</div>
				<!-- /Call To Action -->
		
		<!-- /Header -->
		<div id="about" class="section">
				<!-- container -->
				<div class="container">
	
					<!-- row -->
					<div class="row">
	
						<div class="col-md-6">
							<div class="section-header">
								<h2>Afirmación analizada:</h2>
                                <p class="lead"></p>
                                <i><small>Nota: Que la afirmación contenga errores ortográficos puede influir negativamente en la búsqueda. Asegúrese de que gramaticalmente
                                     el texto en cuestión es correcto.</small>
                                </i>
							</div>
	
							<!-- feature -->
							<div class="feature">
								<i class="feature-icon fa fa-flask"></i>
								<div class="feature-content">
									<h4>¿Qué se afirma o niega?</h4>
									<p class="conclusion"></p>
								</div>
							</div>
							<!-- /feature -->
	
							<!-- feature -->
							<div class="feature">
								<i class="feature-icon fa fa-users"></i>
								<div class="feature-content">
									<h4>¿Qué dicen los expertos?</h4>
									<p class="e1"></p>
									<p class="e2"></p>
									<p class="e3"></p>
									<p class="e4"></p>
									<p class="e5"></p>
									<p class="e6"></p>
								</div>
							</div>
							<!-- /feature -->
	
							<!-- feature -->
							<div class="feature">
								<i class="feature-icon fa fa-comments"></i>
								<div class="feature-content">
									<h4>¿Qué dice la comunidad al respecto?</h4>
                                    <div class="tweets-comunidad">
                                    </div>
							</div>
							<!-- /feature -->
	
						</div>
					</div>
					<!-- row -->
	
				</div>
				<!-- container -->
			</div>

		<footer id="footer" class="section">

			<!-- container -->
			<div class="container">

				<!-- row -->
				<div class="row">

					<!-- footer logo -->
					<div class="col-md-6">
						<div class="footer-logo">
							<div class="logo">
								<a href="https://www.ua.es/" target="_blank"><img src="./img/logo.png" alt="logo-ua"></a>
							</div>
							<div class="logo-eps">
								<a href="https://eps.ua.es/" target="_blank"><img src="./img/logo-eps.png" alt="logo-eps"></a>
							</div>
						</div>
					</div>
					<!-- footer logo -->

					<!-- footer nav -->
					<div class="col-md-6">
						<ul class="footer-nav">
							<li><a href="index.html">Home</a></li>
							<li><a href="#">About</a></li>
							<li><a href="#">Courses</a></li>
							<li><a href="blog.html">Blog</a></li>
							<li><a href="contact.html">Contact</a></li>
						</ul>
					</div>
					<!-- /footer nav -->

				</div>
				<!-- /row -->

				<!-- row -->
				<div id="bottom-footer" class="row">

					<!-- social -->
					<div class="col-md-4 col-md-push-8">
						<ul class="footer-social">
							<li><a href="#" class="facebook"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#" class="twitter"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="#" class="instagram"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#" class="youtube"><i class="fa fa-youtube"></i></a></li>
							<li><a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a></li>
						</ul>
					</div>
					<!-- /social -->

					<!-- copyright -->
					<div class="col-md-8 col-md-pull-4">
							<span>Helena Sánchez Jiménez | Proyecto de Fin de Grado | Escuela Politécnica Superior.</span>
						<div class="footer-copyright">
							<span>&copy; Copyright 2019. Todos los derechos reservados. </span>
						</div>
					</div>
					<!-- /copyright -->

				</div>
				<!-- row -->

			</div>
			<!-- /container -->

		</footer>
		<!-- /Footer -->

		<!-- preloader -->
		<div id='preloader'><div class='preloader'></div></div>
		<!-- /preloader -->


        <!-- jQuery Plugins -->
         <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
        <script src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/salida.js"></script>
	</body>
</html>
