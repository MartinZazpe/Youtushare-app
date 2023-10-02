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

<h3 id="welcome-message">Welcome. Please login or <a href="<%="formularioCrearUsuario"%>">register</a> to access this web</h3>



<% if(request.getAttribute("usuario_nuevo") != null){ %>
<p class="user-created"><%=request.getAttribute("usuario_nuevo") %></p>
<%}else if (((Integer)session.getAttribute("s_intentos")) != null) {%>
<p class="login-failed">Login failed, please try again.</p>
<%} %>


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


<p>Are you an administrator? log in here:<a href="#">YoutuShare Admin's</a><p>

</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>