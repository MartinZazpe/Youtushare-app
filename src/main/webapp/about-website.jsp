<%@page import="com.ipartek.modelo.dto.V_favoritas"%>
<%@page import="com.ipartek.modelo.dto.V_Cancion"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.modelo.dto.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    


    
    
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

<h4>Sobre este sitio web</h4>

<p>Este proyecto es el resultado de aplicar los conocimientos adquiridos durante mis estudios de procesamiento de datos con Java.</p>

<p>Incluye funcionalidades para crear y autenticar usuarios, realizar operaciones CRUD, y almacenar la información en una base de datos.</p>

<p>Aspectos destacados:</p>
<ul>
<li><a href="#">Panel administrativo</a> en un sitio web separado.</li>
<li>Almacenamiento de datos en MySQL.</li>
<li>Funcionalidad de inicio de sesión y persistencia en la sesión.</li>


</ul>













</main>




<%@include file="includes/Footer.jsp" %>

</body>






</html>