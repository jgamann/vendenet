function tramitar(){
		if($('#pass').val()!=""){
			if($('#idAnuncio').val()!=""){
				modificar=$('[name=radiogestion]::checked').val();
				accion="eliminarAnuncio";
				if(modificar==1)accion="modificarAnuncio";
				if(window.XMLHttpRequest) {
					ajax = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					ajax = new ActiveXObject("Microsoft.XMLHTTP");
				}
				ajax.onreadystatechange=volverAjaxTramitarBaja;
				if(modificar==1)ajax.onreadystatechange=volverAjaxTramitarModificacion;
				ajax.open("post","ServletControlador?accion=gestorAjaxAreaGestion&subAccion="+accion+"&idAnuncio="+$('#idAnuncio').val()+"&pass="+$('#pass').val(),true);
				ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				ajax.send("");
			}
		}else alert('Introduce la contraseña');
	}

	function recordarClave(){
		if($('#idAnuncio').val()!=""){
			if(window.XMLHttpRequest) {
				ajax = new XMLHttpRequest();
			}
			else if(window.ActiveXObject) {
				ajax = new ActiveXObject("Microsoft.XMLHTTP");
			}
			ajax.onreadystatechange=volverAjax;
			ajax.open("post","ServletControlador?accion=gestorAjaxAreaGestion&subAccion=recordarClave&idAnuncio="+$('#idAnuncio').val(),true);
			ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			ajax.send("");
		}
	}
	function volverAjaxTramitarBaja(){	
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
					alert("Se ha eliminado tu anuncio");
					//Redireccionar a la pagina de inicio
					document.formulario.action = "ServletControlador?accion=inicio";
					document.formulario.submit();
				}else{
					alert(respuesta);
				}
			}
		}
	}
	
	function volverAjaxTramitarModificacion(){	
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
					//Redireccionar a la pagina de modificacion de anunncio
					document.formulario.action = "ServletControlador?accion=modificarAnuncio&idAnuncio="+$('#idAnuncio').val();
					document.formulario.submit();
				}else{
					alert(respuesta);
				}
			}
		}
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
					alert("Solicitud recibida comprueba tu correo durante los siguientes minutos.");
				}else{
					alert(respuesta);
				}
			}
		}
	}
	
	function teclapulsada(e){
		tecla = (document.all) ? e.keyCode : e.which;
		  if (tecla==13){
			  tramitar();
			  return false;
		  }
	}