<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import= "java.util.Hashtable" %>
<%
	
	Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
	String nombreArchivo = (String)hResultados.get("nombreArchivo");
	String mensaje = (String)hResultados.get("mensaje");
%>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/Tokenizer.js"></script>
<script type="text/javascript" src="js/cargadorArchivos.js"></script>
</head>
<body class="bodyCargadorArchivos">
<div id="contenedorArchivos1">
<form name="CargadorArchivos" method="post" enctype="multipart/form-data" action="ServletControlador?accion=gestorArchivos">
<input type="hidden" id="mensaje" value="<%=mensaje%>" />
<%
String valorEtiqueta=".";
if(nombreArchivo!=null)valorEtiqueta=nombreArchivo;%>
<div class="formulario_web_cagadorArchivos" id="capaCargador">
<label>
<input class="campo_texto_cargadorArchivos" name="cargador" type="file" onchange="javascript:subirArchivo();"/>&nbsp;<a id="linkArchivo" href="javascript:verArchivo('<%=valorEtiqueta%>');"><%=valorEtiqueta%></a>
</label>
</div>
</form>
</div>
</body>
</html>