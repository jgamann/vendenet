package com.vendenet.utilidades.excepciones;


/*******************************************************************************
 * Clase:       ExcepcionJNDI
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Clase para el procesamiento de errores dentro del paquete JNDI. Los
 *   c�digos de error se declaran p�blicos en la propia clase.
 *       Simpre se genera esta clase con un c�digo, una descripci�n y otra
 *   descripci�n ampliada.
 * Versi�n: 1.0.0
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
 * Funci�n:     ExcepcionJNDI(String strCodigo, String strDesc, String strDescDetallada)
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       El constructor recibe tres par�metros para la carga de atributos.
 * Par�metros de entrada:
 *   strCodigo - C�digo identificador del error producido.
 *   strDesc - Descripci�n del error producido.
 *   strDescDetallada - Descripci�n detallada del error producido.
 * Retorno:
 * Versi�n: 1.0.0
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
 * Funci�n:     getStrJNDICodigo()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve el c�digo de error.
 * Par�metros de entrada:
 * Retorno: strJNDICodigo - C�digo de error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrJNDICodigo()
	{
		return strJNDICodigo;
	} // getStrJNDICodigo()






/*******************************************************************************
 * Funci�n:     getStrJNDIDesc()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve la descripci�n del error.
 * Par�metros de entrada:
 * Retorno: strJNDIDesc - Descripci�n del error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrJNDIDesc()
	{
		return strJNDIDesc;
	} // getStrJNDIDesc()






/*******************************************************************************
 * Funci�n:     getStrJNDIDescDetallada()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve la descripci�n del error.
 * Par�metros de entrada:
 * Retorno: strJNDIDescDetallada - Descripci�n detallada del error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrJNDIDescDetallada()
	{
		return strJNDIDescDetallada;
	} // getStrJNDIDescDetallada()
}
