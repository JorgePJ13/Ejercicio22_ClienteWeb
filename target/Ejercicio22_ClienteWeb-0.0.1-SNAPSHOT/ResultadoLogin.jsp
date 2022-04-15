<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Información Usuario</title>
		<link rel="stylesheet" href="css/styleResultado.css">
	</head>
	<body>
		<header>Bienvenido de nuevo, ${param.nombre}</header>
		<p class="privado">Información privada de cuenta: ${info}</p>
		<p class="sesion"> <a href="login.html">Cambiar de Cuenta</a></p>
		<h1>¿Qué quieres saber de mí?</h1>
		<p class="info">
		Accede a cualquiera de estos dos <b>enlaces</b> para consultar mi <i>información
		personal</i> o ver como he desarrollado un <i>sitio web</i></p>
		<div class="izq">
			<a href="cv.html" target="_blank">Curriculum Vitae ${param.nombre}</a>
		</div>
		<div class="der">
			<a href="inicio.html" target="_blank">Sitio Web de Padel de ${param.nombre}</a>
		</div>
		<aside>
			<img id="fotoEjercicio4" src="images/Tom_Shelby.jpg" />
		</aside>
		<footer>
			<p>Todos los derechos reservador! copyright 2022 &copy;</p>
		</footer>
	</body>
</html>