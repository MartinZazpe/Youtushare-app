<%@page import="com.ipartek.modelo.dto.Genero"%>
<%@page import="com.ipartek.modelo.dto.V_Cancion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

       <% List<V_Cancion> todasCanciones = new ArrayList<V_Cancion>();
if (request.getAttribute("atr_lista_canciones") != null) {
	todasCanciones =(List <V_Cancion>) request.getAttribute("atr_lista_canciones");
}
    %>
    
    <%List <Genero> todosGeneros = new ArrayList<Genero>();
    if(request.getAttribute("atr_todosGeneros") != null){
    	todosGeneros =(List<Genero>) request.getAttribute("atr_todosGeneros");
    }
    %>
  

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis canciones</title>
<!-- Bootstrap -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="./styles/misCanciones.css">
 <link rel="stylesheet" href="./styles/styles.css">  
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>


</head>


<body>





<%@include file="includes/cabecera.jsp" %>



<main>


<section id="section-todas-canciones">

<div id="titulo-buscador">
<h4> Mis canciones: </h4>
<form action="BuscarCancionUsuario" method="POST" class="form-group register-form">
<input class="form-control" type="search" name="search"  id="search" placeholder="buscar en mis canciones" required/>
<input class="btn btn-primary register-btn button" type="submit" value="Buscar"/>
</form>
</div>

<div id="todas-canciones-contenedor">



  <%for(V_Cancion ele:todasCanciones) { %>

<div  class="cancion-contenedor">
  <img src="http://img.youtube.com/vi/<%=ele.getEnlaceId() %>/hqdefault.jpg" class="card-img-top" alt="...">
  <div>
   <h6 class="cancion-titulo" ><%= ele.getName() %></h6>
   - <i ><%= ele.getGrupo() %></i>
   
    <p ><%= ele.getComentario() != null ? ele.getComentario() : "" %></p>
    
    <div class="cancion-footer">
    <a href="<%=ele.getEnlace() %>>" class="btn btn-primary" target="_blank">Ir al enlace</a>
    <div>
    <a href="VistaModificarCancion?id=<%=ele.getId()%>"> ✏️</a>
    <a href="borrarCancion?id=<%=ele.getId()%>"> 🗑️️</a>   
    </div>
    </div>
  </div>
</div>
<%} %>
</div>

<%if(todasCanciones.size() == 0){ %>
<p class="sin-canciones-error">No has compartido ninguna canción.</p>
<%} %>
</section>

<section id="section-agregar-cancion">

<h3> Compartir una nueva canción</h3>
<% if(request.getAttribute("enlace_incorrecto") != null){ %>
<p class="form-error-light"><%=request.getAttribute("enlace_incorrecto") %></p>
<%} %>

<% if(request.getAttribute("error_crear_cancion") != null){ %>
<p class="form-error-light"><%=request.getAttribute("error_crear_cancion") %></p>
<%} %>

<form class="form-group register-form" id="subir-cancion-form" action="AgregarCancion" method="POST">
<label for="name">Nombre de canción</label>
<input class="form-control" type="text" name="name"  id="name" required/>
<label for="grupo">Grupo</label>
<input class="form-control" type="text" name="grupo" id="grupo" required/>
<label for="enlace">Enlace</label>
<input class="form-control" type="text" name="enlace" id="enlace"/>
<label for="comentario">Comentario</label>
<input class="form-control" type="text" name="comentario" id="comentario" required/>

<div id="genero-form-container">
<label for="genero">Genero</label>
<select name="genero" id="genero">
<%for(Genero ele:todosGeneros) { %>
<option value="<%=ele.getId()%>"> <%=ele.getNombre() %></option>
<%} %>
</select>
</div>

<input class="btn btn-primary register-btn button" style="display:block" type="submit" value="Agregar"/>
</form>
</section>


</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>