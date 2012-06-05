package br.ufc.apsoo.controle;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ContaDAO;
import br.ufc.apsoo.DAO.DespesaDAO;
import br.ufc.apsoo.DAO.ServiceDAO;
import br.ufc.apsoo.entidades.Conta;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Servico;

public class ContaControl extends HttpServlet {

	
	
	
	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nEntrou Get\n");

		String type = request.getParameter("type");
		String HId = request.getParameter("HId");
		String SId = request.getParameter("SId");
		String CId = request.getParameter("CId");
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String idConta = request.getParameter("idConta");
		

		if (type.equals("listDespesas")) {
			

			
			HashMap<Hospede, HashMap<Conta, Servico>> mapHS = DespesaDAO.listDespesas();
			request.getSession().setAttribute("mapHS", mapHS);
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/view/conta/listDespesas.jsp");
			rd.forward(request, response);
		
			
		}else
		if(type.equals("estornarDespesa"))
		{
			DespesaDAO.estornarDespesa(Long.parseLong(CId), Long.parseLong(SId));
			RequestDispatcher rd = request
					.getRequestDispatcher("/view/sucess/SucessLancamento.jsp");
			rd.forward(request, response);
			
		}else if(type.equals("buscarConta")){
			Long longCpf = null;
			if(cpf!=null && !cpf.trim().isEmpty())
				longCpf = Long.parseLong(cpf);
			
				
			List<Conta> contas = ContaDAO.buscaPorFiltrosConta(name, longCpf);
			request.getSession().setAttribute("contas", contas);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/conta/checkout.jsp");
	        rd.forward(request, response);
		}else if(type.equals("detalhes")){
			Long id = Long.parseLong(idConta);
			Conta conta = ContaDAO.getContaById(id);
			List<BigInteger> ids = ServiceDAO.getServicesByIdConta(conta.getId());
			List<Servico> servicos = new ArrayList<Servico>();
			for(BigInteger i :ids){
				Servico servico = new Servico();
				servico = ServiceDAO.getServiceById(i.longValue());
				servicos.add(servico);
			}
			conta.setServicos(servicos);
			request.getSession().setAttribute("conta", conta);
			RequestDispatcher rd = request.getRequestDispatcher("/view/conta/detalhes.jsp");
	        rd.forward(request, response);
		}else if(type.equals("Encerrar")){
			Long id = Long.parseLong(idConta);
			Conta conta = ContaDAO.getContaById(id);
			ContaDAO.deletarConta(conta);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/SucessService.jsp");
	        rd.forward(request, response);
		}
		
		
	}
}
