package com.vendenet.utilidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.entidad.Critica;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesCritica {
   	
	public static boolean tienePendientes(Session session, String email){
		try{
			Criteria crit = session.createCriteria(Critica.class);
			crit.add(Restrictions.eq("emailCriticon",email));
			crit.add(Restrictions.eq("confirmada",false));
			List listaResultados=crit.list();
			if(listaResultados!=null && listaResultados.size()>0)return true;
			else return false;
		}catch(Exception e){
			System.err.println("Error en UtilidadesCritica - tienePendientes:"+e);
			return false;
		}
	}
	
	public static boolean tieneMuchasOpiniones(Session session, String emailCriticon, String emailCliente){
		try{
			Criteria crit = session.createCriteria(Critica.class);
			crit.add(Restrictions.eq("emailCriticon",emailCriticon));
			crit.add(Restrictions.eq("emailCliente",emailCliente));
			List listaResultados=crit.list();
			if(listaResultados!=null && listaResultados.size()>ConstantesVendenet.OPINIONES_MAX_EMAIL)return true;
			else return false;
		}catch(Exception e){
			System.err.println("Error en UtilidadesCritica - tieneMuchasOpiniones:"+e);
			return false;
		}
	}

	public static List obtenerCriticas(Session session, String email) {
		List listaResultados=null;
		try{
			Criteria crit = session.createCriteria(Critica.class);
			crit.add(Restrictions.eq("confirmada",true));
			crit.add(Restrictions.eq("emailCliente",email));
			listaResultados=crit.list();
			return listaResultados;
		}catch(Exception e){
			System.err.println("Error en UtilidadesCritica - obtenerCriticas:"+e);
			return listaResultados;
		}
	}

	public static List asignarCriticas(Session session, List lstAnuncios) {
		List<Anuncio> listaResultados=lstAnuncios;
		try{
			if(lstAnuncios!=null && lstAnuncios.size()>0){
				listaResultados=new ArrayList<Anuncio>();
				for(int i=0;i<lstAnuncios.size();i++){
					Anuncio anuncioTemp=(Anuncio)lstAnuncios.get(i);
					int numCriticas=0;
					double mediaCriticas=0;
					List lstCriticas=UtilidadesCritica.obtenerCriticas(session,anuncioTemp.getCliente().getEmail());
					if(lstCriticas!=null && lstCriticas.size()>0){
						Iterator<Critica> itCriticas = lstCriticas.iterator();
						while(itCriticas.hasNext()){
							Critica criticaTemp=(Critica)itCriticas.next();
							numCriticas++;
							mediaCriticas+=criticaTemp.getValoracion();
						}
						mediaCriticas= UtilidadesNumericos.redondear(mediaCriticas/numCriticas,2);
					}
					anuncioTemp.setNumCriticas(numCriticas);
					anuncioTemp.setMediaCriticas(mediaCriticas);
					listaResultados.add(anuncioTemp);
				}
			}
			return listaResultados;
		}catch(Exception e){
			System.err.println("Error en UtilidadesCritica - asignarCriticas:"+e);
			return listaResultados;
		}
		
	}
	
	public static boolean meterOpinionesVendenet(Session session){
		try{
			Query query = session.createQuery("from Cliente where email not in (select distinct(emailCliente) from Critica)");
			List list = query.list();
			if((list!=null)&&(list.size()>0)){
				Iterator it = list.iterator();
				while(it.hasNext()){
					Cliente cliente = (Cliente)it.next();
					Criteria crit = session.createCriteria(Critica.class);
					crit.add(Restrictions.eq("emailCliente",cliente.getEmail()));
					List lstCriticas = crit.list();
					if((lstCriticas!= null && lstCriticas.size()==0)){
						Critica critica = new Critica();
						critica.setCodigo("M3");
						critica.setConfirmada(true);
						critica.setEmailCliente(cliente.getEmail());
						critica.setEmailCriticon(TextConstant.EMAIL_VENDENET);
						critica.setFechaAlta(cliente.getFechaAlta()==null?(new Date()):cliente.getFechaAlta());
						critica.setName(TextConstant.ANUNCIO_ACEPTADO_VENDENET);
						critica.setNombreCriticon(TextConstant.VENDENET);
						critica.setTexto(TextConstant.ANUNCIO_ACEPTADO_VENDENET);
						critica.setValoracion(2.5);
						session.saveOrUpdate(critica);
					}
				}
			}
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	
}