<%@page import="pe.edu.unu.evaluacion.util.UtilSesion"%>
<%
	Object usuario = session.getAttribute("usuarioSesion");
	Object perfil = session.getAttribute("perfilSesion");
	Object perfilLista = session.getAttribute("perfilListaSesion");
	
	if(usuario == null){
		response.sendRedirect("mensajes.jsp?in=2");
		
	}else if(perfil == null){
		response.sendRedirect("mensajes.jsp?in=1");
		
	}else if(!perfil.equals("ad")){
		response.sendRedirect("mensajes.jsp?in=4");
		
	}else if(UtilSesion.obtenerUsuarioCache("[index_ad.jsp] ", usuario.toString()) == null){
		session.removeAttribute("usuarioSesion");
		session.removeAttribute("perfilSesion");
		session.removeAttribute("perfilListaSesion");
		response.sendRedirect("mensajes.jsp?in=3");
		
	}else{
		UtilSesion.setSesion("[index_ad.jsp] ", UtilSesion.obtenerUsuarioCache("[index_ad.jsp] ", usuario.toString()));		
	}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC 
	"-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        
        <script src="resources/js/externo/jquery-2.1.4.min.js"></script>
        <script src="resources/js/externo/jquery-ui-1.11.4.min.js"></script>
        <script src="resources/js/externo/alertify-1.6.0.min.js"></script>
        <script src="resources/js/externo/jquery.validate.min.js"></script>
        <script src="resources/js/externo/jquery.validate.localization.messages_es_PE.min.js"></script>
        <script src="resources/js/externo/jquery-ui-datepicker-es.js"></script>
        
        <script src="resources/js/comunes.js"></script>
        
        <%
        String pagina = request.getParameter("pag");
        
       if("usuario".equals(pagina)){
        	%>
        	<script src="resources/js/usuario.js"></script>
        	<%
        }else if("facultad".equals(pagina)){
        	%>
        	<script src="resources/js/facultad.js"></script>
        	<%
        }else if("escuela".equals(pagina)){
        	%>
        	<script src="resources/js/escuela.js"></script>
        	<%
        }else if("semestre".equals(pagina)){
        	%>
        	<script src="resources/js/semestre.js"></script>
        	<%
        }
        %>
        
        <link rel="stylesheet" href="resources/css/estiloPrincipal.css">
        
        <link rel="stylesheet" href="resources/css/externo/jquery-ui-Smoothness.min.css">        
        <link rel="stylesheet" href="resources/css/externo/jquery-ui-adicionales.css">
        <link rel="stylesheet" href="resources/css/externo/alertify-1.6.0.min.css">
        <link rel="stylesheet" href="resources/css/externo/default-alertifyjs-1.6.0.min.css">
        
    </head>
    <body id="bd" class="ad">
    	<input type="hidden" name="sessionUsuario" id="sessionUsuario" 
    		value="<%=usuario %>" >
    	<input type="hidden" name="sessionPerfil" id="sessionPerfil" 
    		value="<%=perfil%>" >
    		
    	<div id="dContenedor">
    		<div id="dCabecera">
	    		<div class="menu">
	    			<div style="clear: both;"></div>
	    			<div class="dLogo">	    				
		    			<h1 class="logo">
		    				<a href="index_ad.jsp" title="Universidad Nacional de Ucayali"></a>
		    			</h1>
	    			</div>	    			
	    			<div class="dUsuario">
	    				USUARIO: <span id="sUsuario"><%=usuario %></span>
	    			</div>
	    			<div style="clear: both;"></div>
	    		</div>
	    	</div>
	    	<div id="dMenu">
	    		<div class="menu">
	    			<table>
	    			<thead>
    					<tr>
	    					<td><a id="btnInicio" href="index_ad.jsp">Inicio</a></td>
	    					<td><a id="btnEvaluado" href="index_ad.jsp">Evaluado</a></td>
	    					<td><a id="btnEncuesta" href="index_ad.jsp">Encuesta</a></td>
	    					<td><a id="btnEvaluador" href="index_ad.jsp">Evaluador</a></td>
	    					<td><a id="btnFacultad" href="index_ad.jsp?pag=facultad">Facultad</a></td>
	    					<td><a id="btnEscuela" href="index_ad.jsp?pag=escuela">Escuela</a></td>
	    					<td><a id="btnSemestre" href="index_ad.jsp?pag=semestre">Semestre</a></td>
	    					<td><a id="btnCurso" href="index_ad.jsp">Curso</a></td>
	    					<td><a id="btnUsuario" href="index_ad.jsp?pag=usuario">Usuario</a></td>
	    					<td><a id="btnCerrarSesion" href="#">Cerrar sesión</a></td>
	    					<td><a id="btnAcercaDe" href="index_ad.jsp">Acerca de</a></td>
	    				</tr>
	    				</thead>
	    			</table>
	    		</div>
	    	</div>
    		
	    	<div id="dCuerpo">
	    		<div class="menu">
	    		<%
	    		if(pagina == null){
         			%>
         			Permite la administración de los actores en la encuesta 
	    			además donde está asigando cada actor.
         			<%
        		}else if(pagina.equals("usuario")){
        			%>
        			<%@include file="administrador/usuario.jsp" %>
        			<%
        		}else if(pagina.equals("facultad")){
        			%>
        			<%@include file="administrador/facultad.jsp" %>
        			<%
        		}else if(pagina.equals("escuela")){
            		%>
            		<%@include file="administrador/escuela.jsp" %>
            		<%
        		}else if(pagina.equals("semestre")){
            		%>
            		<%@include file="administrador/semestre.jsp" %>
            		<%
        		}else{
        			%>
        			Página no encontrada/implementada.
        			<%
        		}
	    		%>	    			
	    		</div>
	    	</div>
	    	<div id="dPiePagina">
	    		<div class="menu">
	    			Pié de página
	    		</div>
	    		<div id="dProcesandoPeticion" title="Inicio de sesión.">
					<div><span id="sContenedor">Procesando petición, espere por favor</span> <img alt="imagen" src="resources/img/loader.gif" style="height: 15px;"/></div>
				</div>
	    	</div>
    	</div>    
    </body>
</html>
