$(document).ready(function() {
	$('#dProcesandoPeticion').dialog({
        modal: true,
        autoOpen: false,
        closeOnEscape: false,
        dialogClass: 'no-titlebar',
        draggable: false,
        height: 50,
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
	
	//Fixed para menú
	var msie6 = $.browser == 'msie' && $.browser.version < 7;
    if (!msie6) {
        var top = $('#dMenu').offset().top - parseFloat($('#dMenu').css('margin-top').replace(/auto/, 0));
        $(window).scroll(function(event) {
            // what the y position of the scroll is
            var y = $(this).scrollTop();
            
            // whether that's below the form
            if ((y - 35) >= top) {
                // if so, ad the fixed class
                $('#dMenu').addClass('menu-fixed');
            } else {
                // otherwise remove it
                $('#dMenu').removeClass('menu-fixed');
            }
        });
    }
    
    //mensajes de error al ejecutar ajax.
	$.ajaxSetup({
        error: function( jqXHR, textStatus, errorThrown ) {
        	
        	if (jqXHR.status == 404){
        		return;
        	}
        	
        	if (jqXHR.status === 0) {
        		alertify.error('No conectado: Verificar red.');
            } else if (jqXHR.status == 500) {
            	alertify.error('Error interno del servidor [500].');
            } else if (textStatus === 'parsererror') {
            	alertify.error('Solicitud de parseo JSON falló.');
            } else if (textStatus === 'timeout') {
            	alertify.error('Error de tiempo de espera.');
            } else if (textStatus === 'abort') {
            	alertify.error('Petición de ajax abortado.');
            } else {
            	alertify.error('Error desconocido: Estado=' + jqXHR.status + ', textStatus=' + textStatus + ', errorThrown=' + errorThrown);
           }

        }

    });	
	
});

function procesarRespuesta(ajaxResponse) {
    var response;
    try {
        eval('response=' + ajaxResponse);
    } catch (ex) {    	
        response = null;
    }
    return response;
}

function fNoImplementado(nombreMetodo) {
	alertify.message('Método/opción ' + nombreMetodo + ' no implementado.');
}