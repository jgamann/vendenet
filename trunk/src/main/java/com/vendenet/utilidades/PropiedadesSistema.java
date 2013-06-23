/*
 * Creado el 03-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.vendenet.utilidades.constantes.ConstantesVendenet;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class PropiedadesSistema {
	private Logger logger = Logger.getLogger(PropiedadesSistema.class);
	Properties propiedadesSistema=new Properties();
	/**
	 * 
	 */
	public PropiedadesSistema(String ruta) {
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(ruta.concat(ConstantesVendenet.ficheroNegocioAplicacion));
			propiedadesSistema.load(fis);
			fis.close();
		}catch(IOException ioEx){
			logger.error("Error al leer el fichero de constantes."+ioEx);
			logger.error(ioEx);
		}finally{
			try{
				if(fis!=null)
				fis.close();
			}catch(IOException ioEx){
				logger.error("Error al cerrar el fichero de Constantes"+ioEx);			
			}
		}
	}
	
	public String getPropiedad(String clave){
		return propiedadesSistema.getProperty(clave);
	}
}
