<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="br.ufc.apsoo.DAO.ServiceDAO" %>
<%@ page import="br.ufc.apsoo.entidades.Servico" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Serviço</title>
</head>
<body>

<h3> Editar Serviço </h3>
<form action="../../addService">
<%
String Id = request.getParameter("id");
long idLong = Long.parseLong(Id);
Servico service = null;
System.out.println("Id: " + Id);
if(Id != null){
service = ServiceDAO.getServiceById(idLong);
}
%>
Novo Nome: <input name="name" type="text" value="<%=service.getNome()%>"> <br />
Novo Valor: <input name="value" type="text" value="<%=service.getValor()%>">
<input name="type" type="hidden" value="edit">
<input name="id" type="hidden" value="<%=idLong%>">
<br />

<button name="bt_Edit_Service">Editar</button>

</form>
</body>
</html>