package com.vendenet.utilidades;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Critica;
import com.vendenet.negocio.entidad.HistoricoBajas;
import com.vendenet.utilidades.constantes.CarEspecialesConstant;
import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;

public class UtilidadesTexto {
	public static StringBuffer obtenerCadenaBuscador(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(UtilidadesAnuncio.obtenerFotoPrincipal(anuncio));
		dev.append(TextConstant.ALMOHADILLA);
		dev.append(anuncio.getName()).append(TextConstant.ALMOHADILLA);
		dev.append(
				resumenCuerpo(anuncio.getCuerpo().replace("\n",
						TextConstant.SPACE))).append(TextConstant.ALMOHADILLA);
		dev
				.append(TextConstant.APERTURA_DIV)
				.append(
						UtilidadesFecha.formatearFechaDDMMYY(anuncio
								.getFechaAlta()))
				.append(TextConstant.CIERRE_DIV)
				.append(TextConstant.APERTURA_DIV)
				.append(UtilidadesNumericos.limpiarDecimal(anuncio.getPrecio()))
				.append(TextConstant.HTML_EURO).append(TextConstant.CIERRE_DIV)
				.append(TextConstant.APERTURA_DIV).append(
						anuncio.getProvincia().getName()).append(
						TextConstant.CIERRE_DIV);
		dev.append(TextConstant.ALMOHADILLA).append(anuncio.getId());
		return dev;
	}

	public static String sustituirLiterales(String cadena, String[] literales) {
		if (literales != null) {
			for (int i = 0; i < literales.length; i++) {
				cadena = cadena.replace("$" + i, literales[i]);
			}
		}
		return cadena;
	}

	private static String resumenCuerpo(String cuerpo) {
		if ((cuerpo != null)
				&& (cuerpo.length() > NumericConstant.LENGTH_RESUMEN_ANUNCIO))
			return new StringBuffer(cuerpo.substring(0,
					NumericConstant.LENGTH_RESUMEN_ANUNCIO)).append(
					TextConstant.PUNTOS_SUSPENSIVOS).toString();
		return cuerpo;
	}

	public static StringBuffer obtenerFilaResultadoHTML(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_RESULTADOS);
		// *****************************************************FECHA HORA Y
		// PROVINCIA*************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_DIEZ);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO).append(
				UtilidadesFecha.formatearFechaDDMMYY(anuncio.getFechaAlta()))
				.append(TextConstant.CIERRE_DIV).append(
						TextConstant.APERTURA_DIV_CENTRADO).append(
						UtilidadesFecha.formatearFechaHHmm(anuncio
								.getFechaAlta())).append(
						TextConstant.CIERRE_DIV).append(
						TextConstant.APERTURA_DIV_CENTRADO).append(
						anuncio.getProvincia().getName()).append(
						TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		// ***************************************************************************************************************************************************************
		// ****************************************************FOTOGRAFIA*************************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_DIEZ);
		// Si tiene Adjuntos se pondra la foto principal
		dev.append("<img border='0px' src='documentos/"
				+ UtilidadesAnuncio.obtenerFotoPrincipal(anuncio) + "'/>");
		dev.append(TextConstant.CIERRE_DIV);
		// ***************************************************************************************************************************************************************
		// ****************************************************TITULO
		// ANUNCIO*********************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_CINCUENTA);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_IZQUIERDA_NEGRITA);
		dev.append(anuncio.getName().toUpperCase());
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		// ***************************************************************************************************************************************************************
		// ****************************************************PRECIO*****************************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_QUINCE);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_DERECHA_NEGRITA);
		dev.append(UtilidadesNumericos.limpiarDecimal(anuncio.getPrecio()))
				.append(TextConstant.HTML_EURO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		// ***************************************************************************************************************************************************************
		// ****************************************************CATEGORIA Y ESTRELLITAS************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_QUINCE);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_DERECHA_REPUTACION);
		int estrellitas = UtilidadesNumericos.convertirAInt(anuncio.getMediaCriticas());
		int numOpiniones = anuncio.getNumCriticas();
		dev.append(UtilidadesTexto.obtenerEstrallitas(estrellitas,numOpiniones,anuncio.getMediaCriticas()));
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_DERECHA);
		dev.append(anuncio.getCategoria().getName());
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		// ***************************************************************************************************************************************************************
		dev.append(TextConstant.CIERRE_DIV);
		return dev;
	}

	private static StringBuffer obtenerEstrallitas(int estrellitas, int numOpiniones, double media ) {
		StringBuffer dev = new StringBuffer("");
		String star = "";
		String half_star = "";
		if(media < 2){
			star=TextConstant.IMAGEN_STAR_RED;
			half_star=TextConstant.IMAGEN_HALF_STAR_RED;
		}else if(media > 4){
			star=TextConstant.IMAGEN_STAR_GREEN;
			half_star=TextConstant.IMAGEN_HALF_STAR_GREEN;
		}else{
			star=TextConstant.IMAGEN_STAR_YELLOW;
			half_star=TextConstant.IMAGEN_HALF_STAR_YELLOW;
		}
		
		for (int i = 0; i < estrellitas; i++) {
			dev.append("<img src='imagenes/");
			dev.append(star);
			dev.append("' title='");
			dev.append(numOpiniones);
			dev.append(" opiniones ");
			dev.append("(valoraci&oacute;n ");
			dev.append(UtilidadesNumericos.formatear2Decimales(media));
			dev.append(")'></img>");
		}
		if(media % 1 > 0){
			dev.append("<img src='imagenes/");
			dev.append(half_star);
			dev.append("' title='");
			dev.append(numOpiniones);
			dev.append(" opiniones ");
			dev.append("(valoraci&oacute;n ");
			dev.append(UtilidadesNumericos.formatear2Decimales(media));
			dev.append(")'></img>");
			estrellitas++;
		}
		for (double i = estrellitas; i < 5; i++) {
			dev.append("<img src='imagenes/");
			dev.append(TextConstant.IMAGEN_STAR_GREY);
			dev.append("' title='");
			dev.append(numOpiniones);
			dev.append(" opiniones ");
			dev.append("(valoraci&oacute;n ");
			dev.append(UtilidadesNumericos.formatear2Decimales(media));
			dev.append(")'></img>");
		}
		return dev;
	}

	public static StringBuffer obtenerFilaSinResultados() {
		StringBuffer dev = new StringBuffer();
		// ***************************************************************************************************************************************************************
		// ****************************************************TITULO
		// ANUNCIO*********************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_SIN_RESULTADOS);
		dev.append(TextConstant.SIN_RESULTADOS);
		dev.append(TextConstant.CIERRE_DIV);

		return dev;
	}

	public static String obtenerFichaAnuncio(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAPA_FICHA_ANUNCIO);
		// ****************************************************TITULO*****************************************************************************************************
		dev.append(TextConstant.APERTURA_DIV_CAMPO_TITULO_ANUNCIO);
		dev.append(anuncio.getName().toUpperCase());
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_FECHA_HORA_ANUNCIO);
		dev.append(UtilidadesFecha.formatearFechaDDMMMMHHmm(anuncio
				.getFechaAlta()));
		dev.append(TextConstant.PUNTO);
		dev.append(TextConstant.SPACE);
		dev.append(anuncio.getProvincia().getNamelong());
		dev.append(TextConstant.COMA);
		dev.append(anuncio.getCategoria().getName());
		dev.append(TextConstant.PUNTO);
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_TEXTO_ANUNCIO);
		dev.append(FormatearTextoAHTML(anuncio.getCuerpo()));
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static StringBuffer FormatearTextoAHTML(String cuerpo) {
		StringBuffer dev = new StringBuffer();
		if (cuerpo != null) {
			StringTokenizer st = new StringTokenizer(cuerpo,
					TextConstant.SALTO_LINEA);
			if (st.countTokens() > 0) {
				while (st.hasMoreTokens()) {
					dev.append("<div style='overflow: hidden;'>");
					dev.append(st.nextElement());
					dev.append("</div>");
				}
			}
		}
		return dev;
	}

	public static String obtenerDatosPieAnuncio(Anuncio anuncio,
			int totalanuncios) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_TEXTO_BLANCO_ANUNCIO);
		dev.append(obtenerPrecioAnuncio(anuncio));
		dev.append(obtenerAreaGestion(anuncio));
		dev.append(obtenerVisitasAnuncio(anuncio));
		dev.append(obtenerContactarTfno(anuncio, totalanuncios));
		dev.append(obtenerContactarMail(anuncio));
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerDatosAreaGestion(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_TEXTO_BLANCO_AREA_GESTION);
		dev.append(obtenerAreaGestion(anuncio));
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	private static String obtenerAreaGestion(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_GRIS);
		dev.append(TextConstant.AREA_GESTION);
		dev.append(TextConstant.HTML_SALTO_LINEA);
		dev.append(obtenerLinkGestion(TextConstant.ACTION_BORRAR_ANUNCIO,
				anuncio.getId(), TextConstant.BORRAR_ANUNCIO));
		dev.append(TextConstant.HTML_SALTO_LINEA);
		dev.append(obtenerLinkGestion(TextConstant.ACTION_MODIFICAR_ANUNCIO,
				anuncio.getId(), TextConstant.MODIFICAR_ANUNCIO));
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	private static String obtenerLinkGestion(String accion, int id, String texto) {
		StringBuffer dev = new StringBuffer();
		dev.append("<a href='ServletControlador?accion=areaGestion&subAccion=");
		dev.append(accion);
		dev.append("&idAnuncio=");
		dev.append(id);
		dev.append("'>");
		dev.append(texto);
		dev.append(TextConstant.CIERRE_A);
		return dev.toString();
	}

	public static String obtenerContactarMail(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CONTACTO_MAIL_ANUNCIO_A);
		dev.append(linkMostrador(TextConstant.CONTACTA_POR_EMAIL,
				"campo_campo_contacto_mail_anuncio_b"));
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_CONTACTO_MAIL_ANUNCIO_B);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO);
		dev.append("<label for='txtNombre'>");
		dev.append(TextConstant.NOMBRE);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CAMPO_FORMULARIO);
		dev
				.append("<input type='text' id='txtNombre' name='txtNombre' class='cajita'/>");
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO);
		dev.append("<label for='txtEmail'>");
		dev.append(TextConstant.EMAIL);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CAMPO_FORMULARIO);
		dev
				.append("<input type='text' id='txtEmail' name='txtEmail' class='cajita'/>");
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO);
		dev.append("<label for='txtTfno'>");
		dev.append(TextConstant.TFNO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CAMPO_FORMULARIO);
		dev
				.append("<input type='text' id='txtTfno' name='txtTfno' class='cajita'/>");
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO);
		dev.append("<label for='txtPregunta'>");
		dev.append(TextConstant.PREGUNTA);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CAMPO_FORMULARIO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV);
		dev
				.append("<textarea id='txtPregunta' name='txtPregunta' class='textarea_pregunta'></textarea>");
		dev
				.append("<label class='campo_label_formulario' id='caracteres'>300 caracteres</label>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV);
		dev
				.append("<input type='button' value='Enviar' onclick='javascript:enviarConsulta();'/>");
		dev.append("<label for='formulario_password_2'>");
		dev
				.append("<input name='check_condiciones' type='checkbox' id='check_condiciones' value='checkbox'/>");
		dev
				.append("<label for='check_condiciones' style='color:#666;'>Acepto las <a href='javascript:condiciones();'>condiciones de uso.</a></label>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerContactarTfno(Anuncio anuncio,
			int numeroAnuncios) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CONTACTO_TFNO_ANUNCIO_A);
		dev.append(linkMostrador(TextConstant.VISUALIZAR_DATOS_ANUNCIANTE,
				"campo_campo_contacto_tfno_anuncio_b"));
		String[] literales = { anuncio.getTipoVendedor().getName(),
				new Integer(numeroAnuncios).toString() };
		dev
				.append(sustituirLiterales(TextConstant.ANUNCIOS_CLIENTE,
						literales));
		dev.append(TextConstant.APERTURA_DIV_FLOAT_LEFT);
		int estrellitas = UtilidadesNumericos.convertirAInt(anuncio.getMediaCriticas());
		int numOpiniones = anuncio.getNumCriticas();
		dev.append(UtilidadesTexto.obtenerEstrallitas(estrellitas,numOpiniones,anuncio.getMediaCriticas()));
		dev.append(TextConstant.HTMLBLANK);
		dev.append(TextConstant.CIERRE_DIV);
		String[] literales_ = { "ServletControlador?accion=opinar&idAnuncio="
				+ anuncio.getId() };
		dev.append(sustituirLiterales(TextConstant.OPINA_SOBRE_VENDEDOR,
				literales_));
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CONTACTO_TFNO_ANUNCIO_B);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO);
		dev.append(TextConstant.NOMBRE);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CAMPO_FORMULARIO);
		dev.append(anuncio.getCliente().getName());
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO);
		dev.append(TextConstant.SPACE).append(TextConstant.TFNO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CAMPO_CAMPO_FORMULARIO);
		dev.append(anuncio.getCliente().getTelefono());
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.APERTURA_DIV_CAMPO_LABEL_FORMULARIO_LARGO);
		// String[] literales = {new Integer(numeroAnuncios).toString()};
		// dev.append(TextConstant.SPACE).append(sustituirLiterales(TextConstant.ANUNCIOS_CLIENTE,literales));
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerPrecioAnuncio(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_PRECIO_ANUNCIO);
		dev.append(UtilidadesNumericos.limpiarDecimal(anuncio.getPrecio()))
				.append(TextConstant.HTML_EURO);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerVisitasAnuncio(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_VISITAS_ANUNCIO);
		dev.append(TextConstant.VISITAS).append(TextConstant.DOS_PUNTOS)
				.append(anuncio.getVisitas().size());
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static boolean isTrue(String asc) {
		try {
			if (new Boolean(asc).booleanValue() == true)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static String linkMostrador(String cuerpo, String capa_oculta) {
		StringBuffer dev = new StringBuffer();
		dev.append("<a href='javascript:mostrar(\"" + capa_oculta + "\")'>");
		dev.append(cuerpo);
		dev.append(TextConstant.CIERRE_A);
		return dev.toString();
	}

	public static String obtenerPlantillaAltaAnuncio() {
		StringBuffer dev = new StringBuffer();
		// ****************************** NOMBRE
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_nombre'>");
		dev.append(TextConstant.NOMBRE);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='35' maxlength='60' id='formulario_nombre' name='formulario_nombre'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** EMAIL
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_email'>");
		dev.append(TextConstant.EMAIL);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='35' maxlength='60' id='formulario_email' name='formulario_email'/>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA_PEQUE);
		dev.append(TextConstant.MENSAJE_NO_SPAM);
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TELEFONO
		// ***************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_tfno'>");
		dev.append(TextConstant.TFNO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='15' maxlength='16' id='formulario_tfno' name='formulario_tfno'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TITULO
		// *****************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_titulo'>");
		dev.append(TextConstant.TITULO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' style='width:100%' size='60' maxlength='60' id='formulario_titulo' name='formulario_titulo'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TEXTO ANUNCIO
		// ***********************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_cuerpo'>");
		dev.append(TextConstant.TEXTO_ANUNCIO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<textarea id='formulario_cuerpo' class='textarea_cuerpo' name='formulario_cuerpo'></textarea>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** PRECIO
		// *****************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_precio'>");
		dev.append(TextConstant.PRECIO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='15' maxlength='16' id='formulario_precio' name='formulario_precio'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** FOTOS
		// ******************************************
		dev.append(obtenerFotografiaPlantillaAnuncio());

		// ****************************** BOTONERA
		// ***************************************
		dev.append(obtenerBotonera());

		return dev.toString();

	}

	public static String obtenerPlantillaModificarAnuncio(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		// ****************************** NOMBRE
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_nombre'>");
		dev.append(TextConstant.NOMBRE);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='35' maxlength='60' id='formulario_nombre' name='formulario_nombre' value='"
						+ anuncio.getCliente().getName() + "'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** EMAIL
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_email'>");
		dev.append(TextConstant.EMAIL);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='35' maxlength='60' id='formulario_email' name='formulario_email' value='"
						+ anuncio.getCliente().getEmail() + "'/>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA_PEQUE);
		dev.append(TextConstant.MENSAJE_NO_SPAM);
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TELEFONO
		// ***************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_tfno'>");
		dev.append(TextConstant.TFNO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='15' maxlength='16' id='formulario_tfno' name='formulario_tfno' value='"
						+ anuncio.getCliente().getTelefono() + "'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TITULO
		// *****************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_titulo'>");
		dev.append(TextConstant.TITULO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' style='width:100%' size='60' maxlength='60' id='formulario_titulo' name='formulario_titulo' value='"
						+ anuncio.getName() + "'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TEXTO ANUNCIO
		// ***********************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_cuerpo'>");
		dev.append(TextConstant.TEXTO_ANUNCIO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<textarea id='formulario_cuerpo' class='textarea_cuerpo' name='formulario_cuerpo'>"
						+ anuncio.getCuerpo() + "</textarea>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** PRECIO
		// *****************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_precio'>");
		dev.append(TextConstant.PRECIO);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='15' maxlength='16' id='formulario_precio' name='formulario_precio' value='"
						+ UtilidadesNumericos.limpiarDecimal(anuncio
								.getPrecio()) + "'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** FOTOS
		// ******************************************
		dev.append(obtenerFotografiaPlantillaAnuncio(anuncio));

		// ****************************** BOTONERA
		// ***************************************
		dev.append(obtenerBotoneraModificacion());

		return dev.toString();

	}

	private static StringBuffer obtenerBotonera() {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_NOVENTA_MARGEN_DERECHA_IZQUIERDA);
		dev
				.append("<input type='button' onclick='javascript:guardarAnuncio();' value='Siguiente'/>");
		dev.append(TextConstant.APERTURA_LABEL_ROJO);
		dev.append(TextConstant.REVISAR_CAMPOS_ROJOS);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		return dev;
	}

	private static StringBuffer obtenerBotoneraOpinion() {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_NOVENTA_MARGEN_DERECHA_DERECHA);
		dev.append(TextConstant.APERTURA_LABEL_ROJO);
		dev.append(TextConstant.REVISAR_CAMPOS_ROJOS_OPINION);
		dev.append(TextConstant.CIERRE_LABEL);
		dev
				.append("<input type='button' onclick='javascript:enviarOpinion();' value='Publicar opini&oacute;n'/>");
		dev.append("<label for='formulario_password_2'>");
		dev
				.append("<input name='check_condiciones' type='checkbox' id='check_condiciones' value='checkbox'/>");
		dev
				.append("<label for='check_condiciones' style='color:#666;'>Acepto las <a href='javascript:condiciones();'>condiciones de uso.</a></label>");
		dev.append(TextConstant.CIERRE_DIV);
		return dev;
	}

	private static StringBuffer obtenerBotoneraModificacion() {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_NOVENTA_MARGEN_DERECHA_IZQUIERDA);
		dev
				.append("<input type='button' onclick='javascript:modificarAnuncio();' value='Modificar'/>");
		// ****************************** CONDICIONES LEGALES
		// ****************************
		dev.append("<label for='formulario_password_2'>");
		dev
				.append("<input name='check_condiciones' type='checkbox' id='check_condiciones' value='checkbox'/>");
		dev
				.append("<label for='check_condiciones' style='color:#666;'>Acepto las <a href='javascript:condiciones();'>condiciones de uso.</a></label>");

		// *******************************************************************************
		// dev.append(TextConstant.APERTURA_LABEL_ROJO);
		// dev.append(TextConstant.REVISAR_CAMPOS_ROJOS);
		// dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		return dev;

	}

	private static StringBuffer obtenerFotografiaPlantillaAnuncio() {
		StringBuffer dev = new StringBuffer();
		// ************************ EXPLORADORES DE WINDOWS
		// *****************************
		for (int i = 0; i < NumericConstant.FOTO_POR_ANUNCIO; i++) {
			dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
			dev.append("<label for='iframe").append((i + 1)).append("'>")
					.append(TextConstant.FOTOGRAFIA).append(TextConstant.SPACE)
					.append((i + 1));
			dev.append(TextConstant.CIERRE_LABEL);
			dev.append(TextConstant.CIERRE_DIV);
			dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
			dev
					.append("<iframe id='iframe_")
					.append((i + 1))
					.append("' name='")
					.append((i + 1))
					.append(
							"' frameborder='0' style='padding:0px; margin:0px; border: none; ' scrolling='no' width='195' height='24' src='ServletControlador?accion=gestorArchivos'></iframe>");
			dev.append(TextConstant.CIERRE_DIV);
		}
		// ************************ PRELIMINARES DE IMAGENES SUBIDAS
		// ********************
		dev.append(TextConstant.APERTURA_DIV_NOVENTA_MARGEN_DERECHA_IZQUIERDA);
		for (int i = 0; i < NumericConstant.FOTO_POR_ANUNCIO; i++) {
			dev.append(TextConstant.APERTURA_DIV_IMAGEN_LOADING);
			dev.append("<label class='label_img_loading' for='img_loading_")
					.append((i + 1)).append("'>").append(
							TextConstant.FOTOGRAFIA).append(TextConstant.SPACE)
					.append((i + 1));
			dev.append(TextConstant.CIERRE_LABEL);
			dev
					.append(
							"<img style='border:#ffffff solid 1px; margin-botton:5px;' id='img_loading_")
					.append((i + 1)).append(
							"' src='documentos/p_0.jpg' alt='Sin foto' />");
			dev.append(TextConstant.CIERRE_DIV);
		}
		dev.append(TextConstant.CIERRE_DIV);

		return dev;
	}

	private static StringBuffer obtenerFotografiaPlantillaAnuncio(
			Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		int numFotos = 0;
		if (anuncio != null && anuncio.getAdjuntos() != null)
			numFotos = anuncio.getAdjuntos().size();
		// ************************ EXPLORADORES DE WINDOWS
		// *****************************
		for (int i = 0; i < NumericConstant.FOTO_POR_ANUNCIO; i++) {
			dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
			dev.append("<label for='iframe").append((i + 1)).append("'>")
					.append(TextConstant.FOTOGRAFIA).append(TextConstant.SPACE)
					.append((i + 1));
			dev.append(TextConstant.CIERRE_LABEL);
			dev.append(TextConstant.CIERRE_DIV);
			dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
			if (i < numFotos && anuncio.getAdjuntos().get(i) instanceof Adjunto) {
				dev
						.append("<iframe id='iframe_")
						.append((i + 1))
						.append("' name='")
						.append((i + 1))
						.append(
								"' frameborder='0' style='padding:0px; margin:0px; border: none; ' scrolling='no' width='195' height='24' src='ServletControlador?accion=gestorArchivos&idArchivo="
										+ ((Adjunto) anuncio.getAdjuntos().get(
												i)).getId() + "'></iframe>");
			} else {
				dev
						.append("<iframe id='iframe_")
						.append((i + 1))
						.append("' name='")
						.append((i + 1))
						.append(
								"' frameborder='0' style='padding:0px; margin:0px; border: none; ' scrolling='no' width='195' height='24' src='ServletControlador?accion=gestorArchivos'></iframe>");
			}
			dev.append(TextConstant.CIERRE_DIV);
		}
		// ************************ PRELIMINARES DE IMAGENES SUBIDAS
		// ********************
		dev.append(TextConstant.APERTURA_DIV_NOVENTA_MARGEN_DERECHA_IZQUIERDA);
		for (int i = 0; i < NumericConstant.FOTO_POR_ANUNCIO; i++) {
			dev.append(TextConstant.APERTURA_DIV_IMAGEN_LOADING);
			dev.append("<label class='label_img_loading' for='img_loading_")
					.append((i + 1)).append("'>").append(
							TextConstant.FOTOGRAFIA).append(TextConstant.SPACE)
					.append((i + 1));
			dev.append(TextConstant.CIERRE_LABEL);
			if (i < numFotos) {
				Adjunto adjTemp = (Adjunto) anuncio.getAdjuntos().get(i);
				dev
						.append(
								"<img style='border:#ffffff solid 1px; margin-botton:5px;' id='img_loading_")
						.append((i + 1)).append(
								"' src='documentos/"
										+ UtilidadesAdjunto
												.obtenerPathFotoPeque(adjTemp
														.getPath())
										+ "' alt='Fotografia" + (i + 1)
										+ "' />");
			} else {
				dev
						.append(
								"<img style='border:#ffffff solid 1px; margin-botton:5px;' id='img_loading_")
						.append((i + 1)).append(
								"' src='documentos/p_0.jpg' alt='Sin foto' />");
			}
			dev.append(TextConstant.CIERRE_DIV);
		}
		dev.append(TextConstant.CIERRE_DIV);

		return dev;
	}

	public static String obtenerPrevisualizacionAnuncio(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);

		// ****************************** REVISION
		// ****************************************
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(TextConstant.REVISA_EL_ANUNCIO);
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** NOMBRE
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.NOMBRE);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getCliente().getName());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TELEFONO
		// ****************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.TFNO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getCliente().getTelefono());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TIPO DE VENDEDOR
		// ********************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.TIPO_VENDEDOR);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getTipoVendedor().getName());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TEXTO ANUNCIO
		// ***********************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.TEXTO_ANUNCIO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getTipoAnuncio().getName());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** CATEGORIA
		// ***************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.CATEGORIA);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getCategoria().getName());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** PROVINCIA
		// ***************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.PROVINCIA);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getProvincia().getName());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TITULO
		// *****************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.TITULO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getName());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** TEXTO ANUNCIO
		// ***********************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.TEXTO_ANUNCIO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(FormatearTextoAHTML(anuncio.getCuerpo()));
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** PRECIO
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.PRECIO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(UtilidadesNumericos.limpiarDecimal(anuncio.getPrecio()))
				.append(TextConstant.HTML_EURO);
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** EMAIL
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append(TextConstant.EMAIL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev.append(anuncio.getCliente().getEmail());
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** PASSWORD
		// ***************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append("<label for='formulario_password_1'>");
		dev.append(TextConstant.CONTRASENA);
		dev.append("</label>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='password' size='15' maxlength='16' id='formulario_password_1' name='formulario_password_1'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** PASSWORD2
		// **************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append("<label for='formulario_password_2'>");
		dev.append(TextConstant.CONFIRMAR_CONTRASENA);
		dev.append("</label>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='password' size='15' maxlength='16' id='formulario_password_2' name='formulario_password_2'/>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** CONDICIONES LEGALES
		// ****************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA_NEGRITA);
		dev.append("<label for='formulario_password_2'>");
		dev
				.append("<input name='check_condiciones' type='checkbox' id='check_condiciones' value='checkbox'/>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<label for='check_condiciones'>Acepto las <a href='javascript:condiciones();' style='font-size: 1em;'>condiciones de uso.</a></label>");
		dev.append(TextConstant.CIERRE_DIV);

		// ****************************** BOTONERA
		// ***************************************
		dev.append(obtenerBotonera());
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();

	}

	public static String obtenerAnuncioEnRevision() {
		StringBuffer dev = new StringBuffer();

		// ****************************** ANUNCIO EN REVISION
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(TextConstant.IMAGEN_OK);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(TextConstant.ANUNCIO_EN_REVISION_INFORMA1);
		dev.append(TextConstant.ANUNCIO_EN_REVISION_INFORMA2);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);

		return dev.toString();

	}

	public static String obtenerAreaGestionCompleta(Anuncio anuncio,
			Boolean eliminar) {
		StringBuffer dev = new StringBuffer();

		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		// Titular anuncio
		dev.append(TextConstant.APERTURA_DIV_GRIS_MARGEN_INFERIOR);
		dev.append(anuncio != null ? anuncio.getName() : "");
		dev.append(TextConstant.CIERRE_DIV);
		// Eliminar anuncio
		dev.append(TextConstant.APERTURA_DIV);
		dev
				.append(eliminar.equals(false) ? TextConstant.RADIO_MODIFICAR_SELECCIONADO
						: TextConstant.RADIO_MODIFICAR);
		dev.append(TextConstant.CIERRE_DIV);
		// Modificar anuncio
		dev.append(TextConstant.APERTURA_DIV_MARGEN_INFERIOR);
		dev
				.append(eliminar.equals(true) ? TextConstant.RADIO_ELIMINAR_SELECCIONADO
						: TextConstant.RADIO_ELIMINAR);
		dev.append(TextConstant.CIERRE_DIV);
		// Clave y recordar clave
		dev.append(TextConstant.APERTURA_DIV);
		dev
				.append("<label for='pass'>Contrase&ntilde;a</label><input type='password' id='pass' name='pass' onkeypress='return teclapulsada(event)'/><a href='javascript:recordarClave();'>Recordar constrase&ntilde;a</a>");
		dev.append(TextConstant.CIERRE_DIV);
		// Boton aceptar
		dev.append(TextConstant.APERTURA_DIV);
		dev
				.append("<input type='button' onclick='javascript:tramitar();' value='Aceptar'/>");
		dev.append(TextConstant.CIERRE_DIV);

		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);

		return dev.toString();

	}

	public static String obtenerConsultaEnviada() {
		StringBuffer dev = new StringBuffer();
		// ****************************** CONSULTA ENVIADA
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(TextConstant.IMAGEN_OK);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(TextConstant.CONSULTA_ENVIADA);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();

	}

	public static String obtenerConsultaNoEnviada(String respuesta) {
		StringBuffer dev = new StringBuffer();
		// ****************************** CONSULTA ENVIADA
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(TextConstant.IMAGEN_KO_P);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(respuesta);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();

	}

	public static String obtenerContacto() {
		StringBuffer dev = new StringBuffer();
		// ****************************** CONSULTA ENVIADA
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(TextConstant.IMAGEN_EMAIL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_SIN_MARGEN);
		dev.append(TextConstant.CONTACTO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();

	}

	public static String obtenerAnuncioNoEncontrado() {
		StringBuffer dev = new StringBuffer();

		// ****************************** ANUNCIO NO ENCONTRADO
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(TextConstant.IMAGEN_KO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(TextConstant.ANUNCIO_NO_ENCONTRADO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);

		return dev.toString();

	}

	public static String obtenerConfirmacion(String respuesta, String icono) {
		StringBuffer dev = new StringBuffer();
		// ****************************** CONSULTA ENVIADA
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(icono);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(respuesta);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerConfirmacionConBoton(String respuesta,
			String icono) {
		StringBuffer dev = new StringBuffer();
		// ****************************** CONSULTA ENVIADA
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_CAPA_PRELIMINAR_ANUNCIO);
		dev.append(TextConstant.APERTURA_DIV_CAPA_FONDO_SOMBREADO);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append(icono);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO);
		dev.append(respuesta);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CENTRADO_LIMPIO);
		dev.append("<input type='submit' value='Confirmar'/>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerLogo() {
		StringBuffer dev = new StringBuffer();
		// ****************************** ANUNCIO NO ENCONTRADO
		// *****************************
		dev.append(TextConstant.APERTURA_DIV_LOGO);
		dev.append(TextConstant.APERTURA_DIV_LOGO_TEXTO);
		dev.append("VendeNet");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_LOGO_PUNTO);
		dev.append(TextConstant.IMAGEN_PUNTO);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_LOGO_DOMINIO);
		dev.append("net");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static String obtenerBotonera_intranet() {
		StringBuffer dev = new StringBuffer();
		// ****************************** BOTONERA *****************************
		dev.append(TextConstant.APERTURA_DIV);
		dev.append(TextConstant.APERTURA_DIV_CUARTO_FLOAT);
		dev.append(TextConstant.APERTURA_A_OK);
		dev.append(TextConstant.IMAGEN_OK_P);
		dev.append(TextConstant.CIERRE_A);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CUARTO_FLOAT);
		dev.append(TextConstant.APERTURA_A_DARK_OK);
		dev.append(TextConstant.IMAGEN_DARK_OK_P);
		dev.append(TextConstant.CIERRE_A);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CUARTO_FLOAT);
		dev.append(TextConstant.APERTURA_A_WRN);
		dev.append(TextConstant.IMAGEN_WRN_P);
		dev.append(TextConstant.CIERRE_A);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_CUARTO_FLOAT);
		dev.append(TextConstant.APERTURA_A_KO);
		dev.append(TextConstant.IMAGEN_KO_P);
		dev.append(TextConstant.CIERRE_A);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		return dev.toString();
	}

	public static boolean IsMobile(String userAgent) {
		if (userAgent == null)
			return false;
		if (userAgent.toLowerCase() == null)
			return false;
		userAgent = userAgent.toLowerCase();
		return userAgent.toLowerCase().contains("iphone")
				| userAgent.toLowerCase().contains("ipad")
				| userAgent.toLowerCase().contains("ppc")
				| userAgent.toLowerCase().contains("windows ce")
				| userAgent.toLowerCase().contains("blackberry")
				| userAgent.toLowerCase().contains("opera mini")
				| userAgent.toLowerCase().contains("mobile")
				| userAgent.toLowerCase().contains("palm")
				| userAgent.toLowerCase().contains("portable");
	}

	public static boolean IsIPhone(String userAgent) {
		userAgent = userAgent.toLowerCase();
		return (userAgent.contains("iphone") || userAgent.contains("ipad"));
	}

	public static String formatearBaja(HistoricoBajas baja) {
		String dev = null;

		return dev;
	}

	public static String obtenerReloj() {
		return new StringBuffer("<div class='fecha_hora'>").append(
				"<div class='right' id='year'></div>").append(
				"<div class='right'>&nbsp;de&nbsp;</div>").append(
				"<div class='right' id='month'></div>").append(
				"<div class='right'>&nbsp;de&nbsp;</div>").append(
				"<div class='right' id='day'></div>").append(
				"<div class='right'>,&nbsp;</div>").append(
				"<div class='right' id='sem'></div>").append(
				"<div class='right'>&nbsp;</div>").append(
				"<div class='right' id='seg'></div>").append(
				"<div class='right'>:</div>").append(
				"<div class='right' id='min'></div>").append(
				"<div class='right'>:</div>").append(
				"<div class='right' id='hour'>:</div>").append("</div>")
				.toString();

	}

	public static String obtenerPlantillaAltaOpinion() {
		StringBuffer dev = new StringBuffer();
		// ****************************** ESTRELLITAS
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_valoracion'>");
		dev.append(TextConstant.OPINION_ANUNCIANTE);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA_BLANCO);
		dev.append(TextConstant.APERTURA_DIV_ESTRELLITAS);
		dev.append(TextConstant.APERTURA_DIV_FLOAT_LEFT);
		for (int i = 0; i < 5; i++) {
			dev.append("<img id='estrella_");
			dev.append(i);
			dev.append("' name='");
			dev.append(i);
			dev.append("' src='imagenes/");
			dev.append(TextConstant.IMAGEN_STAR_GREY);
			dev.append("'/>");
		}
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_FLOAT_LEFT_TOP);
		dev.append(TextConstant.APERTURA_LABEL_OPINION);
		dev.append("");
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.CIERRE_DIV);
		// ****************************** NOMBRE
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_nombre'>");
		dev.append(TextConstant.NOMBRE);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='35' maxlength='60' id='formulario_nombre' name='formulario_nombre'/>");
		dev.append(TextConstant.CIERRE_DIV);
		// ****************************** EMAIL
		// ******************************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_email'>");
		dev.append(TextConstant.EMAIL);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MARGEN_DERECHA);
		dev
				.append("<input type='text' size='35' maxlength='60' id='formulario_email' name='formulario_email'/>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append(TextConstant.HTMLBLANK);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SETENTA_MENSAJE_NO_REPUDIO);
		dev.append(TextConstant.MENSAJE_NO_REPUDIO);
		dev.append(TextConstant.CIERRE_DIV);
		// ****************************** TEXTO ANUNCIO
		// ***********************************
		dev.append(TextConstant.APERTURA_DIV_VEINTE_MARGEN_IZQUIERDA);
		dev.append("<label for='formulario_cuerpo'>");
		dev.append(TextConstant.TEXTO_OPINION);
		dev.append(TextConstant.CIERRE_LABEL);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.APERTURA_DIV_SESENTA_MARGEN_DERECHA);
		dev
				.append("<textarea id='formulario_cuerpo' class='textarea_cuerpo' name='formulario_cuerpo'></textarea>");
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(obtenerBotoneraOpinion());
		return dev.toString();

	}

	
	public static String obtenerCapa_VolverAnuncio(String idAnuncio){
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.APERTURA_DIV_CAMPO_VOLVER_ANUNCIO);
		dev.append(TextConstant.VOLVER_AL_ANUNCIO);
		return dev.toString();
	}
	
	public static String obtenerOpinionDetallada(List<Critica> list){
		StringBuffer dev = new StringBuffer();
		if(list!=null && list.size()>0){
			dev.append("<div class=\"jScrollbar5\">");
			dev.append("<div class=\"jScrollbar_mask\">");
			Iterator it = list.iterator();
			while(it.hasNext()){
				Critica critica = (Critica)it.next();
				dev.append("<div class='critica_detallada'>");				
				dev.append("<div class='fecha_critica_detallada'>");
				dev.append(UtilidadesFecha.formatearFechaDDMMMMHHmm(critica.getFechaAlta()));
				dev.append(TextConstant.COMA_HTML);
				dev.append(critica.getNombreCriticon());
				dev.append(TextConstant.HTMLBLANK);
				dev.append(UtilidadesTexto.obtenerEstrallitas(UtilidadesNumericos.convertirAInt( critica.getValoracion()),1, critica.getValoracion()));
//				dev.append(TextConstant.HTMLBLANK);
//				dev.append(TextConstant.PARENTESIS_ABRIR);
//				dev.append(critica.getEmailCriticon());
//				dev.append(TextConstant.PARENTESIS_CERRAR);
				dev.append("</div>");
				dev.append("<div class='texto_critica_detallada'>");
				dev.append(critica.getTexto());
				dev.append("</div>");
				dev.append("</div>");
			}
			dev.append("</div>");
			dev.append("<div class=\"jScrollbar_draggable\">");
			dev.append("<a href=\"#\" class=\"draggable\"></a>");
			dev.append("</div>");
			dev.append("<div class=\"clr\"></div>");
			dev.append("</div>");
		}else{
			dev.append("<div class='critica_detallada'>");
			dev.append("<div class='texto_critica_detallada'>");
			dev.append(TextConstant.SIN_OPINIONES);
			dev.append("</div>");
			dev.append("</div>");
			
		}
		return dev.toString();
	}
	
	public static String getCadenaAlfanumAleatoria(int longitud) {
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}
	
	
	public static String buscarCaracteresFormato(String patron){
		try {
			if(patron!=null){
				byte[] array = patron.getBytes("iso-8859-1");
				for(int i=0;i<array.length;i++){
					if(array[i]<0){
						if(!CarEspecialesConstant.lstCodigos.contains(new Integer(array[i]))){//Si no esta entre la lista de caracteres especiales reconocidos por ISO-8859-1
							boolean tratado=false;
							StringBuffer pre = new StringBuffer(patron.substring(0, i));
							StringBuffer post = new StringBuffer(patron.substring(i+1,patron.length()));
							switch (array[i])
							{
								case -128:
									patron=pre.append(TextConstant.HTML_EURO).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -109:
									patron=pre.append(TextConstant.HTML_ABRIR_COMITAS).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -103:
									patron=pre.append(TextConstant.HTML_TRADEMARK).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -108:
									patron=pre.append(TextConstant.HTML_CERRAR_COMITAS).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -119:
									patron=pre.append(TextConstant.HTML_PORCIENTO_RARO).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -116:
									patron=pre.append(TextConstant.HTML_COMUNIDAD_EUROPEA).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -125:
									patron=pre.append(TextConstant.HTML_INTEGRAL).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -121:
									patron=pre.append(TextConstant.HTML_CRUZ_DOBLE).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
								case -122:
									patron=pre.append(TextConstant.HTML_CRUZ).append(post).toString();
									array = patron.getBytes("iso-8859-1");
									tratado=true;
									break;
							}
							if(!tratado){
								System.out.println("Borro el caracter:"+patron.charAt(i)+"->"+array[i]);
								patron=pre.append(post).toString();
								array = patron.getBytes("iso-8859-1");
								i--;
							}
						}
					}
				}
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			// TODO Bloque catch generado autom�ticamente
			System.out.println("es iso");
	//	}
	}
	return patron;
}

}
