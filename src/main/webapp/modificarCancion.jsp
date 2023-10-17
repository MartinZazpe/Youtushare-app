<%@page import="com.ipartek.modelo.dto.Cancion"%>
<%@page import="com.ipartek.modelo.dto.Genero"%>
<%@page import="com.ipartek.modelo.dto.V_Cancion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

       <% Cancion cancionModificar = new Cancion();
if (request.getAttribute("atr_cancion_modificar") != null) {
	cancionModificar =(Cancion) request.getAttribute("atr_cancion_modificar");
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
<title>ModificarCancion</title>
<!-- Bootstrap -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="./styles/misCanciones.css">
<link rel="stylesheet" href="./styles/modificarCancion.css">
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
<h4> Cancion a modificar: </h4>
</div>

  <%if(cancionModificar != null) { %>

<div id="todas-canciones-contenedor">
<div  class="cancion-contenedor">

<form class="form-group register-form" id="subir-cancion-form" action="modificarCancion?id=<%=cancionModificar.getId()%>" method="POST">
<label for="name">Nombre de canción</label>
<input class="form-control" type="text" name="name"  id="name" value="<%= cancionModificar.getName() %>" required/>
<label for="grupo">Grupo</label>
<input class="form-control" type="text" name="grupo" id="grupo" value="<%=cancionModificar.getGrupo() %>" required/>
<label for="enlace">Enlace</label>
<input class="form-control" type="text" name="enlace" id="enlace" value="<%=cancionModificar.getEnlace() %>"/>
<label for="comentario">Comentario</label>
<input class="form-control" type="text" name="comentario" id="comentario" value="<%=cancionModificar.getComentario() != null ? cancionModificar.getComentario() : "" %>" required/>
<div id="genero-form-container">
<label for="genero">Genero</label>
<select name="genero" id="genero">
<%if(todosGeneros != null) {%>
<%for(Genero ele:todosGeneros) { %>
<option value="<%=ele.getId()%>" <%=cancionModificar.getFK_genero() == ele.getId() ? "selected" : "" %> > <%=ele.getNombre()%> </option>
<%} %>
<%} %>
</select>
</div>
<input class="btn btn-primary register-btn button" style="display:block" type="submit" value="Modificar"/>
</form>
  </div>
</div>

  <%} %>



</section>



</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>