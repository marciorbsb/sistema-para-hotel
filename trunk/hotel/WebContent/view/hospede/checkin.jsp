<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check In</title>
</head>
<body>
<h1>Check In</h1>

<h3>Dados do Hospede </h3>
<form action="">
Nome: <input name="name" type="text"> <br />
CPF: <input name="cpf" type="text"> <br />
Rua: <input name="rua" type="text"> <br />
Bairro: <input name="bairro" type="text"> <br />
CEP: <input name="cep" type="text"> <br />
Cidade: <input name="cidade" type="text"> <br />
Telefone Fixo: <input name="numberFix" type="text"> <br /> 
Telefone Móvel:<input name="mobile" type="text"> <br />

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