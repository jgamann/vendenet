<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Provincia"%>
<%@page import="com.vendenet.negocio.entidad.CategoriaAnuncio"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.negocio.entidad.Adjunto"%>

<%@page import="com.vendenet.utilidades.UtilidadesAdjunto"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Vendenet.net - La mejor web de compraventa en internet</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<link rel="stylesheet" href="estilos/jquery.lightbox.css" type="text/css" media="screen" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.lightbox.js" type="text/javascript"></script>

<script>
		$(document).ready(function(){
			$('.lightbox').lightBox();

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
			if (capa == 'campo_campo_contacto_tfno_anuncio_b') {
				var val=$('.capa_ficha_anuncio').height();
				var sumador = $('.campo_campo_contacto_tfno_anuncio_b').height();
				$('.capa_ficha_anuncio').css('min-height', val+sumador);
			}
			if (capa == 'campo_campo_contacto_mail_anuncio_b') {
				var val=$('.capa_ficha_anuncio').height();
				var sumador = $('.campo_campo_contacto_mail_anuncio_b').height();
				$('.capa_ficha_anuncio').css('min-height', val+sumador);
			}
		}
		function aceptar(){
			if($('#ultimoSeleccionado').val()!=""){
				if(window.XMLHttpRequest) {
					ajax = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					ajax = new ActiveXObject("Microsoft.XMLHTTP");
				}
				ajax.onreadystatechange=volverAjax;
				ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=activarAnuncio&idAnuncio="+$('#ultimoSeleccionado').val(),true);
				ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				ajax.send("");
			}else alert('Elije uno');
		}
		function despublicar(){
			if($('#ultimoSeleccionado').val()!=""){
				if(window.XMLHttpRequest) {
					ajax = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					ajax = new ActiveXObject("Microsoft.XMLHTTP");
				}
				ajax.onreadystatechange=volverAjax;
				ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=despublicarAnuncio&idAnuncio="+$('#ultimoSeleccionado').val(),true);
				ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				ajax.send("");
			}else alert('Elije uno');
		}
		function denegarKo(){
			if($('#ultimoSeleccionado').val()!=""){
				if(window.XMLHttpRequest) {
					ajax = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					ajax = new ActiveXObject("Microsoft.XMLHTTP");
				}
				ajax.onreadystatechange=volverAjax;
				ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=denegarAnuncio&idAnuncio="+$('#ultimoSeleccionado').val(),true);
				ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				ajax.send("");
			}else alert('Elije uno');
		}
		function denegarWrn(){
			if($('#ultimoSeleccionado').val()!=""){
				if(window.XMLHttpRequest) {
					ajax = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					ajax = new ActiveXObject("Microsoft.XMLHTTP");
				}
				ajax.onreadystatechange=volverAjax;

				var resultado = prompt("Introduce el motivo de la devolucion", "");
				if(resultado!=null){
					ajax.open("post","ServletControlador?accion=gestorAjaxIntranet&subAccion=devolverAnuncio&idsAnuncio="+$('#ultimoSeleccionado').val()+"&motivo="+resultado,true);
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
						alert('Hecho, cierra la ventana y vuelva a buscar...');
						
					}else{
						alert(respuesta);
					}
				}
			}
		}
		

</script>

<script type="text/javascript" src="js/multi-slide.js"></script>
<link href="estilos/estilos_intranetv4.css" rel="stylesheet" type="text/css" />
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<link href="estilos/multi-slidev1.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="estilos/stunicholls.css" />
<script>
		$(document).ready(function(){
			$('#images img').each(function(){
				
				});
		});
</script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
Anuncio anuncio=(Anuncio)hResultados.get("anuncio");
int totalvisitas=(Integer)hResultados.get(TextConstant.KEY_TOTAL_VISITAS);
int totalanuncios=(Integer)hResultados.get(TextConstant.KEY_TOTAL_ANUNCIOS);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form name="FormIntranet" action="ServletControlador?accion=buscarIntranet" method="post">
<input type="hidden" name="accion" value="buscarIntranet"/>
<input type="hidden" id="ultimoSeleccionado" value="<%=anuncio.getId()%>"/>
<div id="contenedor">
<div class="line"></div>
<div class="capa_cabecera_anuncio">
<a href="<%=TextConstant.URL_INICIO%>" title="volver a la p&aacute;gina inicial" class="enlace_imagen">
<%=UtilidadesTexto.obtenerLogo()%>
</a>
<input align="middle" name="nombre" type="text" class="campo_texto" id="nombre"/>
<img id="mense" src="imagenes/info.png" class="class_boton_buscar" title="<%=TextConstant.MENSAJE_AYUDA_BUSCAR%>"/>
<button name="boton" type="button" class="class_boton_buscar"><img src="imagenes/buscar.png" alt="Buscar"/></button>
<div id="ponanuncio_" onclick="window.location.href='ServletControlador?accion=ponerAnuncio'">
	<span class="label_gris">PON TU ANUNCIO</span>GRATIS
	<span style="letter-spacing: 0px; font-size:0.6em; color:#ffffff"><%=new StringBuffer(TextConstant.SPACE).append(TextConstant.TOTAL_VISITAS).append(TextConstant.SPACE).append(totalvisitas).toString()%></span>
</div>
<div class="campo_texto_anuncio">
</div>
</div>
<div class="cuerpo_anuncio">
	<div class="cuarenta"><%=UtilidadesTexto.obtenerFichaAnuncio(anuncio)%></div>
	<div id="slideshow">
		<div id="thumbs">
			<div id="thumbHolder">
			<%Iterator itFotos=anuncio.getAdjuntos().iterator();
			while(itFotos.hasNext()){
				Adjunto adjunto = (Adjunto)itFotos.next();
				%>
				<img src="documentos/<%=UtilidadesAdjunto.obtenerCarpetaFecha(adjunto.getPath())+TextConstant.PRE_FOTO_ICONO+UtilidadesAdjunto.quitarCarpetaFecha(adjunto.getPath())%>" alt="" />
				<%
			}
			%>
			</div>
		</div>
		<div id="images">
		<%itFotos=anuncio.getAdjuntos().iterator();
			if((itFotos!=null)&&(!itFotos.hasNext())){//Si no tiene foto, se visualiza "sin foto"
				%>
				<img src="documentos/<%=TextConstant.SIN_FOTO%>" alt="Sin Fotograf&iacute;a" />
				<%
			}
			while(itFotos.hasNext()){
				Adjunto adjunto = (Adjunto)itFotos.next();
				%>
				
				<a href="documentos/<%=UtilidadesAdjunto.obtenerCarpetaFecha(adjunto.getPath())+TextConstant.PRE_FOTO_MAX+UtilidadesAdjunto.quitarCarpetaFecha(adjunto.getPath())%>" class="lightbox" rel="lightbox" title="<%=anuncio.getName().toUpperCase()%>"><img src="documentos/<%=adjunto.getPath()%>" alt="" /></a>
				<%
			}
			%>
		</div>
	</div>
	<div id="hueco_slideshow">
	</div>
	<div class="bajo_foto"><%=UtilidadesTexto.obtenerDatosPieAnuncio(anuncio,totalanuncios)%></div>
</div>
<div class="line_botton"></div>
<div class="pie_pagina_derecha"><%=TextConstant.COPYRIGHT%></div>
<%=UtilidadesTexto.obtenerBotonera_intranet()%>
</div>
</form>
</body>
</html>


