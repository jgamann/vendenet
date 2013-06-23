/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class NegoCondiciones {
	private Logger logger = Logger.getLogger(NegoCondiciones.class);
	private Hashtable hsResultados = new Hashtable();
	
	public void iniciarAplicacion() throws ErrorVendenet {
				
	}

	/**
	 * @return
	 */
	public Hashtable getHsResultados() {
		return hsResultados;
	}

	/**
	 * @param hashtable
	 */
	public void setHsResultados(Hashtable hashtable) {
		hsResultados = hashtable;
	}

	public boolean validarCampos() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void obtenerTexto() throws ErrorVendenet {
		try{
			hsResultados.put(TextConstant.KEY_MENSAJE, TextConstant.CONDICIONES);
		}catch(Exception eError){
			hsResultados.put(TextConstant.KEY_MENSAJE, "");
			logger.error("Error en NegoCondiciones - obtenerTexto:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
}
