package com.ipartek;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.ipartek.modelo.dto.Usuario;
import com.ipartek.modelo.dto.V_Cancion;
import com.ipartek.modelo.dto.V_Usuario;
// import com.mysql.cj.Session;


@WebServlet("/Login")
public class Login extends HttpServlet implements DAO_Constantes {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ruta = VISTA_INICIO; // Change the default route to VISTA_INICIO

        // 1 y 2 recibir y chequear
        String userUsername = request.getParameter("user");
        String userPassword = request.getParameter("password");

       
        
        
        HttpSession session = request.getSession();
        
        

        // intentos de login
        int intentos = session.getAttribute("s_intentos") != null ? (int) session.getAttribute("s_intentos") : 0;

        if (session.getAttribute("usuario_name") == null || session.getAttribute("usuario_name").equals("")) {

            if (userUsername != null && !userUsername.trim().isEmpty() && userPassword != null) {

                // 1 create SP, get DTOs we receive.
                // 2 layout data to DTOs
                // 3 connect to database
                DB_Helper db = new DB_Helper();
                Connection con = db.conectar();

                // 3 (execute method/s stored in dbhelper)
                List<V_Usuario> loginRs = db.checkLogin(con, userUsername, userPassword);
                List<V_Cancion> todasCancionesRs = db.obtenerTodasCanciones(con);
                
                
                // paso 5 desconectar
                db.desconectar(con);
                
                
                
                System.out.println("Size of todasCancionesRs: " + todasCancionesRs.size());
                request.setAttribute("atr_lista_canciones", todasCancionesRs);

              

                // Si el usuario no es nulo y es 1
                if (loginRs != null && loginRs.size() == 1) {
                	
                    // guardar el usuario en sesión
                    V_Usuario[] loginRsToArray = loginRs.toArray(new V_Usuario[0]);
                    session.setAttribute("usuario_id", loginRsToArray[0].getId());
                    session.setAttribute("usuario_name", loginRsToArray[0].getUsername());
                    session.setAttribute("usuario_rol", loginRsToArray[0].getUser_rol());

                    
                    session.setAttribute("s_intentos", 0);

                    if (session.getAttribute("usuario_rol").equals("user") || session.getAttribute("usuario_rol").equals("admin")) {
                    	System.out.println("se esta logeando alquien correctamente");
                        ruta = VISTA_INDEX;
                    } else {
                        System.out.println("se esta logeando alquien que no es ni admin ni user");
                        ruta = VISTA_INICIO;
                    }
                } else {
                    // aqui entra cuando no está creado
                    intentos++;
                    session.setAttribute("s_intentos", intentos);
                    System.out.println("Login failed");
                }
            } else {
                System.out.println("errors while entering fields");
            }
        } else {
           
            ruta = VISTA_INDEX; // Change the route to VISTA_INDEX
        }

        // if user attempts to log in are too many
        if (intentos >= 100) {
            request.getRequestDispatcher("error404.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher(ruta).forward(request, response);
        }
    }




	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
