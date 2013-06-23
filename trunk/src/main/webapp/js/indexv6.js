var ajax;
var esIE = /*@cc_on!@*/false;
jQuery(document).ready(function() {
	if(esIE==false){
		jQuery(".pie_pagina_izquierda").hide();
		jQuery(".pie_pagina_centro").hide();
		jQuery("#mense[title]").tooltip({
			position: "bottom left",
			opacity: 0.9,
			tipClass: "tooltip_down"/*,
			effect: "fade",
			fadeOutSpeed: 100*/
		});
	}else{
		jQuery("#mense[title]").tooltip({
			position: "bottom left",
			opacity: 0.9,
			tipClass: "tooltip_down"
		});
	}
	jQuery('#botonsearch').click(function(){
		jQuery('#isSearch').val("true");	
	});
	jQuery('#nombre').autocomplete(
		"ServletControlador?accion=gestorAjax&subAccion=buscar&patron="+jQuery('#nombre').val(),
		{
			scroll: true,
			scrollHeight: 300,
			formatItem: function(data, i, n, value) {
			datatrim = data;
			nombretrim = jQuery('#nombre').val();
			if(value.indexOf("~")!=-1){
				dev="<div align='center' class='semi_line'></div>";
				dev+="<div class='cabecera_resultados_ajax'><div class='diez_con_margen_derecho'><img src='documentos/" + value.split("~")[0]+"'/></div><div class='setenta_y_cinco'>"+value.split("~")[1]+"<BR/>"+value.split("~")[2]+"</div><div class='diez_alineado_dcha'>"+value.split("~")[3]+"</div></div>";
				
			}else{
				dev="<div class='cabecera_resultados_ajax'><div class='cien'>"+value.split("]")[1]+"</div></div>";
			}
			return dev;
			},
			formatResult: function(data, value) {
				//alert(data);
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
		var oSuggest = jQuery("#nombre")[0].autocompleter;
		oSuggest.findValue();
		return false;
	}
	jQuery('#nombre').val(jQuery('#patron_recibido').val());
	/*if(!esIE){
		jQuery('.capa_resultados_borde').hide();
		jQuery('.capa_resultados_borde').fadeIn(500);
	}*/
});

function cargarAnuncio(idAnuncio){
	document.formulario.action = "ServletControlador?accion=verAnuncio&idAnuncio="+idAnuncio;
	document.formulario.submit();
}

function cargarFiltro(filtro,idFiltro,asc){
	if(window.XMLHttpRequest) {
		ajax = new XMLHttpRequest();
	}
	else if(window.ActiveXObject) {
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	ajax.onreadystatechange=volverCargarFiltro;
	ajax.open("post","ServletControlador?accion=gestorAjax&subAccion=cambiar"+filtro+"&idFiltro="+idFiltro+"&asc="+asc,true);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.send("");
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

function paginar(numPagina){
	$('#isSearch').val("true");	
	$('#pagina').val(numPagina);
	document.formulario.submit();
}
