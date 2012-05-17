<%@page import="br.ufc.apsoo.entidades.Servico"%>
<%@page import="br.ufc.apsoo.entidades.Conta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datalhes da Conta</title>
</head>
<%
Conta conta = (Conta)session.getAttribute("conta");
session.removeAttribute("conta");
%>
<body>
	
	<form action="<%=getServletContext().getContextPath()%>/ContaControl">
		<div align="center">
		<table>
		<tr>
			<td>Nome: <%=conta.getHospede().getNome()%></td>
		</tr>
		<tr>
			<td>Cpf:<%=conta.getHospede().getCpf()%></td>
			<td></td>
		</tr>
	</table>
	</div>
	<br clear="all"/>
	<div align="center">
	<table border="1" style="text-align: center; margin-left: 280px;">
		<thead>
			<tr>
				<td>Serviço</td>
				<td>Valor</td>
			</tr>
		</thead>
		<tbody>

			<%
				for (Servico servico : conta.getServicos()) {
			%>

			<tr>
				<td><%=servico.getNome()%></td>
				<td><%=servico.getValor()%></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>		
	</div>
	<br clear="all"/>
	
	<label>Total: <%=conta.getTotal()%></label>
	<br clear="all"/>
	<br clear="all"/>
		<input type="hidden" value="<%=conta.getId()%>" name="idConta"/>
		<input type="hidden" value="Encerrar" name="type"/>
		<input type="submit" value="Encerrar">
	</form>
</body>
</html>