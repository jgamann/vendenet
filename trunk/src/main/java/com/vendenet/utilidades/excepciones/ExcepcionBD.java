package com.vendenet.utilidades.excepciones;



/*******************************************************************************
 * Clase:       ExcepcionBD
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Clase para el procesamiento de errores dentro del paquete BD. Los
 *   c�digos de error se declaran p�blicos en la propia clase.
 *       Simpre se genera esta clase con un c�digo, una descripci�n y otra
 *   descripci�n ampliada.
 * Versi�n: 1.0.0 
*******************************************************************************/
public class ExcepcionBD extends Exception
{
	//Constantes con los c�digos de error.
	public static final String BD_ERROR_GENERICO = "-1000";
	public static final String BD_ERROR_CONEXION = "-1001";
	public static final String BD_ERROR_CONEXION_CERRAR = "-1002";

	private String strBDCodigo = null; //Atributo en el que se guarda el c�digo de error.
	private String strBDDesc = null; //Atributo en el que se guarda la descripci�n del error.
	private String strBDDescDetallada = null; //Atributo en el que se guarda la descripci�n larga de error.
	private String strBDOrigenCodigo = null; //Atributo en el que se guarda el c�digo de error de or�gen de base de datos.
	private String strBDOrigenDesc = null; //Atributo en el que se guarda la descripci�n del error de or�gen de base de datos.






/*******************************************************************************
 * Funci�n:     ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)
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
	public ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)
	{
		super(strDescDetallada);
		strBDCodigo = strCodigo;
		strBDDesc = strDesc;
		strBDDescDetallada = strDescDetallada;
	} // ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)






/*******************************************************************************
 * Funci�n:     ExcepcionBD(String strCodigo, String strDesc, String strDescDetallada)
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
 *   strParamBDOrigenCodigo - C�digo identificador del error originario de base
 *     de datos.
 *   strParamBDOrigenDesc - Descripci�n del error originario de base de datos.
 * Retorno:
 * Versi�n: 1.0.0
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
 * Funci�n:     getStrBDCodigo()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve el c�digo de error.
 * Par�metros de entrada:
 * Retorno: strBDCodigo - C�digo de error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDCodigo()
	{
		return strBDCodigo;
	} // getStrBDCodigo





/*******************************************************************************
 * Funci�n:     getStrBDDesc()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve la descripci�n del error.
 * Par�metros de entrada:
 * Retorno: strBDDesc - Descripci�n del error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDDesc()
	{
		return strBDDesc;
	}  // getStrBDDesc()






/*******************************************************************************
 * Funci�n:     getStrBDDescDetallada()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve la descripci�n del error.
 * Par�metros de entrada:
 * Retorno: strBDDescDetallada - Descripci�n detallada del error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDDescDetallada()
	{
		return strBDDescDetallada;
	} // getStrBDDescDetallada()






/*******************************************************************************
 * Funci�n:     getStrBDOrigenCodigo()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve el c�digo del error originario de base de datos.
 * Par�metros de entrada:
 * Retorno: strBDOrigenCodigo - C�digo del error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDOrigenCodigo()
	{
		return strBDOrigenCodigo;
	} // getStrBDOrigenCodigo()






/*******************************************************************************
 * Funci�n:     getStrBDOrigenDesc()
 * Autor:       Jesus Garcia Amann
 * Contactos:   jgamann@gmail.com
 *              
 *
 * Descripci�n:
 *       Devuelve la descripci�n del error originario de base de datos.
 * Par�metros de entrada:
 * Retorno: strBDOrigenDesc - Descripci�n del error.
 * Versi�n: 1.0.0
 * Registro de cambios:
*******************************************************************************/
	public String getStrBDOrigenDesc()
	{
		return strBDOrigenDesc;
	} // getStrBDOrigenDesc()
}
