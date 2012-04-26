package br.ufc.apsoo.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ApartamentoDAO;
import br.ufc.apsoo.DAO.HospedeDAO;

public class HospedeControl extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String numberFix = request.getParameter("numberFix");
		String mobile = request.getParameter("mobile");
		String daily = request.getParameter("daily");
		String number = request.getParameter("number");
		String apType = request.getParameter("apType");
		
		String type = request.getParameter("type");

		if(type.equals("doCheckIn")) //Vem da página checkin
		{
			HospedeDAO.addHospede(name, cpf, rua, bairro, cidade, cep, numberFix, mobile);
			ApartamentoDAO.addApartamento(daily, number, apType);
			
			/*CRIAR METODOS NOS DAO PARA PEGAR O HOSPEDE E O APARTAMENTO PELO ID OU POR QUALQUER OUTRA COISA E CHAMAR ABAIXO*/
			
			//HospedeDAO.doCheckIn(hospede, apartamento);
		}
		

	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	super.doGet(req, resp);
	}
}
