
<%
	String[] mensaje= {
		"Continuar",
		"No se ha establecido perfil en el usuario actual",
		"Sesión no iniciada",
		"Sesión de usuario a finalizado",
		"Usuario no tiene permisos para ingresar a página"
		};

	String indice = request.getParameter("in") == null ? 
			"0": request.getParameter("in");
	int ind = 0;
	
	try{
		ind = Integer.parseInt(indice);
	}catch(Exception e){
		
	}
	
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC 
	"-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensajes</title>        
        
        
    </head>
    <body id="bd" class="ad ev1 ev2">
    	
    	<div id="dContenedor">
    		<h3><%=mensaje[ind] %> <a href="index.jsp">clic aquí.</a></h3>
    	</div>
    </body>
</html>
