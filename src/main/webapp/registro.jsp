<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Registro de usuario</title>
<link rel="stylesheet" type="text/css" href="css/styles.css" />
<link rel="stylesheet" type="text/css" href="css/style_Login.css" />
</head>
<body>
	<div id="cuerpo">
		<div id="cuerpo_Registro">
			<span id="error">${sessionScope.msjErrorCerrar}</span> <span
				id="error">${sessionScope.errorAlta}</span>
			<div id="loginText">
				<h4>Registro de Usuario</h4>
			</div>
			<form method="post" action="AltaUsuarioServlet" autocomplete="on">
				<fieldset id="loginForm">
					<legend> Completa los datos</legend>
					<div id="nombre-usuario">
						<label class="indicador_login" for="username"> Nombre de
							usuario: <input id="username" type="text" name="username"
							placeholder="Nombre de usuario" required>
						</label>
					</div>
					<div id="contraseña">
						<label class="indicador_login" for="password"> Contraseña:
							<input type="password" id="password" name="password"
							placeholder="eg. X8df!90EO"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Debe contener al menos un número y una letra minúscula y mayúscula, y al menos 8 o más caracteres"
							required />
						</label>
					</div>
					<div id="contraseña">
						<label class="indicador_login" for="password"> Confirmar
							Contraseña: <input type="password" id="password"
							name="conf_password" placeholder="eg. X8df!90EO"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Debe contener al menos un número y una letra minúscula y mayúscula, y al menos 8 o más caracteres"
							required />
						</label>
					</div>
					<div id="boton_login">
						<a><input id="button" type="submit" value="Registrarse" /></a>
					</div>
				</fieldset>
			</form>
			<div id="login">
				<a href="login.jsp"><span>¿Eres usuario? ¡Inicia sesión
						aquí!</span></a>
			</div>
		</div>
	</div>
</body>
</html>