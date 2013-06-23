package com.vendenet.utilidades.timer;

import java.util.TimerTask;

import com.vendenet.utilidades.UtilidadesAnuncio;
import com.vendenet.utilidades.UtilidadesVendenet;
import com.vendenet.utilidades.UtilidadesWebsAmigas;

public class TimerVendenet extends TimerTask {
	
	public void run() {
		//Se ejecuta cada hora
		UtilidadesAnuncio.purgarAnunciosSinCliente();
		UtilidadesAnuncio.purgarAdjuntosHaceUnaHora();
		UtilidadesAnuncio.purgarClientesSinAnuncio();
		UtilidadesAnuncio.purgarAnunciosCaducados();
		UtilidadesAnuncio.purgarVisitasSinAnuncio();
		UtilidadesAnuncio.purgarOpinionesSinConfirmar();
		UtilidadesWebsAmigas.obtenerWebsAmigas();
		UtilidadesVendenet.backupFotos();
	}

}
