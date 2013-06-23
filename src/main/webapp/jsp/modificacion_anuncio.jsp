<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Provincia"%>
<%@page import="com.vendenet.negocio.entidad.CategoriaAnuncio"%>
<%@page import="com.vendenet.negocio.entidad.TipoVendedor"%>
<%@page import="com.vendenet.negocio.entidad.TipoAnuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.negocio.entidad.Adjunto"%>
<%@page import="com.vendenet.utilidades.UtilidadesAdjunto"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Vendenet.net - La mejor web de compraventa en internet</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="estilos/sdmenuv2.css" />
<script type="text/javascript" src="js/menu_izda.js"></script>
<script type="text/javascript" src="js/commonfunctionsv4.js"></script>
<script type="text/javascript" src="js/poner_anunciov3.js"></script>
<%
Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
List lstProvincias=(List)hResultados.get(TextConstant.KEY_PROVINCIAS);
List lstCategorias=(List)hResultados.get(TextConstant.KEY_CATEGORIAS);
List lstTiposVendedor=(List)hResultados.get(TextConstant.KEY_TIPOS_VENDEDOR);
List lstTiposAnuncio=(List)hResultados.get(TextConstant.KEY_TIPOS_ANUNCIO);
Anuncio anuncio = (Anuncio)hResultados.get(TextConstant.KEY_ANUNCIO);
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form action="" id="formulario" name="formulario" method="post">
<input type="hidden" id="idAnuncio" name="idAnuncio" value="<%=anuncio.getId()%>"/>
<input type="hidden" id="provincia_selected" name="provincia_selected" value="<%=anuncio.getProvincia().getId()%>"/>
<input type="hidden" id="categoria_selected" name="categoria_selected" value="<%=anuncio.getCategoria().getId()%>"/>
<input type="hidden" id="tipo_vendedor_selected" name="tipo_vendedor_selected" value="<%=anuncio.getTipoVendedor().getId()%>"/>
<input type="hidden" id="tipo_anuncio_selected" name="tipo_anuncio_selected" value="<%=anuncio.getTipoAnuncio().getId()%>"/>
<input type="hidden" id="foto1_selected" name="foto1_selected" value="<%=(anuncio!=null && anuncio.getAdjuntos()!= null && anuncio.getAdjuntos().size()>0)==true?UtilidadesAdjunto.obtenerPathFotoPeque(((Adjunto)anuncio.getAdjuntos().get(0)).getPath()):"" %>"/>
<input type="hidden" id="foto2_selected" name="foto2_selected" value="<%=(anuncio!=null && anuncio.getAdjuntos()!= null && anuncio.getAdjuntos().size()>1)==true?UtilidadesAdjunto.obtenerPathFotoPeque(((Adjunto)anuncio.getAdjuntos().get(1)).getPath()):"" %>"/>
<input type="hidden" id="foto3_selected" name="foto3_selected" value="<%=(anuncio!=null && anuncio.getAdjuntos()!= null && anuncio.getAdjuntos().size()>2)==true?UtilidadesAdjunto.obtenerPathFotoPeque(((Adjunto)anuncio.getAdjuntos().get(2)).getPath()):"" %>"/>
<input type="hidden" id="foto4_selected" name="foto4_selected" value="<%=(anuncio!=null && anuncio.getAdjuntos()!= null && anuncio.getAdjuntos().size()>3)==true?UtilidadesAdjunto.obtenerPathFotoPeque(((Adjunto)anuncio.getAdjuntos().get(3)).getPath()):"" %>"/>
<input type="hidden" id="foto5_selected" name="foto5_selected" value="<%=(anuncio!=null && anuncio.getAdjuntos()!= null && anuncio.getAdjuntos().size()>4)==true?UtilidadesAdjunto.obtenerPathFotoPeque(((Adjunto)anuncio.getAdjuntos().get(4)).getPath()):"" %>"/>
<input type="hidden" id="foto6_selected" name="foto6_selected" value="<%=(anuncio!=null && anuncio.getAdjuntos()!= null && anuncio.getAdjuntos().size()>5)==true?UtilidadesAdjunto.obtenerPathFotoPeque(((Adjunto)anuncio.getAdjuntos().get(5)).getPath()):"" %>"/>
<div id="contenedor">
<div class="line"></div>
<div class="capa_cabecera_anuncio">
<a href="<%=TextConstant.URL_INICIO%>" title="volver a la p&aacute;gina inicial" class="enlace_imagen">
<%=UtilidadesTexto.obtenerLogo()%>
</a>
<span class="titulo_pagina"><%=TextConstant.MODIFICAR_ANUNCIO%></span></div>
<div class="menu_izquierda">
<div style="float: left; text-align:left;" id="menu_izq" class="sdmenu">
      <div class="titulo_scroll">
        <span id="cabecera_menu_provincia"><%=anuncio.getProvincia().getNamelong()%></span>
        <div class="scroll" id="menu_provincia">
        <%Iterator itProvincias=lstProvincias.iterator();
        while(itProvincias.hasNext()){
        	Provincia provincia = (Provincia)itProvincias.next();
        %>
        <a href="javascript:seleccion('provincia','<%=provincia.getId()%>','<%=provincia.getNamelong()%>')"><%=provincia.getNamelong()%></a>
        <%}%>
        </div>
      </div>
      <div class="titulo_scroll">
        <span id="cabecera_menu_categoria"><%=anuncio.getCategoria().getName()%></span>
        <div class="scroll" id="menu_categoria">
        <%Iterator itCategorias=lstCategorias.iterator();
        while(itCategorias.hasNext()){
        	CategoriaAnuncio categoria = (CategoriaAnuncio)itCategorias.next();
        	if(categoria.getId()!=0){
        		if(categoria.getCategoriapadre().getId()==0){
               		%>
                    	<a href="javascript:alert('Elige una subcategoria')"><%=categoria.getName()%></a>
                    <%	
                	}else{
                	%>
                    	<a style="padding-left: 2em;" href="javascript:seleccion('categoria','<%=categoria.getId()%>','<%=categoria.getName()%>')"><%=categoria.getName()%></a>
                    <%	
                	}
        	}
       	}%>
        </div>
      </div>
      <div class="titulo_scroll">
        <span id="cabecera_menu_tipo_vendedor"><%=anuncio.getTipoVendedor().getName()%></span>
        <div class="scroll" id="menu_tipo_vendedor">
        <%Iterator itTiposVendedor=lstTiposVendedor.iterator();
        while(itTiposVendedor.hasNext()){
        	TipoVendedor tipoVendedor = (TipoVendedor)itTiposVendedor.next();
        	%>
            	<a href="javascript:seleccion('tipo_vendedor','<%=tipoVendedor.getId()%>','<%=tipoVendedor.getName()%>')"><%=tipoVendedor.getName()%></a>
            <%
       	}%>
        </div>
      </div>
      <div class="titulo_scroll">
        <span id="cabecera_menu_tipo_anuncio"><%=anuncio.getTipoAnuncio().getName()%></span>
        <div class="scroll" id="menu_tipo_anuncio">
        <%Iterator itTiposanuncio=lstTiposAnuncio.iterator();
        while(itTiposanuncio.hasNext()){
        	TipoAnuncio tipoAnuncio = (TipoAnuncio)itTiposanuncio.next();
        	%>
            	<a href="javascript:seleccion('tipo_anuncio','<%=tipoAnuncio.getId()%>','<%=tipoAnuncio.getName()%>')"><%=tipoAnuncio.getName()%></a>
            <%
       	}%>
        </div>
      </div>
    </div>
</div>

<div class="capa_insertar_anuncio">
<%=UtilidadesTexto.obtenerPlantillaModificarAnuncio(anuncio)%>
</div>
<div class="red_social_botton">
<div class="red_social_text"><%=TextConstant.SIGUENOS_EN%></div>
<div class="red_social_img"><a href="<%=TextConstant.URL_FACEBOOK%>"><%=TextConstant.IMAGEN_FACEBOOK%></a><a href="<%=TextConstant.URL_TWITTER%>"><%=TextConstant.IMAGEN_TWITTER%></a>

</div>
</div>
<div class="line_botton"></div>
<div class="pie_pagina_derecha"><%=TextConstant.COPYRIGHT%></div>
</div>
</form>

</body>
</html>


