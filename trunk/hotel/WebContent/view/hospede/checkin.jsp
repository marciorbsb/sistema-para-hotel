<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check In</title>
</head>
<body>
<h1>Check In</h1>

<h3>Dados do Hospede </h3>
<form action="">
<table>
<tr>
	<td>Nome:</td>
	<td><input name="name" type="text"></td>
</tr>
	<tr>
		<td>CPF:</td>
		<td><input name="cpf" type="text"></td>
	</tr>
	<tr>
		<td>Rua:</td>
		<td> <input name="rua" type="text"></td>
	</tr><tr>
		<td>Bairro:</td>
		<td><input name="bairro" type="text"></td>
	</tr><tr>
		<td>CEP:</td>
		<td><input name="cep" type="text"></td>
	</tr><tr>
		<td>Cidade:</td>
		<td><input name="cidade" type="text"></td>
	</tr><tr>
		<td>Telefone Fixo:</td>
		<td><input name="numberFix" type="text"></td>
	</tr><tr>
		<td>Telefone Móvel:</td>
		<td><input name="mobile" type="text"></td>
	</tr>
</table>
<br />

<h3>Dados do Apartamento </h3>
Valor Diária: <input name="daily" type="text"> <br />
Número do Ap.: <input name="number" type="text"> <br />
Tipo de Ap.: <input name="apType" type="text"> <br />

<input name="type" type="hidden" value="doCheckIn">


<br /><br />
<button>Salvar</button>
</form>
</body>
</html>