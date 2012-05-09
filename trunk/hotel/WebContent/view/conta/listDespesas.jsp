<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.apsoo.DAO.DespesaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.ufc.apsoo.entidades.Despesa"%>
<%@ page import="br.ufc.apsoo.entidades.Hospede"%>
<%@ page import="br.ufc.apsoo.entidades.Servico"%>
<%@ page import="br.ufc.apsoo.entidades.Conta"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Despesas</title>
</head>
<h3>Lista de Despesas</h3>
<body>
<table border="1" style="margin-left: 370px;">

<%
HashMap<Hospede, HashMap<Conta, Servico>> mapHS = (HashMap<Hospede, HashMap<Conta, Servico>>)session.getAttribute("mapHS"); 
Set<Hospede> listHospede = mapHS.keySet();
int count = 0;
for (Hospede hospede : listHospede) {

	if(count % 2 == 1){
%>


<tr>
<%}else{ %>
	<tr bgcolor="#C0C0C0">
<%} %>

<td align="center"><%=hospede.getNome() %> - <%=hospede.getCpf() %></td>
<%
Set<Conta> setC = mapHS.get(hospede).keySet();
for(Conta mapCS : setC){ %>
<td align="center"><%=mapHS.get(hospede).get(mapCS).getNome() %> - R$: <%=mapHS.get(hospede).get(mapCS).getValor() %></td>
<td><a
				href="<%=getServletContext().getContextPath()%>/ContaControl?&type=estornarDespesa&HId=<%=hospede.getId()%>&SId=<%=mapHS.get(hospede).get(mapCS).getId()%>&CId=<%=mapCS.getId()%>"> <img
					alt="Deletar" src="<%=getServletContext().getContextPath()%>/imgs/delete.png" height="40px"
					width="40px">
			</a></td>

<%} %>
</tr>

<%
count++;
}
%>

</table>
</body>
</html>