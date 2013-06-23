function subirArchivo(){
	ruta=document.CargadorArchivos.cargador.value;
	//if(validarExtension(ruta)){
		cambiarCapas();
		document.CargadorArchivos.action = "ServletControlador?accion=gestorArchivos";
		document.CargadorArchivos.submit();
	//}
}
function subirArchivoFondo(){
	ruta=document.CargadorArchivos.cargador.value;
	//if(validarExtension(ruta)){
		//cambiarCapas();
		document.CargadorArchivos.action = "ServletControlador?accion=gestorArchivos&fondo=true";
		document.CargadorArchivos.submit();
	//}
}
function validarExtension(texto){
	esIE='\v'=='v';
	respuesta=true;
	var tokens = texto.tokenize(".",
                               " ",
                               true);
	extension="";
	Reserv = "bmpgifjpgjpegpnggif";
	for(var i=0; i<tokens.length; i++){
		extension=tokens[i];
	}
	if (Reserv.indexOf(extension.toLowerCase())==-1){
		respuesta = false;
		if(esIE){
	    	var link=document.getElementById('linkArchivo');
	    	link.childNodes[0].nodeValue=".";
	    	link.href="javascript:verArchivo('.');";
	    	
	    }else{
	    	var link=document.getElementById('linkArchivo');
	    	link.removeChild(link.childNodes[0])
			var nodoTexto = document.createTextNode(".");
			link.appendChild(nodoTexto);
			link.setAttribute("href","javascript:verArchivo('.');");
	    }
	    alert('Selecciona una archivo del tipo\n\rjpg\n\rjpeg\n\rgif\n\rpng\n\rbmp');
	}
	return respuesta;
}
OCULTO="none";
VISIBLE="block";
function cambiarCapas() {
  /*if(document.getElementById('capaCargador').style.display==OCULTO)
  document.getElementById('capaCargador').style.display=VISIBLE;
  else document.getElementById('capaCargador').style.display=OCULTO;
  if(document.getElementById('capaImagen').style.display==OCULTO)
  document.getElementById('capaImagen').style.display=VISIBLE;
  else document.getElementById('capaImagen').style.display=OCULTO;  */
}
function verArchivo(url){
	if(url!=".")window.open("documentos/"+url, "");
}
