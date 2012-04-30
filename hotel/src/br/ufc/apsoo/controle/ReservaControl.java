package br.ufc.apsoo.controle;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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


    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
    		throws ServletException, IOException {
    	System.out.println("ENTROU SERVICE ------------------------");
    	//doPost(request, response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		System.out.println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String idHospede = request.getParameter("idHospede");
		String dt_inicio = request.getParameter("data_ini");
		String dt_fim = request.getParameter("data_fim");
		String action = request.getParameter("action");
		//String[] apartamentos = request.getParameterValues("apartamentos");
		
		if(type!= null && type.equals("addReserva")){
			System.out.println("Entrou AddReserva");
			SimpleDateFormat sdf = new SimpleDateFormat();
			
			Hospede hospede = HospedeDAO.getHospedeById(Long.parseLong(idHospede));
			//Apartamento apartamento
			
			
			//ReservaDAO.reservar(hospede, apartamento, dt_inicio, dt_fim);
			System.out.println(dt_inicio);
			System.out.println(dt_fim);
			/*EST� ENTRANDO AQUI, FALTA S� ADICIONAR OS CAMPOS DT MARCAO, DTCHEGADA E DTSAIDA NO BANCO*/
		}else if(action!=null)
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