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
<table>
<tr>
<td>Nome:</td> <td><input name="name" id="name" type="text"></td>
</tr>
<tr>
<td>Valor: </td><td><input name="value" id="value" type="text"></td>
</tr>
<input name="type" type="hidden" value="add">
</table>
<button name="bt_Save_Service" onclick="return valida()">Salvar</button>
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