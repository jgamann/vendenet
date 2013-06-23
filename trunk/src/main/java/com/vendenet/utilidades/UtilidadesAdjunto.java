package com.vendenet.utilidades;

import java.io.File;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.ErrorConstant;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesAdjunto {
	private static Logger logger = Logger.getLogger(UtilidadesAdjunto.class);
	public static String obtenerPath(int id, String nombreArchivo, String fecha) throws ErrorVendenet {
		StringBuffer pathCompleto=new StringBuffer("");
		try{
			pathCompleto.append(fecha);
			pathCompleto.append(TextConstant.SLASH);
			pathCompleto.append(id);
			StringTokenizer st = new StringTokenizer(nombreArchivo,".");
			String extension="";
			while (st.hasMoreTokens()) {
				extension=st.nextToken();
			}
			pathCompleto.append(TextConstant.PUNTO).append(extension);
		}catch(Exception e){
			logger.error("Error en UtilidadesAdjunto:"+e);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(e);
			throw err;
		}
		return pathCompleto.toString();
	}

	public static String obtenerPathFotoGrande(String path) {
		return new StringBuffer(obtenerCarpetaFecha(path)).append(TextConstant.PRE_FOTO_MAX).append(quitarCarpetaFecha(path)).toString();
	}
	public static String obtenerPathFotoPeque(String path) {
		return new StringBuffer(obtenerCarpetaFecha(path)).append(TextConstant.PRE_FOTO_PEQUE).append(quitarCarpetaFecha(path)).toString();
	}
	public static String obtenerPathFotoIcono(String path) {
		return new StringBuffer(obtenerCarpetaFecha(path)).append(TextConstant.PRE_FOTO_ICONO).append(quitarCarpetaFecha(path)).toString();
	}

	public static String obtenerIdFromPequePath(String urlFotoPeque) {
		urlFotoPeque=urlFotoPeque.substring(urlFotoPeque.lastIndexOf(TextConstant.GUION_BAJO)+1,urlFotoPeque.lastIndexOf(TextConstant.PUNTO));
		return urlFotoPeque;
	}
	
	public static String quitarCarpetaFecha(String url){
		if(url.indexOf(TextConstant.SLASH)!=-1)
		return url.substring(url.indexOf(TextConstant.SLASH)+1,url.length());
		else return url;
	}
	
	public static String obtenerCarpetaFecha(String url){
		if(url.indexOf(TextConstant.SLASH)!=-1)
		return url.substring(0,url.indexOf(TextConstant.SLASH)+1);
		else return TextConstant.BLANK;
	}
	
	public static String obtenerCarpetaFechaSinSlash(String url){
		if(url.indexOf(TextConstant.SLASH)!=-1)
		return url.substring(0,url.indexOf(TextConstant.SLASH));
		else return TextConstant.BLANK;
	}
	
	public static void borrarFicheros(String path) {
		try{
			logger.debug("Borrando ficheros...");
			File file = new File(ConstantesVendenet.RUTA_DOCUMENTOS.concat(obtenerPathFotoIcono(path)));
			logger.debug(ConstantesVendenet.RUTA_DOCUMENTOS.concat(obtenerPathFotoIcono(path)));
			file.delete();
			file = new File(ConstantesVendenet.RUTA_DOCUMENTOS.concat(obtenerPathFotoPeque(path)));
			logger.debug(ConstantesVendenet.RUTA_DOCUMENTOS.concat(obtenerPathFotoPeque(path)));
			file.delete();
			file = new File(ConstantesVendenet.RUTA_DOCUMENTOS.concat(obtenerPathFotoGrande(path)));
			logger.debug(ConstantesVendenet.RUTA_DOCUMENTOS.concat(obtenerPathFotoGrande(path)));
			file.delete();
			file = new File(ConstantesVendenet.RUTA_DOCUMENTOS.concat(path));
			logger.debug(ConstantesVendenet.RUTA_DOCUMENTOS.concat(path));
			file.delete();
		}catch (Exception e) {
			logger.error(ErrorConstant.ERROR_BORRANDO_FICHEROS);
			logger.error(e);
		}
		
	}
}
