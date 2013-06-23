var ajax;
var esIE = /*@cc_on!@*/false;
var txtopiniones = new Array(6);
txtopiniones[0]="";
txtopiniones[1]="Muy mala";
txtopiniones[2]="Mala";
txtopiniones[3]="Aceptable";
txtopiniones[4]="Buena";
txtopiniones[5]="Muy buena";
jQuery(document).ready(function() {
	$('.jScrollbar5').jScrollbar();
	$('.rojo').hide();
	if(esIE==false){
		jQuery(".pie_pagina_izquierda").hide();
		jQuery(".pie_pagina_centro").hide();
	}
	jQuery(".estrellitas img").click(function(){
		$('#estrellita').val($(this).attr('name'));
		refrescar();
	});
	jQuery(".estrellitas img").mouseenter(function() {
		var id = $(this).attr('name');
		var estrellita="yellow_star.png";
		if(parseInt(id)+1<3)estrellita="red_star.png";
		else if(parseInt(id)+1>4)estrellita="green_star.png"; 
		for(i=0;i<5;i++){
			$('#estrella_'+i).attr('src','imagenes/grey_star.png');
		}
		for(i=0;i<id;i++){
			$('#estrella_'+i).attr('src','imagenes/'+estrellita);
		}
		$(this).attr('src','imagenes/'+estrellita);
		var pos = parseInt(id) + parseInt(1);
		$('#label_opinion').html(txtopiniones[pos]);
	});
	
	jQuery(".estrellitas img").mouseleave(function() {
		refrescar();
	});
});

function refrescar(){
	elegida=$('#estrellita').val();
	var estrellita="yellow_star.png";
	if(parseInt(elegida)+1<3)estrellita="red_star.png";
	else if(parseInt(elegida)+1>4)estrellita="green_star.png"; 
	for(i=0;i<5;i++){
		$('#estrella_'+i).attr('src','imagenes/grey_star.png');
	}
	for(i=0;i<=elegida;i++){
		$('#estrella_'+i).attr('src','imagenes/'+estrellita);
	}
	var pos = parseInt(elegida) + parseInt(1);
	$('#label_opinion').html(txtopiniones[pos]);
}
function enviarOpinion(){
	if(validar_entradas()){
		$('.rojo').hide();
		opinar();
	}else{
		$('.rojo').show();
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
	if(validarCampoValoracion('valoracion')==false)todoOk=false;
	if(validarCampo('nombre')==false)todoOk=false;
	if(validarCampoEmail('email')==false)todoOk=false;
	if(validarCampo('cuerpo', 4000)==false)todoOk=false;
	return todoOk;
}
function opinar(){
	
	if(window.XMLHttpRequest) {
		ajax = new XMLHttpRequest();
	}
	else if(window.ActiveXObject) {
		ajax = new ActiveXObject("Microsoft.XMLHTTP");
	}
	ajax.onreadystatechange=volverOpinar;
	ajax.open("post","ServletControlador?accion=opinarAjax&valoracion="+$('#estrellita').val()+
				"&nombre="+$('#formulario_nombre').val()+
					"&email="+$('#formulario_email').val()+
						"&texto="+$('#formulario_cuerpo').val()+
							"&idAnuncio="+$('#idAnuncio').val(),true);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.send("");
}

function volverOpinar(){
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
				document.formulario.action = "ServletControlador?accion=opinionRegistrada";
				document.formulario.submit();
			}else{
				alert(respuesta);
			}
		}
	}
}
