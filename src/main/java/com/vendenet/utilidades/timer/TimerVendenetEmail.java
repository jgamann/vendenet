package com.vendenet.utilidades.timer;

import java.util.TimerTask;

import com.vendenet.utilidades.UtilidadesMail;

public class TimerVendenetEmail extends TimerTask {
	
	public void run() {
		//Se ejecuta cada 5 minutos
		UtilidadesMail.enviarEmailsPendientes();
	}

}
