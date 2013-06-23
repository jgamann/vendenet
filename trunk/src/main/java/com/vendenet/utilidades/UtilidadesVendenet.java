package com.vendenet.utilidades;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Busqueda;
import com.vendenet.negocio.entidad.CiudadTemp;
import com.vendenet.negocio.entidad.UltimoBackupFoto;
import com.vendenet.negocio.entidad.VisitaIntranet;
import com.vendenet.negocio.entidad.VisitaWeb;
import com.vendenet.negocio.entidad.WebAmiga;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;

public final class UtilidadesVendenet {
	private static Logger logger = Logger.getLogger(UtilidadesVendenet.class);

	public static void registrarVisitaIntranet(Session session,
			HttpServletRequest req, boolean loginOk, String user, String pass) {
		try {
			VisitaIntranet visita = new VisitaIntranet();
			visita.setLoginOk(loginOk);
			visita.setFechaVisita(new Date());
			visita.setName(user + "/" + pass);
			visita.setNavegador(req.getHeader(TextConstant.USER_AGENT));
			visita.setIp(req.getRemoteAddr());
			session.saveOrUpdate(visita);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public static int obtenerNumVisitas(Session session) {
		try {
			int numTotal = 0;
			Criteria crit = session.createCriteria(VisitaWeb.class);
			crit.setProjection(Projections.rowCount());
			numTotal = ((Integer) crit.list().get(0)).intValue();
			return numTotal;
		} catch (Exception eError) {
			logger.error("Error en UtilidadesVendenet: obtenerNumVisitas"+eError);
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			return 0;
		}
	}

	public static String[] obtenerCiudadYComunidadCliente(StringBuffer sburl) {
		try {
			String[] ciuYComun = new String[2];
			MySAXParserBean mySAXParser = new MySAXParserBean();
			Collection v = mySAXParser.parse(sburl.toString());
			;
			Iterator it = v.iterator();
			while (it.hasNext()) {
				MySAXElement element = (MySAXElement) it.next();
				String tag = element.getQname();
				if (tag != null && tag.equals(TextConstant.KEY_CITYNAME)) {
					ciuYComun[0] = element.getValue();
				} else if (tag != null
						&& tag.equals(TextConstant.KEY_REGIONNAME)) {
					ciuYComun[1] = element.getValue();
				}
			}
			return ciuYComun;
		} catch (Exception e) {
			logger.error("URL no encontrada" + sburl+ ":"+e);
			return null;
		}

	}

	public static void guardarCiudad(String[] strings, Session session) {
		try {
			CiudadTemp ciudadTemp = new CiudadTemp();
			ciudadTemp.setName("");
			if (strings != null) {
				ciudadTemp.setName(strings[0]);
				ciudadTemp.setComunidad(strings[1]);
			}
			Criteria crit = session.createCriteria(CiudadTemp.class);
			Criterion crt_ip = Restrictions.eq("name", ciudadTemp.getName());
			crit.add(crt_ip);
			List<CiudadTemp> resultCiudades = crit.list();
			if (resultCiudades.size() == 0) {
				session.saveOrUpdate(ciudadTemp);
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public static void guardarBusqueda(String string, Session session) {
		try {
			Busqueda busqueda = new Busqueda();
			busqueda.setName(string);
			busqueda.setFechaBusqueda(new Date());
			session.saveOrUpdate(busqueda);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public static int obtenerNumAnuncioVendedor(Session session,
			String email) {
		try {
			int numTotal = 0;
			Criteria crit = session.createCriteria(Anuncio.class);
			Criterion crt_cliente = Restrictions.sqlRestriction("{alias}.CLIENTE_ID in (select CLIENTE_ID from cliente where EMAIL = '"+email+"')");
    		crit.add(crt_cliente);
			Criterion crt_publicado = Restrictions.eq("publicado", true);
			crit.add(crt_publicado);
			crit.setProjection(Projections.rowCount());
			numTotal = ((Integer) crit.list().get(0)).intValue();
			return numTotal;
		} catch (Exception eError) {
			logger.error("Error en UtilidadesVendenet: obtenerNumVisitas");
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			return 0;
		}
	}

	public static void backupFotos() {
		List<UltimoBackupFoto> listaResultados=null;
		Transaction transaccion = null;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaccion = session.beginTransaction();
			Criteria crit = session.createCriteria(UltimoBackupFoto.class);
			listaResultados=crit.list();
			Iterator<UltimoBackupFoto> it = listaResultados.iterator();
			if(it.hasNext()){
				UltimoBackupFoto ultimoBackUp = it.next();
				Criteria crit2 = session.createCriteria(Adjunto.class);
				crit2.add(Restrictions.gt("id", ultimoBackUp.getUltimoId()));
				crit2.add(Restrictions.isNotNull("anuncio"));
				crit2.addOrder(Order.asc("id"));
				List lstAdjuntosPendientesBackup = crit2.list();
				Iterator itAdjuntos = lstAdjuntosPendientesBackup.iterator();
				while (itAdjuntos.hasNext()) {
					Adjunto adjunto = (Adjunto)itAdjuntos.next();
					String albumId = crearAlbum(UtilidadesAdjunto.obtenerCarpetaFechaSinSlash(adjunto.getPath()));
					if(albumId!=null){
						albumId=albumId.substring(albumId.indexOf("albumid")+8, albumId.length());
						if(subirFoto(albumId, adjunto)==true){
							ultimoBackUp.setUltimoId(adjunto.getId());
						}else break;
					}
				}
				session.saveOrUpdate(ultimoBackUp);
			}else{
				logger.error("Error controlado en UtilidadesVendenet: no hay registro en la tabla UltimoBackupFoto");
			}
			transaccion.commit();
			session.close();
		}catch(Exception e){
			if(transaccion != null)transaccion.rollback();
			if(session != null)session.close();
			System.err.println("Error en UtilidadesVendenet - backupFotos:"+e);
		}
	}
	
	public static String crearAlbum(String album) {
		String dev=null;
		try{
			PicasawebService myService = new PicasawebService("exampleCo-exampleApp-1");
			myService.setUserCredentials("vendenet.net@gmail.com", "B0rtxus!");
			URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/vendenet.net?kind=album");
			UserFeed myUserFeed = myService.getFeed(feedUrl, UserFeed.class);
			boolean encontrado=false;
			for (AlbumEntry myAlbum : myUserFeed.getAlbumEntries()) {
			    if(myAlbum.getTitle().getPlainText()!=null && myAlbum.getTitle().getPlainText().equals(album)){
			    	dev=myAlbum.getId();
			    	encontrado=true;
			    }
			}
			if(!encontrado){//Si no existe el album, se creará
				AlbumEntry myAlbum = new AlbumEntry();
				myAlbum.setTitle(new PlainTextConstruct(album));
				myAlbum.setDescription(new PlainTextConstruct(album));
				AlbumEntry insertedEntry = myService.insert(feedUrl, myAlbum);
				dev=insertedEntry.getId();
			}
			return dev;
		}catch(Exception e){
			logger.error("Error en UtilidadesVendenet: crearAlbum (album: "+album+") - "+e);
			return null;
		}
	}
	public static boolean subirFoto(String albumid, Adjunto adjunto) {
		try{
			PicasawebService myService = new PicasawebService("exampleCo-exampleApp-1");
			myService.setUserCredentials("vendenet.net@gmail.com", "B0rtxus!");
			URL albumPostUrl = new URL("https://picasaweb.google.com/data/feed/api/user/vendenet.net/albumid/"+albumid);
			PhotoEntry myPhoto = new PhotoEntry();
			PhotoEntry returnedPhoto = null;
			PhotoEntry myPhoto_grande = new PhotoEntry();
			PhotoEntry returnedPhoto_grande = null;
			PhotoEntry myPhoto_peque = new PhotoEntry();
			PhotoEntry returnedPhoto_peque = null;
			PhotoEntry myPhoto_icono = new PhotoEntry();
			PhotoEntry returnedPhoto_icono = null;
			boolean grande=true;
			boolean peque=true;
			boolean icono=true;
			//**************************** AÑADIR LA FOTO NORMAL *****************************************
			try{
				myPhoto.setTitle(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(adjunto.getPath())));
				myPhoto.setDescription(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(adjunto.getPath())));
				myPhoto.setClient("Vendenet.net");
				MediaFileSource myMedia = new MediaFileSource(new File(ConstantesVendenet.RUTA_DOCUMENTOS+adjunto.getPath()), "image/jpeg");
				myPhoto.setMediaSource(myMedia);
				returnedPhoto=myService.insert(albumPostUrl, myPhoto);
			}catch(Exception e){
			}
			//**************************** AÑADIR LA FOTO GRANDE *****************************************
			try{
				myPhoto_grande.setTitle(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(UtilidadesAdjunto.obtenerPathFotoGrande(adjunto.getPath()))));
				myPhoto_grande.setDescription(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(UtilidadesAdjunto.obtenerPathFotoGrande(adjunto.getPath()))));
				myPhoto_grande.setClient("Vendenet.net");
				MediaFileSource myMediaGrande = new MediaFileSource(new File(ConstantesVendenet.RUTA_DOCUMENTOS+ UtilidadesAdjunto.obtenerPathFotoGrande(adjunto.getPath())), "image/jpeg");
				myPhoto_grande.setMediaSource(myMediaGrande);
				returnedPhoto_grande = myService.insert(albumPostUrl, myPhoto_grande);
			}catch(Exception e){
				grande=false;
			}
			//**************************** AÑADIR LA FOTO PEQUE *****************************************
			try{
				myPhoto_peque.setTitle(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(UtilidadesAdjunto.obtenerPathFotoPeque(adjunto.getPath()))));
				myPhoto_peque.setDescription(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(UtilidadesAdjunto.obtenerPathFotoPeque(adjunto.getPath()))));
				myPhoto_peque.setClient("Vendenet.net");
				MediaFileSource myMedia_peque = new MediaFileSource(new File(ConstantesVendenet.RUTA_DOCUMENTOS+ UtilidadesAdjunto.obtenerPathFotoPeque(adjunto.getPath())), "image/jpeg");
				myPhoto_peque.setMediaSource(myMedia_peque);
				returnedPhoto_peque = myService.insert(albumPostUrl, myPhoto_peque);
			}catch(Exception e){
				peque=false;
			}
			//**************************** AÑADIR LA FOTO ICONO *****************************************
			try{
				myPhoto_icono.setTitle(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(UtilidadesAdjunto.obtenerPathFotoIcono(adjunto.getPath()))));
				myPhoto_icono.setDescription(new PlainTextConstruct(UtilidadesAdjunto.quitarCarpetaFecha(UtilidadesAdjunto.obtenerPathFotoIcono(adjunto.getPath()))));
				myPhoto_icono.setClient("Vendenet.net");
				MediaFileSource myMedia_icono = new MediaFileSource(new File(ConstantesVendenet.RUTA_DOCUMENTOS+UtilidadesAdjunto.obtenerPathFotoIcono(adjunto.getPath())), "image/jpeg");
				myPhoto_icono.setMediaSource(myMedia_icono);
				returnedPhoto_icono = myService.insert(albumPostUrl, myPhoto_icono);
			}catch(Exception e){
				icono=false;
			}
			if(!icono){//Si fallo el icono, revertir las otras 3
				returnedPhoto.delete();
				returnedPhoto_grande.delete();
				returnedPhoto_peque.delete();
				return false;
			}else if (!peque){
				returnedPhoto.delete();
				returnedPhoto_grande.delete();
				return false;
			}else if (!grande){
				returnedPhoto.delete();
				return false;
			}
			return true;
		}catch(Exception e){
			logger.error("Error en UtilidadesVendenet: subirFoto (foto: "+adjunto.getPath()+") - "+e);
			return false;
		}
	}
}