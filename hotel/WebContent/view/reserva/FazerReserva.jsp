<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.apsoo.DAO.HospedeDAO"%>
<%@page import="br.ufc.apsoo.entidades.Hospede"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fazer Reserva</title>
</head>
<body>
<h3>Hospedes Cadastrados</h3>
<form action="../../ReservaControl" method="post" >
<table border="1">
<%
ArrayList<Hospede> hospedes = HospedeDAO.listHospedes(); 
if(hospedes.size() <= 0)
{
	out.write("Não há hospedes cadastrados!");
}
int count = 0;
for(Hospede hospede : hospedes){
	
if(count % 2 == 1){
%>
<tr>
<%}else{ %>
	<tr bgcolor="#C0C0C0">
<%} %>
<input type="hidden" name="type" id="type" value="addReserva">
<input type="hidden" name="idHospede" id="idHospede" value="<%=hospede.getId()%>">

<td><%=hospede.getNome() %></td>
<td><%=hospede.getEndereco().getRua()%> - <%=hospede.getEndereco().getBairro()%> - <%=hospede.getEndereco().getCidade()%></td>
<td> <input type="radio" name="radio" value="<%=hospede.getId()%>"> </td>
</tr>
<%count++;} %>
</table>

<a href="view/hospede/cadastrarHospede.jsp"> Cadastrar novo hospede </a>

<br /><br />

<button type="submit" >Salvar Reserva</button>
</form>
</body>
</html>