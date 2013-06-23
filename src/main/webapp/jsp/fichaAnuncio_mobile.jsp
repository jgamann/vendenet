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
<link href="estilos/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/commonfunctionsv4.js"></script>
<script type="text/javascript" src="js/fecha_horav2.js"></script>
<script type="text/javascript" src="js/jquery.autocompletev1.js"></script>
<script src="js/jquery.lightbox.js" type="text/javascript"></script>
<script>
		$(document).ready(function(){
			$('.lightbox').lightBox();
		});
</script>
<script type="text/javascript" src="js/multi-slide.js"></script>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<link href="estilos/multi-slidev1.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" media="all" type="text/css" href="estilos/stunicholls.css" />
<script src="js/fichaAnuncio_mobilev4.js" type="text/javascript"></script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
Anuncio anuncio=(Anuncio)hResultados.get("anuncio");
int totalvisitas=(Integer)hResultados.get(TextConstant.KEY_TOTAL_VISITAS);
int totalanuncios=(Integer)hResultados.get(TextConstant.KEY_TOTAL_ANUNCIOS);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form action="" id="formulario" name="formulario" method="post">
<input type="hidden" id="idAnuncio" name="idAnuncio" value="<%=anuncio.getId()%>"/>
<input type="hidden" id="URL_FACEBOOK" name="URL_FACEBOOK" value="<%=TextConstant.URL_COMPARTIR_FACEBOOK%>"/>
<input type="hidden" id="URL_TWITTER" name="URL_TWITTER" value="<%=TextConstant.URL_COMPARTIR_TWITTER%>"/>
<input type="hidden" id="URL_VENDENET_VERANUNCIO" name="URL_VENDENET_VERANUNCIO" value="<%=TextConstant.URL_VENDENET_VERANUNCIO%>"/>
<input id="pagina" name="pagina" type="hidden" value="1"/>
<input id="desde" name="desde" type="hidden" value="fichaAnuncio"/>
<input id="idAnuncioFicha" name="idAnuncioFicha" type="hidden" value=""/>
<div id="contenedor">
<%=UtilidadesTexto.obtenerReloj()%>
<div class="line"></div>
<div class="capa_cabecera_anuncio">
<a href="<%=TextConstant.URL_INICIO%>" title="volver a la p&aacute;gina inicial" class="enlace_imagen">
<%=UtilidadesTexto.obtenerLogo()%>
</a>
<input align="middle" name="nombre" type="text" class="campo_texto_mobile" id="nombre"/>

<button name="boton" type="submit" class="class_boton_buscar"><img src="imagenes/buscar.png" alt="Buscar"/></button>
<div class="red_social">
	<div class="red_social_img">
	<a href='javascript:compartirRedSocial("FACEBOOK",<%=anuncio.getId()%>);'>&nbsp;<img src='imagenes/facebook.png'/></a><a href='javascript:compartirRedSocial("TWITTER",<%=anuncio.getId()%>);'>&nbsp;<img src='imagenes/twitter.png'/></a>
	</div>
	<div class="red_social_text">
	<%=TextConstant.ANYADE_ANUNCIO_RED_SOCIAL%>
	</div>
</div>
<div id="ponanuncio_" onclick="window.location.href='ServletControlador?accion=ponerAnuncio'">
	<a href="ServletControlador?accion=ponerAnuncio"><span class="label_gris">PON TU ANUNCIO</span>GRATIS</a>
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
</div>
</form>
</body>
</html>


