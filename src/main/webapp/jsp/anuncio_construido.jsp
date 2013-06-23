<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Provincia"%>
<%@page import="com.vendenet.negocio.entidad.CategoriaAnuncio"%>
<%@page import="com.vendenet.negocio.entidad.TipoVendedor"%>
<%@page import="com.vendenet.negocio.entidad.TipoAnuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Vendenet.net - La mejor web de compraventa en internet</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="estilos/sdmenuv2.css" />
<script type="text/javascript" src="js/menu_izda.js"></script>
<script type="text/javascript" src="js/fecha_horav2.js"></script>
<script type="text/javascript" src="js/anuncio_construido.js"></script>
<script type="text/javascript" src="js/commonfunctionsv4.js"></script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
Anuncio anuncio=null;
String email=null;
String nombre=null;
String telefono=null;
if(hResultados.get(TextConstant.KEY_ANUNCIO)!=null){
	anuncio=(Anuncio)hResultados.get(TextConstant.KEY_ANUNCIO);
}%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form action="" id="formulario" name="formulario" method="post">
<div id="contenedor">
<%=UtilidadesTexto.obtenerReloj()%>
<div class="line"></div>
<div class="capa_buscador">
<a href="/vendenet" title="volver a la p&aacute;gina inicial" class="enlace_imagen"><%=UtilidadesTexto.obtenerLogo()%></a>
</div>
<div class="titulo_pagina" align="left"><%=TextConstant.CONFIRMAR_ANUNCIO%></div>

<div class="capa_preliminar_anuncio">
<%=UtilidadesTexto.obtenerPrevisualizacionAnuncio(anuncio)%>
</div>
<div class="red_social_botton">
<div class="red_social_text"><%=TextConstant.SIGUENOS_EN%></div>
<div class="red_social_img"><a href="<%=TextConstant.URL_FACEBOOK%>"><%=TextConstant.IMAGEN_FACEBOOK%></a><a href="<%=TextConstant.URL_TWITTER%>"><%=TextConstant.IMAGEN_TWITTER%></a>

</div>
</div>
<div class="line_botton_iconos"></div>
<div class="pie_pagina_derecha"><%=TextConstant.COPYRIGHT%></div>
</div>
</form>

</body>
</html>


