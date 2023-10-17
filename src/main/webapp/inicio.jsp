<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Bootstrap -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="./styles/inicio.css">
<link rel="stylesheet" href="./styles/styles.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>


<body>
<%@include file="includes/cabecera.jsp" %>



<main>

<section id="section-login">

<h3 id="welcome-message">Bienvenido. Porfavor ingresa o <a href="<%="formularioCrearUsuario"%>">registrate</a> para acceder a esta web.</h3>



<% if(request.getAttribute("usuario_nuevo") != null){ %>
<p class="user-created"><%=request.getAttribute("usuario_nuevo") %></p>
<%}else if ((Integer)session.getAttribute("s_intentos") != null &&(Integer)session.getAttribute("s_intentos") >= 1 ) {%>
<p class="form-error">Login failed, please try again.</p>
<%} %>


<!-- Add a rule to show error if username exists -->

<% if(session.getAttribute("usuario_rol") != null && (session.getAttribute("usuario_rol").equals("pending"))) { %>
<p class="user-pending-approval">Your account is pending approval.</p>
<%} %>

<% if(session.getAttribute("usuario_rol") != null && (session.getAttribute("usuario_rol").equals("banned"))) { %>
<p class="user-banned">Your account has been banned, you may get in touch with </p><a class="support-link" href="#">support.</a>
<%} %>


<form class="form-group register-form" id="login-form" action="Login" method="POST">
<label for="name">Username</label>
<input class="form-control" type="text" name="user"  pattern="[^' ']+" id="name" required/>
<label for="password">Password</label>
<input class="form-control" type="password" name="password" id="password" required/>

<input class="btn btn-primary register-btn button" type="submit" value="login"/>

</form>

</section>

<p>Crear <a href="formularioCrearUsuario">un usuario nuevo</a><p>
 
<p>¿Eres administrador?
 Ingresa aquí:<a href="https://martinzazpe.com/youtushare_admins/"> YoutuShare for Admin's</a><p>



</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>