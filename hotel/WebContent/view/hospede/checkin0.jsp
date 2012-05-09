<%@page import="br.ufc.apsoo.entidades.Reserva"%>
<%@page import="br.ufc.apsoo.entidades.Apartamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkin</title>
</head>
<body>
<%List<Reserva> reservas = (List<Reserva>) session.getAttribute("reservas");
session.removeAttribute("reservas");
%>

<form action="<%=getServletContext().getContextPath()%>/HospedeControl?type=buscarReservas" method="post">
		<h4>Realizar Checkin</h4>
		<table>
			<tr> <td>
				<label>CPF</label>
				<input type="text" name="cpf" />
			</td></tr>
			<tr><td>
				<label>Nome</label>
				<input type="text" name="name" />
			</td></tr>
		</table>
		<input type="submit" value="Buscar">
</form>
	<br /><br />
	<%if(reservas!=null && reservas.size()>0){ %>
	<table border="1" style="text-align: center; margin-left: 280px;">
		<thead>
			<tr>
				<td>Nome</td>
				<td>Cpf</td>
				<td>Número do Apartamento</td>
				<td>Tipo</td>
				<td>Valor</td>
				<td>Checkin</td>
			</tr>
		</thead>
		<tbody>
		
		<%for(Reserva reserva : reservas){ %>
		
			<tr>
				<td><%=reserva.getHospede().getNome()%>
				<td><%=reserva.getHospede().getCpf()%>
				<td><%=reserva.getApartamento().getNumero()%></td>
				<td><%=reserva.getApartamento().getTipo().getNome()%></td>
				<td><%=reserva.getApartamento().getTipo().getValor()%></td>
				<td><a href="HospedeControl?type=doCheckIn&idReserva=<%=reserva.getId()%>">OK</a></td>
			</tr>
		<%} %>
		
		</tbody>
	</table>
	<%}else if(reservas != null){%>
	<h2 style="text-align: center;">NÃO HÁ RESERVAS PARA ESSE HOSPEDE! FAÇA UMA RESERVA. :)</h2>
	<%} %>

</body>
</html>