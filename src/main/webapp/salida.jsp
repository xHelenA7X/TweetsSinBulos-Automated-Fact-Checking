<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>Es lo que verdaderamente está pasando.</title>

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
						<li><a href="index.jsp">Inicio</a></li>
						<li><a href="info.jsp">De qué trata</a></li>
					</ul>
				</nav>
				<!-- /Navigation -->

			</div>
		</header>
			<!-- Call To Action -->
			<div id="cta" class="section">

					<!-- Backgound Image -->
					<div class="bg-image bg-parallax overlay" style="background-image:url(./img/salida.jpeg)"></div>
					<!-- /Backgound Image -->
		
					<!-- container -->
					<div class="container">
		
						<!-- row -->
						<div class="row">
		
							<div class="col-md-6">
                                <h2 class="white-text">Resultados</h2>
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
									<a class="e5"></a>
									<p class="e6"></p>
								</div>
							</div>
																		<!-- feature -->
							<div class="feature">
								<i class="feature-icon fa fa-fire"></i>
								<div class="feature-content">
									<h4>¿Qué dicen fuentes externas fiables?</h4>
									<i>Nota: Las siguientes fuentes están supervisadas por expertos encargados
									en desmontar y verificar noticias falsas, por lo que han sido categorizadas
									como fuentes fiables.</i>
									<div class="maldita">
										<a href="https://maldita.es/" target="_blank">
											<img src="img/maldita-logo.webp">
										</a>
									</div>
									<div class="saludsinbulos">
										<a href="https://saludsinbulos.com/" target="_blank">
											<img src="img/saludsinbulos-logo.png">
										</a>
									</div>
									<div class="vost">
										<a href="https://www.vost.es/stopbulos" target="_blank">
											<img src="img/vost-logo.png">
										</a>
									</div>
									<div class="snopes">
										<a href="https://www.snopes.com/" target="_blank">
											<img src="img/snopes-logo.png">
										</a>
									</div>
									<div class="eldiario">
										<a href="https://www.eldiario.es/" target="_blank">
											<img src="img/eldiario-logo.png">
										</a>
									</div>
									<div class="verificado">
										<a href="https://verificado.mx/categoria/verificaciones/" target="_blank">
											<img src="img/verificado-logo.png">
										</a>
									</div>
									<div class="chequeado">
										<a href="https://chequeado.com/" target="_blank">
											<img src="img/chequeado-logo.webp">
										</a>
									</div>
									
									<div class="aecc">
										<a href="https://www.aecc.es/es" target="_blank">
											<img src="img/aecc-logo.jpg">
										</a>
									</div>
								</div>
							</div>
							
							<div class="feature">
								<i class="feature-icon fa fa-exclamation-triangle"></i>
								<div class="feature-content">
									<h4>¿Qué dicen otras fuentes? (no verificadas)</h4>
									<i>Nota: Las siguientes noticias NO están verificadas por expertos... ¡Que no te la cuelen!</i>
									<div class="elpais">
										<a href="https://elpais.com/" target="_blank">
											<img src="img/el-pais.jpg">
										</a>
									</div>
									
									<div class="larazon">
										<a href="https://larazon.es/" target="_blank">
											<img src="img/la-razon.png">
										</a>
									</div>
									
									<div class="elmundo">
										<a href="https://elmundo.es/" target="_blank">
											<img src="img/el-mundo.jpg">
										</a>
									</div>
								</div>
							</div>
							
							
							<!-- /feature -->
	
							<!-- feature -->
							<div class="feature">
								<i class="feature-icon fa fa-comments"></i>
								<div class="feature-content">
									<h4>¿Qué dice la comunidad al respecto?</h4>
									<i class="nota-comunidad">Aportes de la comunidad... ¡Pero que no te la cuelen!</i>
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
				</div>
				<!-- /row -->

				<!-- row -->
				<div id="bottom-footer" class="row">

					<!-- social -->
					<div class="col-md-4 col-md-push-8">
						<ul class="footer-social">
							<li><a href="https://twitter.com/tweetssinbulos" class="twitter"><i class="fa fa-twitter"></i></a></li>
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
