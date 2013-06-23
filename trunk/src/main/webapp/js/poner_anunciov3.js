var ajax;
var esIE = /*@cc_on!@*/false;
$(document).ready(function(){
		if(esIE==false){
			jQuery(".pie_pagina_izquierda").hide();
			jQuery(".pie_pagina_centro").hide();
		}
		$('.rojo').hide();
		$("iframe").each(function(){
			var numFrame = $(this).attr("name");
			$(this).load(function(){
				$("iframe").each(function(){
					$(this).contents().find("input").attr('disabled','');
				});
				if ($(this).contents().find("#linkArchivo").text() != "."){
					$('#foto'+numFrame+'_selected').val($(this).contents().find("#linkArchivo").text());
					mostrarFotoSubida(numFrame,$(this).contents().find("#linkArchivo").text());
				} else {
					if ($(this).contents().find('#mensaje').val() != "null")
						alert($(this).contents().find('#mensaje').val());
						$('#foto'+numFrame+'_selected').val(".");
						mostrarSinFoto(numFrame);
					}
				$(this).contents().find("input").attr('disabled','');
				$(this).contents().find("input").change(
						function(){
							mostrarSubiendo(numFrame);
						});
			});
		});
	});

function mostrarSubiendo(numFrame) {
	$('input').attr('disabled', 'disabled');
	$("iframe").each(function() {
		$(this).contents().find("input").attr('disabled', 'disabled');
	});
	$('#img_loading_' + numFrame).attr( {
		src : "imagenes/100x75_loading.gif",
		title : "jQuery",
		alt : "jQuery Logo"
	});
}
function mostrarSinFoto(numFrame) {
	$('input').attr('disabled', '');
	$('#img_loading_' + numFrame).attr( {
		src : "documentos/p_0.jpg",
		title : "Sin foto",
		alt : "Sin foto"
	});
}
function mostrarFotoSubida(numFrame, ruta) {
	$('input').attr('disabled', '');
	$('#img_loading_' + numFrame).attr( {
		src : "documentos/" + ruta,
		title : "Fotografia " + numFrame,
		alt : "Fotografia " + numFrame
	});
}
function seleccion(item,idItem, textoItem){
	expandir();
	$('#cabecera_menu_'+item).text(textoItem);
	$('#cabecera_menu_'+item).css('color','#ffffff');
	$('#'+item+'_selected').val(idItem);
}
function expandir(){
	var menuActual=$(this).next('div.scroll');
	$(menuActual).slideToggle(350);
	if(ultimoMenuDesplegado!=null){
		if(menuActual.attr('id')!=ultimoMenuDesplegado.attr('id')){
			$("div.scroll").each(function(){
				if($(this).attr('id')==ultimoMenuDesplegado.attr('id')){
					$(this).slideToggle(350);
				}
	    	});
		}
	}
	if(ultimoMenuDesplegado!=null){
		if(menuActual.attr('id')==ultimoMenuDesplegado.attr('id')){
			ultimoMenuDesplegado=null;
		}else{
			ultimoMenuDesplegado=menuActual;
		}
	}else{
		ultimoMenuDesplegado=menuActual;
	}
}

function guardarAnuncio(){
	if(validar_entradas()){
		$('.rojo').hide();
		$('#formulario').attr("action","ServletControlador?accion=validarAnuncio");
		$('#formulario').submit();
	}else{
		$('.rojo').show();
	}
}

function modificarAnuncio(){
	
	if(validar_entradas()){
//		$('.rojo').hide();
		$('#formulario').attr("action","ServletControlador?accion=confirmarModificacionAnuncio");
		$('#formulario').submit();
	}else{
//		$('.rojo').show();
	}
}

function volverCargarFiltro(){
	// Comprobamos si la peticion se ha completado (estado 4)
	if( ajax.readyState == 4 )
	{
		// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
		if( ajax.status == 200 )
		{
			//var respuesta=ajax.responseText;			
			var documento_xml = ajax.responseXML;			
			var root = documento_xml.getElementsByTagName("xml")[0];
			var respuesta = root.getElementsByTagName("respuesta")[0].firstChild.nodeValue;
			if (respuesta=='OK'){
				document.formulario.action = "ServletControlador?accion=inicio";
				document.formulario.submit();
			}else{
				alert(respuesta);
			}
		}
	}
}


function validar_entradas(){
	todoOk=true;
	if($('#check_condiciones').attr('checked')==false){
		$("label[for=check_condiciones]").css('color','red');
		$("label[for=check_condiciones] a").css('color','red');
		todoOk=false;
	}else{
		$("label[for=check_condiciones]").css('color','#666');
		$("label[for=check_condiciones] a").css('color','#666');
		$("label[for=check_condiciones] a").css('text-decoration','none');
	}
	if(validarOpcionMenu('provincia')==false)todoOk=false;
	if(validarOpcionMenu('categoria')==false)todoOk=false;
	if(validarOpcionMenu('tipo_vendedor')==false)todoOk=false;
	if(validarOpcionMenu('tipo_anuncio')==false)todoOk=false;
	if(validarCampo('nombre')==false)todoOk=false;
	//if(validarCampo('email')==false)todoOk=false;
	if(validarCampoEmail('email')==false)todoOk=false;
	if(validarTelefono('tfno')==false)todoOk=false;
	if(validarCampo('titulo')==false)todoOk=false;
	if(validarCampo('cuerpo', 4000)==false)todoOk=false;
	if(validarCampoNumerico('precio')==false)todoOk=false;
	return todoOk;
}
