var ultimoMenuDesplegado;
$(document).ready(function() {
	$('div.scroll').hide();
	$('div.titulo_scroll span').click(function(){
		var menuActual=$(this).next('div.scroll');
		$(menuActual).slideToggle(350);
		if(ultimoMenuDesplegado!=null){
			if(menuActual.attr('id')!=ultimoMenuDesplegado.attr('id')){
				$("div.scroll").each(function(){
					if($(this).attr('id')==ultimoMenuDesplegado.attr('id')){
						$(this).slideToggle(350);
					}
		    	});
			}
		}
		if(ultimoMenuDesplegado!=null){
			if(menuActual.attr('id')==ultimoMenuDesplegado.attr('id')){
				ultimoMenuDesplegado=null;
			}else{
				ultimoMenuDesplegado=menuActual;
			}
		}else{
			ultimoMenuDesplegado=menuActual;
		}
	});
	
});
