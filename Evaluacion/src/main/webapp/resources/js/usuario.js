$(document).ready(function() {
	var $usuario = $('#sessionUsuario');
	$('#sUsuario').text($usuario.val());
	document.title = 'Usuarios';
	usuarioCargar();
});

function usuarioCargar() {
	
	var $dProcesandoPeticion = $('#dProcesandoPeticion');
	$dProcesandoPeticion.dialog('open');
	
	var url = 'UsuarioServlet';
	var datos = {
			accion: 'usuarioCargarTodos'
		};
	
	try{
		$.ajax({
			type : 'post',
    		url : url,
    		data : datos,
            success: function(ajaxResponse, textStatus){            	
            	var jsonRespuesta = procesarRespuesta(ajaxResponse);   	
            	if(jsonRespuesta == null){
                    console.log('Error json.');
            		return;
            	}
            	
            	var auditoria = jsonRespuesta.auditoria;
            	
            	if(auditoria.codRes == '0'){
            		
            		var $tUsuarios = $('#tUsuarios tbody');            		
            		$tUsuarios.empty();
            		
            		var tamanioUsuarioLista = jsonRespuesta.usuarioLista.length;

    				if(tamanioUsuarioLista == 0){
            			var $tr = $('<tr/>').appendTo($tUsuarios);
            			var $td = $('<td/>',{colspan : 6, text: 'No hay usuarios registrados.'}).appendTo($tr);
            		}else{
            			for(var i=0; i<tamanioUsuarioLista; i++){
            				
            				var usuarioItem = jsonRespuesta.usuarioLista[i];
            				var $tr = $('<tr/>').appendTo($tUsuarios);
            				var $td1 = $('<td/>', {text:usuarioItem.idUsuario}).appendTo($tr);
            				var $td2 = $('<td/>', {text:usuarioItem.idUsuario2}).appendTo($tr);
            				var $td3 = $('<td/>', {text:usuarioItem.usuario}).appendTo($tr);
            				var $td4 = $('<td/>', {text:usuarioItem.contrasenia}).appendTo($tr);
            				var $td5 = $('<td/>', {text:usuarioItem.estado}).appendTo($tr);
            				var $td5 = $('<td/>', {id: usuarioItem.idUsuario ,text: 'Ver detalles', 'class': 'verRegistro manoClic'}).appendTo($tr);
            			}
            			
            			$('.verRegistro').bind('click', fVerRegistro);
            		}
            	}else{
            		alertify.warning(auditoria.msjRes);
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
		alertify.error('Ocurrió un error al obtener usuario=' + usuario);
	}
}

function fVerRegistro(objeto){
	var $this = $(this).attr('id') ? $(this) : objeto;
	
	console.log($this.attr('id'))
}

