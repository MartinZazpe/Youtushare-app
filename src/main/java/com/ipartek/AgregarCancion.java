package com.ipartek;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.Genero;
import com.ipartek.modelo.dto.V_Cancion;

@WebServlet("/AgregarCancion")
public class AgregarCancion extends HttpServlet implements DAO_Constantes {
	private static final long serialVersionUID = 1L;
       

    public AgregarCancion() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		String ruta = VISTA_MISCANCIONES;
		
		HttpSession session = request.getSession();
		
		int userId =0;
		
			//si ya hay un usuario logeado y guardado en sesion:
			if(session.getAttribute("usuario_name") != null
					&& 
					session.getAttribute("usuario_rol") != null &&
					session.getAttribute("usuario_rol").equals("user")
					) {
				
				
				//ejecutar lo necesario para poder enviar en la mochila
				// 3 connect to database
		   DB_Helper db= new DB_Helper();
		   Connection con= db.conectar();
		   
		   //crear la nueva cancion para enviar a la db;
		   Boolean error=false;
		   String fieldErrors = "";
		   
		   
		   Cancion nuevaCancion = new Cancion();
		   
		
		   //check for user logged's id.
			if(session.getAttribute("usuario_id") != null) {
				
				userId = (Integer)session.getAttribute("usuario_id");
				nuevaCancion.setFK_usuario(userId);
				
				
				   if (request.getParameter("name") != null) {
						nuevaCancion.setName(request.getParameter("name"));
					} else {
						error = true;
						fieldErrors = "name of song can't be received by servlet.";
					}
					
				
					if(request.getParameter("grupo") != null) {
						nuevaCancion.setGrupo(request.getParameter("grupo"));
					} else {
						error = true;
						fieldErrors += "song grupo can't be received by servlet.";
					}
					

					if(request.getParameter("enlace") != null) {
						nuevaCancion.setEnlace(request.getParameter("enlace"));
						
					      String videoIdFromYoutubeRs = db.getVideoIdFromYoutubeUrl(request.getParameter("enlace"));					      
					      nuevaCancion.setEnlaceId(videoIdFromYoutubeRs);
						
					} else {
						error = true;
						fieldErrors += "song enlace can't be received by servlet.";
					}
					

					if(request.getParameter("comentario") != null) {
						nuevaCancion.setComentario(request.getParameter("comentario"));
					} else {
						error = true;
						fieldErrors += "song comentario can't be received by servlet.";
					}
					

					if(request.getParameter("genero") != null) {
						nuevaCancion.setFK_genero(Integer.parseInt(request.getParameter("genero")));
					} else {
						error = true;
						fieldErrors += "song comentario can't be received by servlet.";
					}
					
			};
			
		   
			// 3 (execute method/s stored in dbhelper)
		  List<Cancion> agregarCancionRs = db.agregarCancion(con, userId, nuevaCancion);
	      List<V_Cancion> todasCancionesUsuarioRs = db.obtenerTodasUsuario(con, userId);
	      List<Genero> todosLosGenerosRs = db.obtenerTodosGeneros(con);


			// paso 5 desconectar
	       db.desconectar(con);
	       
	       // paso 6 mochila
	   		request.setAttribute("atr_lista_canciones", todasCancionesUsuarioRs);	
	   		request.setAttribute("atr_todosGeneros", todosLosGenerosRs);
	   		
	   		
	   		if(error == false) {
	  	   	  System.out.println("there has been no errors, song: " + agregarCancionRs + " added correctly");   			
	   		}
	   		else if(error == true) {
	   			System.out.println("could NOT add song: "  + fieldErrors);
	   		}  
			}
			
			
			
			//si no hay un usuario logeado en sesion no haremos nada e ira a la vista inicio	
			// paso 7
			request.getRequestDispatcher(ruta).forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
