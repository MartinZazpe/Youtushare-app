package com.ipartek.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.Genero;
import com.ipartek.modelo.dto.Usuario;
import com.ipartek.modelo.dto.V_Cancion;
import com.ipartek.modelo.dto.V_Usuario;
import com.ipartek.modelo.dto.V_favoritas;
import com.google.gson.Gson;




public class DB_Helper implements DAO_Constantes{
	
	
	
	
	public Connection conectar() {
		
		
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

		try {
		    prop.load(input);
		} catch (IOException e) {
		    e.printStackTrace(); // Handle the exception in an appropriate way (logging, throwing a custom exception, etc.)
		}

		String USER = prop.getProperty("db.username");
		String PASSWORD = prop.getProperty("db.password");	
		String CONNECTI = prop.getProperty("db.connection");
		String DRIVR = prop.getProperty("db.driver");
		
		Connection con = null;

		try {
			Class.forName(DRIVR);
			con = DriverManager.getConnection(CONNECTI, USER, PASSWORD);
			System.out.println("BASE DE DATOS CONECTADA");
		} catch (ClassNotFoundException e) {
			System.out.println("NO SE ENCONTRO EL DRIVER");
		} catch (SQLException e) {
			System.out.println("ERROR AL CONECTAR A LA BD");
		}

		return con;
	}

	public void desconectar(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("NO SE PUDO DESCONECTAR");
		}

	}
	
	
	public List<V_Cancion> obtenerTodasCanciones(Connection con){
		
		// crear una lista vacia
		List<V_Cancion> lista = new ArrayList<V_Cancion>();
		
		try {
			
		// hacer una select que devuelva 
		CallableStatement cStmt = con.prepareCall("call sp_obtenerTodasCanciones();");

		// execute
		boolean dbExecution = cStmt.execute();
					

		if(dbExecution) {
			ResultSet rs = cStmt.getResultSet();

			while(rs.next()) {
				// crear objecto vacio
				V_Cancion producto = new V_Cancion();
				
				// rellenarlo con los datos del RS
				producto.setId(rs.getInt(V_CANCIONES_ID));
				producto.setName(rs.getString(V_CANCIONES_NAME));
				producto.setGrupo(rs.getString(V_CANCIONES_GRUPO));
				producto.setEnlace(rs.getString(V_CANCIONES_ENLACE));
				producto.setComentario(rs.getString(V_CANCIONES_COMENTARIO));
				producto.setGenero(rs.getString(V_CANCIONES_GENERO));
				producto.setUsuario(rs.getString(V_CANCIONES_USUARIO));
				producto.setFK_usuario(rs.getInt(V_CANCIONES_FK_USUARIO));
				producto.setFK_genero(rs.getInt(V_CANCIONES_FK_GENERO));
				producto.setEnlaceId(rs.getString(V_CANCION_ENLACEID));
				producto.setUser_rol(rs.getString(v_CANCION_USER_ROL));
				
				// añadir a la lista
				lista.add(producto);
			}
		}								
		}
		catch (Exception e) {
		
			System.out.println("ERROR, no se pudo efectuar la peticion para: todas las canciones");
			return new ArrayList<V_Cancion>();
		}
		return lista;
	}
	
	
	public List<V_Usuario> checkLogin (Connection con, String username, String userpass){
		
		//hacemos una lista. Solamente debería haber 1 usuario, de esta manera chequeamos.
		List<V_Usuario> listaDeUsuarios = new ArrayList<V_Usuario>();
		
				
		try {
       CallableStatement cStmt = con.prepareCall("call sp_comprobarUsuario(?,?)");
		cStmt.setString(1, username);
		cStmt.setString(2, userpass);

		// execute
		boolean dbExecution = cStmt.execute();
							
		if(dbExecution) {
			 ResultSet rs = cStmt.getResultSet();
			  
			 while(rs.next()) {
				// crear objecto vacio
					V_Usuario usr = new V_Usuario();
					
					usr.setId(rs.getInt(V_USUARIO_ID));
					usr.setUsername(rs.getString(V_USUARIO_USERNAME));
					usr.setUser_rol(rs.getString(V_USUARIO_USER_ROL));
				 
					// añadir a la lista
					listaDeUsuarios.add(usr);
			 }
		}

		} catch (Exception e) {
			System.out.println("ERROR, could not process login on DB");
			return new ArrayList<V_Usuario>();
		}		
		return listaDeUsuarios;
	}
	
	
	
	public List<V_Cancion> obtenerTodasUsuario(Connection con, int userId){
		
		// crear una lista vacia
		List<V_Cancion> lista = new ArrayList<V_Cancion>();
		
		try {
		// hacer una select que devuelva 
		CallableStatement cStmt = con.prepareCall("call sp_vObtenerCancionesUsuario(?);");
		cStmt.setInt(1, userId);


		// execute
		boolean dbExecution = cStmt.execute();
					

		if(dbExecution) {
			ResultSet rs = cStmt.getResultSet();

			while(rs.next()) {
				// crear objecto vacio
				V_Cancion producto = new V_Cancion();
				
				// rellenarlo con los datos del RS
				producto.setId(rs.getInt(V_CANCIONES_ID));
				producto.setName(rs.getString(V_CANCIONES_NAME));
				producto.setGrupo(rs.getString(V_CANCIONES_GRUPO));
				producto.setEnlace(rs.getString(V_CANCIONES_ENLACE));
				producto.setComentario(rs.getString(V_CANCIONES_COMENTARIO));
				producto.setGenero(rs.getString(V_CANCIONES_GENERO));
				producto.setUsuario(rs.getString(V_CANCIONES_USUARIO));
				producto.setFK_usuario(rs.getInt(V_CANCIONES_FK_USUARIO));
				producto.setFK_genero(rs.getInt(V_CANCIONES_FK_GENERO));
				producto.setEnlaceId(rs.getString(V_CANCION_ENLACEID));
				producto.setUser_rol(rs.getString(v_CANCION_USER_ROL));

				
				// añadir a la lista
				lista.add(producto);
			}
		}								
		}
		catch (Exception e) {
		
			System.out.println("ERROR, no se pudo efectuar la peticion para: todas las canciones de el usuario " + userId);
			
			return new ArrayList<V_Cancion>();
		}
		return lista;
	}
	
	
	
	public List<Genero> obtenerTodosGeneros(Connection con){
		
		
		List<Genero> list = new ArrayList<Genero>();
		
		try {
			CallableStatement cStmt = con.prepareCall("call sp_obtenerTodosGeneros();");
			
			
			boolean dbExecution = cStmt.execute();

				
			if(dbExecution) {
				ResultSet rs = cStmt.getResultSet();
				
				while(rs.next()) {
					Genero genero = new Genero();
					
					//rellenar nuevo objeto
					genero.setId(rs.getInt(V_GENERO_ID));
					genero.setNombre(rs.getString(V_GENERO_NOMBRE));
					
					//añadir a la lista
					list.add(genero);
				}
			}
			
		} catch (Exception e) {
			System.out.println("no se han podido obtener los generos");
			return new ArrayList<Genero>();
		}
		
		return list;
	}
	
	
	
	public List<Cancion> agregarCancion(Connection con, int userId, Cancion nuevaCancion){
		
		List<Cancion> list = new ArrayList<Cancion>();
		
		try {
			CallableStatement cStmt = con.prepareCall("call sp_agregarCancion(?,?,?,?,?,?,?);");
			cStmt.setString(1, nuevaCancion.getName());
			cStmt.setString(2, nuevaCancion.getGrupo());
			cStmt.setString(3, nuevaCancion.getEnlace());
			cStmt.setString(4, nuevaCancion.getComentario());
			cStmt.setInt(5, nuevaCancion.getFK_genero());
			cStmt.setInt(6, nuevaCancion.getFK_usuario());
			cStmt.setString(7,nuevaCancion.getEnlaceId());
			
			// execute
			boolean dbExecution = cStmt.execute();
			
			
			if(dbExecution) {
				 ResultSet rs = cStmt.getResultSet();
				  
				 while(rs.next()) {
					// crear objecto vacio
						Cancion uploadedSong = new Cancion();
											
						uploadedSong.setName(rs.getString(CANCION_NAME));
						uploadedSong.setGrupo(rs.getString(CANCION_GRUPO));
						uploadedSong.setEnlace(rs.getString(CANCION_ENLACE));
						uploadedSong.setComentario(rs.getString(CANCION_COMENTARIO));
						uploadedSong.setFK_genero(rs.getInt(CANCION_FK_GENERO));
						uploadedSong.setFK_usuario(rs.getInt(CANCION_FK_USUARIO));
						uploadedSong.setEnlaceId(rs.getString(CANCION_ENLACEID));
						
						// añadir a la lista
						list.add(uploadedSong);
				 }
			}
			
			
		} catch (Exception e) {
			System.out.println("there was an error uploading the song to db");
			return new ArrayList<Cancion>();
		}
		System.out.println("song correctly uploaded: " + list);
		return list;
	}
	
	
	
	
	
	public String getVideoIdFromYoutubeUrl(String url){
	    String videoId = null;
	    String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
	    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(url);
	    if(matcher.find()){
	        videoId = matcher.group(1);
	    }
	    return videoId;
	}
	
	
	
	public List<V_Cancion> cancionesUsuarioBusqueda(Connection con, String searchText, int idUser){
		
		// crear una lista vacia
		List<V_Cancion> lista = new ArrayList<V_Cancion>();
		
		try {
			
		// hacer una select que devuelva 
		CallableStatement cStmt = con.prepareCall("call sp_buscar_cancion_usuario(?,?);");
		cStmt.setString(1, searchText);
		cStmt.setInt(2, idUser);

		// execute
		boolean dbExecution = cStmt.execute();
					

		if(dbExecution) {
			ResultSet rs = cStmt.getResultSet();

			while(rs.next()) {
				// crear objecto vacio
				V_Cancion producto = new V_Cancion();
				
				// rellenarlo con los datos del RS
				producto.setId(rs.getInt(V_CANCIONES_ID));
				producto.setName(rs.getString(V_CANCIONES_NAME));
				producto.setGrupo(rs.getString(V_CANCIONES_GRUPO));
				producto.setEnlace(rs.getString(V_CANCIONES_ENLACE));
				producto.setComentario(rs.getString(V_CANCIONES_COMENTARIO));
				producto.setGenero(rs.getString(V_CANCIONES_GENERO));
				producto.setUsuario(rs.getString(V_CANCIONES_USUARIO));
				producto.setFK_usuario(rs.getInt(V_CANCIONES_FK_USUARIO));
				producto.setFK_genero(rs.getInt(V_CANCIONES_FK_GENERO));
				producto.setEnlaceId(rs.getString(V_CANCION_ENLACEID));
	
				
				// añadir a la lista
				lista.add(producto);
			}
		}								
		}
		catch (Exception e) {
		
			System.out.println("ERROR, no se pudo efectuar la busqueda de canciones para: " + idUser);
			return new ArrayList<V_Cancion>();
		}
		return lista;
	}
	
	
	
	
	 public String borrarCancion(Connection con, Integer idCancion, Integer idUsuario) {
		
		 String cancionBorrada = "";
		 
		
		 try {
				// hacer una select que devuelva 
			    CallableStatement cStmt = con.prepareCall("call sp_borrarCancion(?,?);");
				 cStmt.setInt(1, idCancion);
				 cStmt.setInt(2, idUsuario);
				 
				// execute
				boolean dbExecution = cStmt.execute();
				
				if(dbExecution) {
					System.out.println("succesfully deleted song: " + idCancion);
				}
						
		} catch (Exception e) {
			System.out.println("could not delete song for userid: " + idUsuario);
		}

		 
		return cancionBorrada;
	}
	
	 
	 
		public String crearUsuario (Connection con, String username, String userpass){
			
			//hacemos una lista para devolver el usuario creado, sino devolveremos una vacia.
			String usuarioCreado = "";
			
			
			System.out.println(username + " " + userpass);
					
			try {
	       CallableStatement cStmt = con.prepareCall("call sp_crearUsuario(?,?,?)");
			cStmt.setString(1, username);
			cStmt.setString(2, userpass);
			cStmt.setInt(3, 1); // if set to 4, the user will be creating with a "pending" status

			// execute
			boolean dbExecution = cStmt.execute();
								
		
			} catch (Exception e) {
				System.out.println("ERROR, could not process login on DB");
				return usuarioCreado;
			}		

			usuarioCreado = "success";
			return usuarioCreado;
		}
	 
	 
	
		
		public List<V_Cancion> busquedaGeneral(Connection con, String searchText){
			
			// crear una lista vacia
			List<V_Cancion> lista = new ArrayList<V_Cancion>();
			
			try {
				
			// hacer una select que devuelva 
			CallableStatement cStmt = con.prepareCall("call sp_buscar_cancion(?);");
			cStmt.setString(1, searchText);


			// execute
			boolean dbExecution = cStmt.execute();
						

			if(dbExecution) {
				ResultSet rs = cStmt.getResultSet();

				while(rs.next()) {
					// crear objecto vacio
					V_Cancion producto = new V_Cancion();
					
					// rellenarlo con los datos del RS
					producto.setId(rs.getInt(V_CANCIONES_ID));
					producto.setName(rs.getString(V_CANCIONES_NAME));
					producto.setGrupo(rs.getString(V_CANCIONES_GRUPO));
					producto.setEnlace(rs.getString(V_CANCIONES_ENLACE));
					producto.setComentario(rs.getString(V_CANCIONES_COMENTARIO));
					producto.setGenero(rs.getString(V_CANCIONES_GENERO));
					producto.setUsuario(rs.getString(V_CANCIONES_USUARIO));
					producto.setFK_usuario(rs.getInt(V_CANCIONES_FK_USUARIO));
					producto.setFK_genero(rs.getInt(V_CANCIONES_FK_GENERO));
					producto.setEnlaceId(rs.getString(V_CANCION_ENLACEID));
		
					
					// añadir a la lista
					lista.add(producto);
				}
			}								
			}
			catch (Exception e) {
			
				System.out.println("ERROR, no se pudo efectuar la busqueda de canciones");
				return new ArrayList<V_Cancion>();
			}
			return lista;
		}
		
		
	    	
	
	
		
	public List<V_Cancion> todasCancionesJson(Connection con){
		
		// crear una lista vacia
		List<V_Cancion> lista = new ArrayList<V_Cancion>();
		
	   String csvFilePath ="/Users/martinzazpe/documents/ficherosjava/E005-cancionesYoutube/Canciones-db.csv";
		
		
		try {
			
		// hacer una select que devuelva 
		CallableStatement cStmt = con.prepareCall("call sp_obtenerTodasCanciones();");

		// execute
		boolean dbExecution = cStmt.execute();
					

		if(dbExecution) {
			
			ResultSet rs = cStmt.getResultSet();
			
		
			//Instantiating the CSVWriter class
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));			
			
			ResultSetMetaData Mdata = rs.getMetaData();
		    Mdata.getColumnName(1);
		    
		  //Writing data to a csv file

			// write header line containing column names       
		  
           fileWriter.write("id,name,grupo,enlace,genero,usuario");
           fileWriter.newLine();
         
           String[] data = new String[6];
           
			while(rs.next()) {
								
				data[0] = Integer.toString(rs.getInt(V_CANCIONES_ID));
				data[1] =  rs.getString(V_CANCIONES_NAME);
				data[2] =  rs.getString(V_CANCIONES_GRUPO);
			    data[3] =  rs.getString(V_CANCIONES_ENLACE);
				data[4] = rs.getString(V_CANCIONES_GENERO);
				data[5] = rs.getString(V_CANCIONES_USUARIO);
				
				
				for(int i=0; i < data.length; i++) {
					fileWriter.write(data[i]);
					fileWriter.write(",");
				}
				
				
				fileWriter.newLine();
			    
			    System.out.println(data[0]);
			}
			 fileWriter.flush();
			 fileWriter.close();
		}			
		
		}
		catch (Exception e) {	
			System.out.println("No se pudo efectuar el proceso de guardado en csv: " + e);
			return new ArrayList<V_Cancion>();
		}
		return lista;
	}
	
	
	
	
	public Cancion encontrarCancionAModificar(Connection con, int idCancion) {
		
		
		Cancion cancionAmodificar = new Cancion();

		
		try {
			
			// hacer una select que devuelva 
			CallableStatement cStmt = con.prepareCall("call sp_buscar_cancion_por_id(?);");
			cStmt.setInt(1, idCancion);
			
			// execute
			boolean dbExecution = cStmt.execute();
			
			
			if(dbExecution) {
							
				ResultSet rs = cStmt.getResultSet();

				
				while(rs.next()) {
					
					cancionAmodificar.setId(rs.getInt(CANCION_ID));
					cancionAmodificar.setName(rs.getString(CANCION_NAME));
					cancionAmodificar.setGrupo(rs.getString(CANCION_GRUPO));
					cancionAmodificar.setEnlace(rs.getString(CANCION_ENLACE));
					cancionAmodificar.setComentario(rs.getString(CANCION_COMENTARIO));
					cancionAmodificar.setFK_genero(rs.getInt(CANCION_FK_GENERO));
					cancionAmodificar.setFK_usuario(rs.getInt(CANCION_FK_USUARIO));
					cancionAmodificar.setEnlaceId(rs.getString(CANCION_ENLACEID));

				}
				
			}
			
		} catch (Exception e) {
			System.out.println("ERROR, no se pudo obtener cancion a modificar: " + e);
			return new Cancion();
			}		
		
		System.out.println("la cancion a modificar: " + cancionAmodificar);
		return cancionAmodificar;
	}
	

	
	public Cancion modificarCancion(Connection con, Cancion modificarCancion) {
	
		String fieldErrors = "";
		
		boolean dbExecution;
		
		try {
			CallableStatement cStmt = con.prepareCall("call sp_modificar_cancion(?,?,?,?,?,?,?);");
			cStmt.setInt(1, modificarCancion.getId());
			cStmt.setString(2, modificarCancion.getName());
			cStmt.setString(3, modificarCancion.getGrupo());
			cStmt.setString(4, modificarCancion.getEnlace());
			cStmt.setString(5, modificarCancion.getComentario());
			cStmt.setInt(6, modificarCancion.getFK_genero());
			cStmt.setString(7, modificarCancion.getEnlaceId());
			
			
			System.out.println("lo recibido a modificar: " + modificarCancion.getId() + " " +  modificarCancion.getName());
			
			dbExecution	= cStmt.execute();
			
		} catch (Exception e) {
			System.out.println("There was an error modifying the song");
			return new Cancion();
		}
		System.out.println("song correctly updated: " + dbExecution );
		return new Cancion();		
	}
	
	
	public Usuario buscarUsuarioPorId(Connection con, int idUsuario) {
		
		
		//modificar este codigo para que devuelva el usuario correcto o nada si no hay match
		
		Usuario usuarioConfirmado = new Usuario();
				
		try {
			
			// hacer una select que devuelva 
			CallableStatement cStmt = con.prepareCall("call sp_buscar_usuario_por_id(?);");
			cStmt.setInt(1, idUsuario);
			
			// execute
			boolean dbExecution = cStmt.execute();
			
			
			if(dbExecution) {
							
				ResultSet rs = cStmt.getResultSet();

				
				while(rs.next()) {
					Usuario  usr = new Usuario();
					
					usr.setId(rs.getInt(USUARIO_ID));
					usr.setUsername(rs.getString(USUARIO_USERNAME));
					usr.setPassword(rs.getString(USUARIO_PASSWORD));
					usr.setFK_rol(rs.getInt(USUARIO_FK_ROL));

					usuarioConfirmado = usr;
				}
			}
			
		} catch (Exception e) {
			System.out.println("ERROR, no se pudo encontrar el usuario: " + e);
			return new Usuario();
			}		
		return usuarioConfirmado;
	}
	
	
	public int agregarFavorito (Connection con,int userId,int cancionId) {
		
		int dbExecution;
		int idCancionAgregada = 0;
		
		try {
			
			// hacer una select que devuelva 
			CallableStatement cStmt = con.prepareCall("call sp_agregar_favoritos(?,?);");
			cStmt.setInt(1, cancionId);
			cStmt.setInt(2, userId);
			
			dbExecution = cStmt.executeUpdate();

			if(dbExecution>0) {
				
				System.out.println("this: "+ dbExecution);
				
				idCancionAgregada = cancionId;
			}
			
		} catch (Exception e) {
			System.out.println("could not add to favorite " + e);
			return idCancionAgregada;
		}
		return idCancionAgregada;
	}
	
	
	
	
public List<V_favoritas> obtenerFavoritasCanciones(Connection con, int userId){
		
		// crear una lista vacia
		List<V_favoritas> lista = new ArrayList<V_favoritas>();
		
		try {
			
		// hacer una select que devuelva 
		CallableStatement cStmt = con.prepareCall("call sp_obtener_favoritos(?);");
         cStmt.setInt(1, userId);
		
		// execute
		boolean dbExecution = cStmt.execute();
					

		if(dbExecution) {
			ResultSet rs = cStmt.getResultSet();

			while(rs.next()) {
				// crear objecto vacio
				V_favoritas producto = new V_favoritas();
				
				// rellenarlo con los datos del RS
				producto.setId(rs.getInt(V_CANCIONES_ID));
				producto.setName(rs.getString(V_CANCIONES_NAME));
				producto.setGrupo(rs.getString(V_CANCIONES_GRUPO));
				producto.setEnlace(rs.getString(V_CANCIONES_ENLACE));
				producto.setComentario(rs.getString(V_CANCIONES_COMENTARIO));
				producto.setGenero(rs.getString(V_CANCIONES_GENERO));
				producto.setUsuario(rs.getString(V_CANCIONES_USUARIO));
				producto.setFK_usuario(rs.getInt(V_CANCIONES_FK_USUARIO));
				producto.setFK_genero(rs.getInt(V_CANCIONES_FK_GENERO));
				producto.setEnlaceId(rs.getString(V_CANCION_ENLACEID));
				producto.setUser_rol(rs.getString(v_CANCION_USER_ROL));
				producto.setId(rs.getInt("id"));
				producto.setFK_usuario_fav(rs.getInt("FK_usuario_fav"));
				producto.setFK_cancion_fav(rs.getInt("FK_cancion_fav"));
				
				// añadir a la lista
				lista.add(producto);
			}
		}								
		}
		catch (Exception e) {
		
			System.out.println("ERROR, no se pudo obtener favoritas: " + e);
			return new ArrayList<V_favoritas>();
		}
		return lista;
	}
	


public String borrarFavorita(Connection con, int idCancion, int idUsuario, String nombreCancion) {
	
	 String favoritaBorrada = "";
	
	 
	 System.out.println(idCancion + " " + idUsuario + " " + nombreCancion);
	
	 try {
			// hacer una select que devuelva 
		    CallableStatement cStmt = con.prepareCall("call sp_borrar_favorita(?,?);");
			 cStmt.setInt(1, idCancion);
			 cStmt.setInt(2, idUsuario);
			 
			// execute
			boolean dbExecution = cStmt.execute();
		
					
	} catch (Exception e) {
		System.out.println("Could not delete favorite " + favoritaBorrada);
		favoritaBorrada = nombreCancion;
		return favoritaBorrada;
	}

	 
	return favoritaBorrada;
}




	
}
