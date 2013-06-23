
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%><%@ page import= "java.util.Hashtable" %>
<%
	response.setContentType("text/xml");
	response.setHeader("Cache-Control", "no-cache");
	Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
	String respuesta=(String)hResultados.get(TextConstant.KEY_RESPUESTA);
	out.print("<xml>");
	out.print("<respuesta>"+respuesta+"</respuesta>");
	out.print("</xml>");
%>
