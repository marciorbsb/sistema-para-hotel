<%@page import="br.ufc.apsoo.entidades.Apartamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Apartamento> apartamentos = (List<Apartamento>) session.getAttribute("apartamentos");

int x=0;%>
<form action="../../HospedeControl?type=buscarApartamentosLivres" method="post">
	<label>Tipo do Apartamento</label>
	<select name="tipoApartamento">
	       <option value="1">Solteiro</option>
	       <option value="2">Casal</option>
	       <option value="3">Triplo</option>
	       <option value="4">Quádruplo</option>
	</select>
	<input type="submit" value="Buscar">
</form>
	<table border="1">
		<thead>
			<tr>
				<td>Número do Apartamento</td>
				<td>Tipo</td>
				<td>Valor</td>
				<td></td>
			</tr>
		</thead>
		<%if(apartamentos!=null && apartamentos.size()>0){ %>
		<%for(Apartamento apartamento:apartamentos){ %>
		<tbody>
			<tr>
				<td><%=apartamento.getNumero()%></td>
				<td><%=apartamento.getTipo().getNome()%></td>
				<td><%=apartamento.getTipo().getValor()%></td>
				<td><input type="checkbox"  name="idApartamento" value="<%=apartamento.getId()%>"/> </td>
			</tr>
		</tbody>
		<%} %>
		<%}else{  %>
			<h4>Nenhum registro de apartamentos</h4>
		<%}%>
	</table>

</body>
</html>