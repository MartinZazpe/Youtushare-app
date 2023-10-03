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
import com.ipartek.modelo.dto.Usuario;
import com.ipartek.modelo.dto.V_Cancion;
import com.ipartek.modelo.dto.V_favoritas;


@WebServlet("/Inicio")
public class Inicio extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
 
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ruta = VISTA_INDEX;

		
		HttpSession session = request.getSession();

		//si ya hay un usuario logeado y guardado en sesion:
		if(session.getAttribute("usuario_name") != null 
				) {
			
			//ejecutar lo necesario para poder enviar en la mochila
			// 3 connect to database
	   DB_Helper db= new DB_Helper();
	   Connection con= db.conectar();
	   
	   
		 //Check that the user in session still exists in db.
		    if((Integer)session.getAttribute("usuario_id") != null) {	    	
		    	Usuario buscarUsuarioPorId = db.buscarUsuarioPorId(con, (Integer)session.getAttribute("usuario_id")); 
		    	

		    	
		    	if((Integer)session.getAttribute("usuario_id") == (buscarUsuarioPorId.getId())){
		    		
		    		
		    		int userId = (Integer)session.getAttribute("usuario_id");

		    		// 3 (execute method/s stored in dbhelper)
		    		   List<V_Cancion> todasCancionesRs  = db.obtenerTodasCanciones(con);
		    		   List<V_favoritas> todasCancionesFavsRs = db.obtenerFavoritasCanciones(con, userId);

		    		   
		    		// paso 5 desconectar
		    	       db.desconectar(con);
		    	       
		    	       // paso 6 mochila
		    	   		request.setAttribute("atr_lista_canciones", todasCancionesRs);	
		    	   		request.setAttribute("atr_lista_canciones_favs", todasCancionesFavsRs);	
		    	   	    ruta = VISTA_INDEX;

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
		} else {
		    ruta = VISTA_INICIO;
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
