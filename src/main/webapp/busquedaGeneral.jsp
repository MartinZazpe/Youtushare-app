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
  

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Resultado de busqueda </title>
<!-- Bootstrap -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="./styles/index.css">
<link rel="stylesheet" href="./styles/styles.css">
<script src="https://kit.fontawesome.com/d4fe87d88c.js" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>


<body>
<%@include file="includes/cabecera.jsp" %>

<main>



<section id="section-todas-canciones">

<%if(todasCanciones.size() > 0 ) {%>
<h4> Hemos encontrado las siguientes canciones: </h4>
<%} %>

<%if(todasCanciones.size() <= 0 ) {%>
<h4> No hay ningun resultado para esta busqueda. </h4>
<%} %>



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
    <p>Subido por: <a><%=ele.getUsuario() %></a></p>
    </div>
  </div>
</div>

<%} %>



</div>


</section>


</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>