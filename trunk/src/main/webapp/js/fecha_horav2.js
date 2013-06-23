jQuery(document).ready(function() {
	muestraSeg();  
	muestraMin();  
	muestraHour();
	muestraSem();  
	muestraDay();  
	muestraMon();  
	muestraYear();
});
function muestraSeg() {
	hoyseg = new Date();
	$("#seg").html(formatear(hoyseg.getSeconds()));
	setTimeout("muestraSeg()", 1000);
}

function muestraMin() {
	hoymin = new Date();
	$('#min').html(formatear(hoymin.getMinutes()));
	setTimeout("muestraMin()", 1000);
}

function muestraHour() {
	hoyhour = new Date();
	$('#hour').html(hoyhour.getHours());
	setTimeout("muestraHour()", 1000);
}

function muestraDay() {
	hoyday = new Date();
	$('#day').html(formatear(hoyday.getDate()));
	setTimeout("muestraDay()", 1000);
}

function muestraMon() {
	hoymonth = new Date();
	$('#month').val(hoymonth.getMonth());

	if ($('#month').val() == 0) {
		$('#month').html("Enero");
	} else if ($('#month').val() == 1) {
		$('#month').html("Febrero");
	} else if ($('#month').val() == 2) {
		$('#month').html("Marzo");
	} else if ($('#month').val() == 3) {
		$('#month').html("Abril");
	} else if ($('#month').val() == 4) {
		$('#month').html("Mayo");
	} else if ($('#month').val() == 5) {
		$('#month').html("Junio");
	} else if ($('#month').val() == 6) {
		$('#month').html("Julio");
	} else if ($('#month').val() == 7) {
		$('#month').html("Agosto");
	} else if ($('#month').val() == 8) {
		$('#month').html("Septiembre");
	} else if ($('#month').val() == 9) {
		$('#month').html("Octubre");
	} else if ($('#month').val() == 10) {
		$('#month').html("Noviembre");
	} else {
		$('#month').html("Diciembre");
	}

	setTimeout("muestraMon()", 1000);
}

function muestraSem() {
	hoysem = new Date();
	$('#sem').val(hoysem.getDay());
	if ($('#sem').val() == 0) {
		$('#sem').html("Domingo");
	} else if ($('#sem').val() == 1) {
		$('#sem').html("Lunes");
	} else if ($('#sem').val() == 2) {
		$('#sem').html("Martes");
	} else if ($('#sem').val() == 3) {
		$('#sem').html("Miércoles");
	} else if ($('#sem').val() == 4) {
		$('#sem').html("Jueves");
	} else if ($('#sem').val() == 5) {
		$('#sem').html("Viernes");
	} else {
		$('#sem').html("Sábado");
	}
	setTimeout("muestraSem()", 1000);
}

function muestraYear() {
	hoyyear = new Date();
	$('#year').html(hoyyear.getFullYear());
	setTimeout("muestraYear()", 1000);
}

function formatear(num){
	var N = "" + num;
	var NN = "0" + N;
	NN = NN.substring(NN.length-2, NN.length);
	return NN;
}