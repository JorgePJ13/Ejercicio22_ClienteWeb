<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Inicio de Sesión</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" type="text/css" href="css/style_Login.css">
</head>
<body>
	<div id="cuerpo">
		<div id="cuerpo_Login">
			<span id="error">${sessionScope.errorInicio}</span>
			<div id="loginText">
				<h4>Iniciar Sesión</h4>
			</div>
			<form method=get action="LoginServlet">
				<fieldset id="loginForm">
					<legend> Introducir Credenciales</legend>
					<div id="usuario">
						<label class="indicador_login" for="fName"> Usuario: <input
							id="fName" type="text" name="fName" required />
						</label>
					</div>
					<div id="contraseña">
						<label class="indicador_login" for="password"> Contraseña:
							<input type="password" id="password" name="password" required />
						</label>
					</div>
					<div id="boton_login">
						<a><input id="button" type="submit" value="Login" /> </a>
					</div>
				</fieldset>
			</form>
			<div class="opciones">
				<div id="registro">
					<a href="registro.jsp"><span>Regístrate ahora</span></a>
				</div>
				<br>
			</div>

		</div>
	</div>
</body>
</html>