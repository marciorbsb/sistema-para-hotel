<%@page import="br.ufc.apsoo.DAO.TipoDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.apsoo.DAO.HospedeDAO"%>
<%@page import="br.ufc.apsoo.entidades.Hospede"%>
<%@page import="br.ufc.apsoo.entidades.Apartamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />

<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fazer Reserva</title>
</head>
<body>

<%ArrayList<Apartamento> apartamentos = (ArrayList<Apartamento>) session.getAttribute("apartamentos");
session.removeAttribute("apartamentos");
Calendar cal = Calendar.getInstance();
String DATE_FORMAT_NOW = "dd/MM/yyyy";
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
%>
<form action="<%=getServletContext().getContextPath() %>/ReservaControl?type=buscarApartamentosLivresReserva" method="get">

	<label>Tipo do Apartamento</label>
	<select name="tipoApartamento">
	       <option value="1">Solteiro</option>
	       <option value="2">Casal</option>
	       <option value="3">Triplo</option>
	       <option value="4">Quádruplo</option>
	</select>
		
	<br />
	<br />	

<table border="1" style="margin-left: 80px">
		<thead>
			<tr>
				<td>Número do Apartamento</td>
				<td>Tipo</td>
				<td>Valor</td>
				<td></td>
			</tr>
		</thead>
		<%if(apartamentos!=null && apartamentos.size()>0){ %>
		<%for(Apartamento apartamento:apartamentos){ %>
		<tbody>
			<tr>
				<td><%=apartamento.getNumero()%></td>
				<td><%=apartamento.getTipo().getNome()%></td>
				<td><%=apartamento.getTipo().getValor()%></td>
				<td><input type="checkbox" id="idApartamento" name="apartamentos" value="<%=apartamento.getId()%>"/> </td>
			</tr>
		</tbody>
		<%}%>
			<h4>Nenhum registro de apartamentos</h4>
		<%}%>
	</table> 
	<% %>
	<br />
	<br />


Data Início: <input type="text" name="data_ini" id="data_ini" size="10" maxlength="10" value="<%=sdf.format(cal.getTime())%>" />

<br /><br />

Data Término: <input type="text" name="data_fim" id="data_fim" size="10" maxlength="10" />

<br /><br />

	<input type="hidden" name="type" id="type" value="buscarApartamentosLivresReserva">
	<input type="submit" value="Buscar" onclick="return validarNulls()">
	
	</form>

<br />

<h3>Hospedes Cadastrados</h3>


<form action="<%=getServletContext().getContextPath() %>/ReservaControl?type=addReserva" method="post" >

<table border="1" style="margin-left: 50px">
<%
ArrayList<Hospede> hospedes = HospedeDAO.listHospedes();


if(hospedes.size() <= 0)
{
	out.write("Não há hospedes cadastrados!");
}
int count = 0;
for(Hospede hospede : hospedes){
	
if(count % 2 == 1){
%>
<tr>
<%}else{ %>
	<tr bgcolor="#C0C0C0">
<%} %>
<input type="hidden" name="type" id="type" value="addReserva">


<td><%=hospede.getNome() %></td>
<td><%=hospede.getEndereco().getRua()%> - <%=hospede.getEndereco().getBairro()%> - <%=hospede.getEndereco().getCidade()%></td>
<td> <input type="radio" name="radio" value="<%=hospede.getId()%>"> </td>
</tr>
<%count++;} %>
</table>

<a href="<%=getServletContext().getContextPath()%>/view/hospede/cadastrarHospede.jsp"> Cadastrar novo hospede </a>

<br /><br />


<br /><br />
<button type="submit" onclick="return validar()">Salvar Reserva</button>
</form>

 

</body>
</html>



<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/jquery.click-calendario-1.0.js"></script>
<link href="<%=getServletContext().getContextPath()%>/js/_style/jquery.click-calendario-1.0.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
$('#data_ini').focus(function(){
    $(this).calendario({
        target :'#data_ini',
        dateDefault:$(this).val()
    });
});
$('#data_fim').focus(function(){
    $(this).calendario({
        target :'#data_fim',
    });
});

function validar()
{
	var dt_ini =  document.getElementById("data_ini");
	var dt_fim =  document.getElementById("data_fim");
	var radio =  document.getElementsByName("radio");
	var hospedeSelecionado = false;
	for(var h in radio)
	{
		
		if(radio[h].checked)
		{
			hospedeSelecionado = true;
			break;	
		}
		
	}
	if(!hospedeSelecionado)
	{
		alert('Selecione um hospede!');
		return false;
	}
	if(dt_ini.value == "")
	{
		alert('Preencha a data de início!');
		return false;
	}
	if(dt_fim.value == "")
	{
		alert('Preencha a data de fim!');
		return false;
	}
	if (! (parseInt( dt_fim.value.split( "/" )[2].toString() + dt_fim.value.split( "/" )[1].toString() + dt_fim.value.split( "/" )[0].toString() ) > parseInt( dt_ini.value.split( "/" )[2].toString() + dt_ini.value.split( "/" )[1].toString() + dt_ini.value.split( "/" )[0].toString() ) ))	{
		alert('Data de início deve ser MENOR que a data de término!');
		return false;
	}
	
	return salvarSessao();
	
	}

	function salvarSessao()
	{
		var apartamentos = document.getElementsByName('apartamentos');
		var ids = '';
		if(apartamentos.length <= 0)
		{
			alert('Você deve selecionar um apartamento!');
			return false;
		}
		for (var i=0;i<apartamentos.length;i++){  
			if (apartamentos[i].checked == true){  
		    	 ids += apartamentos[i].value+'#';
		     }
		}
			$.ajax({    
	             type: "POST",    
	             url: "/sistema-hotel/ReservaControl?",    
	             data: "type=salvarSessao&data="+ids,  
	             success: ""
	        });	
		return true;
	}
	
	function validarNulls()
	{
		var dt_ini =  document.getElementById("data_ini");
		var dt_fim =  document.getElementById("data_fim");
		
		if(dt_ini.value == "")
		{
			alert('Preencha a data de início!');
			return false;
		}
		if(dt_fim.value == "")
		{
			alert('Preencha a data de fim!');
			return false;
		}
		if (! (parseInt( dt_fim.value.split( "/" )[2].toString() + dt_fim.value.split( "/" )[1].toString() + dt_fim.value.split( "/" )[0].toString() ) > parseInt( dt_ini.value.split( "/" )[2].toString() + dt_ini.value.split( "/" )[1].toString() + dt_ini.value.split( "/" )[0].toString() ) ))	{
			alert('Data de início deve ser MENOR que a data de término!');
			return false;
		}
	}

</script>


