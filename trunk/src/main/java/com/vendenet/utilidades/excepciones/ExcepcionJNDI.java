package com.vendenet.utilidades.excepciones;


/*******************************************************************************
 * Clase:       ExcepcionJNDI
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Clase para el procesamiento de errores dentro del paquete JNDI. Los
 *   códigos de error se declaran públicos en la propia clase.
 *       Simpre se genera esta clase con un código, una descripción y otra
 *   descripción ampliada.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
public class ExcepcionJNDI extends Exception
{
	public static final String JNDI_ERROR_GENERICO = "-1000";
	public static final String JNDI_ERROR_RECURSO = "-1001";

	private String strJNDICodigo = null;
	private String strJNDIDesc = null;
	private String strJNDIDescDetallada = null;






/*******************************************************************************
 * Función:     ExcepcionJNDI(String strCodigo, String strDesc, String strDescDetallada)
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
 * Retorno:
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public ExcepcionJNDI(String strCodigo, String strDesc, String strDescDetallada)
	{
		super(strDescDetallada);
		strJNDICodigo = strCodigo;
		strJNDIDesc = strDesc;
		strJNDIDescDetallada = strDescDetallada;
	} // ExcepcionJNDI(String strCodigo, String strDesc, String strDescDetallada)






/*******************************************************************************
 * Función:     getStrJNDICodigo()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve el código de error.
 * Parámetros de entrada:
 * Retorno: strJNDICodigo - Código de error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrJNDICodigo()
	{
		return strJNDICodigo;
	} // getStrJNDICodigo()






/*******************************************************************************
 * Función:     getStrJNDIDesc()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve la descripción del error.
 * Parámetros de entrada:
 * Retorno: strJNDIDesc - Descripción del error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrJNDIDesc()
	{
		return strJNDIDesc;
	} // getStrJNDIDesc()






/*******************************************************************************
 * Función:     getStrJNDIDescDetallada()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripción:
 *       Devuelve la descripción del error.
 * Parámetros de entrada:
 * Retorno: strJNDIDescDetallada - Descripción detallada del error.
 * Versión: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrJNDIDescDetallada()
	{
		return strJNDIDescDetallada;
	} // getStrJNDIDescDetallada()
}
