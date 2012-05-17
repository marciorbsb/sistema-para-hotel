<%@page import="java.util.List"%>
<%@page import="br.ufc.apsoo.entidades.Conta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CheckOut</title>
</head>
<body>
<%List<Conta> contas = (List<Conta>) session.getAttribute("contas");
session.removeAttribute("contas");
%>
<form action="<%=getServletContext().getContextPath()%>/ContaControl" method="get">
		<h4>Realizar CheckOut</h4>
		<table>
			<tr> <td>
				<label>CPF</label>
				<input type="hidden" name="type" value="buscarConta">
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
	<%if(contas!=null && contas.size()>0){ %>
	<div align="center">
	<table border="1" style="text-align: center; margin-left: 280px;">
		<thead>
			<tr>
				<td>Nome</td>
				<td>Cpf</td>
				<td>Detalhes</td>
			</tr>
		</thead>
		<tbody>
		
		<%for(Conta conta : contas){ %>
		
			<tr>
				<td><%=conta.getHospede().getNome()%>
				<td><%=conta.getHospede().getCpf()%>
				<td><a href="ContaControl?type=detalhes&idConta=<%=conta.getId()%>"><img
					alt="detalhes" src="<%=getServletContext().getContextPath()%>/imgs/icone_lista.gif" height="30px"
					width="25px"></a></td>
			</tr>
		<%} %>
		
		</tbody>
	</table>
	</div>
	<%}else{%>
		<div align="center">
		<h4>Nenhum registro encontrado.</h4>
		</div>
	<%}%>
	
</body>
</html>