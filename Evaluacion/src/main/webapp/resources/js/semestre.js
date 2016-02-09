var $dProcesandoPeticion = $('#dProcesandoPeticion');

$(document).ready(function() {
	document.title = 'Semestre';
	
	$('#btn_crear').click(function(event){
		$('#div_semestre_crear').dialog('open');
		event.preventDefault();
	});
	
	$('#div_semestre_crear').dialog({
        modal: true,
        autoOpen: false,
        close: function( event, ui ) {
        	$('#form_crear .limpiar').val('').text('');
        	$('#form_actualizar .limpiar').val('').text('');
        },
        buttons:[
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
                 
        ],
        dialogClass: 'pequenio',
        height: 400,
        width: 400,
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
	
	$("#form_crear").validate({
		rules:{
			txt_anio_crear: {
				required: true,
				digits: true,
				minlength: 4,
				maxlength: 4
			},
			txt_periodo_crear: {
				required: true,
				digits: true,
				minlength: 1,
				maxlength: 1
			},
			txt_fechaInicio_crear: 'required',
			txt_fechaFin_crear: 'required'
		},
		errorLabelContainer: $("#form_crear div.div_error"),
		submitHandler: function() {
			f_crear();
		},
		messages: {
			txt_anio_crear: {
				required: 'Ingrese <b>año</b>',
				digits: 'Año debe ser numérico',
				minlength: 'Año debe ser {0} caracter(es).',
				maxlength: 'Año debe ser {0} caracter(es).'
			},
			txt_periodo_crear: {
				required: 'Ingrese <b>periodo</b>',
				digits: 'Periodo debe ser numérico',
				minlength: 'Periodo debe ser {0} caracter(es).',
				maxlength: 'Periodo debe ser {0} caracter(es).'
			},
			txt_fechaInicio_crear: 'Ingrese <b>fecha inicio</b> de periodo',
			txt_fechaFin_crear: 'Ingrese <b>fecha fin</b> de periodo'
		}
	});	

    $('#txt_fechaInicio_crear').datepicker({
    	changeMonth: true,
        changeYear: true,
        numberOfMonths: 3,
        onClose: function( selectedDate ) {
        	$('#txt_fechaFin_crear').datepicker('option', 'minDate', selectedDate)
        }
     });
    
    $('#txt_fechaFin_crear').datepicker({
        changeMonth: true,
        changeYear: true,
        numberOfMonths: 3,
        onClose: function( selectedDate ) {
        	$('#txt_fechaInicio_crear').datepicker('option', 'maxDate', selectedDate)
        }
    });
});

function f_crear() {
	var $form = $('#form_crear');
	
	var url = $form.attr('action');
	var datos = $form.serialize() + '&accion=crear';
	
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
    				$('#div_semestre_crear').dialog('close');
    				alertify.success('Semestre creado.');
            		leerTodos();
            	}else if(auditoria.codRes > 0){
            		alertify.warning('No se ha creado semestre: <br>' 
            				+ auditoria.msjRes);
            	}else{
            		alertify.error('Error al crear semestre: <br>' 
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

function leerTodos() {
	fNoImplementado('leer todos los semestres.');
};