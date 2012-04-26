package br.ufc.apsoo.controle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ServiceDAO;
import br.ufc.apsoo.entidades.Servico;

public class ServiceControl extends HttpServlet{
@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	super.service(request, response);
	
	//listService(request, response); testado ok
	//ServiceDAO.EditService(1, "jose", 12); testado ok
	
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	String type = request.getParameter("type");
	String id = request.getParameter("id");
	
	if(type.equals("add")){
		
	addService(name, value, request, response);
	
	}else if(type.equals("edit"))
	{
		//Servico service = ServiceDAO.getServiceByNameAndValue(name, Float.parseFloat(value)); //testado, pegando objeto
		System.out.println("ID da peste: " + id);
		Long idLong = Long.parseLong(id);
		Servico service = ServiceDAO.getServiceById(idLong);
		System.out.println("Valor: " + value);
		editService(service.getId(), name, Float.parseFloat(value), request, response);
	}else if(type.equals("delete"))
	{
		System.out.println("entrou no DELETE");
		Long idLong = Long.parseLong(id);
		ServiceDAO.deleteService(idLong);
		RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/SucessService.jsp");
        rd.forward(request, response);
	}
	
}

private void addService(String name, String value, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	
	
	System.out.println("name: " + name + "Value: " + value);
	
	/*FAZER VALIDAÇÃO CAMPOS AQUI*/
	
	/*SALVAR BANCO*/
	
	if(ServiceDAO.SaveService(name, Float.parseFloat(value)))
	{
		
		System.out.println("Sucesso, cadastrou!");
		RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/SucessService.jsp");
        rd.forward(request, response);
	}else
	{
		System.out.println("Erro - nao cadastrou");
		RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/ErroService.jsp");
        rd.forward(request, response);
		
		
		
	}	
}

private void listService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	ArrayList<Servico> listServicos = ServiceDAO.ListServices();
	
	/*TEST - OK LISTANDO*/
	for (Servico servico : listServicos) {
		System.out.println(servico.getNome() + "\n");
	}
}

private void editService(long id, String newName, float newValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	ServiceDAO.EditService(id, newName, newValue);
	/*
	RequestDispatcher rd = request.getRequestDispatcher("/view/service/ListService.jsp");
    rd.forward(request, response);
    */
	RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/SucessService.jsp");
    rd.forward(request, response);
}

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		System.out.println("ENTROU DO GET");
	}

}

