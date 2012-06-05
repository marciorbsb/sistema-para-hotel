<%@page import="br.ufc.apsoo.DTO.ServicoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Total Servicos</title>
</head>
<body>
<%
	List<ServicoDTO> servicoDTOs = (List<ServicoDTO>) request.getSession().getAttribute("tservicos");
	request.getSession().removeAttribute("tservicos");
	%>
	<div align="center">
	<h3>Servicos do último mês</h3>
	<table border="1">
		<thead>
			<tr>
				<td>Serviço</td>
				<td>Total</td>
			</tr>
		</thead>
		<tr>
			<%for(ServicoDTO s:servicoDTOs){%>
			<tr>
			<td><%=s.getNome()%></td>
			<td><%=s.getValor()%></td>
			</tr>
			<%}%>
		</tr>
	</table>
	</div>
</body>
</html>