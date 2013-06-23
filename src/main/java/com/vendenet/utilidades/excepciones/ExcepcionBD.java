package com.vendenet.utilidades.excepciones;



/*******************************************************************************
 * Clase:       ExcepcionBD
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Clase para el procesamiento de errores dentro del paquete BD. Los
 *   códigos de error se declaran públicos en la propia clase.
 *       Simpre se genera esta clase con un código, una descripción y otra
 *   descripción ampliada.
 * Versión: 1.0.0 
*******************************************************************************/
public class ExcepcionBD extends Exception
{
	//Constantes con los códigos de error.
	public static final String BD_ERROR_GENERICO = "-1000";
	public static final String BD_ERROR_CONEXION = "-1001";
	public static final String BD_ERROR_CONEXION_CERRAR = "-1002";

	private String strBDCodigo = null; //Atributo en el que se guarda el código de error.
	private String strBDDesc = null; //Atributo en el que se guarda la descripción del error.
	private String strBDDescDetallada = null; //Atributo en el que se guarda la descripción larga de error.
	private String strBDOrigenCodigo = null; //Atributo en el que se guarda el código de error de orígen de base de datos.
	private String strBDOrigenDesc = null; //Atributo en el que se guarda la descripción del error de orígen de base de datos.






/*******************************************************************************
 * Función:     ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)
 *
 * Descripción:
 *       El constructor recibe tres parámetros para la carga de atributos.
 * Parámetros de entrada:
 *   strCodigo - Código identificador del error producido.
 *   strDesc - Descripción del error producido.
 *   strDescDetallada - Descripción detallada del error producido.
 * Retorno:
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)
	{
		super(strDescDetallada);
		strBDCodigo = strCodigo;
		strBDDesc = strDesc;
		strBDDescDetallada = strDescDetallada;
	} // ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)






/*******************************************************************************
 * Función:     ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       El constructor recibe tres parámetros para la carga de atributos.
 * Parámetros de entrada:
 *   strCodigo - Código identificador del error producido.
 *   strDesc - Descripción del error producido.
 *   strDescDetallada - Descripción detallada del error producido.
 *   strParamBDOrigenCodigo - Código identificador del error originario de base
 *     de datos.
 *   strParamBDOrigenDesc - Descripción del error originario de base de datos.
 * Retorno:
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada, String strParamBDOrigenCodigo, String strParamBDOrigenDesc)
	{
		super(strDescDetallada);
		strBDCodigo = strCodigo;
		strBDDesc = strDesc;
		strBDDescDetallada = strDescDetallada;
		strBDOrigenCodigo = strParamBDOrigenCodigo;
		strBDOrigenDesc = strParamBDOrigenDesc;
	} // ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)






/*******************************************************************************
 * Función:     getStrBDCodigo()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve el código de error.
 * Parámetros de entrada:
 * Retorno: strBDCodigo - Código de error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDCodigo()
	{
		return strBDCodigo;
	} // getStrBDCodigo





/*******************************************************************************
 * Función:     getStrBDDesc()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve la descripción del error.
 * Parámetros de entrada:
 * Retorno: strBDDesc - Descripción del error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDDesc()
	{
		return strBDDesc;
	}  // getStrBDDesc()






/*******************************************************************************
 * Función:     getStrBDDescDetallada()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve la descripción del error.
 * Parámetros de entrada:
 * Retorno: strBDDescDetallada - Descripción detallada del error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDDescDetallada()
	{
		return strBDDescDetallada;
	} // getStrBDDescDetallada()






/*******************************************************************************
 * Función:     getStrBDOrigenCodigo()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve el código del error originario de base de datos.
 * Parámetros de entrada:
 * Retorno: strBDOrigenCodigo - Código del error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDOrigenCodigo()
	{
		return strBDOrigenCodigo;
	} // getStrBDOrigenCodigo()






/*******************************************************************************
 * Función:     getStrBDOrigenDesc()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve la descripción del error originario de base de datos.
 * Parámetros de entrada:
 * Retorno: strBDOrigenDesc - Descripción del error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDOrigenDesc()
	{
		return strBDOrigenDesc;
	} // getStrBDOrigenDesc()
}
