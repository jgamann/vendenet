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
<script type="text/javascript">
$(document).ready(function() {
	$('[id^=previsualizado_]').each(function() {
		$(this).hide();
	});
});
</script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute(TextConstant.RESULTADOS);
List lstVisitasIntranet=(List)hResultados.get(TextConstant.KEY_VISITAS_INTRANET);
List lstVisitasWeb=(List)hResultados.get(TextConstant.KEY_VISITAS_WEB);
List lstTiposNavegador=(List)hResultados.get(TextConstant.KEY_TIPOS_NAVEGADOR);

String fechaDesde="";
if(hResultados.get(TextConstant.KEY_FECHA_DESDE)!=null)fechaDesde=(String)hResultados.get(TextConstant.KEY_FECHA_DESDE);
String fechaHasta="";
if(hResultados.get(TextConstant.KEY_FECHA_HASTA)!=null)fechaHasta=(String)hResultados.get(TextConstant.KEY_FECHA_HASTA);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form name="FormIntranet" action="ServletControlador?accion=estadisticasIntranet" method="post">
<input type="hidden" name="accion" value="estadisticasIntranet"/>

<div id="contenedor">
<div class="line"></div>
<div class="izquierda"><%=TextConstant.ESTADISTICAS%></div>
<div class="derecha"><a href="javascript:buscarIntranet();"><%=TextConstant.REVISION_ANUNCIOS%></a> | <%=TextConstant.ESTADISTICAS%> | <a href="javascript:historicoEmails();"><%=TextConstant.HISTORICO_EMAILS%></a> | <a href="javascript:historicoBajas();"><%=TextConstant.HISTORICO_BAJAS%></a> | <a href="javascript:ciudades();"><%=TextConstant.CIUDADES%></a> | <a href="javascript:historicoBusquedas();"><%=TextConstant.HISTORICO_BUSQUEDAS%></a> | <a href="javascript:cerrarSesion();"><%=TextConstant.CERRAR_SESION%></a></div>

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
int i=1;
int visitasIntranetOk=0;
int visitasIntranetKo=0;
for(int in=0;in<lstVisitasIntranet.size();in++){
	VisitaIntranet visitaIntranet = (VisitaIntranet)lstVisitasIntranet.get(in);
	if(visitaIntranet.isLoginOk())visitasIntranetOk++;
	else visitasIntranetKo++;
}%>
<li id="fila_<%=i%>" title="Visitas Intranet Exitosas" class="anuncio_revision_impar" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">Visitas Intranet Exitosas</div><div class="veinte"><%=visitasIntranetOk%></div></li>
<div style="background-color:black;" id="previsualizado_<%=i%>">
<hmtl>
<table class="detalle_navegador">
	<%for(int k=0;k<lstVisitasIntranet.size();k++){
		VisitaIntranet visitaTemp = (VisitaIntranet)lstVisitasIntranet.get(k);
		if(visitaTemp.isLoginOk()){
			StringBuffer sb = new StringBuffer(UtilidadesFecha.formatearFechaDDMMMMHHmm(visitaTemp.getFechaVisita())).append(TextConstant.SPACE).append(visitaTemp.getIp()).append(TextConstant.SPACE).append(visitaTemp.getNavegador());
		%>
		<tr><td align="left"><%=sb.toString()%></td></tr>
	<%}
	}%>
</table>
</hmtl>
</div>
<%
i++;
%>
<li id="fila_<%=i%>" title="Visitas Intranet Fallidas" class="anuncio_revision_par" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">Visitas Intranet Fallidas</div><div class="veinte"><%=visitasIntranetKo%></div></li>
<div style="background-color:black;" id="previsualizado_<%=i%>">
<hmtl>
<table class="detalle_navegador">
	<%for(int k=0;k<lstVisitasIntranet.size();k++){
		VisitaIntranet visitaTemp = (VisitaIntranet)lstVisitasIntranet.get(k);
		if(!visitaTemp.isLoginOk()){
			StringBuffer sb = new StringBuffer(UtilidadesFecha.formatearFechaDDMMMMHHmm(visitaTemp.getFechaVisita())).append(TextConstant.SPACE).append(TextConstant.PARENTESIS_ABRIR).append(visitaTemp.getName()).append(TextConstant.PARENTESIS_CERRAR).append(TextConstant.SPACE).append(visitaTemp.getIp()).append(TextConstant.SPACE).append(visitaTemp.getNavegador());
		%>
		<tr><td align="left"><%=sb.toString()%></td></tr>
	<%}
	}%>
</table>
</hmtl>
</div>
<%
i++;
%>
<li id="fila_<%=i%>" title="TOTAL Visitas Intranet" class="anuncio_revision_impar" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">TOTAL Visitas Intranet</div><div class="veinte"><%=(visitasIntranetOk+visitasIntranetKo)%></div></li>
<%
i++;
Iterator itTiposNavegador = lstTiposNavegador.iterator();

int navegadorConocidos=0;
int navegadorBot=0;
int totalVisitas=lstVisitasWeb.size();
List lstVisitasTemp=new ArrayList();
boolean tratandoBots=true;
try{
while(itTiposNavegador.hasNext()){
	lstVisitasTemp=new ArrayList();
	String navegador = (String)itTiposNavegador.next();
	if (navegador!=null && navegador.equals(TextConstant.KEY_NAVEGADORES_BOT)){
		tratandoBots=false;
	}
	int subtotal=0;
	for(int j=0;j<lstVisitasWeb.size();j++){
		VisitaWeb visita = (VisitaWeb)lstVisitasWeb.get(j);
		if((visita.getNavegador()!=null)&&(visita.getNavegador().toLowerCase().contains(navegador.toLowerCase()))){
			lstVisitasTemp.add(visita);
			lstVisitasWeb.remove(visita);
			subtotal++;
			j--;
		}
	}
	if(navegador!=null && !navegador.equals(TextConstant.KEY_NAVEGADORES_BOT)){
		if(!tratandoBots){
			//navegadorConocidos+=subtotal;
			if(i%2==0){
			%>
			<li id="fila_<%=i%>" title="Navegador '<%=navegador%>'" class="anuncio_revision_par" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">Navegador '<%=navegador%>'</div><div class="veinte"><%=subtotal%></div></li>
			<%}else{
				%>
				<li id="fila_<%=i%>" title="Navegador '<%=navegador%>'" class="anuncio_revision_impar" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">Navegador '<%=navegador%>'</div><div class="veinte"><%=subtotal%></div></li>
				<%
			}	
		}else navegadorBot+=subtotal;
	
		%>
		<div style="background-color:black;" id="previsualizado_<%=i%>">
		<hmtl>
		<table class="detalle_navegador">
		<%for(int k=0;k<lstVisitasTemp.size();k++){
			VisitaWeb visitaTemp = (VisitaWeb)lstVisitasTemp.get(k);
		%>
		<tr><td align="left"><%=visitaTemp.getNavegador()%></td></tr>
		<%}%>
		</table>
		</hmtl>
		</div>
		<%
		i++;
	}
}
}catch(Exception e){
	System.out.println(e);
}
%>
<%
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="OTROS" class="anuncio_revision_par" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">Otros Navegadores</div><div class="veinte"><%=lstVisitasWeb.size()%></div></li>
	<%}else{
		%>
		<li id="fila_<%=i%>" title="OTROS" class="anuncio_revision_impar" onclick="javascript:verEmail(<%=i%>)"><div class="ochenta">Otros Navegadores</div><div class="veinte"><%=lstVisitasWeb.size()%></div></li>
		<%
	}
	%>
	<div style="background-color:black;" id="previsualizado_<%=i%>">
	<hmtl>
	<table class="detalle_navegador">
	<%for(int k=0;k<lstVisitasWeb.size();k++){
		VisitaWeb visitaTemp = (VisitaWeb)lstVisitasWeb.get(k);
	%>
	<tr><td align="left"><%=visitaTemp.getNavegador()%></td></tr>
	<%}%>
	</table>
	</hmtl>
	</div>
	<%
	i++;
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="TOTAL BOTS" class="anuncio_revision_par"><div class="ochenta">Navegadores ROBOT</div><div class="veinte"><%=navegadorBot%></div></li>
	<%}else{
		%>
		<li id="fila_<%=i%>" title="TOTAL BOTS" class="anuncio_revision_impar"><div class="ochenta">Navegadores ROBOT</div><div class="veinte"><%=navegadorBot%></div></li>
		<%
	}
	i++;
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="TOTAL" class="anuncio_revision_par"><div class="ochenta">Total de visitas VENDENET</div><div class="veinte"><%=totalVisitas%></div></li>
	<%}else{
		%>
		<li id="fila_<%=i%>" title="TOTAL" class="anuncio_revision_impar"><div class="ochenta">Total de visitas VENDENET</div><div class="veinte"><%=totalVisitas%></div></li>
		<%
	}
	%>

</ul>
</div>
</div>
</form>
</body>
</html>
