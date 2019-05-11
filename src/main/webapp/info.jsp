<%@page language="java"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Es lo que verdaderamente está pasando.</title>
		<link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
		<header id="header" class="transparent-nav">
			<div class="container">
				<div class="navbar-header">
					<div class="navbar-brand">
						<a class="logo" href="https://www.ua.es/" target="_blank">
							<img src="./img/logo.png" alt="logo">
						</a>
					</div>
					<button class="navbar-toggle">
						<span></span>
					</button>
				</div>
				<nav id="nav">
					<ul class="main-menu nav navbar-nav navbar-right">
						<li><a href="index.jsp">Inicio</a></li>
						<li><a href="info.jsp">De que trata</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<div id="cta" class="section">
				<div class="bg-image bg-parallax overlay" style="background-image:url(./img/about.jpeg)"></div>
				<div class="container">
					<div class="row">
						<div class="col-md-6">
                               <h2 class="white-text">¿Qué es TweetsSinBulos?</h2>
						</div>
					</div>
				</div>
			</div>
		<div id="about" class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="section-header">
							<h2>TweetsSinBulos</h2>
							<p class="lead">Verificación de hechos automatizado.</p>
						</div>
						<div class="feature">
							<i class="feature-icon fa fa-flask"></i>
							<div class="feature-content">
								<h4>¿Qué es TweetsSinBulos? </h4>
								<p>TweetsSinBulos se trata de una plataforma de verificación de hechos automatizada. Se trata de un
							Trabajo de Fin de Grado realizado por Helena Sánchez Jiménez, estudiante en la Escuela Politécnica Superior, del grado Ingeniería Informática
							en la Universidad de Alicante. El objetivo de esta plataforma es el desmantelamiento de bulos existentes en la red a través de Tweets, procedentes de la plataforma
							y red social 'Twitter'. De esta forma, cualquier tweet pueda ser analizado y contrastado mediante PLN, verificando si dicha afirmación se trata de un
							bulo o no.</p>
							</div>
						</div>

					<div class="col-md-6">
						<div class="logo-comousar">
							<img src="img/pinocho-logo.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
    </body>
    <footer id="footer" class="section">
		<div class="container">
			<div class="row">
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
			</div>

			<div id="bottom-footer" class="row">
				<div class="col-md-4 col-md-push-8">
					<ul class="footer-social">
						<li><a href=https://twitter.com/tweetssinbulos class="twitter"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a></li>
					</ul>
				</div>
				<div class="col-md-8 col-md-pull-4">
						<span>Helena Sánchez Jiménez | Proyecto de Fin de Grado | Escuela Politécnica Superior.</span>
					<div class="footer-copyright">
						<span>&copy; Copyright 2019. Todos los derechos reservados. </span>
					</div>
				</div>
			</div>
		</div>
	</footer>
    
</html>