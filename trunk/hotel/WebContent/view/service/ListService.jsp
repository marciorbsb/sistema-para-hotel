<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="br.ufc.apsoo.DAO.ServiceDAO"%>
<%@ page import="br.ufc.apsoo.entidades.Servico"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Serviços Cadastrados</title>
</head>
<body>

	<h3>Lista de Serviços Cadastrados</h3>

	<!--  CARREGAR LISTA, POR BOTAO EDITAR E DELETAR DO LADO, AO CLICAR, VAI PARA A PAGINA ESPECIFICA -->

	<table border="1" style="margin-left: 370px;">
		<%
			ArrayList<Servico> listServices = new ArrayList();
			listServices = ServiceDAO.ListServices();
		%>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Valor</th>
			<th>Editar</th>
			<th>Apagar</th>
		</tr>
		<%
			for (Servico servico : listServices) {
		%>

		<tr>
			<td><%=servico.getId()%></td>
			<td><%=servico.getNome()%></td>
			<td><%=servico.getValor()%></td>
			<td><a href="EditService.jsp?&id=<%=servico.getId()%>"> <img
					alt="Editar" src="../../imgs/edit.png" height="40px" width="40px">
			</a></td>
			<td><a
				href="../../addService?&type=delete&id=<%=servico.getId()%>"> <img
					alt="Deletar" src="../../imgs/delete.png" height="40px"
					width="40px">
			</a></td>
		</tr>

		<%
			}
		%>
	</table>
</body>
</html>