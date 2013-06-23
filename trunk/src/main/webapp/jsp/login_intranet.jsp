<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Intranet</title>
<link href="estilos/estilos_intranetv4.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="imagenes/favicon.ico" type="image/x-icon" />
</head>
<body class="body_confondo">
<div id="contenedor">
<div class="line"></div>
<div class="caja_login">
<form id="form1" name="form1" method="post" action="ServletControlador?accion=validarLoginIntranet">
	<div id="login">
	<div>Usuario:</div>
	<div><input id="usuarioform" name="usuarioform" type="text" class="login_ex" size="30" /></div>
	<div> Contrase&ntilde;a:</div>
	<div><input id="passform" name="passform" type="password" class="login_ex" size="30" /></div>
    <div><input name="entrar" type="submit" class="boton" id="entrar" value="ENTRAR" /></div>	
	</div>
</form>
</div>
</div>
</body>
</html>
