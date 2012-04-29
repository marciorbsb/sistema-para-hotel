


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.apsoo.DAO.HospedeDAO"%>
<%@page import="br.ufc.apsoo.entidades.Hospede"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fazer Reserva</title>
</head>
<body>
<h3>Hospedes Cadastrados</h3>
<form action="../../ReservaControl" method="post" >
<table border="1">
<%
ArrayList<Hospede> hospedes = HospedeDAO.listHospedes();
Calendar cal = Calendar.getInstance();
String DATE_FORMAT_NOW = "dd/MM/yyyy";
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
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
<input type="hidden" name="idHospede" id="idHospede" value="<%=hospede.getId()%>">

<td><%=hospede.getNome() %></td>
<td><%=hospede.getEndereco().getRua()%> - <%=hospede.getEndereco().getBairro()%> - <%=hospede.getEndereco().getCidade()%></td>
<td> <input type="radio" name="radio" value="<%=hospede.getId()%>"> </td>
</tr>
<%count++;} %>
</table>

<a href="view/hospede/cadastrarHospede.jsp"> Cadastrar novo hospede </a>

<br /><br />

<!-- HTML !--> 
Data Início: <input type="text" name="data_ini" id="data_ini" size="10" maxlength="10" value="<%=sdf.format(cal.getTime()) %>" />

<br /><br />

Data Término: <input type="text" name="data_fim" id="data_fim" size="10" maxlength="10" />

<br /><br />
 
<button type="submit" >Salvar Reserva</button>
</form>
</body>
</html>

<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.click-calendario-1.0.js"></script>
<link href="../../js/_style/jquery.click-calendario-1.0.css" rel="stylesheet" type="text/css"/>

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
</script>


