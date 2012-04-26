<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Novo Serviço</title>
</head>
<body>

<h3> Cadastrar Novo Serviço </h3>
<form action="../../addService" method="get">

Nome: <input name="name" type="text"> <br />
Valor: <input name="value" type="text">
<input name="type" type="hidden" value="add">
<br />

<button name="bt_Save_Service">Salvar</button>
</form>
</body>
</html>