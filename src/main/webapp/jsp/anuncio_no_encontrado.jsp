<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Vendenet.net - La mejor web de compraventa en internet</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form action="" id="formulario" name="formulario" method="post">
<div id="contenedor">
<div class="line"></div>
<div class="capa_buscador">
<a href="<%=TextConstant.URL_INICIO%>" title="volver a la p&aacute;gina inicial" class="enlace_imagen">
<%=UtilidadesTexto.obtenerLogo()%>
</a>
</div>
<div class="titulo_pagina" align="left"></div>
<%=UtilidadesTexto.obtenerAnuncioNoEncontrado()%>
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


