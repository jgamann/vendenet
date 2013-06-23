package com.vendenet.utilidades;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesHibernate {
	private static Logger logger = Logger.getLogger(UtilidadesHibernate.class);
	public static String obtenerPath(int id, String nombreArchivo) throws ErrorVendenet {
		StringBuffer pathCompleto=new StringBuffer("");
		try{
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
		return new StringBuffer(TextConstant.PRE_FOTO_MAX).append(path).toString();
	}
	public static String obtenerPathFotoPeque(String path) {
		return new StringBuffer(TextConstant.PRE_FOTO_PEQUE).append(path).toString();
	}
	public static String obtenerPathFotoIcono(String path) {
		return new StringBuffer(TextConstant.PRE_FOTO_ICONO).append(path).toString();
	}

	public static String obtenerIdFromPequePath(String urlFotoPeque) {
		//urlFotoPeque=urlFotoPeque.substring(TextConstant.PRE_FOTO_PEQUE.length(),urlFotoPeque.length());
		urlFotoPeque=urlFotoPeque.substring(TextConstant.PRE_FOTO_PEQUE.length(), urlFotoPeque.lastIndexOf('.')==-1 ? urlFotoPeque.length() : urlFotoPeque.lastIndexOf('.')); 
		return urlFotoPeque;
	}
}