package br.ufc.apsoo.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ServiceDAO;

public class ServiceControl extends HttpServlet{
@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	super.service(request, response);
	System.out.println("aeeeeeeeee");
	
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	
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
		//response.sendRedirect("/view/sucess/SucessService.jsp");
	}else
	{
		response.getWriter().write("<html>");
		response.getWriter().write("<h1>");
		response.getWriter().write("ERRO! O Serviço NÃO foi Adicionado!");
		response.getWriter().write("</h1>");
		response.getWriter().write("</html>");
		
	}
	
}

@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
