<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.negocio.entidad.VisitaWeb"%>
<%@page import="com.vendenet.negocio.entidad.EnvioRealizado"%>
<%@page import="com.vendenet.utilidades.UtilidadesFecha"%><html xmlns="http://www.w3.org/1999/xhtml">
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
<script type="text/javascript" src="js/historico_emails.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('[id^=previsualizado_]').each(function() {
		$(this).hide();
	});
});
</script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute(TextConstant.RESULTADOS);
List lstEnviosRealizados=(List)hResultados.get(TextConstant.KEY_ENVIOS_REALIZADOS);
String fechaDesde="";
if(hResultados.get(TextConstant.KEY_FECHA_DESDE)!=null)fechaDesde=(String)hResultados.get(TextConstant.KEY_FECHA_DESDE);
String fechaHasta="";
if(hResultados.get(TextConstant.KEY_FECHA_HASTA)!=null)fechaHasta=(String)hResultados.get(TextConstant.KEY_FECHA_HASTA);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form name="FormIntranet" action="ServletControlador?accion=historicoEmails" method="post">
<input type="hidden" name="accion" value="historicoEmails"/>

<div id="contenedor">
<div class="line"></div>
<div class="izquierda"><%=TextConstant.HISTORICO_EMAILS%></div>
<div class="derecha"><a href="javascript:buscarIntranet();"><%=TextConstant.REVISION_ANUNCIOS%></a> | <a href="javascript:estadisticasWeb();"><%=TextConstant.ESTADISTICAS%></a> | <%=TextConstant.HISTORICO_EMAILS%> | <a href="javascript:historicoBajas();"><%=TextConstant.HISTORICO_BAJAS%></a> | <a href="javascript:ciudades();"><%=TextConstant.CIUDADES%></a> | <a href="javascript:historicoBusquedas();"><%=TextConstant.HISTORICO_BUSQUEDAS%></a> | <a href="javascript:cerrarSesion();"><%=TextConstant.CERRAR_SESION%></a></div>
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
<div class="derecha"><a href='javascript:invocarEnvio()'>Enviar Mails Pendientes (Esperar hasta que aparezca el resultado)</a></div>
</div>
<div class="lista_anuncios">
<ul>
<%
int totalEnvios=lstEnviosRealizados.size();
int totalEnviosAnuncioRegitrados=0;
int totalEnviosAnuncioAceptado=0;
int totalEnviosAnuncioNoAceptado=0;
int totalEnviosAnuncioModificado=0;
int totalEnviosOpiniones=0;
int totalEnviosAnuncioBorrado=0;
for(int i=0;i<lstEnviosRealizados.size();i++){
	EnvioRealizado envioTemp = (EnvioRealizado)lstEnviosRealizados.get(i);
	if(envioTemp.getEmailSubject()!=null && envioTemp.getEmailSubject().contains(TextConstant.ANUNCIO_NOTIFICACION_NUEVO)){
		totalEnviosAnuncioRegitrados++;
		lstEnviosRealizados.remove(i);
		i--;
	}else if(envioTemp.getEmailSubject()!=null && envioTemp.getEmailSubject().equals(TextConstant.CONFIRMAR_OPINION)){
		totalEnviosOpiniones++;
		lstEnviosRealizados.remove(i);
		i--;
	}else if(envioTemp.getEmailSubject()!=null && envioTemp.getEmailSubject().equals(TextConstant.ANUNCIO_ACEPTADO)){
		totalEnviosAnuncioAceptado++;
		lstEnviosRealizados.remove(i);
		i--;
	}else if(envioTemp.getEmailSubject()!=null && envioTemp.getEmailSubject().equals(TextConstant.ANUNCIO_NO_ACEPTADO)){
		totalEnviosAnuncioNoAceptado++;
		lstEnviosRealizados.remove(i);
		i--;
	}else if(envioTemp.getEmailSubject()!=null && envioTemp.getEmailSubject().contains(TextConstant.ANUNCIO_NOTIFICACION_MODIFICADO)){
		totalEnviosAnuncioModificado++;
		lstEnviosRealizados.remove(i);
		i--;
	}else if(envioTemp.getEmailSubject()!=null && envioTemp.getEmailSubject().contains(TextConstant.ANUNCIO_NOTIFICACION_BORRADO)){
		totalEnviosAnuncioBorrado++;
		lstEnviosRealizados.remove(i);
		i--;
	}
}
%>
<li id="fila_0" title="TOTAL ENVIOS" class="anuncio_revision_par"><div class="ochenta">TOTAL ENVIOS</div><div class="veinte"><%=totalEnvios%></div></li>
<li id="fila_1" title="Emails de nuevo anuncio registrado" class="anuncio_revision_impar"><div class="ochenta">Emails de nuevo anuncio registrado</div><div class="veinte"><%=totalEnviosAnuncioRegitrados%></div></li>
<li id="fila_2" title="Emails de anuncio aceptado" class="anuncio_revision_par"><div class="ochenta">Emails de anuncio aceptado</div><div class="veinte"><%=totalEnviosAnuncioAceptado%></div></li>
<li id="fila_3" title="Emails de anuncio no aceptado" class="anuncio_revision_impar"><div class="ochenta">Emails de anuncio no aceptado</div><div class="veinte"><%=totalEnviosAnuncioNoAceptado%></div></li>
<li id="fila_4" title="Emails de anuncio modificado" class="anuncio_revision_par"><div class="ochenta">Emails de anuncio modificado</div><div class="veinte"><%=totalEnviosAnuncioModificado%></div></li>
<li id="fila_5" title="Emails de anuncio borrado" class="anuncio_revision_impar"><div class="ochenta">Emails de anuncio borrado</div><div class="veinte"><%=totalEnviosAnuncioBorrado%></div></li>
<li id="fila_6" title="Emails de opinion registrada" class="anuncio_revision_par"><div class="ochenta">Emails de opinion registrada</div><div class="veinte"><%=totalEnviosOpiniones%></div></li>
<li id="fila_7" title="Emails entre usuarios" class="anuncio_revision_impar"><div class="ochenta">Emails entre usuarios</div><div class="veinte"><%=lstEnviosRealizados.size()%></div></li>
<li id="fila_8" title="Margen" class="anuncio_revision_par"><div class="ochenta">&nbsp;</div><div class="veinte"><a href="javascript:ocultarTodos()"><%=TextConstant.OCULTAR_TODOS%></a></div></li>
<%
Iterator itEnviosRealizados = lstEnviosRealizados.iterator();
int i=9;
try{
while(itEnviosRealizados.hasNext()){
	EnvioRealizado envio = (EnvioRealizado)itEnviosRealizados.next();
	int subtotal=0;
	//navegadorConocidos+=subtotal;
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="Email" class="anuncio_revision_par"><div class="ochenta">Consulta de usuario | <%=UtilidadesFecha.formatearFechaDDMMMMHHmm(envio.getFechaAlta())%></div><div class="veinte"><a href="javascript:verEmail(<%=envio.getId()%>)"><%=TextConstant.VER_EMAIL%></a></div></li>
	<%}else{
		%>
    <li id="fila_<%=i%>" title="Email" class="anuncio_revision_impar"><div class="ochenta">Consulta de usuario | <%=UtilidadesFecha.formatearFechaDDMMMMHHmm(envio.getFechaAlta())%></div><div class="veinte"><a href="javascript:verEmail(<%=envio.getId()%>)"><%=TextConstant.VER_EMAIL%></a></div></li>
		<%
	}
	%>
	<div style="background-color:black;" id="previsualizado_<%=envio.getId()%>"><%=envio.getEmailBody()%></div>
	<%
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
