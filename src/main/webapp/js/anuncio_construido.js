var ajax;
$(document).ready(function(){
	$('.rojo').hide();
});
function guardarAnuncio(){
	if(validar_entradas()){
		$('#formulario').attr("action","ServletControlador?accion=confirmarAnuncio");
		$('#formulario').submit();
	}else{
		//alert('Revise por favor los campos en rojo');
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
	if(validarCampo('password_1')==false)todoOk=false;
	if(validarCampo('password_2')==false)todoOk=false;
	if(todoOk==true){
		if(!camposIguales('formulario_password_1','formulario_password_2'))todoOk=false;
	}
	return todoOk;
}

function validarCampo(item){
	if($('#formulario_'+item).val()==""){
		$("label[for=formulario_"+item+"]").css('color','red');
		return false;
	}else{
		$("label[for=formulario_"+item+"]").css('color','#ffffff');
	}
	return true;
}

function camposIguales(item1,item2){
	if($('#'+item1).val()==$('#'+item2).val()){
		$("label[for="+item1+"]").css('color','#ffffff');
		$("label[for="+item2+"]").css('color','#ffffff');
		return true;
	}
	else {
		$("label[for="+item1+"]").css('color','red');
		$("label[for="+item2+"]").css('color','red');
		return false;
	}
}

function condiciones(){
	window.open("ServletControlador?accion=condiciones");
}