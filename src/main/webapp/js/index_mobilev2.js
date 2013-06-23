var ajax;
jQuery(document).ready(function() {
	jQuery("#mense[title]").tooltip({
		position: "bottom left",
		opacity: 0.9,
		tipClass: "tooltip_down"/*,
		effect: "fade",
		fadeOutSpeed: 100*/
	});
	jQuery('#botonsearch').click(function(){
		jQuery('#isSearch').val("true");	
	});
	jQuery('.flechita[title]').tooltip();
	jQuery('#nombre').val(jQuery('#patron_recibido').val());
	/*jQuery('.capa_resultados_borde').hide();
	jQuery('.capa_resultados_borde').fadeIn(500);*/
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
	$('#pagina').val(numPagina);
	document.formulario.submit();
}