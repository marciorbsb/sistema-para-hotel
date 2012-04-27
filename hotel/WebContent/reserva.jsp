<%@page import="br.ufc.apsoo.entidades.Apartamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservar</title>
</head>
<body>
<% List<Apartamento> apartamentos = (List<Apartamento>)session.getAttribute("apartamentos");%>


<h3>Filtro para buscar apartamento</h3>
<form action="ReservaControl?action=buscarApartamentos" method="post">
<label>Tipo do Apartamento</label>
<select name="tipoApartamento">
       <option value="1">Solteiro</option>
       <option value="2">Casal</option>
       <option value="3">Triplo</option>
       <option value="4">Quádruplo</option>
</select>

<input type="submit" value="Buscar"/>
</form>

<h3>Lista Apartamentos disponíveis</h3>
<table border="1">
	<thead>
		<tr>
			<td>Número do Apartamento</td>
			<td>Tipo</td>
			<td>Valor</td>
			<td>Reservar?</td>
		</tr>
	</thead>
	<%if(apartamentos!=null && apartamentos.size()>0){ %>
	<%for(Apartamento apartamento:apartamentos){ %>
	<tbody>
		<tr>
			<td><%=apartamento.getNumero()%></td>
			<td><%=apartamento.getTipo().getNome()%></td>
			<td><%=apartamento.getTipo().getValor()%></td>
			<td><a href="ReservaControl?action=cadastroReserva&idApartamento=<%=apartamento.getId()%>">Sim</a></td>
		</tr>
	</tbody>
	<%} %>
	<%}else{  %>
		<h4>Nenhum registro de apartamentos</h4>
	<%}%>
</table>
</body>
</html>