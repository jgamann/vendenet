package com.vendenet.utilidades;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.SysexMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.entidad.Critica;
import com.vendenet.negocio.entidad.IpVisitante;
import com.vendenet.negocio.entidad.Palabrota;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesAnuncio {
	public static String obtenerFotoPrincipal(Anuncio anuncio) {
		if(anuncio.getAdjuntos().size()>0){
			Iterator itAdjuntos = anuncio.getAdjuntos().iterator();
			itAdjuntos = anuncio.getAdjuntos().iterator();
			Adjunto adjTemp = (Adjunto)itAdjuntos.next();
			String path=adjTemp.getPath();
			return new StringBuffer(UtilidadesAdjunto.obtenerCarpetaFecha(path)).append(TextConstant.PRE_FOTO_ICONO).append(UtilidadesAdjunto.quitarCarpetaFecha(path)).toString();
		}
		return TextConstant.SIN_FOTO_ICONO;
	}

	public static void purgarAdjuntosHaceUnaHora() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		 Criteria crit = session.createCriteria(Adjunto.class);
		 Date ahora = new Date();
		 crit.add(Restrictions.le(TextConstant.FECHA_ADJUNTO, new Date(ahora.getTime()-NumericConstant.ONE_HOUR_IN_MILISECONDS)));
		 crit.add(Restrictions.isNull("anuncio"));
		List lstAdjuntosHuerfanos = crit.list();
		Iterator itAdjuntos = lstAdjuntosHuerfanos.iterator();
		while (itAdjuntos.hasNext()) {
			Adjunto adjTemp = (Adjunto)itAdjuntos.next();
			if((adjTemp!=null)&&(adjTemp.getPath()!=null)&&(!adjTemp.getPath().equals(TextConstant.BLANK)))
				UtilidadesAdjunto.borrarFicheros(adjTemp.getPath());
			session.delete(adjTemp);
		}
		transaccion.commit();
		session.close();
	}
	
	public static void purgarAnunciosSinCliente() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		 Criteria crit = session.createCriteria(Anuncio.class);
		 Date ahora = new Date();
		 crit.add(Restrictions.le(TextConstant.FECHA_ALTA, new Date(ahora.getTime()-NumericConstant.ONE_HOUR_IN_MILISECONDS)));
		 crit.add(Restrictions.isNull("cliente"));
		List lstAnunciosHuerfanos = crit.list();
		Iterator itAnunciosHuerfanos = lstAnunciosHuerfanos.iterator();
		while (itAnunciosHuerfanos.hasNext()) {
			Anuncio anuncioTemp = (Anuncio)itAnunciosHuerfanos.next();
			session.delete(anuncioTemp);
		}
		transaccion.commit();
		session.close();
	}
	
	
	public static void purgarClientesSinAnuncio() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		Criteria crit = session.createCriteria(Cliente.class);
    	Criterion crt = Restrictions.sqlRestriction("{alias}.CLIENTE_ID not in (select CLIENTE_ID from anuncio)");
    	Date ahora = new Date();
    	crit.add(Restrictions.le(TextConstant.FECHA_ALTA, new Date(ahora.getTime()-NumericConstant.ONE_HOUR_IN_MILISECONDS)));
    	crit.add(crt);
		List lstClienteSinAnuncio = crit.list();
		Iterator itClientes = lstClienteSinAnuncio.iterator();
		while (itClientes.hasNext()) {
			Cliente clienteTemp = (Cliente)itClientes.next();
			session.delete(clienteTemp);
		}
		transaccion.commit();
		session.close();
	}
	
	
	public static void purgarAnunciosCaducados() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		Criteria crit = session.createCriteria(Anuncio.class);
		crit.add(Restrictions.le(TextConstant.FECHA_CADUCIDAD, new Date()));
		List lstAnuciosCaducados = crit.list();
		Iterator itAnuncios = lstAnuciosCaducados.iterator();
		while (itAnuncios.hasNext()) {
			Anuncio anuncioTemp = (Anuncio)itAnuncios.next();
			if(anuncioTemp!=null){
				if(anuncioTemp.getAdjuntos()!=null){
					Iterator itAdjuntos = anuncioTemp.getAdjuntos().iterator();
					while (itAdjuntos.hasNext()) {
						Adjunto adjTemp = (Adjunto)itAdjuntos.next();
						if((adjTemp!=null)&&(adjTemp.getPath()!=null)&&(!adjTemp.getPath().equals(TextConstant.BLANK)))
							UtilidadesAdjunto.borrarFicheros(adjTemp.getPath());
							session.delete(adjTemp);
					}
				}
				session.delete(anuncioTemp);
			}
		}
		transaccion.commit();
		session.close();
	}

	public static void purgarOpinionesSinConfirmar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		 Criteria crit = session.createCriteria(Critica.class);
		 Date ahora = new Date();
		 crit.add(Restrictions.le(TextConstant.FECHA_ALTA, new Date(ahora.getTime()-NumericConstant.ONE_HOUR_IN_MILISECONDS)));
		 crit.add(Restrictions.eq("confirmada",false));
		List lstCriticasABorrar = crit.list();
		Iterator itCriticas = lstCriticasABorrar.iterator();
		while (itCriticas.hasNext()) {
			Critica critTemp = (Critica)itCriticas.next();
			session.delete(critTemp);
		}
		transaccion.commit();
		session.close();
	}
	
	
	public static void purgarVisitasSinAnuncio() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		Criteria crit = session.createCriteria(IpVisitante.class);
    	Criterion crt = Restrictions.isNull("anuncio");// sqlRestriction("{alias}.ANUNCIO_ID not in (select ANUNCIO_ID from anuncio)");
    	crit.add(crt);
		List lstVisitasSinAnuncio = crit.list();
		Iterator itVisitas = lstVisitasSinAnuncio.iterator();
		while (itVisitas.hasNext()) {
			IpVisitante ipVisitante = (IpVisitante)itVisitas.next();
			session.delete(ipVisitante);
		}
		transaccion.commit();
		session.close();
	}
	
	public static boolean tienePalabrotas(Anuncio anuncio){
		String texto = new StringBuffer(anuncio.getName()).append(anuncio.getCuerpo()).toString().toUpperCase();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaccion = session.beginTransaction();
		Criteria crit = session.createCriteria(Palabrota.class);
    	Criterion crt = Restrictions.isNotNull("name");
    	crit.add(crt);
		List lstPalabrotas = crit.list();
		Iterator itPalabrotas = lstPalabrotas.iterator();
		boolean encontrado = false;
		while (itPalabrotas.hasNext() && !encontrado) {
			Palabrota palabrota = (Palabrota)itPalabrotas.next();
			if(texto.contains(palabrota.getName().toUpperCase()))encontrado=true;
		}
		transaccion.commit();
		session.close();
		return encontrado;
	}

	public static boolean tienePalabrotas(Session session, Anuncio anuncio) {
		String texto = new StringBuffer(anuncio.getName()).append(anuncio.getCuerpo()).toString().toUpperCase();
		Criteria crit = session.createCriteria(Palabrota.class);
    	Criterion crt = Restrictions.isNotNull("name"); 
    	crit.add(crt);
		List lstPalabrotas = crit.list();
		Iterator itPalabrotas = lstPalabrotas.iterator();
		boolean encontrado = false;
		while (itPalabrotas.hasNext() && !encontrado) {
			Palabrota palabrota = (Palabrota)itPalabrotas.next();
			if(texto.contains(palabrota.getName().toUpperCase()))encontrado=true;
		}
		return encontrado;
	}
	
	
	public static Anuncio obtenerAnuncioPorId(Integer idAnuncio,Session session) throws ErrorVendenet {
		try{
			Anuncio loadedAnuncio = null;
			if(session!=null){
				Criteria crit = session.createCriteria(Anuncio.class);
				crit.add(Restrictions.eq("id",idAnuncio));
				List listaResultados=crit.list();
				if(listaResultados!=null && listaResultados.size()>0)loadedAnuncio=(Anuncio)listaResultados.get(0);
			}
			return loadedAnuncio;
		}			
		catch(Exception eError)
		{
			System.err.println("Error en UtilidadesAnuncio: obtenerAnuncioActivoPorId:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
}