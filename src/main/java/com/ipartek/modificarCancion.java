package com.ipartek;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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


@WebServlet("/modificarCancion")
public class modificarCancion extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
   
    public modificarCancion() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String ruta = VISTA_INICIO;
	       
	       String fieldErrors = "";
			
		     HttpSession session = request.getSession();
					
			
			// 3 connect to database
			   DB_Helper db= new DB_Helper();
			   Connection con= db.conectar();
			   
			   int userId=0;
			   
			   
			   Cancion cancionNuevosDatos = new Cancion();
			   
	          //ver si ya hay un usuario logeado;
			   if(session.getAttribute("usuario_id") != null) {			
				   userId = (Integer)session.getAttribute("usuario_id");
				cancionNuevosDatos.setFK_usuario((Integer)session.getAttribute("usuario_id"));
				
				if(request.getParameter("id") != null) {
					cancionNuevosDatos.setId(Integer.parseInt(request.getParameter("id")));
					System.out.println("this is value from from idcancion: " + cancionNuevosDatos.getId());
				} else {
					fieldErrors = "Error at: id, ";
				}
				
				if(request.getParameter("name") != null) {
					cancionNuevosDatos.setName(request.getParameter("name"));
					System.out.println("this is value from form nombreCancion: " + cancionNuevosDatos.getName());
				} else {
					fieldErrors += "name, ";
				}
				
				if(request.getParameter("grupo") != null) {
					cancionNuevosDatos.setGrupo(request.getParameter("grupo")); 
					System.out.println("this is value from form grupoCancion: " + cancionNuevosDatos.getGrupo());
				}
			} else {
				fieldErrors += "grupo, ";
			}
				
	      	if(request.getParameter("enlace") != null) {
					cancionNuevosDatos.setEnlace(request.getParameter("enlace")); 
					System.out.println("this is value from form enlaceCancion: " + cancionNuevosDatos.getEnlace());
					
					String videoIdFromYoutubeRs = db.getVideoIdFromYoutubeUrl(request.getParameter("enlace"));					      
					cancionNuevosDatos.setEnlaceId(videoIdFromYoutubeRs);
				} else {
					fieldErrors += "enlace or enlaceId, ";
				}
				
			
				if(request.getParameter("comentario") != null) {
					cancionNuevosDatos.setComentario(request.getParameter("comentario")); 
					System.out.println("this is value from form comentarioCancion: " + cancionNuevosDatos.getComentario());
				}
				else {
					fieldErrors += "comentario, ";
				}
				
				if(request.getParameter("genero") != null) {
					cancionNuevosDatos.setFK_genero(Integer.parseInt(request.getParameter("genero"))); 
					System.out.println("this is value from form generoCancion: " + cancionNuevosDatos.getFK_genero());
				}
				else {
					fieldErrors += "genero, ";
				}
	          
	       		
				 
	               // 3 (execute method/s stored in dbhelper)
				  Cancion songToModify = db.modificarCancion(con, cancionNuevosDatos);			
				    List<V_Cancion> todasCancionesUsuarioRs = db.obtenerTodasUsuario(con, userId);
				     List<Genero> todosLosGenerosRs = db.obtenerTodosGeneros(con);
				      

						// paso 5 desconectar
				       db.desconectar(con);
				       
				       // paso 6 mochila
				   		request.setAttribute("atr_lista_canciones", todasCancionesUsuarioRs);	
				   		request.setAttribute("atr_todosGeneros", todosLosGenerosRs);
		
			       	       

	       if(songToModify != null) {
		    request.setAttribute("atr_cancion_modificar", songToModify);
		    ruta = VISTA_MISCANCIONES;
	          }
	       else {
	    	   System.out.println("there was an error when setting song to modify attribute");
	       }
	       
	       if(todosLosGenerosRs != null) {
		   		request.setAttribute("atr_todosGeneros", todosLosGenerosRs);
	       }
	       else {
	    	   System.out.println("there was an error when setting generos attribute");
	       }
			  
			request.getRequestDispatcher(ruta).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
