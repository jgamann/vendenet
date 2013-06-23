var ajax;

function seleccionarAnuncio(fila, idAnuncio){
	/*if($('#ultimoSeleccionado').val()!=""){
		if($('#ultimoSeleccionado').val() % 2 == 0){
			document.getElementById('fila_'+$('#ultimoSeleccionado').val()).style.backgroundColor="#555";
			
		}else{
			document.getElementById('fila_'+$('#ultimoSeleccionado').val()).style.backgroundColor="#222";
		}
	}*/
	//alert($('#grupoSeleccionados').val());
	if($('#grupoSeleccionados').val().indexOf(";"+idAnuncio+";") == -1){
		document.getElementById('fila_'+fila).style.backgroundColor="blue";
		$('#grupoSeleccionados').val($('#grupoSeleccionados').val()+";"+idAnuncio+";");
	}else{
		if(fila % 2 == 0)document.getElementById('fila_'+fila).style.backgroundColor="#555";
		else document.getElementById('fila_'+fila).style.backgroundColor="#222";
		posicionIdAnucio = $('#grupoSeleccionados').val().lastIndexOf(";"+idAnuncio+";");
		
		strIdAnuncio=";"+idAnuncio+";";
		pre = $('#grupoSeleccionados').val()
				.substring(0,
						posicionIdAnucio);
		post = $('#grupoSeleccionados').val().substring(posicionIdAnucio+(strIdAnuncio.length),$('#grupoSeleccionados').val().length);
		$('#grupoSeleccionados').val(
				pre + post);
	}
}

function abrirAnuncio(idAnuncio){
	window.open("ServletControlador?accion=verAnuncioIntranet&idAnuncio="+idAnuncio, "");
}

function aceptar(){
	if($('#grupoSeleccionados').val()!=""){
		if(window.XMLHttpRequest) {
			ajax = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			ajax = new ActiveXObject("Microsoft.XMLHTTP");
		}
		ajax.onreadystatechange=volverAjax;
		ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=activarAnuncio&idsAnuncio="+$('#grupoSeleccionados').val(),true);
		ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		ajax.send("");
	}else alert('Elije uno');
}
function despublicar(){
	if($('#grupoSeleccionados').val()!=""){
		if(window.XMLHttpRequest) {
			ajax = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			ajax = new ActiveXObject("Microsoft.XMLHTTP");
		}
		ajax.onreadystatechange=volverAjax;
		ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=despublicarAnuncio&idsAnuncio="+$('#grupoSeleccionados').val(),true);
		ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		ajax.send("");
	}else alert('Elije uno');
}
function denegarKo(){
	if($('#grupoSeleccionados').val()!=""){
		if(window.XMLHttpRequest) {
			ajax = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			ajax = new ActiveXObject("Microsoft.XMLHTTP");
		}
		ajax.onreadystatechange=volverAjax;
		ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=denegarAnuncio&idsAnuncio="+$('#grupoSeleccionados').val(),true);
		ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		ajax.send("");
	}else alert('Elije uno');
}
function denegarWrn(){
	if($('#grupoSeleccionados').val()!=""){
		if(window.XMLHttpRequest) {
			ajax = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			ajax = new ActiveXObject("Microsoft.XMLHTTP");
		}
		ajax.onreadystatechange=volverAjax;
		var resultado = prompt("Introduce el motivo de la devolucion", "");
		if(resultado!=null){
			ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=devolverAnuncio&idsAnuncio="+$('#grupoSeleccionados').val()+"&motivo="+resultado,true);
			ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			ajax.send("");
		}
	}else alert('Elije uno');
}
function volverAjax(){	
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
				//$('#FormIntranet').attr('action',"ServletControlador?accion=validarLoginIntranet");
				//$('#action').val("ServletControlador?accion=validarLoginIntranet");
				//$('#FormIntranet').submit();
				document.FormIntranet.action = "ServletControlador?accion=buscarIntranet";
				document.FormIntranet.submit();
			}else{
				alert(respuesta);
			}
		}
	}
}
function cerrarSesion(){
	if(window.XMLHttpRequest){
		ajax = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	ajax.onreadystatechange=volverAjax;
	ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=cerrarSesion",true);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.send("");
}

function estadisticasWeb(){	
	document.FormIntranet.action = "ServletControlador?accion=estadisticasIntranet";
	document.FormIntranet.submit();
}

function buscarIntranet(){	
	document.FormIntranet.action = "ServletControlador?accion=buscarIntranet";
	document.FormIntranet.submit();
}

function historicoEmails(){	
	document.FormIntranet.action = "ServletControlador?accion=historicoEmails";
	document.FormIntranet.submit();
}

function historicoBajas(){	
	document.FormIntranet.action = "ServletControlador?accion=historicoBorrados";
	document.FormIntranet.submit();
}

function historicoBusquedas(){	
	document.FormIntranet.action = "ServletControlador?accion=historicoBusquedas";
	document.FormIntranet.submit();
}

function ciudades(){	
	document.FormIntranet.action = "ServletControlador?accion=ciudadesIntranet";
	document.FormIntranet.submit();
}

function verEmail(id){
//	document.getElementById('previsualizado_'+id).style.display=VISIBLE;
	$('#previsualizado_'+id).slideToggle(250);
}
function ocultarTodos(){
	$('[id^=previsualizado_]').each(function() {
		$(this).hide();
	});
}