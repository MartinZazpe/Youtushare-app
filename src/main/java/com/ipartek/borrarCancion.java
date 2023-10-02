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
import com.ipartek.modelo.dto.Genero;
import com.ipartek.modelo.dto.V_Cancion;


@WebServlet("/borrarCancion")
public class borrarCancion extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       

    public borrarCancion() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ruta = VISTA_INICIO;
		
		HttpSession session = request.getSession();
		
		int userId =0;
		
		int idCancion = 0;
		
		int idUsuario = 0;

		//ver si ya hay un usuario logeado;
		if(session.getAttribute("usuario_id") != null) {
			userId = (Integer)session.getAttribute("usuario_id");
			
			
			if(request.getParameter("id") != null) {
				idCancion =  Integer.parseInt(request.getParameter("id")); 
				System.out.println("this is value from form idcancion: " + idCancion);
			}

			if(session.getAttribute("usuario_id") != null) {
				idUsuario = (int) session.getAttribute("usuario_id"); 
				System.out.println("this is value from form idusuario: " + idUsuario);
			}

			
	 // 3 connect to database
	   DB_Helper db= new DB_Helper();
	   Connection con= db.conectar();
	   
	   
		// 3 (execute method/s stored in dbhelper)
	   String borrarCancion = db.borrarCancion(con, idCancion, idUsuario);
      List<V_Cancion> todasCancionesUsuarioRs = db.obtenerTodasUsuario(con, userId);
      List<Genero> todosLosGenerosRs = db.obtenerTodosGeneros(con);
      

		// paso 5 desconectar
       db.desconectar(con);
       
       // paso 6 mochila
   		request.setAttribute("atr_lista_canciones", todasCancionesUsuarioRs);	
   		request.setAttribute("atr_todosGeneros", todosLosGenerosRs);
   		
   		
  
   	  ruta = VISTA_MISCANCIONES;	
		};
			
			//si no hay un usuario logeado en sesion no haremos nada e ira a la vista inicio	
			// paso 7
			request.getRequestDispatcher(ruta).forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
