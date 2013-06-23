var ajax;
function invocarEnvio(){
	if(window.XMLHttpRequest) {
		ajax = new XMLHttpRequest();
	}
	else if(window.ActiveXObject) {
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	ajax.onreadystatechange=volverAjax;
	ajax.open("post","ServletControlador?accion=invocarEnvioEmail",true);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.send("");
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
			alert('Resultado del envio:'+respuesta);
		}
	}
}