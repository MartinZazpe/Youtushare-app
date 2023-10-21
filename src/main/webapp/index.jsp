<%@page import="com.ipartek.modelo.dto.V_favoritas"%>
<%@page import="com.ipartek.modelo.dto.V_Cancion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    

<%
List<V_Cancion> todasCanciones = new ArrayList<V_Cancion>();

// Debug: Check request attribute first
if (request.getAttribute("atr_lista_canciones") != null) {
    todasCanciones = (List<V_Cancion>) request.getAttribute("atr_lista_canciones");
} 
%>

  <% List<V_favoritas> todasCancionesFavs = new ArrayList<V_favoritas>();
if (request.getAttribute("atr_lista_canciones_favs") != null) {
	todasCancionesFavs =(List <V_favoritas>) request.getAttribute("atr_lista_canciones_favs");
}
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
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

<h4>Los posts más actuales.</h4>

<div id="todas-canciones-contenedor">
<%if(todasCanciones != null && todasCanciones.size() >= 1){ %>

  <%for(V_Cancion ele:todasCanciones.subList(todasCanciones.size()-3, todasCanciones.size())) { %>


<div  class="cancion-contenedor">


<div class="star-container">
       <a href="AgregarFavoritos?idCancion=<%=ele.getId()%>"> <i class="fa-solid fa-star fa-xl favoriteElement" style="color: #fff700;"></i>  </a> 
    </div>

  <img src="http://img.youtube.com/vi/<%=ele.getEnlaceId() %>/hqdefault.jpg" class="card-img-top" alt="...">
  <div>
   <h6 class="cancion-titulo" ><%= ele.getName() %></h6>
   - <i ><%= ele.getGrupo() %></i>
   
    <p ><%= ele.getComentario() != null ? ele.getComentario() : "" %></p>
    
    <div class="cancion-footer">
    <a href="<%=ele.getEnlace() %>>" class="btn btn-primary" target="_blank">Ir al enlace</a>
   <div class="cancion-footer-text">
    <p>Subido por: <a class="<%= ele.getUser_rol() == "banned" ? "banned-user-striked" : "" %>"><%=ele.getUsuario() %></a></p>
       <%if(ele.getUser_rol().equals("banned")){ %>
       <p class="banned-user">Banned</p>
       <%} %>
           </div>
    </div>
  </div>
</div>

<%} %>
<%} %>

</div>










</div>


</section>


</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>