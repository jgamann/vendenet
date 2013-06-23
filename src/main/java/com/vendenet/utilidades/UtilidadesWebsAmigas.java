package com.vendenet.utilidades;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.entidad.WebAmiga;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesWebsAmigas {
   	
	
	public static void obtenerWebsAmigas() {
		List<WebAmiga> listaResultados=null;
		Transaction transaccion = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaccion = session.beginTransaction();
			Criteria crit = session.createCriteria(WebAmiga.class);
			listaResultados=crit.list();
			Iterator<WebAmiga> it = listaResultados.iterator();
			StringBuffer divWebAmigas = new StringBuffer("<div class='webs_amigas'><div class='webs_amigas_titulo'><div class='webs_amigas_titulo'>P&aacute;ginas recomendadas </div></div><div class='webs_amigas_web'>");
			while(it.hasNext()){
				WebAmiga web = it.next();
				divWebAmigas.append("<div class=\"webamiga\"><a class=\"link_webs_amigas\" href=\"javascript:abrirUrl('");
				divWebAmigas.append(web.getUrl());
				divWebAmigas.append("');\">");
				divWebAmigas.append(web.getName());
				divWebAmigas.append("</a></div>");
			}
			divWebAmigas.append("</div></div>");
			TextConstant.WEB_AMIGAS = divWebAmigas.toString();
			transaccion.commit();
			session.close();
		}catch(Exception e){
			if(transaccion != null)transaccion.rollback();
			if(session != null)session.close();
			System.err.println("Error en UtilidadesWebsAmigas - obtenerWebsAmigas:"+e);
		}
	}
}