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
	
	if(type.equals("add")){
		
	addService(name, value, request, response);
	
	}else if(type.equals("edit"))
	{
		
	}
	
}

private void addService(String name, String value, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	
	
	System.out.println("name: " + name + "Value: " + value);
	
	/*FAZER VALIDAÇÃO CAMPOS AQUI*/
	
	/*SALVAR BANCO*/
	
	if(ServiceDAO.SaveService(name, Float.parseFloat(value)))
	{
		/*
		response.getWriter().write("<html>");
		response.getWriter().write("<h1>");
		response.getWriter().write("Serviço Adicionado com Sucesso!");
		response.getWriter().write("</h1>");
		response.getWriter().write("</html>");
		*/
		System.out.println("Sucesso, cadastrou!");
		RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/SucessService.jsp");
        rd.forward(request, response);
	}else
	{
		response.getWriter().write("<html>");
		response.getWriter().write("<h1>");
		response.getWriter().write("ERRO! O Serviço NÃO foi Adicionado!");
		response.getWriter().write("</h1>");
		response.getWriter().write("</html>");
		
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

}
