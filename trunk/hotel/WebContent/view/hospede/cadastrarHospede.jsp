<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Novo Hospede</title>
</head>
<body>

<h3>Cadastrar Novo Hospede</h3>


<form action="../../HospedeControl" method="post">
<table>

<tr>
<td>Nome:</td> <td><input name="name" id="name" type="text"></td>
</tr>
<tr>
<td>CPF:</td> <td><input name="cpf" id="cpf" type="text"></td>
</tr><tr>
<td>Rua:</td> <td><input name="rua" id="rua" type="text"></td>
</tr><tr>
<td>Bairro:</td> <td><input name="bairro" id="bairro" type="text"></td>
</tr><tr>
<td>Cidade:</td> <td><input name="cidade" id="cidade" type="text"></td>
</tr><tr>
<td>CEP:</td> <td><input name="cep" id="cep" type="text"></td>
</tr><tr>
<td>Telefone Fixo:</td> <td><input name="telfixo" id="telfixo" type="text"></td>
</tr>
<tr>
<td>Telefone Móvel: </td><td><input name="telmovel" id="telmovel" type="text"></td>
</tr>
<input name="type" type="hidden" value="addHospede">
</table>
<button onclick="return valida()">Salvar</button>
</form>

</body>

<script type="text/javascript">
function valida()
{
	var nome = document.getElementById("name");
	var cpf = document.getElementById("cpf");
	var rua = document.getElementById("rua");
	var bairro = document.getElementById("bairro");
	var cidade = document.getElementById("cidade");
	var cep = document.getElementById("cep");
	var telfixo = document.getElementById("telfixo");
	var telmovel = document.getElementById("telmovel");

	if(nome.value == "")
	{
		alert("Preencha o NOME!");
		return false;
	}
	if(cpf.value == "")
	{
		alert("Preencha o CPF!");
		return false;
	}
	if(rua.value == "")
	{
		alert("Preencha a RUA!");
		return false;
	}
	if(bairro.value == "")
	{
		alert("Preencha o BAIRRO!");
		return false;
	}
	if(cidade.value == "")
	{
		alert("Preencha a CIDADE!");
		return false;
	}
	if(cep.value == "")
	{
		alert("Preencha o CEP!");
		return false;
	}
	if(telfixo.value == "")
	{
		alert("Preencha o TELEFONE FIXO!");
		return false;
	}
	if(telmovel.value == "")
	{
		alert("Preencha o TELEFONE CELULAR!");
		return false;
	}
	
	
	
	if(isNaN(cep.value))
	{
		alert("Preencha APENAS NÚMEROS no campo CEP!");
		return false;
	}
	if(isNaN(cpf.value))
	{
		alert("Preencha APENAS NÚMEROS no campo CPF!");
		return false;
	}
	if(isNaN(telfixo.value))
	{
		alert("Preencha APENAS NÚMEROS no campo TELEFONE FIXO!");
		return false;
	}
	if(isNaN(telmovel.value))
	{
		alert("Preencha APENAS NÚMEROS no campo TELEFONE MÓVEL!");
		return false;
	}
return true;
	}
</script>

</html>