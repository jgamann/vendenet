var desplazadoCorto=false;
var desplazadoLargo=false;
var esIE = /*@cc_on!@*/false;
$(document).ready(function() {
	if(esIE==false){
		jQuery(".pie_pagina_izquierda").hide();
		jQuery(".pie_pagina_centro").hide();
	}
	$('#nombre').autocomplete(
		"ServletControlador?accion=gestorAjax&subAccion=buscar&patron="+$('#nombre').val(),
		{
			scroll: true,
			scrollHeight: 300,
			formatItem: function(data, i, n, value) {
			datatrim = data;
			nombretrim = $('#nombre').val();
			if(value.indexOf("~")!=-1){
				dev="<div align='center' class='semi_line'></div>";
				dev+="<div class='cabecera_resultados_ajax'><div class='diez_con_margen_derecho'><img src='documentos/" + value.split("~")[0]+"'/></div><div class='setenta_y_cinco'>"+value.split("~")[1]+"<BR/>"+value.split("~")[2]+"</div><div class='diez_alineado_dcha'>"+value.split("~")[3]+"</div></div>";
				
			}else{
				dev="<div class='cabecera_resultados_ajax'><div class='cien'>"+value.split("]")[1]+"</div></div>";
			}
			return dev;
			},
			formatResult: function(data, value) {
				if(value.indexOf("~")!=-1){
					return value.split("~")[1];
				}else{
					 return value.split("]")[1];
				}
			},
			selectFirst: false
		}
	);
	function findValueCallback(event, data, formatted) {
	}
	function selectItemAnuncio(li) {
		findValue(li);
	}

	function findValue(li) {
		if( li == null ) return alert("No match!");
		// if coming from an AJAX call, let's use the CityId as the value
		if( !!li.extra ) var sValue = li.extra[0];
		// otherwise, let's just display the value in the text box
		else var sValue = li.selectValue;
		//document.InicioWeb.ultimaEmpresaElegida.value=sValue;
	}

	function lookupAjax(){
		var oSuggest = $("#nombre")[0].autocompleter;
		oSuggest.findValue();
		return false;
	}
	$('#nombre').val($('#patron_recibido').val());

	
	$('[id^=capaOculta]').each(function() {
		$(this).hide();
	});
	$('#txtPregunta').keyup( function(i) {
		var elem = $(this);
		var text = elem.val();
		var limit = 300;
		var textlength = text.length;
		if( textlength > limit)
		{
			elem.val(text.substr(0,limit));
			textlength = elem.val().length;
		}
		$('#caracteres').text((limit-textlength)+" caracteres");
	});
});
function mostrar(capa) {
	$('.' + capa).slideDown(150, sumarAltura(capa));
}
function sumarAltura(capa) {
//	if (capa == 'campo_campo_contacto_tfno_anuncio_b') {
//		if(desplazadoCorto==false){
//			var val=$('.capa_ficha_anuncio').height();
//			var sumador = $('.campo_campo_contacto_tfno_anuncio_b').height();
////			$('.capa_ficha_anuncio').css('min-height', val+sumador);
//			desplazadoCorto=true;
//		}
//	}
//	if (capa == 'campo_campo_contacto_mail_anuncio_b') {
//		if(desplazadoLargo==false){
//			var val=$('.capa_ficha_anuncio').height();
//			var sumador = $('.campo_campo_contacto_mail_anuncio_b').height();
////			$('.capa_ficha_anuncio').css('min-height', val+sumador);
//			desplazadoLargo=true;
//		}
//	}
}

function enviarConsulta() {
	if (validar_entradas()) {
		$('#formulario').attr("action",
				"ServletControlador?accion=enviarConsulta");
		$('#formulario').submit();
	}
}

function validar_entradas() {
	todoOk = true;
	if($('#check_condiciones').attr('checked')==false){
		$("label[for=check_condiciones]").css('color','red');
		$("label[for=check_condiciones] a").css('color','red');
		todoOk=false;
	}else{
		$("label[for=check_condiciones]").css('color','#666');
		$("label[for=check_condiciones] a").css('color','#666');
		$("label[for=check_condiciones] a").css('text-decoration','none');
	}
	if (validarCampo_('txtNombre') == false)
		todoOk = false;
	if (validarCampoEmail_('txtEmail') == false)
		todoOk = false;
	if (validarTelefono_('txtTfno') == false)
		todoOk = false;
	if (validarCampo_('txtPregunta') == false)
		todoOk = false;
	return todoOk;
}

function validarCampo_(item) {
	if ($('#' + item).val() == "") {
		$("label[for=" + item + "]").css('color', '#ff0000');
		return false;
	} else {
		$("label[for=" + item + "]").css('color', '#666666');
	}
	return true;
}

function validarCampoEmail_(item) {
	if (validarEmail($('#' + item).val()) == false) {
		$("label[for=" + item + "]").css('color', '#ff0000');
		return false;
	} else {
		$("label[for=" + item + "]").css('color', '#666666');
	}
	return true;
}
function validarTelefono_(item) {
	if ($('#' + item).val().length < 9) {
		$("label[for=" + item + "]").css('color', '#ff0000');
		return false;
	} else {
		return validarCampoNumericoEntero_(item);
	}
}
function validarCampoNumericoEntero_(item) {
	if (($('#' + item).val().indexOf(",") != -1)
			|| ($('#' + item).val().indexOf(".") != -1)) {
		$("label[for=" + item + "]").css('color', '#ff0000');
		return false;
	} else {
		return validarCampoNumerico_(item);
	}
}
function validarCampoNumerico_(item) {
	if ($('#' + item).val() == "") {
		$("label[for=" + item + "]").css('color', '#ff0000');
		return false;
	} else {
		$('#' + item).val($('#' + item).val().replace(",", "."));
		if (isNaN($('#' + item).val())) {
			$("label[for=" + item + "]").css('color', '#ff0000');
			return false;
		} else {
			$("label[for=" + item + "]").css('color', '#666666');
		}
	}
	return true;
}
function cargarAnuncio(idAnuncio){
	$('#idAnuncioFicha').val(idAnuncio);
	document.formulario.action = "ServletControlador?accion=verAnuncio&desde=fichaAnuncio";
	document.formulario.submit();
}

function compartirRedSocial(redSocial,idAnuncio){
	url=$('#URL_'+redSocial).val();
	urlVendenet=$('#URL_VENDENET_VERANUNCIO').val();
	window.open(url+urlVendenet+idAnuncio);
}
