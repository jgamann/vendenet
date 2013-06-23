/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.negocio.negocio;

import java.io.File;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.CategoriaAdjunto;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.ImageUtil;
import com.vendenet.utilidades.ImageWatermark;
import com.vendenet.utilidades.UtilidadesAdjunto;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class NegoGestorArchivos {
	private Logger logger = Logger.getLogger(NegoGestorArchivos.class);
	private Hashtable hsResultados = new Hashtable();
	
	public void iniciarAplicacion() throws ErrorVendenet {
				
	}

	/**
	 * @return
	 */
	public Hashtable getHsResultados() {
		return hsResultados;
	}

	/**
	 * @param hashtable
	 */
	public void setHsResultados(Hashtable hashtable) {
		hsResultados = hashtable;
	}

	/**
	 * 
	 */
	public boolean subirArchivo(HttpServletRequest req, Session session) throws ErrorVendenet {
		try{
			CategoriaAdjunto categoriaFotografia = (CategoriaAdjunto)session.get(CategoriaAdjunto.class, NumericConstant.ID_CATEGORIA_FOTOGRAFIA);
			if(ServletFileUpload.isMultipartContent(req)){
				ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
				//ServletFileUpload  upload = new ServletFileUpload();
				List items = upload.parseRequest(req);
				Iterator iter = items.iterator();
				for(int i=0;i<items.size();i++){
					FileItem item = (FileItem) iter.next();
					if(!item.isFormField()){
						if(item.getSize()<NumericConstant.TAMANYO_MAX_FOTO){
							File fullFile  = new File(item.getName());
							Adjunto adjunto = new Adjunto();
							adjunto.setCategoriaAdjunto(categoriaFotografia);
							//adjunto = (Adjunto)session.save(adjunto);
							session.saveOrUpdate(adjunto);
							adjunto.setFecha(new Date());
							adjunto.setName(new Integer(adjunto.getId()).toString());
							String nombreDirectorio = UtilidadesFecha.formatearFechaYYYYw(new Date());
							adjunto.setPath(UtilidadesAdjunto.obtenerPath(adjunto.getId(),item.getName(),nombreDirectorio));
							session.saveOrUpdate(adjunto);
							String nuevoArchivo=new StringBuffer(ConstantesVendenet.RUTA_DOCUMENTOS).append(adjunto.getPath()).toString();
							//************************************************ Crear el directorio donde ira la foto ******************************************************************
							File savedDirectory = new File((new StringBuffer(ConstantesVendenet.RUTA_DOCUMENTOS).append(nombreDirectorio)).toString());
							savedDirectory.mkdir();
							//************************************************ Un vez creado el directorio procedemos a grabar el archivo *********************************************
							File savedFile = new File(nuevoArchivo);
							item.write(savedFile);
							
							//************************************************************ Marca de agua ******************************************************************************
//							ImageWatermark.markImage(nuevoArchivo, TextConstant.WATERMARK, new Float(0.2).floatValue(),ImageWatermark.MARK_CENTER);
							
							//*********************************************************************************************************************************************************
							//************************************************ Un vez guardado el archivo creamos el resto de tamaños de foto******************************************
							if(ImageIO.read(savedFile)!=null){
								StringTokenizer st = new StringTokenizer(adjunto.getPath(),".");
								String ultimoToken="";
								while(st.hasMoreTokens()){
									ultimoToken=st.nextToken();
								}
								File savedFileIcono = new File(new StringBuffer(ConstantesVendenet.RUTA_DOCUMENTOS).append(nombreDirectorio).append(TextConstant.SLASH).append(TextConstant.PRE_FOTO_ICONO).append(adjunto.getName()).append(TextConstant.PUNTO).append(ultimoToken).toString());
								ImageUtil.createImage(savedFile,savedFileIcono,ultimoToken,NumericConstant.WIDTH_ICONO,NumericConstant.HEIGHT_ICONO);
								
								File savedFilePeque = new File(new StringBuffer(ConstantesVendenet.RUTA_DOCUMENTOS).append(nombreDirectorio).append(TextConstant.SLASH).append(TextConstant.PRE_FOTO_PEQUE).append(adjunto.getName()).append(TextConstant.PUNTO).append(ultimoToken).toString());
								ImageUtil.createImage(savedFile,savedFilePeque,ultimoToken,NumericConstant.WIDTH_PEQUE,NumericConstant.HEIGHT_PEQUE);
								
								File savedFileGrande = new File(new StringBuffer(ConstantesVendenet.RUTA_DOCUMENTOS).append(nombreDirectorio).append(TextConstant.SLASH).append(TextConstant.PRE_FOTO_MAX).append(adjunto.getName()).append(TextConstant.PUNTO).append(ultimoToken).toString());
								ImageUtil.createImage(savedFile,savedFileGrande,ultimoToken,NumericConstant.WIDTH_MAX,NumericConstant.HEIGHT_MAX);
								ImageWatermark.markImage(savedFileGrande.getAbsolutePath(), TextConstant.WATERMARK, new Float(0.1).floatValue(),ImageWatermark.MARK_CENTER);
								
								ImageUtil.resize(savedFile, NumericConstant.WIDTH_FOTO, NumericConstant.HEIGHT_FOTO,ultimoToken);
								ImageWatermark.markImage(savedFile.getAbsolutePath(), TextConstant.WATERMARK, new Float(0.1).floatValue(),ImageWatermark.MARK_CENTER);
								hsResultados.put("nombreArchivo",new StringBuffer(nombreDirectorio).append(TextConstant.SLASH).append(TextConstant.PRE_FOTO_PEQUE).append(adjunto.getName()).append(TextConstant.PUNTO).append(ultimoToken).toString());
							}else{
								hsResultados.put("nombreArchivo",TextConstant.PUNTO);
								hsResultados.put("mensaje",TextConstant.FORMATO_INVALIDO);
								return false;
							}
						}else{
							hsResultados.put("nombreArchivo",TextConstant.PUNTO);
							hsResultados.put("mensaje",TextConstant.TAMANYO_FOTO_SUPERADO);
							return false;
						}
						
					}
				}
			}
			return true;
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoGestorArchivos:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public void cargarRutaArchivo(String idArchivo,Session session) throws ErrorVendenet {
		try{
			if(UtilidadesNumericos.isNumber(idArchivo)){
				Adjunto loadedAdjunto = (Adjunto)session.get(Adjunto.class, new Integer(idArchivo));
				hsResultados.put("nombreArchivo", UtilidadesAdjunto.obtenerPathFotoPeque(loadedAdjunto.getPath()));
			}else hsResultados.put("nombreArchivo",TextConstant.PUNTO);
			
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoGestorArchivos:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

}
