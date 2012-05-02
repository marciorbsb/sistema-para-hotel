package br.ufc.apsoo.controle;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import br.ufc.apsoo.DAO.ApartamentoDAO;
import br.ufc.apsoo.DAO.HospedeDAO;
import br.ufc.apsoo.DAO.ReservaDAO;
import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Hospede;

/**
 * Servlet implementation class ReservaControl
 */
public class ReservaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dt_inicio = request.getParameter("data_ini");
		String dt_fim = request.getParameter("data_fim");
		String type = request.getParameter("type");
		String tipoApartamento =request.getParameter("tipoApartamento");
		
		if(type != null&& type.equals("buscarApartamentosLivresReserva"))
		{
		
			Date date_ini = null;
			Date date_fim = null;
			DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
			
			  try {
				date_ini = dfm.parse(dt_inicio);
				date_fim = dfm.parse(dt_fim);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
			List<Apartamento> apartamentos = ApartamentoDAO.buscarApartamentos(tipoApartamento, date_ini, date_fim);
			request.getSession().setAttribute("apartamentos", apartamentos);
			request.getSession().setAttribute("dt_ini", date_ini);
			request.getSession().setAttribute("dt_fim", date_fim);
			RequestDispatcher rd = request.getRequestDispatcher("/view/reserva/FazerReserva.jsp");
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String idHospede = request.getParameter("radio");
		/*String dt_inicio = request.getParameter("data_ini");
		String dt_fim = request.getParameter("data_fim");*/
		String action = request.getParameter("action");
		String data = request.getParameter("data");
		
		
		if(type != null && type.equals("salvarSessao"))
		{
			request.getSession().setAttribute("ids", data.substring(0, data.length()-1));
		}
		if(type!= null && type.equals("addReserva")){
			System.out.println("Entrou AddReserva");
			//SimpleDateFormat sdf = new SimpleDateFormat();
			String[] idsApartamentos = ((String) request.getSession().getAttribute("ids")).split("#");
			Hospede hospede = HospedeDAO.getHospedeById(Long.parseLong(idHospede));
			Date date_ini;
			Date date_fim;
			for(int i=0; i<idsApartamentos.length; i++)
			{
				Apartamento apartamento = (Apartamento)ApartamentoDAO.buscarApartamento(Long.parseLong(idsApartamentos[i]));
				
				    date_ini = (Date) request.getSession().getAttribute("dt_ini");
				    date_fim = (Date) request.getSession().getAttribute("dt_fim");
				    
					/*date_ini = dfm.parse(dt_inicio);
					date_fim = dfm.parse(dt_fim);*/
					ReservaDAO.reservar(hospede, apartamento, date_ini, date_fim);
					/*DEPOIS DE SALVAR, LIMPA A SESSÃO*/
					request.getSession().removeAttribute("ids");					
					request.getSession().removeAttribute("dt_ini");
					request.getSession().removeAttribute("dt_fim");
					request.getSession().removeAttribute("apartamentos");
					
					RequestDispatcher rd = request.getRequestDispatcher("view/sucess/reservaSucesso.jsp");
					rd.forward(request, response);
				
				
			int x = 0;
				
			}
		}
		else if(action!=null)
		{
			if(action.equals("buscarApartamentos")) buscarApartamentos(request, response);
			if(action.equals("cadastroReserva")) cadastroReserva(request, response);
			if(action.equals("reservar"))reservar(request, response);
		}
	}

	private void reservar(HttpServletRequest request,
			HttpServletResponse response) {
		Apartamento apartamento = (Apartamento)request.getSession().getAttribute("apartamento");
		Hospede hospede = new Hospede();
		hospede.setNome(request.getParameter("nome"));
		String id = request.getParameter("cpf");
		hospede.setCpf(Long.parseLong(id));
		ReservaDAO.reservar(hospede, apartamento);
		
	}

	private void cadastroReserva(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("idApartamento");
		Apartamento apartamento = ApartamentoDAO.buscarApartamento(Long.parseLong(id));
		request.setAttribute("apartamento", apartamento);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/cadastrarReserva.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buscarApartamentos(HttpServletRequest request, HttpServletResponse response){
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String tipoApartamento = request.getParameter("tipoApartamento");
		
		List<Apartamento> apartamentos = ApartamentoDAO.buscarApartamentos(tipoApartamento);
		
		request.getSession().setAttribute("apartamentos", apartamentos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/reserva.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	

}
