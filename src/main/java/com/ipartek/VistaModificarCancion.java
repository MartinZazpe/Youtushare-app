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


@WebServlet("/VistaModificarCancion")
public class VistaModificarCancion extends HttpServlet implements DAO_Constantes {
	private static final long serialVersionUID = 1L;
       
  
    public VistaModificarCancion() {
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
		   
          int idCancion = 0;
     
          if(request.getParameter("id") != null) {
        	  idCancion = Integer.parseInt(request.getParameter("id"));
          }
		
			 
			  // 3 (execute method/s stored in dbhelper)
			  List<Genero> todosLosGenerosRs = db.obtenerTodosGeneros(con);
			  Cancion songToModify = db.encontrarCancionAModificar(con, idCancion);			
			  
			  
			  // paso 5 desconectar
		       db.desconectar(con);
		       
			  
       if(songToModify != null) {
	    request.setAttribute("atr_cancion_modificar", songToModify);
	    ruta = VISTA_MODIFICARCANCION;
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
