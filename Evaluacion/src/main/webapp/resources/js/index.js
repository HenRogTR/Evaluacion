$(document).ready(function() {
	$('#btnIniciarSesion').click(function(event){
		fUsuarioIngresar();
		event.preventDefault();
	});
	
	$('#txtUsuario, #txtUsuario').keyup(function(event) {
        var key = event.charCode ? event.charCode : event.keyCode ? event.keyCode : 0;
        if (key == 13) {            
            fUsuarioIngresar();
            event.preventDefault();
        }
    });
	
	$('#btnIniciarAplicacionPrimeraVez').click(function(event) {
		
		var $dProcesandoPeticion = $('#dProcesandoPeticion');
		$dProcesandoPeticion.dialog('open');
		setTimeout(function(){
			fIniciarAplicacionPrimeraVez($dProcesandoPeticion);
		},1000);
		event.preventDefault();
	})
});

$(function() {
	$('#dInicioSesionClic').dialog({
		autoOpen: false,
        closeOnEscape: false,
        dialogClass: 'no-titlebar',
        draggable: false,
        height: 50,
        modal: true,
        resizable: false,
        width: 500
    });
	
	$('#dSelecionarRol').dialog({
        modal: true,
        autoOpen: false,
        buttons:{
            Continuar: function() {
                
            }
        },
        closeOnEscape: false,
        dialogClass: 'pequenio',
        draggable: false,
        height: 150,
        width: 500,
        show: {
            effect: "drop",
            duration: 200
        },
        hide: {
        	effect: "drop",
            duration: 200
        },
        resizable: false
    });
	
	
	
});

function fUsuarioIngresar() {    
	$('.dLogin').hide('drop', 300 );
	
	var usuario = $('#txtUsuario').val();
    var contrasenia = $('#txtContrasenia').val();
    if (!usuario.match(/^[a-zA-Z0-9]{4,16}$/)) {
        alert('*Usuario entre 4-16 caracteres, sólo alfanuméricos.');
        $('.dLogin').removeClass('ocultar');
        return;
    }
    if (!contrasenia.match(/^[a-zA-Z0-9]{4,16}$/)) {
        alert('*Contraseña entre 4-16 caracteres, sólo alfanuméricos.');
        $('.dLogin').removeClass('ocultar');
        return;
    }
    
    var $dProcesandoPeticion = $('#dProcesandoPeticion');
    var $dInicioSesionClic = $('#dInicioSesionClic');
    $dProcesandoPeticion.dialog('open');
    
    setTimeout(function(){
    	var $frm = $('#formIniciarSesion');
        var datos = $frm.serialize();
        var action = $frm.attr('action');
        
        try{
        	$.ajax({
        		type : 'post',
        		url : action,
        		data : datos,
        		error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert('Error: ' + XMLHttpRequest + textStatus + errorThrown);
                },
                success: function(ajaxResponse, textStatus){
                	var jsonCliente = procesarRespuesta(ajaxResponse);
                	var auditoria = jsonCliente[0];
                	if(auditoria.codigoRespuesta == '0'){
                		$dProcesandoPeticion.dialog('close');
                		alertify.success('Sesión inicada correctamente.');                		
                		
                		var roles = jsonCliente[1].rol;
                		var rolesArray = roles.split('\\:');                		
                		
                		if(rolesArray.length == 1){
                			alertify.message('Usuario sólo tiene un rol, se redireccionará a página según rol.');
            				$dProcesandoPeticion.find('#sContenedor').html('Redireccionando según rol, espere por favor.');
                			$dProcesandoPeticion.dialog('open');
                			
                			fEstablecerPerfilEnSesion(auditoria.idTransaccion, rolesArray[0]);                				
                			
                		}else{
                			alertify.message('Usuario con varios roles.');
                			$('#dSelecionarRol').dialog('open');
                		}
                	}else{
                		$('.dLogin').show('drop', 300 );
                		alertify.warning(auditoria.mensajeRespuesta);
                	}
                	
                },
                complete: function(jqXHR, textStatus){
                	$dProcesandoPeticion.dialog('close');
                },
                statusCode: {
                    404: function() {
                    	alertify.error('Página no encontrada [404]: <b>' + url + '</b>');
                    }
                }
        	});
        }catch (e) {
    		alert('Error al inciar sesión: ' + e);
    	}
    }, 2000);    
}

function fIniciarAplicacionPrimeraVez($dProcesandoPeticion){
	var url = $('#btnIniciarAplicacionPrimeraVez').attr('href');
	try{
		$.ajax({
			type : 'post',
    		url : url,
            success: function(ajaxResponse, textStatus){            	
            	var jsonRespuesta = procesarRespuesta(ajaxResponse);
            	
            	var auditoria= jsonRespuesta[0];
            	if(auditoria.codigoRespuesta == '0'){
            		alertify.success('Inicio de aplicación por primera vez exitosa.');
            	}else{
            		alertify.warning(auditoria.mensajeRespuesta);
            	}
            },
            complete: function(jqXHR, textStatus){
            	$dProcesandoPeticion.dialog('close');
            },
            statusCode: {
            	404: function() {
            		alertify.error('Página no encontrada [404]: <b>' + url + '</b>');
                }
            }
		})
	}catch (e) {
		alertify.error('Error al inicar aplicación por primera vez, ' + e);
	}
}

function fEstablecerPerfilEnSesion(idTx, perfil){
	var url = 'UsuarioServlet';
	var datos = {
			idTransaccion: idTx,
			perfil: perfil, 
			accion: 'establecerPerfil'
		};
	
	try{
		$.ajax({
			type : 'post',
    		url : url,
    		data : datos,
            success: function(ajaxResponse, textStatus){  	
            	var jsonRespuesta = procesarRespuesta(ajaxResponse);
            	
            	if(jsonRespuesta == null){
                    alertify.error('Error formato json, favor de corregir: ' + ajaxResponse);
            		return;
            	}
            	
            	var auditoria = jsonRespuesta[0];
            	if(auditoria.codigoRespuesta == '0'){
            		alertify.success('Perfil establecido.');
            		setTimeout(function(){
            			window.location = 'index_'+ perfil + '.jsp';
        			} ,2000);            		
            		
            	}else{
            		alertify.warning(auditoria.mensajeRespuesta);
            	}
            },
            complete: function(jqXHR, textStatus){
            	$('#dProcesandoPeticion').dialog('close');
            },
            statusCode: {
            	404: function() {
            		alertify.error('Página no encontrada [404]: <b>' + url + '</b>');
                }
            }
		})
	}catch (e) {
		alertify.error('Ocurrió un error al obtener usuario=' + usuario);
	}
}


