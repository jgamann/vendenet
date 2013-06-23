
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%><%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.StringBuffer"%>
<%@ page import= "java.util.Hashtable" %>
<%@ page import= "java.util.Vector" %>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Content-Type","text/html; charset=utf-8");
	Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
	String patron=(String)hResultados.get(TextConstant.PATRON);
	out.println(TextConstant.CORCHO+patron+TextConstant.CORCHO);
	List resultados=(List)hResultados.get("lstAnuncios");
	Iterator itAnuncios=resultados.iterator();
	while(itAnuncios.hasNext()){
		Anuncio anuncioTemp = (Anuncio)itAnuncios.next();		
		StringBuffer sb = UtilidadesTexto.obtenerCadenaBuscador(anuncioTemp);
		out.println(sb);
	}
%>
