$(document).ready(function() {
	document.title = 'Facultad';
	leerTodos();
	
	$('.dialogoIniciar').dialog({
        modal: true,
        autoOpen: false,
        close: function( event, ui ) {
        	$('#form_crear .limpiar').val('').text('');
        	$('#form_actualizar .limpiar').val('').text('');
        },
        dialogClass: 'pequenio',
        draggable: false,
        height: 350,
        width: 500,
        show: {
            effect: 'drop',
            duration: 200
        },
        hide: {
        	effect: 'drop',
            duration: 200
        },
        resizable: false
    });
	
	$('#dFacultadActualizar').dialog('option','buttons',[
 	    {
	    	text: 'Actualizar',
	    	click: $.noop,
	    	type: 'submit',
	    	form: 'form_actualizar'
		},
		{
			text: 'Cancelar',
			click: function() {
				$(this).dialog('close');
			}			
		}
		
	]);
	
	$('#dFacultadCrear').dialog('option','buttons',[
	    {
	    	text: 'Guardar',
	    	click: $.noop,
	    	type: 'submit',
	    	form: 'form_crear'
		},
		{
			text: 'Cancelar',
			click: function() {
				$(this).dialog('close');
			}			
		}
		
	]);
	
	$("#form_crear").validate({
		rules:{
			txt_nombreFacultad_crear: 'required',
			txt_abreviaturaFacultad_crear: 'required'
		},
		errorLabelContainer: $("#form_crear div.div_error"),
		submitHandler: function() {
			fCrearAjax();
		},
		messages: {
			txt_nombreFacultad_crear: 'Ingrese <b>nombre de facultad</b>',
			txt_abreviaturaFacultad_crear: 'Ingrese <b>abreviatura de facultad</b>'
		}
	});
	
	$("#form_actualizar").validate({
		rules:{
			txt_nombreFacultad_actualizar: 'required',
			txt_abreviaturaFacultad_actualizar: 'required'
		},
		errorLabelContainer: $("#form_actualizar div.div_error"),
		submitHandler: function() {
			fActualizarAjax();
		},
		messages: {
			txt_nombreFacultad_actualizar: 'Ingrese <b>nombre de facultad</b>',
			txt_abreviaturaFacultad_actualizar: 'Ingrese <b>abreviatura de facultad</b>'
		}
	});
	
	$('#btn_crear').click(function(event){
		$('#dFacultadCrear').dialog('open');
		event.preventDefault();
	});
	
	$('#btn_buscar').click(function(event){
		fNoImplementado();
		event.preventDefault();
	});
	
	$('.inicioOculto').removeClass('inicioOculto');
});

function leerTodos() {
	var $dProcesandoPeticion = $('#dProcesandoPeticion');
	$dProcesandoPeticion.dialog('open');

	var url = 'FacultadServlet';
	var datos = {
		accion : 'leerTodos'
	}

	try {
		$.ajax({
			type : 'post',
			url : url,
			data : datos,
			success : function(ajaxResponse, textStatus) {
				var jsonRespuesta = procesarRespuesta(ajaxResponse);   	
            	if(jsonRespuesta == null){
            		alertify.error('Error en respuesta servidor [JSON].');
            		return;
            	}
            	
            	var auditoria = jsonRespuesta.auditoria;
            	if(auditoria.codRes == '0'){
            		var $tFacultadLista = $('#tFacultadLista tbody');            		
            		$tFacultadLista.empty();
            		
            		var tamanioUsuarioLista = jsonRespuesta.facultadLista.length;
            		
            		if(tamanioUsuarioLista == 0){
            			var $tr = $('<tr/>').appendTo($tFacultadLista);
            			var $td = $('<td/>',{
            				colspan : 4, 
            				text: 'No hay facultades registradas.'
            			}).appendTo($tr);
            		}else{
            			var html = '';
            			for(var i=0; i < tamanioUsuarioLista; i++){
            				var facultadItem = jsonRespuesta.facultadLista[i];
            				
            				html += '<tr>';
            				html += '	<td>';
            				html += '		<span id="txt_nombreFacultad_' + facultadItem.idFacultad + '">' + facultadItem.nombreFacultad + '</span>';
            				html += '		<input type="hidden" id="txt_idFacultad2_' + facultadItem.idFacultad + '" value="' + facultadItem.idFacultad2 + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<span>' + facultadItem.abreviatura + '</span>';
            				html += '		<input type="hidden" id="txt_abreviatura_' + facultadItem.idFacultad + '" value="' + facultadItem.abreviatura + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<span>' + facultadItem.descripcion + '</span>';
            				html += '		<input type="hidden" id="txt_descripcion_' + facultadItem.idFacultad + '" value="' + facultadItem.descripcion + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<input type="button" id="btn_actualizar_' + facultadItem.idFacultad + '" name="btn_actualizar" value="Editar"/>';
            				html += '		<input type="button" id="btn_borrar_' + facultadItem.idFacultad + '" name="btn_borrar" value="Borrar"/>';
            				html += '		<input type="button" id="btn_masDetalles_' + facultadItem.idFacultad + '" name="btn_masDetalles" value="Mas detalles"/>';
            				html += '	</td>';
            				html += '</tr>';
            			}
            			
            			$tFacultadLista.append(html);
            			
            			$('input[name=btn_actualizar]').bind('click', fActualizar);
            			$('input[name=btn_borrar]').bind('click', fNoImplementado);
            			$('input[name=btn_masDetalles]').bind('click', fNoImplementado);
            		}
            	}else if(auditoria.codRes > 0){
            		alertify.warning(auditoria.msjRes);
            	}else{
            		alertify.error(auditoria.msjRes);
            	}
			},
			complete : function(jqXHR, textStatus) {
				$dProcesandoPeticion.dialog('close');
			},
			statusCode : {
				404 : function() {
					alertify.error('Página no encontrada [404]: <b>' + url
							+ '</b>');
				}
			}
		});
	} catch (e) {
		alertify.error('Ocurrió un error:' + e);
		$dProcesandoPeticion.dialog('close');
	}
}

function fActualizar() {
	var $this = $(this);
	var id = $this.attr('id');
	var idFacultad = id.split('_')[2];
	$('#lbl_idFacultad_actualizar').text(idFacultad);
	$('#txt_idFacultad_actualizar').val(idFacultad);
	$('#txt_idFacultad2_actualizar').val($('#txt_idFacultad2_'+ idFacultad).val());
	$('#txt_nombreFacultad_actualizar').val($('#txt_nombreFacultad_'+idFacultad).text());
	$('#txt_abreviaturaFacultad_actualizar').val($('#txt_abreviatura_'+ idFacultad).val());
	$('#txt_descripcion_actualizar').val($('#txt_descripcion_'+ idFacultad).val());
	
	$('#dFacultadActualizar').dialog('open');	
}

function fBorrar() {
}

function fActualizarAjax() {
	var $dProcesandoPeticion = $('#dProcesandoPeticion');
	$dProcesandoPeticion.dialog('open');
	
	var url =  'FacultadServlet';
	var datos = $('#form_actualizar').serialize() + '&accion=actualizar';
	
	try {
		$.ajax({
			type : 'post',
			url : url,
			data : datos,
			success : function(ajaxResponse, textStatus) {
				var jsonRespuesta = procesarRespuesta(ajaxResponse);   	
            	if(jsonRespuesta == null){
            		console.log('Respuesta JSON: ' + ajaxResponse);
            		alertify.error('Error en respuesta servidor [JSON].');
            		return;
            	}
            	var auditoria = jsonRespuesta.auditoria;
            	if(auditoria.codRes == '0'){
    				$('#dFacultadActualizar').dialog('close');
    				alertify.success('Facultada actualizada.');
            		leerTodos();
            	}else if(auditoria.codRes > 0){
            		alertify.warning('No se ha actualizado facultad: <br>' 
            				+ auditoria.msjRes);
            	}else{
            		alertify.error('Error al actualizar facultat: <br>' 
            				+ auditoria.msjRes);
            	}
			},
			complete : function(jqXHR, textStatus) {
				$dProcesandoPeticion.dialog('close');
			},
			statusCode : {
				404 : function() {
					alertify.error('Página no encontrada [404]: <b>' + url
							+ '</b>');
				}
			}
		});
	} catch (e) {
		alertify.error('Ocurrió un error:' + e);
		$dProcesandoPeticion.dialog('close');
	}
}

function fCrearAjax() {
	var $dProcesandoPeticion = $('#dProcesandoPeticion');
	$dProcesandoPeticion.dialog('open');

	var url = 'FacultadServlet';
	var datos = {
		accion : 'crear',
		idFacultad2: $('#txt_idFacultad2_crear').val(),
		nombreFacultad: $('#txt_nombreFacultad_crear').val(),
		abreviatura: $('#txt_abreviaturaFacultad_crear').val(),
		descripcion: $('#txt_descripcion_crear').val()
	}
	
	try {
		$.ajax({
			type : 'post',
			url : url,
			data : datos,
			success : function(ajaxResponse, textStatus) {
				var jsonRespuesta = procesarRespuesta(ajaxResponse);   	
            	if(jsonRespuesta == null){
            		console.log('Respuesta JSON: ' + ajaxResponse);
            		alertify.error('Error en respuesta servidor [JSON].');
            		return;
            	}
            	var auditoria = jsonRespuesta.auditoria;
            	if(auditoria.codRes == '0'){
    				$('#dFacultadCrear').dialog('close');
    				alertify.success('Facultada creada.');
            		leerTodos();
            	}else if(auditoria.codRes > 0){
            		alertify.warning('No se ha creado facultad: <br>' 
            				+ auditoria.msjRes);
            	}else{
            		alertify.error('Error al crear facultat: <br>' 
            				+ auditoria.msjRes);
            	}
			},
			complete : function(jqXHR, textStatus) {
				$dProcesandoPeticion.dialog('close');
			},
			statusCode : {
				404 : function() {
					alertify.error('Página no encontrada [404]: <b>' + url
							+ '</b>');
				}
			}
		});
	} catch (e) {
		alertify.error('Ocurrió un error:' + e);
		$dProcesandoPeticion.dialog('close');
	}
	
}
