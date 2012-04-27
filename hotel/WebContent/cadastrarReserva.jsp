<%@page import="br.ufc.apsoo.entidades.Apartamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Reserva</title>
</head>
<body>
<% Apartamento apartamento = (Apartamento)request.getAttribute("apartamento");%>
<form action="ReservaControl?action=reservar" method="post">
Nome: <input name="name" type="text"> <br />
CPF: <input name="cpf" type="text"> <br />
<input value="Reservar" type="submit">
</form>
<br/>
<fieldset style="width: 20%">
<legend>Apartamento</legend>
Número:<%=apartamento.getNumero()%>
<br/>
Valor:<%=apartamento.getTipo().getValor()%>
<br/>
Tipo:<%=apartamento.getTipo().getNome()%>
</fieldset>
</body>
</html>