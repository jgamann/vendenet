package com.vendenet.utilidades;

import java.text.DecimalFormat;



public class UtilidadesNumericos {
	
	
	
	public static StringBuffer limpiarDecimal(double numero){
		if((numero-(int)numero)==0){
			return new StringBuffer(new Integer((int)numero).toString()); 
		}else return new StringBuffer(new Double(numero).toString());
	}
	
	
	public static boolean isNumber(String texto){
		try{
			Double.parseDouble(texto);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isNumberInteger(String texto){
		try{
			Integer.parseInt(texto);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static int convertirAInt(double numero){
		return new Double(numero).intValue();
	}
	
	public static double redondear(double num,int ndecimal){
        double aux0 = Math.pow(10,ndecimal);
        double aux = num * aux0;
        int tmp = (int) aux;
        return (double) (tmp / aux0);     
    }
	
	public static String formatear2Decimales(double in){
		 DecimalFormat  df = new DecimalFormat ("#.00");
		 return df.format(in);
	}
	
}