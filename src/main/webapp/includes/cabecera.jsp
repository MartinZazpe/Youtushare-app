<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./styles/cabecera.css">
<link rel="stylesheet" href="./styles/styles.css">
<script src="https://kit.fontawesome.com/d4fe87d88c.js" crossorigin="anonymous"></script>
</head>


<header>
<a id="title" href="Inicio">Youtu<span id="title-span">Share</span></a>


<nav id=header-nav>


<% if(session.getAttribute("usuario_name") != null) {%>
<a class="header-nav-element" href="Inicio">Recientes</a>
<a class="header-nav-element" href="VerTodo">Ver todas</a>
<a class="header-nav-element" href="obtenerFavoritos">Favoritas</a>
<a class="header-nav-element" href="VerCancionesUsuario">Mis canciones</a>
<%}%>
</nav>

<%if(session.getAttribute("usuario_rol") != null){ %>
<div id="search-user-details-sections">
<section id="search-bar-section">
<form id="search-canciones-form" action="BusquedaGeneral" method="POST">
<div>
<input placeholder="¿Buscando algo?" type="text" name="search">
<button type="submit">
<i class="fa-solid fa-magnifying-glass"></i>
</button>
</div>
</form>
</section>

<section id="user-details-section">
<div id="user-details">
<div>

<p>Bienvenido, </p>
&nbsp

<b><%= session.getAttribute("usuario_name") %></b>

<%if(session.getAttribute("usuario_rol").equals("admin")){ %>
🔑
<%} %>
</div>
<%if(session.getAttribute("usuario_id") != null){ %>
<div>
<a class="log-out-button" href="LogOut">Log out</a>
</div>
<%} %>
<%} %>

</div>
</section>
</div>


</header>

</html>