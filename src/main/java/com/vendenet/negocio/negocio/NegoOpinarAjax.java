/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Critica;
import com.vendenet.negocio.entidad.EnvioPendiente;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.UtilidadesAnuncio;
import com.vendenet.utilidades.UtilidadesCritica;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.UtilidadesTexto;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 * 
 *         Para cambiar la plantilla para este comentario de tipo generado vaya
 *         a Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de
 *         c�digo&gt;C�digo y comentarios
 */
public class NegoOpinarAjax {
	private Logger logger = Logger.getLogger(NegoOpinarAjax.class);
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

	public void opinar(String patron, HttpSession sesion) throws ErrorVendenet {
	}

	public void opinar(Integer valoracion, String nombre, String email,
			String texto, Integer idAnuncio) throws ErrorVendenet {
		Transaction transaction = null;
		List lstAnuncios = new ArrayList();
		Session session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		Anuncio anuncio = UtilidadesAnuncio.obtenerAnuncioPorId(idAnuncio,
				session);
		if ((anuncio==null)||(anuncio != null && anuncio.getCliente() != null
				&& anuncio.getCliente().getEmail() != null
				&& email.equals(anuncio.getCliente().getEmail())))
			hsResultados.put(TextConstant.KEY_RESPUESTA,
					"No puedes opinar sobre este anunciante con ese email");
		else {
			//No es el propio vendedor que se pasa de listo y quiere votarse a si mismo
			//Ahora comprobar que ese usuario no tiene mas opiniones pendientes de confirmacion
			if(UtilidadesCritica.tienePendientes(session, email)){
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.TIENES_OPINIONES_PENDIENTES);	
			}else if(UtilidadesCritica.tieneMuchasOpiniones(session, email, anuncio.getCliente().getEmail())){//Ni tampoco tenga mas de 10 opiniones para el mismo vendedor
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.TIENES_MUCHAS_OPINIONES);	
			}else{
				Critica critica = new Critica();
				critica.setEmailCliente(anuncio.getCliente().getEmail());
				critica.setEmailCriticon(email);
				critica.setFechaAlta(new Date());
				critica.setConfirmada(false);
				critica.setName(anuncio.getName());
				critica.setNombreCriticon(nombre);
				critica.setTexto(texto);
				critica.setValoracion(valoracion+1);
				critica.setCodigo(UtilidadesTexto.getCadenaAlfanumAleatoria(15));
				session.saveOrUpdate(critica);
				enviarEmailConfirmacion(session,critica.getCodigo(),critica.getEmailCriticon(),critica.getNombreCriticon());
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
			}
		}
		if (transaction != null)
			transaction.commit();

	}
	
	
	public void enviarEmailConfirmacion(Session session, String codigo, String email, String nombreCriticon) throws ErrorVendenet {
		try{
				EnvioPendiente envio = new EnvioPendiente();
				envio.setFechaAlta(new Date());
				envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
				envio.setEmailTo(email);
				envio.setEmailSubject(TextConstant.CONFIRMAR_OPINION);
				envio.setEnviado(false);
				envio.setName(TextConstant.BLANK);
				envio.setAnuncio(null);
				envio.setEmailBody(UtilidadesMail.cabeceraEmailSinBaja(UtilidadesMail.cuerpoMailConfirmarOpinion(codigo,nombreCriticon)));
				session.saveOrUpdate(envio);
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoAnuncio - devolverAnuncioPalabrota:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
	
}
