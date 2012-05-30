<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.apsoo.entidades.Tipo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Taxa de Ocupacao</title>
</head>

<body>
<%
String DATE_FORMAT_NOW = "dd/MM/yyyy";
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
%>

<form action="<%=getServletContext().getContextPath()%>/QueryControl" method="get">
<input type="hidden" name="tipo" value="taxaOcupacao">
Data: <input type="text" name="data" id="data" size="10" maxlength="10" value="<%=sdf.format(cal.getTime())%>" />

<button type="submit">Pesquisar </button>
</form>
<% List<Integer> taxaOcup = (ArrayList<Integer>)session.getAttribute("taxaOcup"); 
List<Tipo> listTipos	 = (ArrayList<Tipo>)session.getAttribute("listTipos");
%>

<br /><br />

<table border="1">
<%
if(listTipos != null){
int k=0;

for(int i=0; i<listTipos.size(); i++)
{ %>
	<tr>
	<th><%=listTipos.get(i).getNome()%> - Ocupados / Total</th>
	<td>
	<%=taxaOcup.get(k) %> 
	</td>
	<td>
	<%=taxaOcup.get(k+1) %> 
	</td>
	</tr>
<%
k += 2;
} 
}
%>

</table>

</body>
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=getServletContext().getContextPath()%>/js/jquery.click-calendario-1.0.js"></script>
<link href="<%=getServletContext().getContextPath()%>/js/_style/jquery.click-calendario-1.0.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
$('#data').focus(function(){
    $(this).calendario({
        target :'#data',
        dateDefault:$(this).val()
    });
});
</script>
</html>