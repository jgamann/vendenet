var focoBusqueda=false;
var ratonBusqueda=false;

function trim(cadena)
{
	var strCadena="";
	strCadena+=cadena;
	strCadena = strCadena.replace(/^\s*|\s*$/g,"");
	return strCadena;
}


function tokenize(p0, p1, p2, p3)
{
   var input             = p0;
   var separator         = p1;
   var trim              = p2;
   var ignoreEmptyTokens = p3;

   try {
     String(input.toLowerCase());
   }
   catch(e) {
     window.alert("Tokenizer Usage: string myTokens[] = myString.tokenize(string separator, string trim, boolean ignoreEmptyTokens);");
     return;
   }
   var array = input.split(separator);

   if(trim)
     for(var i=0; i<array.length; i++)
       {
         while(array[i].slice(0, trim.length) == trim)
           array[i] = array[i].slice(trim.length);
         while(array[i].slice(array[i].length-trim.length) == trim)
           array[i] = array[i].slice(0, array[i].length-trim.length);
       }

   var token = new Array();
   if(ignoreEmptyTokens)
     {
        for(var i=0; i<array.length; i++)
          if(array[i] != "")
            token.push(array[i]);
     }
   else
     {
        token = array;
     }

   return token;
}

function validarEmail(Cadena) {
	Punto = Cadena.substring(Cadena.lastIndexOf('.') + 1, Cadena.length);
	Dominio = Cadena.substring(Cadena.lastIndexOf('@') + 1, Cadena.lastIndexOf('.'));
	Usuario = Cadena.substring(0, Cadena.lastIndexOf('@'));
	Reserv = "€@/º\"\'+*{}\\<>?¿[]áéíóú#·¡!^*;,: àèìòù?äëïöüÿñÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÑ~%&¬()=";
	valido = true;
	// verifica que el Usuario no tenga un caracter especial
	
	for (var Cont=0; Cont<Usuario.length; Cont++) {
	X = Usuario.substring(Cont,Cont+1);
	if (Reserv.indexOf(X)!=-1)
	valido = false;
	}
	
	// verifica que el Punto no tenga un caracter especial
	for (var Cont=0; Cont<Punto.length; Cont++) {
	X=Punto.substring(Cont,Cont+1);
	if (Reserv.indexOf(X)!=-1)
	valido = false;
	}
	
	// verifica que el Dominio no tenga un caracter especial
	for (var Cont=0; Cont<Dominio.length; Cont++) {
	X=Dominio.substring(Cont,Cont+1);
	if (Reserv.indexOf(X)!=-1)
	valido = false;
	}
	
	// Verifica la sintaxis básica.....
	if (Punto.length<2 || Dominio <1 || Cadena.lastIndexOf('.')<0 || Cadena.lastIndexOf('@')<0 || Usuario<1) {
	valido = false;
	}
	return valido;
}

function abrirUrl(url){
	window.open(url, "");
}

function cambiarBorde(color){
	$(".capa_seleccionable").css('border-color',color);
}
function focoPerdido(){
	focoBusqueda=false;
	if(!ratonBusqueda)cambiarBorde('blue');
}
function focoGanado(){
	cambiarBorde('red');
	focoBusqueda=true;
}
function ratonDentro(){
	cambiarBorde('red');
	ratonBusqueda=true;
}
function ratonFuera(){
	ratonBusqueda=false;
	if(!focoBusqueda)cambiarBorde('blue');
}
function condiciones(){
	window.open("ServletControlador?accion=condiciones");
}

function validarCampo(item){
	if($('#formulario_'+item).val()==""){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else{
		$("label[for=formulario_"+item+"]").css('color','#ffffff');
	}
	return true;
}

function validarCampo(item, num){
	if($('#formulario_'+item).val()==""){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else{
		if($('#formulario_'+item).val().length > num){
			alert("El cuerpo del mensaje excede del limite '"+num+" caracteres'");
			$("label[for=formulario_"+item+"]").css('color','#ff0000');
			return false;
		}else $("label[for=formulario_"+item+"]").css('color','#ffffff');
	}
	return true;
}

function validarCampoEmail(item){
	if(validarEmail($('#formulario_'+item).val())==false){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else{
		$("label[for=formulario_"+item+"]").css('color','#ffffff');
	}
	return true;
}

function validarCampoValoracion(item){
	if($('#estrellita').val()==-1){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else{
		$("label[for=formulario_"+item+"]").css('color','#ffffff');
	}
	return true;
}
function validarOpcionMenu(item){
	if($('#'+item+'_selected').val()==0){
		$('#cabecera_menu_'+item).css('color','#ff0000');
		return false;
	}
	return true;
}

function validarTelefono(item){
	if($('#formulario_'+item).val().length<9){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else return validarCampoNumericoEntero(item);
	
}
function validarCampoNumericoEntero(item){
	if(($('#formulario_'+item).val().indexOf(",")!=-1)||($('#formulario_'+item).val().indexOf(".")!=-1)){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else return validarCampoNumerico(item);
	
}
function validarCampoNumerico(item){
	if($('#formulario_'+item).val()==""){
		$("label[for=formulario_"+item+"]").css('color','#ff0000');
		return false;
	}else{
		$('#formulario_'+item).val($('#formulario_'+item).val().replace(",","."));
		if(isNaN($('#formulario_'+item).val())){
			$("label[for=formulario_"+item+"]").css('color','#ff0000');
			return false;
		}
		else{
			$("label[for=formulario_"+item+"]").css('color','#ffffff');
		}
	}
	return true;
}