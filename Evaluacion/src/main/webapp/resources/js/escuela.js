var $dProcesandoPeticion = $('#dProcesandoPeticion');

$(document).ready(function() {
	document.title = 'Escuela';
	leerTodos();	
	
	$('.dialogoIniciar').dialog({
        modal: true,
        autoOpen: false,
        close: function( event, ui ) {
        	$('#form_crear .limpiar').val('').text('');
        	$('#form_actualizar .limpiar').val('').text('');
        },
        dialogClass: 'pequenio',
        height: 400,
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
	
	$('#dCrear').dialog('option','buttons',[
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
	
	$('#dActualizar').dialog('option','buttons',[
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
	
	$("#form_crear").validate({
		rules:{
			txt_nombreEscuela_crear: 'required',
			txt_abreviaturaEscuela_crear: 'required',
			sel_idFacultad_crear: 'required'
		},
		errorLabelContainer: $("#form_crear div.div_error"),
		submitHandler: function() {
			fCrearAjax();
		},
		messages: {
			txt_nombreEscuela_crear: 'Ingrese <b>nombre de escuela</b>',
			txt_abreviaturaEscuela_crear: 'Ingrese <b>abreviatura de escuela</b>',
			sel_idFacultad_crear: 'Seleccione facultad a asignar'
		}
	});
	
	$("#form_actualizar").validate({
		rules:{
			txt_nombreEscuela_actualizar: 'required',
			txt_abreviaturaEscuela_actualizar: 'required',
			sel_idFacultad_actualizar: 'required'
		},
		errorLabelContainer: $("#form_actualizar div.div_error"),
		submitHandler: function() {
			fActualizar();
		},
		messages: {
			txt_nombreEscuela_actualizar: 'Ingrese <b>nombre de escuela</b>',
			txt_abreviaturaEscuela_actualizar: 'Ingrese <b>abreviatura de escuela</b>',
			sel_idFacultad_actualizar: 'Seleccione facultad a asignar'
		}
	});
	
	
	$('#btn_crear').click(function(event){
		fFacultadLeerTodos($('#sel_idFacultad_crear'));
		$('#dCrear').dialog('open');
		event.preventDefault();
	});
});

function leerTodos() {
	$dProcesandoPeticion.dialog('open');

	var url = 'EscuelaServlet';
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
            		var $tEscuelaLista = $('#tabla_escuelaLista tbody');            		
            		$tEscuelaLista.empty();
            		
            		var tamanioLista = jsonRespuesta.escuelaLista.length;
            		
            		if(tamanioLista == 0){
            			var $tr = $('<tr/>').appendTo($tEscuelaLista);
            			var $td = $('<td/>',{
            				colspan : 5, 
            				text: 'No hay escuelas registradas.'
            			}).appendTo($tr);
            		}else{
            			var html = '';
            			for(var i=0; i < tamanioLista; i++){
            				var escuelaItem = jsonRespuesta.escuelaLista[i];
            				
            				html += '<tr>';
            				html += '	<td>';
            				html += '		<span id="txt_nombreEscuela_' + escuelaItem.idEscuela + '">' + escuelaItem.nombreEscuela + '</span>';
            				html += '		<input type="hidden" id="txt_idEscuela2_' + escuelaItem.idEscuela + '" value="' + escuelaItem.idEscuela2 + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<span id="txt_abreviaturaEscuela_' + escuelaItem.idEscuela + '">' + escuelaItem.abreviatura + '</span>';
            				html += '		<input type="hidden" id="txt_abreviaturaEscuela_' + escuelaItem.idEscuela + '" value="' + escuelaItem.abreviatura + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<span id="txt_nombreFacultad_'+ escuelaItem.idEscuela + '">' + escuelaItem.nombreFacultad + '</span>';
            				html += '		<input type="hidden" id="txt_idFacultad_'+ escuelaItem.idEscuela + '" value="' + escuelaItem.idFacultad + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<span id="txt_descripcion_'+ escuelaItem.idEscuela + '">' + escuelaItem.descripcion + '</span>';
            				html += '		<input type="hidden" id="txt_descripcion_'+ escuelaItem.idEscuela + '" value="' + escuelaItem.descripcion + '"/>';
            				html += '	</td>';
            				html += '	<td>';
            				html += '		<input type="button" id="btn_actualizar_' + escuelaItem.idEscuela + '" name="btn_actualizar" value="Editar"/>';
            				html += '		<input type="button" id="btn_borrar_' + escuelaItem.idEscuela + '" name="btn_borrar" value="Borrar"/>';
            				html += '		<input type="button" id="btn_masDetalles_' + escuelaItem.idEscuela + '" name="btn_masDetalles" value="Mas detalles"/>';
            				html += '	</td>';
            				html += '</tr>';
            			}
            			
            			$tEscuelaLista.append(html);
            			
            			$('input[name=btn_actualizar]').bind('click', function() {
            				var $this = $(this);
            				var id = $this.attr('id');
            				var idEscuela = id.split('_')[2];
            				
            				$('#lbl_idEscuela_actualizar').text(idEscuela);
            				$('#txt_idEscuela_actualizar').val(idEscuela);
            				$('#txt_idEscuela2_actualizar').val($('#txt_idEscuela2_'+idEscuela).val());
            				$('#txt_nombreEscuela_actualizar').val($('#txt_nombreEscuela_'+idEscuela).text());
            				$('#txt_abreviaturaEscuela_actualizar').val($('#txt_abreviaturaEscuela_'+idEscuela).text());
            				$('#txt_descripcion_actualizar').val($('#txt_descripcion_'+idEscuela).text());
            				
            				fFacultadLeerTodos($('#sel_idFacultad_actualizar'), function() {
            					$('#sel_idFacultad_actualizar').val($('#txt_idFacultad_' + idEscuela).val());
							});
            				$('#dActualizar').dialog('open');
						});
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

function fCrearAjax() {
	
	$dProcesandoPeticion.dialog('open');
	
	var url = 'EscuelaServlet';
	var datos = $('#form_crear').serialize() + '&accion=crear';
	
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
    				$('#dCrear').dialog('close');
    				alertify.success('Escuela creada.');
            		leerTodos();
            	}else if(auditoria.codRes > 0){
            		alertify.warning('No se ha creado escuela: <br>' 
            				+ auditoria.msjRes);
            	}else{
            		alertify.error('Error al crear escuela: <br>' 
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

function fFacultadLeerTodos($sel_facultad, callback){
	
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
            		$sel_facultad.empty();
            		
            		var tamFacultadLista = jsonRespuesta.facultadLista.length;            		
            		var html = '<option value="">Seleccione facultad</option>';            		
            		if(tamFacultadLista == 0){
            			html = '<option value="">No hay facultad registrada</option>';
            			$sel_facultad.html(html);
            			
            		}else{
            			for(var i=0; i < tamFacultadLista; i++){
            				var facultadItem = jsonRespuesta.facultadLista[i];            				
            				html += '<option value="'+ facultadItem.idFacultad + '">' + facultadItem.nombreFacultad + '</option>';            				
            			}
            			
            			$sel_facultad.html(html);
            			
            			if(callback != null){
            				callback();
            			}
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

function fActualizar(){
	$dProcesandoPeticion.dialog('open');
	
	var url = 'EscuelaServlet';
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
    				$('#dActualizar').dialog('close');
    				alertify.success('Escuela actualizada.');
            		leerTodos();
            	}else if(auditoria.codRes > 0){
            		alertify.warning('No se ha actualizado escuela: <br>' 
            				+ auditoria.msjRes);
            	}else{
            		alertify.error('Error al actualizar escuela: <br>' 
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

