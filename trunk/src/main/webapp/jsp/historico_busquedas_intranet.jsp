<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.negocio.entidad.VisitaWeb"%>
<%@page import="com.vendenet.negocio.entidad.EnvioRealizado"%>
<%@page import="com.vendenet.utilidades.UtilidadesFecha"%>
<%@page import="com.vendenet.negocio.entidad.HistoricoBajas"%>
<%@page import="com.vendenet.utilidades.UtilidadesNumericos"%>
<%@page import="com.vendenet.negocio.entidad.Busqueda"%><html xmlns="http://www.w3.org/1999/xhtml">
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
List lstHistorico=(List)hResultados.get(TextConstant.KEY_HISTORICO_BUSQUEDAS);
String fechaDesde="";
if(hResultados.get(TextConstant.KEY_FECHA_DESDE)!=null)fechaDesde=(String)hResultados.get(TextConstant.KEY_FECHA_DESDE);
String fechaHasta="";
if(hResultados.get(TextConstant.KEY_FECHA_HASTA)!=null)fechaHasta=(String)hResultados.get(TextConstant.KEY_FECHA_HASTA);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form name="FormIntranet" action="ServletControlador?accion=historicoBusquedas" method="post">
<input type="hidden" name="accion" value="historicoEmails"/>

<div id="contenedor">
<div class="line"></div>
<div class="izquierda"><%=TextConstant.HISTORICO_BUSQUEDAS%></div>
<div class="derecha"><a href="javascript:buscarIntranet();"><%=TextConstant.REVISION_ANUNCIOS%></a> | <a href="javascript:estadisticasWeb();"><%=TextConstant.ESTADISTICAS%></a> | <a href="javascript:historicoEmails();"><%=TextConstant.HISTORICO_EMAILS%></a> | <a href="javascript:historicoBajas();"><%=TextConstant.HISTORICO_BAJAS%></a> | <a href="javascript:ciudades();"><%=TextConstant.CIUDADES%></a> | <%=TextConstant.HISTORICO_BUSQUEDAS%> | <a href="javascript:cerrarSesion();"><%=TextConstant.CERRAR_SESION%></a></div>

<div>

<div class='cuarto_float'>
<label class="label_negra" for="fechainicio">Desde:</label>
<input name="fechainicio" type="text" class="campo_texto" id="fechainicio" value="<%=fechaDesde%>"/>
		<img src="js/jscalendar/img.gif" alt="" id="f_trigger_i"/>
		<script type="text/javascript">
    	Calendar.setup({
        inputField     :    "fechainicio",     // id of the input field
        ifFormat       :    "%d/%m/%Y",      // format of the input field
        button         :    "f_trigger_i",  // trigger for the calendar (button ID)
        align          :    "Bl",           // alignment (defaults to "Bl")
        singleClick    :    true
    	});
		</script>

</div>
<div class='cuarto_float'>
<label class="label_negra" for="fechafin">Hasta:</label>
<input name="fechafin" type="text" class="campo_texto" id="fechafin" value="<%=fechaHasta%>"/>
		<img src="js/jscalendar/img.gif" alt="" id="f_trigger_f"/>
		<script type="text/javascript">
    	Calendar.setup({
        inputField     :    "fechafin",     // id of the input field
        ifFormat       :    "%d/%m/%Y",      // format of the input field
        button         :    "f_trigger_f",  // trigger for the calendar (button ID)
        align          :    "Bl",           // alignment (defaults to "Bl")
        singleClick    :    true
    	});
		</script>
		
</div>
<div class='sexto_float'>
<input type="submit" value="Buscar"/>
</div>
</div>
<div class="lista_anuncios">
<ul>
<%
int totalBusquedas=lstHistorico.size();
%>
<li id="fila_0" title="TOTAL BUSQUEDAS" class="anuncio_revision_par"><div class="ochenta">TOTAL BUSQUEDAS</div><div class="veinte"><%=totalBusquedas%></div></li>
<%
Iterator itHistorico = lstHistorico.iterator();
int i=1;
try{
while(itHistorico.hasNext()){
	Busqueda busqueda = (Busqueda)itHistorico.next();
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="Busqueda" class="anuncio_revision_par"><div class="ochenta">'<%=busqueda.getName()%>' <%=UtilidadesFecha.formatearFechaDDMMMMHHmm(busqueda.getFechaBusqueda())%></div><div class="veinte">&nbsp;</div></li>
	<%}else{
		%>
    <li id="fila_<%=i%>" title="Busqueda" class="anuncio_revision_impar"><div class="ochenta">'<%=busqueda.getName()%>' <%=UtilidadesFecha.formatearFechaDDMMMMHHmm(busqueda.getFechaBusqueda())%></div><div class="veinte">&nbsp;</div></li>
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
