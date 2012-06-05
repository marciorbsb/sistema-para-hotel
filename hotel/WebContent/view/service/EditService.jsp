<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="br.ufc.apsoo.DAO.ServiceDAO" %>
<%@ page import="br.ufc.apsoo.entidades.Servico" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Serviço</title>
</head>
<body>

<h3> Editar Serviço </h3>
<form action="<%=getServletContext().getContextPath()%>/addService">
<%
String Id = request.getParameter("id");
long idLong = Long.parseLong(Id);
Servico service = null;
System.out.println("Id: " + Id);
if(Id != null){
service = ServiceDAO.getServiceById(idLong);
}
%>
<table>
<tr>
<td>Novo Nome: </td> <td><input name="name" type="text" value="<%=service.getNome()%>"></td>
</tr>
<tr>
<td>Novo Valor: </td><td><input name="value" type="text" value="<%=service.getValor()%>"></td>
</tr>
<input name="type" type="hidden" value="edit">
<input name="id" type="hidden" value="<%=idLong%>">
</table>
<br />
<button name="bt_Edit_Service" onclick="return valida()">Editar</button>

</form>
</body>

<script type="text/javascript">
function valida()
{
	var nome = document.getElementById("name");
	var valor = document.getElementById("value");

	if(nome.value == "")
	{
		alert("Preencha o NOME!");
		return false;
	}
	if(valor.value == "")
	{
		alert("Preencha o VALOR!");
		return false;
	}
	if(isNaN(valor.value))
	{
		alert("Preencha APENAS NÚMEROS no campo Valor!");
		return false;
	}
return true;
	}
</script>


</html>