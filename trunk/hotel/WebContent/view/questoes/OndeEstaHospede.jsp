<%@page import="br.ufc.apsoo.entidades.Hospede"%>
<%@page import="br.ufc.apsoo.entidades.Apartamento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Onde está o hospede?</title>
</head>
<body>

<form action="<%=getServletContext().getContextPath()%>/QueryControl" method="get">

<input type="hidden" name="tipo" value="ondeEstaHospede">

Nome Hospede: <input type="text" name="nomeHospede" />

<br /><br />

<button type="submit">Pesquisar</button>
</form>
<%
List<Hospede> listHospedes = (ArrayList<Hospede>) session.getAttribute("listHospedesOndeEstou");
if(listHospedes != null){
%>
<form action="<%=getServletContext().getContextPath()%>/QueryControl" method="get">
<input type="hidden" name="tipo" value="QualApEstaHospede">
<table border="1" align="center">
	<%for(int i=0; i<listHospedes.size(); i++){%>
	
	<tr>
	<td>
	<input type="radio" name="hospedeOndeEstou" value="<%=listHospedes.get(i).getId()%>"> 
	</td>
	<td>
	<%=listHospedes.get(i).getNome()%> - <%=listHospedes.get(i).getCpf()%>  
	</td>
	</tr>
	<%} %>
</table>

<br />
<button type="submit"> Pesquisar Apartamento </button>
</form>

<%
List<Apartamento> listApartamentosOndeEstou = (List<Apartamento>) session.getAttribute("listApartamentosOndeEstou");
if(listApartamentosOndeEstou != null)
{%>
<br />
<table border="1" align="center">
<%
	for(int i=0; i<listApartamentosOndeEstou.size(); i++){
	%>
	<tr>
	<td>
	<%=listApartamentosOndeEstou.get(i).getNumero()%> - <%=listApartamentosOndeEstou.get(i).getTipo().getNome()%>  
	</td>
	</tr>

<%} } 

%>
</table>
<% } %>
</body>
</html>