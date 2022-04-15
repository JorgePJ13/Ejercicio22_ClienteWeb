<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cerrar Sesión</title>
<link rel="stylesheet" type="text/css" href="css/style_Logout.css">
</head>
<body>
	<div class="out">
		<br>
		<p id="error">${sessionScope.msjErrorCerrar}</p>
		<form class="formularioLogOut" action="LogoutServlet" method="post">
			<span class="abandonar">¿Quieres abandonar la sesión?</span>
			<div class="salir">
				<button>Cerrar Sesión</button>
				<a href="index.jsp">Volver a la página de
					Inicio (Home)</a>
			</div>
		</form>
	</div>
</body>
</html>