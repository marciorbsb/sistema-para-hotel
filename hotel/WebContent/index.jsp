<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/index.css" type="text/css" media="screen" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistemas Hotel Quixadá</title>
<img src="<%=getServletContext().getContextPath() %>/css/logo.png" style='margin-left:70px '/> <br /><br />
</head>
<body>
<a href="view/service/CreateService.jsp">Cadastrar Serviço </a> <br /><br />
<a href="view/service/ListService.jsp">Listar Serviços </a> <br /> <br />

<a href="view/hospede/checkin0.jsp">Fazer Check In </a> <br /><br />
<a href="view/conta/checkout.jsp">Fazer CheckOut </a> <br /><br />

<a href="view/reserva/FazerReserva.jsp">Fazer Reserva</a> <br /><br />

<a href="view/questoes/TaxaOcupacao.jsp">Taxa de Ocupacao</a> <br /><br />

<a href="view/questoes/OndeEstaHospede.jsp">Onde está o Hospede?</a> <br /><br />

<a href="<%=getServletContext().getContextPath() %>/addService?type=evolucao">Evolucao dos ultimos tres meses</a> <br /><br />

<a href="<%=getServletContext().getContextPath() %>/addService?type=ultimomes">Serviços do ultimo mês</a> <br /><br />

</body>
</html>