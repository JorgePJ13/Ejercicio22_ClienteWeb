<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>
<body>
	<h1>Hola de nuevo, ${sessionScope.usuario.getNombre()}</h1>
	<p>Contraseña: ${sessionScope.usuario.getPassword()}</p>
	<em>Autor: Jorge Pando Jimeno</em>
	<a href="logout.jsp">Cerrar Sesión</a>
</body>
</html>