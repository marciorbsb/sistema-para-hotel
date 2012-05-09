package br.ufc.apsoo.controle;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.IdClass;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.DespesaDAO;
import br.ufc.apsoo.entidades.Conta;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Servico;

public class ContaControl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nEntrou Get\n");

		String type = request.getParameter("type");
		String HId = request.getParameter("HId");
		String SId = request.getParameter("SId");
		String CId = request.getParameter("CId");
		

		if (type.equals("listDespesas")) {
			

			
			HashMap<Hospede, HashMap<Conta, Servico>> mapHS = DespesaDAO.listDespesas();
			request.getSession().setAttribute("mapHS", mapHS);
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/view/conta/listDespesas.jsp");
			rd.forward(request, response);
		
			
		}
		if(type.equals("estornarDespesa"))
		{
			DespesaDAO.estornarDespesa(Long.parseLong(CId), Long.parseLong(SId));
			
		}
		
	}
}
