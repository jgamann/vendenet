package com.vendenet.negocio.error;

import org.apache.log4j.Logger;

import com.vendenet.utilidades.excepciones.ExcepcionBD;


public class ErrorVendenet extends Throwable {
	
	/*public ErrorWebComVen () {
						
	}
	
	public ErrorWebComVen(String pStrErrCod) {
							
	}	
	
	public ErrorWebComVen(String pStrErrCod, String pStrErrDes) {
				
	}	
}*/


public final static String LITERAL_ADMIN = "En caso de duda póngase en contacto con el administrador del sistema.";
	// Constante, Error Genérico
	private final static Logger  logger = Logger.getLogger(ErrorVendenet.class);
	private final static String ERROR_WCV_GENERICO = "HR-100000";
	private final static String ERROR_WCV_GENERICO_LIT = "Se produjo un error en la operación realizada.";
	private final static String ERROR_WCV_GENERICO_DESC =	"No se realizó la operación pedida.";

	private final static String ERROR_SIGNALIA_CLAVE_DUPLICADA = "HR-000001";
	private final static String ERROR_SIGNALIA_CLAVE_DUPLICADA_LIT = "El registro que ha intentado grabar ya existe.";
	
	private final static String ERROR_SIGNALIA_REFER_BORRADA = "HR-000002";
	private final static String ERROR_SIGNALIA_REFER_BORRADA_LIT = "El registro que ha intentado grabar contiene algun dato referencial que ha dejado de existir. Pruebe a realizar de nuevo la operación.";

	private final static String ERROR_SIGNALIA_REFERECIAL = "HR-000003";
	private final static String ERROR_SIGNALIA_REFERECIAL_LIT = "El registro que ha intentado borrar/grabar contiene algun dato referencial que debe modificarse primero.";

	private final static String ERROR_SIGNALIA_CONEXIONBD = "HR-000004";
	private final static String ERROR_SIGNALIA_CONEXIONBD_LIT = "No se pudo conectar con la Base de Datos. Vuelva a intentarlo y si se produce el mismo falle consulte con soporte de sistemas.";


	// Atributos
	private String strErrorCodigo = null; // Código del error
	private String strErrorDescripcion = null; // Descripción que nos proporciona el servidor sobre el error
	private String strErrorDescripcionLarga = null; // Descripción detallada del error
	private String strErrorDescripcionProgramacion = null; // Texto que se va a mostrar al usuario


	/**
	 * Contructores.
	 * 
	 *  - Sin parámetros, para el error genérico
	 *  - Pasándole el código
	 *  - Pasándole el código y la descripción 
	 *  
	 */
	
	public ErrorVendenet () {
		
		strErrorCodigo = ERROR_WCV_GENERICO;
		strErrorDescripcion = ERROR_WCV_GENERICO_LIT;
		strErrorDescripcionLarga = ERROR_WCV_GENERICO_DESC;
		
	}
	
	public ErrorVendenet(String pStrErrCod) {
		
		strErrorCodigo = pStrErrCod;
			
	}	
	
	public ErrorVendenet(String pStrErrCod, String pStrErrDes) {
		
		strErrorCodigo = pStrErrCod;
		strErrorDescripcion = pStrErrDes;
	
	}

	/**
	 *  Métodos GET y SET
	 */

	/**
	 * Returns the strErrorCodigo.
	 * @return String
	 */
	public String getStrErrorCodigo() {
		return strErrorCodigo;
	}

	/**
	 * Returns the strErrorDescripcion.
	 * @return String
	 */
	public String getStrErrorDescripcion() {
		return strErrorDescripcion;
	}

	/**
	 * Returns the strErrorDescripcionLarga.
	 * @return String
	 */
	public String getStrErrorDescripcionLarga() {
		return strErrorDescripcionLarga;
	}

	/**
	 * Returns the strErrorDescripcionProgramacion.
	 * @return String
	 */
	public String getStrErrorDescripcionProgramacion() {
		return strErrorDescripcionProgramacion;
	}

	/**
	 * Sets the strErrorCodigo.
	 * @param strErrorCodigo The strErrorCodigo to set
	 */
	public void setStrErrorCodigo(String strErrorCodigo) {
		this.strErrorCodigo = strErrorCodigo;
	}

	/**
	 * Sets the strErrorDescripcion.
	 * @param strErrorDescripcion The strErrorDescripcion to set
	 */
	public void setStrErrorDescripcion(String strErrorDescripcion) {
		this.strErrorDescripcion = strErrorDescripcion;
	}

	/**
	 * Sets the strErrorDescripcionLarga.
	 * @param strErrorDescripcionLarga The strErrorDescripcionLarga to set
	 */
	public void setStrErrorDescripcionLarga(String strErrorDescripcionLarga) {
		this.strErrorDescripcionLarga = strErrorDescripcionLarga;
	}

	/**
	 * Sets the strErrorDescripcionProgramacion.
	 * @param strErrorDescripcionProgramacion The strErrorDescripcionProgramacion to set
	 */
	public void setStrErrorDescripcionProgramacion(String strErrorDescripcionProgramacion) {
		this.strErrorDescripcionProgramacion = strErrorDescripcionProgramacion;
	}
	
	
	
	public static ErrorVendenet tratarErrorBD(ExcepcionBD e)
	{
			logger.error("Codigo de Error: " + e.getStrBDCodigo());
			logger.error("Describ. del Error: " + e.getStrBDDescDetallada());
			logger.error("Codigo de Error SQL: " + e.getStrBDOrigenCodigo());
			logger.error("Describ. del Error SQL: " + e.getStrBDOrigenDesc());
			int iCodSQL = Integer.parseInt(e.getStrBDOrigenCodigo());
			ErrorVendenet err = new ErrorVendenet();
			switch (iCodSQL)
			{
				case 1:
						err.setStrErrorCodigo(ERROR_SIGNALIA_CLAVE_DUPLICADA);
						err.setStrErrorDescripcion(ERROR_SIGNALIA_CLAVE_DUPLICADA_LIT);
						break;
				case 2291:
						err.setStrErrorCodigo(ERROR_SIGNALIA_REFER_BORRADA);
						err.setStrErrorDescripcion(ERROR_SIGNALIA_REFER_BORRADA_LIT);
						break;					
				case 2292:
						err.setStrErrorCodigo(ERROR_SIGNALIA_REFERECIAL);
						err.setStrErrorDescripcion(ERROR_SIGNALIA_REFERECIAL_LIT);
						break;					
				case 17002:
						err.setStrErrorCodigo(ERROR_SIGNALIA_CONEXIONBD);
						err.setStrErrorDescripcion(ERROR_SIGNALIA_CONEXIONBD_LIT);
						break;					
			}
			
			err.setStrErrorDescripcionProgramacion(e.getStrBDDescDetallada());
			return err;		
	}
	
	public static ErrorVendenet tratarErrorEx(Exception e)
	{
		logger.error("Mensaje de Error: " + e.getMessage());
		logger.error("Mensaje de Error total: " + e.toString());
		logger.error("Causa de Error total: " + e.getCause());
		ErrorVendenet err = new ErrorVendenet();
		err.setStrErrorDescripcionProgramacion(e.toString());
		return err;
	}
	
	
	public static ErrorVendenet tratarErrorTh(Throwable th)
	{
		logger.error("Mensaje de Error: " + th.getMessage());
		logger.error("Mensaje de Error total: " + th.toString());
		ErrorVendenet err = new ErrorVendenet();
		return err;
	}
}