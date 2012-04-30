package br.ufc.apsoo.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ApartamentoDAO;
import br.ufc.apsoo.DAO.HospedeDAO;
import br.ufc.apsoo.entidades.Apartamento;

public class HospedeControl extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(request, response);
		System.out.println("segundo---------------------------------");
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String telfixo = request.getParameter("telfixo");
		String telmovel = request.getParameter("telmovel");
		String daily = request.getParameter("daily");
		String number = request.getParameter("number");
		String apType = request.getParameter("apType");
		
		String type = request.getParameter("type");
		
		String tipoApartamento =request.getParameter("tipoApartamento");

		if(type.equals("doCheckIn")) //Vem da página checkin
		{
			HospedeDAO.addHospede(name, cpf, rua, bairro, cidade, cep, telfixo, telmovel);
			ApartamentoDAO.addApartamento(daily, number, apType);
			
			/*CRIAR METODOS NOS DAO PARA PEGAR O HOSPEDE E O APARTAMENTO PELO ID OU POR QUALQUER OUTRA COISA E CHAMAR ABAIXO*/
			
			//HospedeDAO.doCheckIn(hospede, apartamento);
		}else if(type.equals("buscarApartamentosLivres")){
			List<Apartamento> apartamentos = ApartamentoDAO.buscarApartamentos(tipoApartamento);
			request.getSession().setAttribute("apartamentos", apartamentos);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/hospede/checkin0.jsp");
	        rd.forward(request, response);
		}else if(type.equals("addHospede"))
		{
			System.out.println("chegou add HOSPEDE");
			HospedeDAO.addHospede(name, cpf, rua, bairro, cidade, cep, telfixo, telmovel);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/reserva/FazerReserva.jsp");
	        rd.forward(request, response);
		}else if(type.equals("buscarApartamentosLivresReserva")){
			List<Apartamento> apartamentos = ApartamentoDAO.buscarApartamentos(tipoApartamento);
			request.getSession().setAttribute("apartamentos", apartamentos);
			RequestDispatcher rd = request.getRequestDispatcher("/view/reserva/FazerReserva.jsp");
	        rd.forward(request, response);
		}
		

	}
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doGet(req, resp);
		doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		System.out.println("primeiro-------------------");
	}
}
