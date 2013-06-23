package com.vendenet.utilidades;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.vendenet.utilidades.excepciones.ExcepcionJNDI;


public class JNDICarga
{
	private static JNDICarga instance;
	private static Logger logger = Logger.getLogger(JNDICarga.class);

	private JNDICarga() throws ExcepcionJNDI, Exception
	{
	}

	public static synchronized JNDICarga getInstance() throws ExcepcionJNDI, Exception
	{
		if (instance == null)
		{
			instance = new JNDICarga();
		}
		return instance;
	} // getInstance()



	public DataSource getDataSource(String strDataSourceName)
	{
		DataSource dataSource = null;
		InitialContext initialContext = null;
		Hashtable hsParametros = new Hashtable();
		try{
			hsParametros.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			hsParametros.put(Context.PROVIDER_URL, "iiop:///");
			initialContext = new InitialContext(hsParametros);
			dataSource = (DataSource)initialContext.lookup(strDataSourceName);
		}catch(NamingException NEx){
			logger.error(NEx);
		}
		return dataSource;
	} // (String strDataSourceName)
}
