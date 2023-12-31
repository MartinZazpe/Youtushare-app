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
import com.ipartek.modelo.dto.Favorita;
import com.ipartek.modelo.dto.Usuario;
import com.ipartek.modelo.dto.V_Cancion;
import com.ipartek.modelo.dto.V_favoritas;



@WebServlet("/AgregarFavoritos")
public class AgregarFavoritos extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
 
	
    public AgregarFavoritos() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String ruta = VISTA_INICIO;

		
		HttpSession session = request.getSession();

		//si ya hay un usuario logeado y guardado en sesion:
		if(session.getAttribute("usuario_name") != null && 
				session.getAttribute("usuario_rol") != null &&
				session.getAttribute("usuario_rol").equals("user")
				) {
			
			//ejecutar lo necesario para poder enviar en la mochila
			// 3 connect to database
	   DB_Helper db= new DB_Helper();
	   Connection con= db.conectar();
	  	   
	   
		 //Check that the user in session still exists in db.
		    if((Integer)session.getAttribute("usuario_id") != null) {	    	
		    	Usuario buscarUsuarioPorId = db.buscarUsuarioPorId(con, (Integer)session.getAttribute("usuario_id")); 
		    	
		    	int userId = (Integer)session.getAttribute("usuario_id");
		    	
		    	int idCancion = Integer.parseInt(request.getParameter("idCancion"));
		    	
		    	System.out.println("userId = " + userId + " " + "idCancion= " + idCancion);
		    	
		    	if((Integer)session.getAttribute("usuario_id") == (buscarUsuarioPorId.getId())){
		    		// 3 (execute method/s stored in dbhelper)
		    		
		    		   int agregarFavRs  = db.agregarFavorito(con, userId, idCancion);
		    		   List<V_Cancion> todasCancionesRs  = db.obtenerTodasCanciones(con); 
		    		   List<V_favoritas> todasCancionesFavsRs = db.obtenerFavoritasCanciones(con, userId);

		    		
		    		   
		    		   System.out.println(agregarFavRs);
		    		// paso 5 desconectar
		    	       db.desconectar(con);
		    	       
		    	       // paso 6 mochila
		    	   		request.setAttribute("atr_lista_canciones", todasCancionesRs);	
		    	   		request.setAttribute("atr_lista_canciones_favs", todasCancionesFavsRs);	
		    	   		request.setAttribute("cancion_agregada_fav", agregarFavRs);

			  	   	
		    	     	  ruta = VISTA_VERTODO;
		    	}
		    	   else {
				    	//si el usuario en session ya no existe en la db:
							session.removeAttribute("usuario_id");
							session.removeAttribute("usuario_name");
							session.removeAttribute("usuario_rol");
				    	}
		    }  else {
		    	System.out.println("Could not find an attribute for id on session. ");
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
