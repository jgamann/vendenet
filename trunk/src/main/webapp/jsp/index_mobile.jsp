<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.vendenet.negocio.entidad.TipoVendedor"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.vendenet.negocio.entidad.Provincia"%>
<%@page import="com.vendenet.negocio.entidad.CategoriaAnuncio"%>
<%@page import="com.vendenet.utilidades.constantes.TextConstant"%>
<%@page import="com.vendenet.negocio.entidad.Anuncio"%>
<%@page import="com.vendenet.utilidades.UtilidadesTexto"%>
<%@page import="com.vendenet.utilidades.constantes.ConstantesVendenet"%>
<%@page import="com.vendenet.negocio.entidad.CriterioOrden"%>
<%@page import="com.vendenet.utilidades.constantes.NumericConstant"%>
<%@page import="com.vendenet.negocio.entidad.TipoAnuncio"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Vendenet.net - La mejor web de compraventa en internet</title>
<meta name='keywords' content='anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<meta name='description' content='Web de Compra Venta en internet. anuncios, segundamano, segunda mano, clasificados, compraventa, comprar en internet, vender en internet, la mejor pagina de compraventa online, la mejor pagina para vender en internet, vendo, vendido, superventas, compro'/>
<script type="text/javascript" src="js/jquery.tooltip.js"></script>
<link href="estilos/estilosv33.css" rel="stylesheet" type="text/css" />
<link href="estilos/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="estilos/sdmenuv2.css" />
<script type="text/javascript" src="js/commonfunctionsv4.js"></script>
<script type="text/javascript" src="js/fecha_horav2.js"></script>
<script type="text/javascript" src="js/Tokenizer.js"></script>
<script type="text/javascript" src="js/jquery.autocompletev1.js"></script>

<script type="text/javascript" src="js/index_mobilev2.js"></script>
<script type="text/javascript" src="js/menu_izda.js"></script>

<%
CategoriaAnuncio categoriaSeleccionada=null;
Provincia provinciaSeleccionada=null;
TipoAnuncio tipoAnunciosSeleccionado=null;
TipoVendedor tipoVendedorSeleccionado=null;
CriterioOrden ordenSeleccionado=null;
boolean isIPhone=false;
if(session.getAttribute(TextConstant.KEY_CATEGORIA)!=null)categoriaSeleccionada=(CategoriaAnuncio)session.getAttribute(TextConstant.KEY_CATEGORIA);
if(session.getAttribute(TextConstant.KEY_PROVINCIA)!=null)provinciaSeleccionada=(Provincia)session.getAttribute(TextConstant.KEY_PROVINCIA);
if(session.getAttribute(TextConstant.KEY_TIPO_ANUNCIO)!=null)tipoAnunciosSeleccionado=(TipoAnuncio)session.getAttribute(TextConstant.KEY_TIPO_ANUNCIO);
if(session.getAttribute(TextConstant.KEY_TIPO_VENDEDOR)!=null)tipoVendedorSeleccionado=(TipoVendedor)session.getAttribute(TextConstant.KEY_TIPO_VENDEDOR);
if(session.getAttribute(TextConstant.KEY_CRITERIO_ORDEN)!=null)ordenSeleccionado=(CriterioOrden)session.getAttribute(TextConstant.KEY_CRITERIO_ORDEN);
if(session.getAttribute(TextConstant.KEY_IPHONE)!=null)isIPhone=((Boolean)session.getAttribute(TextConstant.KEY_IPHONE)).booleanValue();
Hashtable hResultados = (Hashtable)request.getAttribute("resultados");
List lstProvincias=(List)hResultados.get(TextConstant.KEY_PROVINCIAS);
List lstCategorias=(List)hResultados.get(TextConstant.KEY_CATEGORIAS);
List lstAnuncios=(List)hResultados.get("lstAnuncios");
List lstTiposAnuncio=(List)hResultados.get(TextConstant.KEY_TIPOS_ANUNCIO);
List lstTiposVendedor=(List)hResultados.get(TextConstant.KEY_TIPOS_VENDEDOR);
List lstCriteriosOrden=(List)hResultados.get(TextConstant.KEY_CRITERIOS_ORDEN);
int totalvisitas=(Integer)hResultados.get(TextConstant.KEY_TOTAL_VISITAS);
String patron="";
if(hResultados.get(TextConstant.PATRON_NO_AJAX)!=null)patron=(String)hResultados.get(TextConstant.PATRON_NO_AJAX);
int numPaginaActual=1;
if(hResultados.get(TextConstant.KEY_PAGINA_ACTUAL)!=null)numPaginaActual=((Integer)hResultados.get(TextConstant.KEY_PAGINA_ACTUAL)).intValue();
int numResultados=0;
if(hResultados.get(TextConstant.KEY_NUM_TOTAL)!=null)numResultados=((Integer)hResultados.get(TextConstant.KEY_NUM_TOTAL)).intValue();
/*if(request.getParameter("numPaginaActual")!=null)
	numPaginaActual=Integer.parseInt((String)request.getParameter("numPaginaActual"));*/
%>
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<form action="" id="formulario" name="formulario" method="post">
<input id="patron_recibido" type="hidden" value="<%=patron%>"/>
<input id="pagina" name="pagina" type="hidden" value="1"/>
<input type="hidden" id="accion" name="accion" value="inicio"/>
<input type="hidden" id="isSearch" name="isSearch" value="false"/>
<input type="hidden" id="subAccionFormu" name="subAccionFormu" value=""/>
<div id="contenedor">
<%=UtilidadesTexto.obtenerReloj()%>
<div class="line"></div>
<div class="capa_buscador">
<a href="<%=TextConstant.URL_INICIO%>" title="volver a la p&aacute;gina inicial" class="enlace_imagen">
<%=UtilidadesTexto.obtenerLogo()%>
</a>
<input align="middle" name="nombre" type="text" class="campo_texto_mobile" id="nombre"/>
<img id="mense" src="imagenes/info.png" class="class_boton_buscar" title="<%=TextConstant.MENSAJE_AYUDA_BUSCAR_HMTL%>"/>
<button name="boton" id="botonsearch" type="submit" class="class_boton_buscar"><img src="imagenes/buscar.png" alt="Buscar"/></button>
</div>
<div id="demo" class="menu_izquierda">
<div style="float: left; text-align:left;" id="menu_izq" class="sdmenu">
      <div class="titulo_scroll">
        <span>Provincia - <%=((provinciaSeleccionada!=null)&&(provinciaSeleccionada.getId()!=0))?provinciaSeleccionada.getNamelong():TextConstant.TODAS%>
        <%if(isIPhone==true){%>
	        <img src="imagenes/info.png" class="flechita" alt="" title="<%=TextConstant.MENSAJE_DEPLAZAMIENTO_PROVINCIA_IPHONE%>"/>
        <%}%>
        </span>
        <div class="scroll" id="menu_provincias">
        <a href="javascript:cargarFiltro('Provincia',0)"><%=TextConstant.TODAS%></a>
        <%Iterator itProvincias=lstProvincias.iterator();
        while(itProvincias.hasNext()){
        	Provincia provincia = (Provincia)itProvincias.next();
        %>
        <a href="javascript:cargarFiltro('Provincia',<%=provincia.getId()%>)"><%=provincia.getNamelong()%></a>
        <%}%>
        </div>
      </div>
      <div class="titulo_scroll">
        <span><%=TextConstant.CATEGORIA_GUION%><%=((categoriaSeleccionada!=null)&&(categoriaSeleccionada.getId()!=0))?categoriaSeleccionada.getName():TextConstant.TODAS%>
        <%if(isIPhone==true){%>
	        <img src="imagenes/info.png" class="flechita" alt="" title="<%=isIPhone==true?TextConstant.MENSAJE_DEPLAZAMIENTO_CATEGORIA_IPHONE:""%>"/>
        <%}%>
        </span>
        <div class="scroll" id="menu_categorias">
        <a href="javascript:cargarFiltro('Categoria',0)"><%=TextConstant.TODAS%></a>
        <%Iterator itCategorias=lstCategorias.iterator();
        while(itCategorias.hasNext()){
        	CategoriaAnuncio categoria = (CategoriaAnuncio)itCategorias.next();
        	if(categoria.getId()!=0){
        		if(categoria.getCategoriapadre().getId()==0){
               		%>
                    	<a href="javascript:cargarFiltro('Categoria',<%=categoria.getId()%>)"><%=categoria.getName()%></a>
                    <%	
                	}else{
                	%>
                    	<a style="padding-left: 2em;" href="javascript:cargarFiltro('Categoria',<%=categoria.getId()%>)"><%=categoria.getName()%></a>
                    <%	
                	}
        	}
       	}%>
        </div>
      </div>
      
      <div class="titulo_scroll">
        <span><%=TextConstant.TIPO_ANUNCIO_GUION%><%=((tipoAnunciosSeleccionado!=null)&&(tipoAnunciosSeleccionado.getId()!=0))?tipoAnunciosSeleccionado.getName():TextConstant.TODOS%></span>
        <div class="scroll" id="menu_tipos_anuncio">
        <a href="javascript:cargarFiltro('TipoAnuncio',0)"><%=TextConstant.TODOS%></a>
        <%Iterator itTiposAnuncio=lstTiposAnuncio.iterator();
        while(itTiposAnuncio.hasNext()){
        	TipoAnuncio tipoAnuncio = (TipoAnuncio)itTiposAnuncio.next();
            %>
            <a href="javascript:cargarFiltro('TipoAnuncio',<%=tipoAnuncio.getId()%>)"><%=tipoAnuncio.getName()%></a>
            <%
       	}%>
        </div>
      </div>
      
      <div class="titulo_scroll">
        <span><%=TextConstant.TIPO_VENDEDOR_GUION%><%=((tipoVendedorSeleccionado!=null)&&(tipoVendedorSeleccionado.getId()!=0))?tipoVendedorSeleccionado.getName():TextConstant.TODAS%></span>
        <div class="scroll" id="menu_tipos_vendedor">
        <a href="javascript:cargarFiltro('TipoVendedor',0)"><%=TextConstant.TODOS%></a>
        <%Iterator itTiposVendedor=lstTiposVendedor.iterator();
        while(itTiposVendedor.hasNext()){
        	TipoVendedor tipoVendedor = (TipoVendedor)itTiposVendedor.next();
        	 %>
             <a href="javascript:cargarFiltro('TipoVendedor',<%=tipoVendedor.getId()%>)"><%=tipoVendedor.getName()%></a>
             <%
       	}%>
        </div>
      </div>
      <div class="titulo_scroll">
        <span><%=TextConstant.ORDENADOR_POR_GUION%><%=((ordenSeleccionado!=null)&&(ordenSeleccionado.getId()!=0))?ordenSeleccionado.getName():TextConstant.FECHA%><img src="<%=((ordenSeleccionado!=null)&&(ordenSeleccionado.getId()!=0))?(ordenSeleccionado.isAsc()?TextConstant.FLECHA_ARRIBA_GRIS:TextConstant.FLECHA_ABAJO_GRIS):TextConstant.FLECHA_ABAJO_GRIS%>"></img></span>
        <div class="scroll" id="menu_criteriosorden">
        <%Iterator itCriterios=lstCriteriosOrden.iterator();
        while(itCriterios.hasNext()){
        	CriterioOrden criterio = (CriterioOrden)itCriterios.next();
        	if(criterio.getId()!=0){
        		if((ordenSeleccionado!=null)&&(criterio.getId()==ordenSeleccionado.getId())){
        			if(ordenSeleccionado.isAsc()){
        				%><a href="javascript:cargarFiltro('CriterioOrden',<%=criterio.getId()%>, false)"><img class="flechita"  src="<%=TextConstant.FLECHA_ABAJO%>"></img><%=criterio.getName()%></a><%
        			}else{
        				%><a href="javascript:cargarFiltro('CriterioOrden',<%=criterio.getId()%>, true)"><img class="flechita"  src="<%=TextConstant.FLECHA_ARRIBA%>"></img><%=criterio.getName()%></a><%
        			}
        		}else{
        			%><a href="javascript:cargarFiltro('CriterioOrden',<%=criterio.getId()%>, true)"><img class="flechita"  src="<%=TextConstant.FLECHA_ARRIBA%>"></img><%=criterio.getName()%></a><%
        		}
        	}
       	}%>
        </div>
      </div>
    </div>
</div>
<div class="capa_resultados">
<div class="capa_resultados_borde" >
<%if(numResultados>0){%>
<div class="letramediana"><span style="color:#ffffff"><%=TextConstant.SE_HAN_ENCONTRADO %></span><span style="color:#000000"><%=numResultados%></span><span style="color:#ffffff"><%=new StringBuffer(TextConstant.SPACE).append(TextConstant.ANUNCIOS).toString()%></span>
<div id="ponanuncio" onclick="window.location.href='ServletControlador?accion=ponerAnuncio'">
<span class="label_gris">PON TU ANUNCIO</span>GRATIS
<span style="letter-spacing: 0px; font-size:0.6em; color:#ffffff"><%=new StringBuffer(TextConstant.SPACE).append(TextConstant.TOTAL_VISITAS).append(TextConstant.SPACE).append(totalvisitas).toString()%></span>
</div>
</div>
<div class="red_social_text"><%=TextConstant.SIGUENOS_EN%></div>
<div class="red_social_img"><a href="<%=TextConstant.URL_FACEBOOK%>"><%=TextConstant.IMAGEN_FACEBOOK%></a><a href="<%=TextConstant.URL_TWITTER%>"><%=TextConstant.IMAGEN_TWITTER%></a>

</div>
<%}%>
	<ul class="list_resultados">
	<%
	if(lstAnuncios.size()>0){
		for(int i=0;i<lstAnuncios.size();i++){
			Anuncio anuncio=(Anuncio)lstAnuncios.get(i);
		%>
	    <li class="pinchable"><a href="ServletControlador?accion=verAnuncio&amp;idAnuncio=<%=anuncio.getId()%>"><%=UtilidadesTexto.obtenerFilaResultadoHTML(anuncio)%></a></li>
	    <%}
	}else{%>
	    <li><%=UtilidadesTexto.obtenerFilaSinResultados()%></li>
	<%}%>
	</ul>
	<div class="paginado_centro">
	<%if(numPaginaActual>1){%>
	    <a href="javascript:paginar(<%=(numPaginaActual-1)%>);"><b>Anterior</b></a>
	<%}%>
	<%
	int paginas = 0;
	if(numResultados>0){ 
		paginas=(numResultados/ConstantesVendenet.ANUNCIOS_POR_PAGINA);
		if(numResultados%ConstantesVendenet.ANUNCIOS_POR_PAGINA>0)paginas++;
		if(numPaginaActual>ConstantesVendenet.PAGINACION_PAGINA_MEDIA){
			if((ConstantesVendenet.PAGINACION_PAGINA_MAX+(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA))<=paginas){
				for(int i=(1+(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA));i<=((ConstantesVendenet.PAGINACION_PAGINA_MAX)+(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA)) && i<=paginas;i++){
					if(numPaginaActual==(i)){
						%>
						<a id="currentPageNum" title="Pag.<%=(i)%>" href="#"><b><%=i%><%if(i<((ConstantesVendenet.PAGINACION_PAGINA_MAX)+(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA))&&i<paginas){%></b></a>
				        <%
				        }
				    	}else{
				    	%>
				        <a title="Pag.<%=(i)%>" href="javascript:paginar(<%=(i)%>);"><b><%=(i)%></b></a><%if(i<((ConstantesVendenet.PAGINACION_PAGINA_MAX)+(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA))&&i<paginas){%>
				        <%
				        }
				    	}
				}
			}else{
				int ratio = (ConstantesVendenet.PAGINACION_PAGINA_MAX+(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA)-paginas);//Ratio es cuantas dejo de ver por la derecha porque llego al numero maximo, y por tanto, tengo qeu visualizarlas por la izquierda
				//controlar eso si, que si por la izquierda no hay (porque es menor que 0 pues no visualizar)
				int indice=0;
				if((numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA-ratio)>=0){
					indice=(numPaginaActual-ConstantesVendenet.PAGINACION_PAGINA_MEDIA-ratio);
				}
				for(int i=1+(indice);i<=paginas;i++){
					if(numPaginaActual==(i)){
						%>
						<a href="#" id="currentPageNum"><b><%=(i)%></b></a><%if(i<paginas){%>
				        <%
				        }
				    	}else{
				    	%>
				        <a href="javascript:paginar(<%=(i)%>);"><b><%=(i)%></b></a><%if(i<paginas){%>
				        <%}
				    	}
				}
			}
		}else{
			for(int i=1;(i<=ConstantesVendenet.PAGINACION_PAGINA_MAX && i<=paginas);i++){
				if(numPaginaActual==(i)){
					%>
					<a href="#" id="currentPageNum"><b><%=(i)%></b></a><%if((i<paginas)&&(i<ConstantesVendenet.PAGINACION_PAGINA_MAX)){%>
			        <%}
			    	}else{
			    	%>
			        <a href="javascript:paginar(<%=(i)%>);"><b><%=(i)%></b></a><%if((i<paginas)&&(i<ConstantesVendenet.PAGINACION_PAGINA_MAX)){%>
			        <%}
			    	}
			}
		}
	    
	}%>
	<%if((numPaginaActual<paginas)&&(paginas>1)){%>
		<a href="javascript:paginar(<%=(numPaginaActual+1)%>);"><b>Siguiente</b></a>
	<%}%>
	</div>
</div>
</div>
<div class="line_botton_iconos"></div>
<div class="pie_pagina_derecha"><%=TextConstant.COPYRIGHT%></div>
</div>
</form>

</body>
</html>


