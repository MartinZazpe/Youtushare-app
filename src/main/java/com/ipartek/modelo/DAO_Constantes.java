package com.ipartek.modelo;


public interface DAO_Constantes {


	String DRIVER = "com.mysql.jdbc.Driver";
	String CONEXION = "jdbc:mysql://localhost:3306/canciones_youtube";
	String USUARIO = "root";
	String PASS = "";

	
	//vistas jsp
	String VISTA_INICIO= "inicio.jsp";
	String VISTA_INDEX ="index.jsp";  //aca mostraremos las mas recientes, y alguna otra noticia.
	String VISTA_VERTODO = "todasCanciones.jsp";
	String VISTA_MISCANCIONES = "misCanciones.jsp";
    String VISTA_MISCANCIONESBUSQUEDA = "busquedaUsuario.jsp";
    String VISTA_CREARUSUARIOFORMULARIO = "crearUsuario.jsp";
    String VISTA_BUSQUEDAGENERAL = "busquedaGeneral.jsp";
    String VISTA_MODIFICARCANCION = "modificarCancion.jsp";
    String VISTA_FAVORITOS = "favoritos.jsp";
    String VISTA_ABOUT_WEBSITE = "about-website.jsp";

	
	//db vista usuario
	String V_USUARIO_ID="id";
	String V_USUARIO_USERNAME="username";
	String V_USUARIO_PASSWORD="password";
	String V_USUARIO_USER_ROL="user_rol";

	
	//db vista genero
	String V_GENERO_ID = "id";
	String V_GENERO_NOMBRE ="nombre";
	
	
	//db Cancion
	String CANCION_ID="id";
	String CANCION_NAME="name";
	String CANCION_GRUPO="grupo";
	String CANCION_ENLACE="enlace";
	String CANCION_COMENTARIO = "comentario";
	String CANCION_FK_GENERO= "FK_genero";
	String CANCION_FK_USUARIO = "FK_usuario";
	String CANCION_ENLACEID = "enlaceId";
	

	
	// db vista canciones
	String V_CANCIONES_ID= "id";
	String V_CANCIONES_NAME="name";
	String V_CANCIONES_GRUPO="grupo";
	String V_CANCIONES_ENLACE="enlace";
	String V_CANCIONES_COMENTARIO = "comentario";
	String V_CANCIONES_GENERO = "genero";
	String V_CANCIONES_USUARIO = "usuario";
	String V_CANCIONES_FK_USUARIO = "FK_usuario";
	String V_CANCIONES_FK_GENERO = "FK_genero";
	String V_CANCION_ENLACEID = "enlaceId";
	String v_CANCION_USER_ROL ="user_rol";
	
	
	//db favorito
	String FAVORITO_ID= "id";
	String FK_USUARIO_FAV = "FK_usuario_fav";
	String FK_CANCION_FAV = "FK_cancion_fav";

	//db usuario
	String USUARIO_ID = "id";
	String USUARIO_USERNAME = "username";
	String USUARIO_PASSWORD  ="password";
	String USUARIO_FK_ROL = "FK_rol";
	
	
	//db Customer
	String CUSTOMER_ID= "id";
	String CUSTOMER_USERNAME= "username";
	String CUSTOMER_PASSWORD= "password";
	String CUSTOMER_FK_ROL = "FK_rol";
	
	
	
}
