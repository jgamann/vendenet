<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%><html xmlns="http://www.w3.org/1999/xhtml">
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
<%
Hashtable hResultados = (Hashtable)request.getAttribute(TextConstant.RESULTADOS);
List lstAnuncios=(List)hResultados.get(TextConstant.KEY_LISTA_ANUNCIOS_SIN_PUBLICAR);
int numTotalAnuncioRevision=((Integer)hResultados.get(TextConstant.KEY_NUM_TOTAL_ANUNCIOS_REVISION)).intValue();
String fechaDesde="";
if(hResultados.get(TextConstant.KEY_FECHA_DESDE)!=null)fechaDesde=(String)hResultados.get(TextConstant.KEY_FECHA_DESDE);
String fechaHasta="";
if(hResultados.get(TextConstant.KEY_FECHA_HASTA)!=null)fechaHasta=(String)hResultados.get(TextConstant.KEY_FECHA_HASTA);
boolean incluirRevisados=hResultados.get(TextConstant.KEY_INCLUIR_REVISADOS)!=null;
boolean incluirPublicados=hResultados.get(TextConstant.KEY_INCLUIR_PUBLICADOS)!=null;
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form name="FormIntranet" action="ServletControlador?accion=buscarIntranet" method="post">
<input type="hidden" name="accion" value="buscarIntranet"/>
<input id="ultimoSeleccionado" type="hidden"/>
<input id="grupoSeleccionados" type="hidden"/>
<div id="contenedor">
<div class="line"></div>
<div class="izquierda"><%=TextConstant.ANUNCIOS_POR_REVISAR%><span style="color:red"><%=numTotalAnuncioRevision%></span></div>
<div class="derecha"><%=TextConstant.REVISION_ANUNCIOS%> | <a href="javascript:estadisticasWeb();"><%=TextConstant.ESTADISTICAS%></a> | <a href="javascript:historicoEmails();"><%=TextConstant.HISTORICO_EMAILS%></a> | <a href="javascript:historicoBajas();"><%=TextConstant.HISTORICO_BAJAS%></a> | <a href="javascript:ciudades();"><%=TextConstant.CIUDADES%></a> | <a href="javascript:historicoBusquedas();"><%=TextConstant.HISTORICO_BUSQUEDAS%></a> | <a href="javascript:cerrarSesion();"><%=TextConstant.CERRAR_SESION%></a></div>


<div style="float: left; width: 100%;">
<div class='tercio_float'>
<label class="label_negra" for="incluirrevisados"><%=TextConstant.INCLUIR_REVISADOS%> <input name="incluirrevisados" type="checkbox" id="incluirrevisados" value="1" <%=incluirRevisados==true?"checked='checked'":""%> /></label>
<label class="label_negra" for="incluirpublicados"><%=TextConstant.INCLUIR_PUBLICADOS%> <input name="incluirpublicados" type="checkbox" id="incluirpublicados" value="1" <%=incluirPublicados==true?"checked='checked'":""%> /></label>
</div>
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
<%=UtilidadesTexto.obtenerBotonera_intranet()%>
<div class="lista_anuncios">
<ul>
<%
Iterator itAnuncios = lstAnuncios.iterator();
int i=1;
try{
while(itAnuncios.hasNext()){
	Anuncio anuncio = (Anuncio)itAnuncios.next();	
	if(i%2==0){
	%>
	<li id="fila_<%=i%>" title="<%=anuncio.getId()%>" onclick="javascript:seleccionarAnuncio('<%=i%>','<%=anuncio.getId()%>');" class="anuncio_revision_par"><div class="ochenta"><%=anuncio.toString()%></div><div class="veinte"><a href="javascript:abrirAnuncio(<%=anuncio.getId()%>)"><%=TextConstant.VER_ANUNCIO%></a></div></li>
	<%}else{
		%>
		<li id="fila_<%=i%>" title="<%=anuncio.getId()%>" onclick="javascript:seleccionarAnuncio('<%=i%>','<%=anuncio.getId()%>');" class="anuncio_revision_impar"><div class="ochenta"><%=anuncio.toString()%></div><div class="veinte"><a href="javascript:abrirAnuncio(<%=anuncio.getId()%>)"><%=TextConstant.VER_ANUNCIO%></a></div></li>
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
