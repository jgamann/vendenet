package com.vendenet.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;


public class UtilidadesFecha {
   	
	public static String formatearFechaDDMMYY(Date fecha){
		if(fecha != null){
			Calendar fechaHoy = Calendar.getInstance();
			fechaHoy.set(Calendar.DAY_OF_YEAR, fechaHoy.get(Calendar.DAY_OF_YEAR)-1);
			fechaHoy.set(Calendar.HOUR_OF_DAY, 23);
			fechaHoy.set(Calendar.MINUTE, 59);
			fechaHoy.set(Calendar.SECOND, 59);
			if(fecha.after(fechaHoy.getTime())){
				return TextConstant.HOY;
			}
			SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
			return inFmt.format(fecha);	
		}else return "";
	}
	
	public static String formatearFechaDDMMYYNoHoy(Date fecha){
		if(fecha != null){
			Calendar fechaHoy = Calendar.getInstance();
			fechaHoy.set(Calendar.DAY_OF_YEAR, fechaHoy.get(Calendar.DAY_OF_YEAR)-1);
			fechaHoy.set(Calendar.HOUR_OF_DAY, 23);
			fechaHoy.set(Calendar.MINUTE, 59);
			fechaHoy.set(Calendar.SECOND, 59);
			SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
			return inFmt.format(fecha);	
		}else return "";
	}
	
	public static String formatearFechaHHmm(Date fecha){
		if(fecha != null){
			SimpleDateFormat inFmt = new SimpleDateFormat("HH:mm");
			return inFmt.format(fecha);	
		}else return "";
	}
	
	public static String formatearFechaDDMMMMHHmm(Date fecha){
		if(fecha != null){
			SimpleDateFormat inFmt = new SimpleDateFormat("dd MMM, HH:mm");
			return inFmt.format(fecha);	
		}else return "";
	}
	
	/*public static String formatearFechaYYYYMMDD(Date fecha){
		if(fecha != null){
			SimpleDateFormat inFmt = new SimpleDateFormat("yyyy_MM_dd");
			return inFmt.format(fecha);	
		}else return "";
	}*/
	public static String formatearFechaYYYYw(Date fecha){
		if(fecha != null){
			SimpleDateFormat inFmt = new SimpleDateFormat("yyyy_w");
			return inFmt.format(fecha);	
		}else return "";
	}
	public static Date crearFechaDesdeDDMMYYYY(String strFecha){
		SimpleDateFormat inFmt = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return inFmt.parse(strFecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date devolverFechaCaducidad(Date fecha){
		if(fecha != null){
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(fecha);
			fechaCalendar.set(Calendar.YEAR, fechaCalendar.get(Calendar.YEAR)+NumericConstant.CADUCIDAD_ANUNCIO);
			return fechaCalendar.getTime();
		}else return new Date();
	}

	public static Date devolverFechaCaducidadDevuelto(Date fecha){
		if(fecha != null){
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(fecha);
			fechaCalendar.set(Calendar.WEEK_OF_YEAR, fechaCalendar.get(Calendar.WEEK_OF_YEAR)+NumericConstant.CADUCIDAD_ANUNCIO);
			return fechaCalendar.getTime();
		}else return new Date();
	}

	public static Date devolverFechaDiaMenos(Date date) {
		if(date != null){
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(date);
			fechaCalendar.set(Calendar.DAY_OF_YEAR, fechaCalendar.get(Calendar.DAY_OF_YEAR)-1);
			return fechaCalendar.getTime();
		}else return new Date();
		
	}

	public static Date devolverFechaDiaMas(Date date) {
		if(date != null){
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(date);
			fechaCalendar.set(Calendar.DAY_OF_YEAR, fechaCalendar.get(Calendar.DAY_OF_YEAR)+1);
			return fechaCalendar.getTime();
		}else return new Date();
		
	}
	
	
}