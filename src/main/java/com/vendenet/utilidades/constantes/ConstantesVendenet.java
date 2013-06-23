/*
 * Creado el 03-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.utilidades.constantes;



/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public final class ConstantesVendenet {

	public static final int ANUNCIOS_POR_PAGINA = 10;
	public static final int ANUNCIOS_POR_PAGINA_AJAX = 50;
	public static final int ANUNCIOS_POR_PAGINA_INTRANET = 100;
	public static int ANYO_ACTUAL = 2011;
	public final static String COD_ERROR_CTRLBD_NULO = "HR-000001";
	public static final String DATA_SOURCE = "nomDataSource";
	public static final String EMAIL_FROM = "info@vendenet.net";
	public static final String EMAIL_WEBMASTER = "jgamann@gmail.com;darthpak@hotmail.com";
//	public static final String EMAIL_WEBMASTER = "jgamann@gmail.com";
	public static final String ficheroActividadWeb="/WEB-INF/actividadWeb.properties";
	public static final String ficheroNegocioAplicacion="/WEB-INF/negocioAplicacion.properties";
	public static final String ficheroHibernate="/WEB-INF/hibernate.cfg.xml";
	public static final int OPINIONES_MAX_EMAIL = 10;
	public static final String PAG_ERRORES = "/index.html";
	public static final int PAGINACION_PAGINA_MEDIA = 5;
	public static final int PAGINACION_PAGINA_MAX = 9;
	public static String paginaInicio = "";
	public static String PATH_REAL="";
	public static String PATH_ESTATICO= "";
	public static String RUTA_PDFS= "";
	public static String RUTA_DOCUMENTOS= "";
	public static String RUTA_IMAGENES= "";
	public static int TamanyoXFoto;
	public static int TamanyoYFoto;
	public static int TamanyoXFotoPeque;
	public static int TamanyoYFotoPeque;
	public static int TamanyoXFotoGrande;
	public static int TamanyoYFotoGrande;
	public static String URL_LOCALIZADOR_IP= "";
	
	
	public ConstantesVendenet() {
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
	}
	/**
	 * @return
	 */
	public static int getANYO_ACTUAL() {
		return ANYO_ACTUAL;
	}

	/**
	 * @param i
	 */
	public static void setANYO_ACTUAL(int i) {
		ANYO_ACTUAL = i;
	}

}