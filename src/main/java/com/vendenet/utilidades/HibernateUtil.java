package com.vendenet.utilidades;

import java.io.File;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.vendenet.utilidades.constantes.ConstantesVendenet;

public class HibernateUtil {
	private static final  Logger logger = Logger.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory;
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml   
        	
            sessionFactory = new Configuration().configure(new File(ConstantesVendenet.PATH_REAL.concat(ConstantesVendenet.ficheroHibernate)))
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static LogicalExpression metodoBuclado(StringTokenizer st){
		int j = st.countTokens();
		if(j>1)return Restrictions.and(crearOrNameCuerpo(st.nextToken()),metodoBuclado(st));
		else return crearOrNameCuerpo(st.nextToken());
	}
    public static LogicalExpression crearOrNameCuerpo(String token){
		Criterion name = Restrictions.like("name","%"+token+"%");
     	Criterion cuerpo = Restrictions.like("cuerpo","%"+token+"%");
        return Restrictions.or(cuerpo,name);
    }
}