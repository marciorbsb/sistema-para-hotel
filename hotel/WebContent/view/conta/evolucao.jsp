<%@page import="br.ufc.apsoo.DTO.EvolucaoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evolução dos ultimos três meses</title>
</head>
<body>
	<%
	List<EvolucaoDTO> evolucaoDTO = (List<EvolucaoDTO>) request.getSession().getAttribute("meses");
	request.getSession().removeAttribute("meses");
	%>
	<div align="center">
	<h3>Evolução dos ultimos três meses</h3>
	<table border="1">
		<thead>
			<tr>
				<td>Mês</td>
				<td>Total</td>
			</tr>
		</thead>
		<tr>
			<%for(EvolucaoDTO e:evolucaoDTO){%>
			<tr>
			<td><%=e.getMes().intValue()%></td>
			<td><%=e.getTotal()%></td>
			</tr>
			<%}%>
		</tr>
	</table>
	</div>
</body>
</html>