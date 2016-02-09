<%
	Object usuario = session.getAttribute("usuarioSesion");
	Object perfil = session.getAttribute("perfilSesion");
	Object perfilLista = session.getAttribute("perfilListaSesion");
	
	if(usuario!= null ){
		if(perfil != null){			
			response.sendRedirect("index_" + perfil + ".jsp");
		}else if(perfilLista != null){
			String[] perfilArreglo = perfilLista.toString().split("\\:");
			
			if(perfilArreglo.length == 1){
				session.setAttribute("perfilSesion", perfilArreglo[0]);
				response.sendRedirect("index_" + perfilArreglo[0] + ".jsp");
			}
			
		}else{
			session.removeAttribute("usuarioSesion");
			session.removeAttribute("perfilSesion");
			session.removeAttribute("perfilListaSesion");
			
			usuario = null;
			perfil = null;
			perfilLista = null;
		}
	}else{
		session.removeAttribute("usuarioSesion");
		session.removeAttribute("perfilSesion");
		session.removeAttribute("perfilListaSesion");
		
		usuario = null;
		perfil = null;
		perfilLista = null;
	}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC 
	"-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evaluación de docentes</title>
        
        <script src="resources/js/externo/jquery-2.1.4.min.js"></script>
        <script src="resources/js/externo/jquery-ui-1.11.4.min.js"></script>
        <script src="resources/js/externo/alertify-1.6.0.min.js"></script>
        <script src="resources/js/comunes.js"></script>
        
        <script src="resources/js/index.js"></script>
        
        <link rel="stylesheet" href="resources/css/estiloPrincipal.css">
        <link rel="stylesheet" href="resources/css/externo/jquery-ui-Smoothness.min.css">
        <link rel="stylesheet" href="resources/css/externo/jquery-ui-adicionales.css">
        <link rel="stylesheet" href="resources/css/externo/alertify-1.6.0.min.css">
        <link rel="stylesheet" href="resources/css/externo/default-alertifyjs-1.6.0.min.css">
        
    </head>
    <body id="bd" class="">
    	<input type="hidden" name="sessionUsuario" id="sessionUsuario" 
    		value="<%=usuario %>">
    	<input type="hidden" name="sessionPerfil" id="sessionPerfil" 
    		value="<%=perfil%>">
    	<input type="hidden" name="sessionPerfilLista" id="sessionPerfilLista" 
    		value="<%=perfilLista%>">    		
    		
    	<div id="dContenedor">
    		<div id="dCabecera">
	    		<div class="menu">
	    			<div style="clear: both;"></div>
	    			<div class="dLogo">	    				
		    			<h1 class="logo">
		    				<a href="#" title="Universidad Nacional de Ucayali"></a>
		    			</h1>
	    			</div>
	    			<div class="dLogin">
	    				<form id="formIniciarSesion" action="UsuarioIniciarSesion">
		    				<table>
		    					<tr>
		    						<td><label for="txtUsuario">Usuario / Código</label></td>
		    						<td><label for="txtContrasenia">Constraseña</label></td>
		    					</tr>
		    					<tr>
		    						<td><input type="text" id="txtUsuario" name="txtUsuario"></td>
		    						<td><input type="password" id="txtContrasenia" name="txtContrasenia"></td>
		    						<td><input type="submit" id="btnIniciarSesion" name="btnIniciarSesion" value="Iniciar sesión"></td>
		    					</tr>
		    				</table>
	    				</form>
	    			</div>
	    			<div style="clear: both;"></div>
	    		</div>
	    	</div>
	    	<div id="dMenu">
	    		<div class="menu">
	    			<table>
	    				<thead>
		    				<tr>
		    					<td><a id="btnAcercaDe" href="#">Acerca de</a></td>
		    				</tr>
	    				</thead>
	    				<tbody class="sesionIniciada">	    					
		    				<tr>
		    					<td><a></a></td>
		    				</tr>
	    				</tbody>
	    			</table>
	    		</div>
	    	</div>
	    	<div id="dCuerpo">
	    		<div class="menu">
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
		    		<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
		    		<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
					<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
					<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
					<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
					<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
					<br>
		    		This is Bedazzled , a free, fully standards-compliant CSS template designed by FreeCssTemplates for Free CSS Templates.	This free template is released under a Creative Commons Attributions 2.5 license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you keep the links in the footer intact. Aside from that, have fun with it :)
	
					Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.
				
				</div>
	    	</div>
	    	<div id="dPiePagina">
	    		<div class="menu">	    			
		    		<div>
		    			<a id="btnIniciarAplicacionPrimeraVez" href="UsuarioIniciarAplicacionPrimeraVez">Iniciar aplicación primera vez.</a>
		    		</div>	    			
	    		</div>
				
				<div id="dSelecionarRol" title="Seleccione rol de usuario para continuar.">
					<strong>Seleccione rol de usuario para continuar.</strong>
				</div>
				
				<div id="dProcesandoPeticion" title="Inicio de sesión.">
					<div><span id="sContenedor">Procesando petición, espere por favor</span> <img alt="imagen" src="resources/img/loader.gif" style="height: 15px;"/></div>
				</div>
	    	</div>
    	</div>
    </body>
</html>
