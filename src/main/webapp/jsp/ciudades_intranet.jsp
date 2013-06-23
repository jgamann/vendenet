<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.negocio.entidad.VisitaWeb"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vendenet.negocio.entidad.VisitaIntranet"%>
<%@page import="com.vendenet.utilidades.UtilidadesFecha"%>
<%@page import="com.vendenet.negocio.entidad.CiudadTemp"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Administracion</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<link href="estilos/estilos_intranetv4.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" media="all" href="js/jscalendar/calendar-blue.css" title="verde" />
<script type="text/javascript" src="js/jscalendar/calendar.js"></script>
<script type="text/javascript" src="js/jscalendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="js/jscalendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/Tokenizer.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/h345h_3j34ui34u_3uy5u33uy_4uy35.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('[id^=previsualizado_]').each(function() {
		$(this).hide();
	});
});
</script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute(TextConstant.RESULTADOS);
List lstCiudadesIntranet=(List)hResultados.get(TextConstant.KEY_CIUDADES_TEMP);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form name="FormIntranet" action="ServletControlador?accion=ciudadesIntranet" method="post">
<input type="hidden" name="accion" value="ciudadesIntranet"/>

<div id="contenedor">
<div class="line"></div>
<div class="izquierda"><%=TextConstant.CIUDADES%></div>
<div class="derecha"><a href="javascript:buscarIntranet();"><%=TextConstant.REVISION_ANUNCIOS%></a> | <a href="javascript:estadisticasWeb();"><%=TextConstant.ESTADISTICAS%></a> | <a href="javascript:historicoEmails();"><%=TextConstant.HISTORICO_EMAILS%></a> | <a href="javascript:historicoBajas();"><%=TextConstant.HISTORICO_BAJAS%></a> | <%=TextConstant.CIUDADES%> | <a href="javascript:historicoBusquedas();"><%=TextConstant.HISTORICO_BUSQUEDAS%></a> | <a href="javascript:cerrarSesion();"><%=TextConstant.CERRAR_SESION%></a></div>

<div>

<div class='cuarto_float'>
</div>
<div class='cuarto_float'>
</div>
<div class='sexto_float'>
<input type="submit" value="Buscar"/>
</div>
</div>

<div class="lista_anuncios">
<ul>
<% 
int i=1;
Iterator itCiudadesIntranet = lstCiudadesIntranet.iterator();
try{
while(itCiudadesIntranet.hasNext()){
	CiudadTemp ciudad = (CiudadTemp)itCiudadesIntranet.next();
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="<%=ciudad.getName()%>" class="anuncio_revision_par"><div class="ochenta"><%=ciudad.getName()%></div><div class="veinte"><%=ciudad.getComunidad()%></div></li>
	<%}else{
		%>
		<li id="fila_<%=i%>" title="<%=ciudad.getName()%>" class="anuncio_revision_impar"><div class="ochenta"><%=ciudad.getName()%></div><div class="veinte"><%=ciudad.getComunidad()%></div></li>
		<%
	}
	i++;
}
}catch(Exception e){
	System.out.println(e);
}
%>
</ul>
</div>
</div>
</form>
</body>
</html>
