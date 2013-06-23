package com.vendenet.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vendenet.utilidades.HiloInicial;
import com.vendenet.utilidades.PropiedadesSistema;
import com.vendenet.utilidades.UtilidadesCritica;
import com.vendenet.utilidades.constantes.CarEspecialesConstant;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;
import com.vendenet.utilidades.timer.TimerVendenet;
import com.vendenet.utilidades.timer.TimerVendenetEmail;

/**
 * @version 	1.0
 * @author
 */
public class ServletInicio extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ServletInicio.class);
	
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			cargarElementos(req,resp);
	}

	/**
	 * @param req
	 * @param resp
	 */
	private void cargarElementos(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Apéndice de método generado automáticamente
		
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	}

	/**
	* @see javax.servlet.GenericServlet#void ()
	*/
	public void init() throws ServletException {
		LOG.info("ServletInicio - Inicio");
		Calendar fechaHoy = Calendar.getInstance();
		ConstantesVendenet.ANYO_ACTUAL=fechaHoy.get(Calendar.YEAR);
		LOG.info("Año asignado:"+ConstantesVendenet.ANYO_ACTUAL);
		ConstantesVendenet.PATH_REAL=getServletContext().getRealPath("");
		PropiedadesSistema propiedadesSistema=null;
		propiedadesSistema=new PropiedadesSistema(getServletContext().getRealPath(""));
		ConstantesVendenet.paginaInicio=propiedadesSistema.getPropiedad("paginaInicio");
		ConstantesVendenet.PATH_ESTATICO=propiedadesSistema.getPropiedad("path_estatico");
		ConstantesVendenet.RUTA_PDFS =ConstantesVendenet.PATH_ESTATICO+propiedadesSistema.getPropiedad("ruta_pdfs");
		ConstantesVendenet.RUTA_DOCUMENTOS =ConstantesVendenet.PATH_ESTATICO+propiedadesSistema.getPropiedad("ruta_documentos");
		ConstantesVendenet.RUTA_IMAGENES =ConstantesVendenet.PATH_ESTATICO+propiedadesSistema.getPropiedad("ruta_imagenes");
		ConstantesVendenet.URL_LOCALIZADOR_IP=propiedadesSistema.getPropiedad("URL_LOCALIZADOR_IP");
		TextConstant.PLANTILLA_EMAIL_PRE=propiedadesSistema.getPropiedad("plantilla_email_pre");
		TextConstant.PLANTILLA_EMAIL_POST=propiedadesSistema.getPropiedad("plantilla_email_post");
		TextConstant.CONDICIONES=propiedadesSistema.getPropiedad("condiciones");
		List<Integer> lst = new ArrayList<Integer>();
		for(int i=0;i<CarEspecialesConstant.arraycodigos.length;i++){
			lst.add(new Integer(CarEspecialesConstant.arraycodigos[i]));
		}
		CarEspecialesConstant.lstCodigos=lst;
		TimerVendenet timerVendenet = new TimerVendenet();
		Timer timer = new Timer(); 
	    // Dentro de 0 milisegundos avísame cada 1 hora 
	    timer.scheduleAtFixedRate(timerVendenet, 0, NumericConstant.ONE_HOUR_IN_MILISECONDS);
	    Timer timer_2 = new Timer();
	    TimerVendenetEmail timerVendenetEmail = new TimerVendenetEmail();
	    // Dentro de 0 milisegundos avísame cada 1 minuto
	    timer_2.scheduleAtFixedRate(timerVendenetEmail, 0, NumericConstant.ONE_MINUTES_IN_MILISECONDS);
		super.init();
		HiloInicial hiloInicial = new HiloInicial();
		hiloInicial.start();
		LOG.info("ServletInicio - Fin");
	}

}
