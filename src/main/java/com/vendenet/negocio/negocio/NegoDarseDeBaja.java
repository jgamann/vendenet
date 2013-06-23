/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.entidad.HistoricoBajas;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoDarseDeBaja {
	private Logger logger = Logger.getLogger(NegoGestorArchivos.class);
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


	public void baja(Session session, String idCliente, String email, String pass) throws ErrorVendenet {
		try{
			if(idCliente!= null && email != null && pass != null && UtilidadesNumericos.isNumberInteger(idCliente)){
				Criteria crit = session.createCriteria(Cliente.class);
				Criterion crt = Restrictions.eq("email",email);
				crit.add(crt);
				crt = Restrictions.eq("pass",pass);
				crit.add(crt);
				crt = Restrictions.eq("id",new Integer(idCliente));
				crit.add(crt);
		        List lstClientes = crit.list();
		        if(lstClientes!=null && lstClientes.size()>0){
		        	if(lstClientes.get(0) != null && lstClientes.get(0) instanceof Cliente){
		        		Cliente clienteBorrar = (Cliente)lstClientes.get(0);
		        		//Registrar en el historico la baja
		        		HistoricoBajas historico = new HistoricoBajas();
		        		historico.setEmailCliente(clienteBorrar.getEmail());
		        		historico.setFechaBorrado(new Date());
		        		historico.setName(clienteBorrar.getName());
		        		
		        		if(clienteBorrar.getAnuncios()!=null && clienteBorrar.getAnuncios().size()>0){
		        			Iterator itAnuncios = clienteBorrar.getAnuncios().iterator();
		        			while(itAnuncios.hasNext()){
		        				Anuncio anuncio = (Anuncio)itAnuncios.next();
		        				historico.setCategoria(anuncio.getCategoria());
		        				historico.setTituloAnuncio(anuncio.getName());
		        				historico.setCuerpoAnuncio(anuncio.getCuerpo());
		        				historico.setPrecio(anuncio.getPrecio());
		        				historico.setProvincia(anuncio.getProvincia());
		        				historico.setTipoAnuncio(anuncio.getTipoAnuncio());
		        				session.delete(anuncio);
		        			}
		        		}
		        		session.saveOrUpdate(historico);
		        		session.delete(lstClientes.get(0));
		        		hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.BAJA_REALIZADA_CORRECTAMENTE);
			        	hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_OK_P);
		        	}else{
		        		hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NO_SE_HA_PODIDO_DAR_DE_BAJA);
			        	hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_KO_P);
		        	}
		        }else{
		        	hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NO_SE_HA_PODIDO_DAR_DE_BAJA);
		        	hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_KO_P);
		        }	
			}else{
	        	hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NO_SE_HA_PODIDO_DAR_DE_BAJA);
	        	hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_KO_P);
	        }	
			
		}			
		catch(Exception eError)
		{
			hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NO_SE_HA_PODIDO_DAR_DE_BAJA_ERROR);
        	hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_KO_P);
			logger.error("Error en NegoDarseDeBaja:"+eError);
			System.err.println(eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
