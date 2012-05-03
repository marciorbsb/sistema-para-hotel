package br.ufc.apsoo.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ApartamentoDAO;
import br.ufc.apsoo.DAO.HospedeDAO;
import br.ufc.apsoo.DAO.ReservaDAO;
import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Conta;
import br.ufc.apsoo.entidades.Reserva;

public class HospedeControl extends HttpServlet{

	@SuppressWarnings("null")
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
			String id = request.getParameter("idReserva");
			Reserva reserva = ReservaDAO.buscarPorId(Long.parseLong(id));
			
			ArrayList<Apartamento> apartamentos=new ArrayList<Apartamento>();
			apartamentos.add(reserva.getApartamento());
			
			Conta conta = new Conta();
			conta.setHospede(reserva.getHospede());
			conta.setApartamentos(apartamentos);
			conta.setDataInicio(reserva.getDataInicio());
			conta.setDataFim(reserva.getDataFim());
			
			HospedeDAO.save(conta);
			
			ReservaDAO.deletarReserva(reserva);
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/sucess/SucessService.jsp");
	        rd.forward(request, response);
			
			
		}else if(type.equals("buscarReservas")){
			Long longCpf = null;
			if(cpf!=null && !cpf.trim().isEmpty())
				longCpf = Long.parseLong(cpf);
			
				
			List<Reserva> reservas = HospedeDAO.buscaPorFiltrosHospede(name, longCpf);
			request.getSession().setAttribute("reservas", reservas);
			
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
