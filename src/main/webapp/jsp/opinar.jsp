<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="com.vendenet.negocio.entidad.Critica"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Hashtable"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Vendenet.net - La mejor web de compraventa en internet</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/fecha_horav2.js"></script>
<script type="text/javascript" src="js/commonfunctionsv4.js"></script>
<script type="text/javascript" src="js/opinarv3.js"></script>
<link rel="stylesheet" href="estilos/jScrollbar.jquery.css" type="text/css" />

<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery-mousewheel.js"></script>
<script type="text/javascript" src="js/jScrollbar.jquery.js"></script>

<%
Hashtable hResultados = (Hashtable)request.getAttribute(TextConstant.RESULTADOS);
String idAnuncio=(String)hResultados.get(TextConstant.KEY_ANUNCIO);
List<Critica> lstCrititicas=(List<Critica>)hResultados.get(TextConstant.KEY_LISTA_CRITICAS);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form action="" id="formulario" name="formulario" method="post">
<input type="hidden" id="idAnuncio" name="idAnuncio" value="<%=idAnuncio%>"/>
<input type="hidden" id="estrellita" name="estrellita" value="-1"/>
<div id="contenedor">
<%=UtilidadesTexto.obtenerReloj()%>
<div class="line"></div>
<div class="capa_cabecera_anuncio">
<a href="<%=TextConstant.URL_INICIO%>" title="volver a la p&aacute;gina inicial" class="enlace_imagen">
<%=UtilidadesTexto.obtenerLogo()%>
</a>
<span class="titulo_pagina"><%=TextConstant.PONER_OPINION%></span></div>

<div class="cien">
<%=UtilidadesTexto.obtenerCapa_VolverAnuncio(idAnuncio)%>
</div>
<div class="capa_opinion_detallada">
<%=UtilidadesTexto.obtenerOpinionDetallada(lstCrititicas)%>
</div>
<div class="capa_insertar_opinion">
<%=UtilidadesTexto.obtenerPlantillaAltaOpinion()%>
</div>
<div class="red_social_botton">
<div class="red_social_text"><%=TextConstant.SIGUENOS_EN%></div>
<div class="red_social_img"><a href="<%=TextConstant.URL_FACEBOOK%>"><%=TextConstant.IMAGEN_FACEBOOK%></a><a href="<%=TextConstant.URL_TWITTER%>"><%=TextConstant.IMAGEN_TWITTER%></a>

</div>
</div>
<div class="line_botton_iconos"></div>
<div class="pie_pagina_izquierda">
<%=TextConstant.NAVEGADORES_RECOMENDADOS_A%> 
</div>
<div class="pie_pagina_centro">
<%=TextConstant.NAVEGADORES_RECOMENDADOS_B%> 
</div>
<div class="pie_pagina_derecha"><%=TextConstant.COPYRIGHT%></div>
</div>
</form>

</body>
</html>


