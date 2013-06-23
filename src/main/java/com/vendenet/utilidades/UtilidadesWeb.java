package com.vendenet.utilidades;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.vendenet.negocio.entidad.VisitaWeb;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoGestorArchivos;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesWeb {
	private static Logger logger = Logger.getLogger(NegoGestorArchivos.class);
	public static void aumentarVisitasWeb(Session session, String ip, String navegador) throws ErrorVendenet {
		try{
			if(navegador==null)navegador=new String(TextConstant.NAVEGADOR_DESCONOCIDO);
			if(ip==null)ip=new String(TextConstant.IP_VACIA);
			
			VisitaWeb nuevaVisita = new VisitaWeb();
			nuevaVisita.setName(ip);
			nuevaVisita.setFechaVisita(new Date());
			nuevaVisita.setNavegador(navegador);
			session.saveOrUpdate(nuevaVisita);
		}catch(Exception eError){
			logger.error("Error en UtilidadesWeb: aumentarVisitasWeb"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
}