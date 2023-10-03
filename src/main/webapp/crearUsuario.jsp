<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a user</title>
<!-- Bootstrap -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="./styles/inicio.css">
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

<section id="section-login">

<h3 id="welcome-message">Welcome. Create a user and join our family.</h3>
<p>Return to <a href="Inicio">login</a></p>



<%if(request.getAttribute("username")!= null){ %>
<p class="form-error"><%=request.getAttribute("username") %></p>
<%} %>

<%if(request.getAttribute("password")!= null){ %>
<p class="form-error"><%=request.getAttribute("password") %></p>
<%} %>


<%if(request.getAttribute("no_matching_password")!= null){ %>
<p class="form-error"><%=request.getAttribute("no_matching_password") %></p>
<%} %>




<form class="form-group register-form" id="login-form" action="crearUsuario" method="POST">
<label for="name">Username</label>
<input class="form-control" type="text" name="user"  pattern="[^' ']+" id="name" required/>
<label for="password">Password</label>
<input class="form-control" type="password" name="password" id="password" required/>
<label for="passwordCheck">Re enter password</label>
<input class="form-control" type="password" name="passwordCheck" id="passwordCheck" required/>

<input class="btn btn-primary register-btn button" type="submit" value="Register"/>

</form>

</section>


</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>