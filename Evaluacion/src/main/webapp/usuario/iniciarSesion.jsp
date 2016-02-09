<%@page import="pe.edu.unu.evaluacion.util.ParametrosExternos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evaluación de docentes</title>
    </head>
    <body>
    	<div>
    		<div>
        		<h2>Inicio de sesión</h2>
        		<%
        			session = request.getSession();
        			String mensajeRespuesta = (String)session.getAttribute("mensajeRespuesta");
        			mensajeRespuesta = mensajeRespuesta == null ? "" : mensajeRespuesta;
        		%>
        		<h5><%=mensajeRespuesta %></h5>
	        </div>
	        <form action="/Evaluacion/UsuarioIniciarSesion" method="post">
		        <table>
		        	<tbody>
		        		<tr>
		        			<td><label for="usuario">Usuario o correo electrónico</label></td>
		        			<td><input type="text" id="usuario" name="usuario"></td>
		        		</tr>
		        		<tr>
		        			<td><label>Contraseña</label></td>
		        			<td><input type="password" id="contrasenia" name="contrasenia"></td>
		        		</tr>
		        		<tr>
		        			<td><input type="submit" id="btnIngresar" name="btnIngresar" value="Iniciar sesión"></td>
		        		</tr>
		        	</tbody>
		        </table>
	        </form>
    	</div>
        <div>
        	<a href="/Evaluacion/UsuarioIniciarSesion?primeraVez=primeraVez">Iniciar aplicación primera vez</a>
        </div>
    </body>
</html>
