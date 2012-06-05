package br.ufc.apsoo.controle;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufc.apsoo.DAO.ApartamentoDAO;
import br.ufc.apsoo.DAO.HospedeDAO;
import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Tipo;

public class QueriesControl extends HttpServlet {

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	protected void service(HttpServletRequest request, HttpServletResponse response)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		System.out.println("Entrou QueryControl!\n");
		String data = (String) request.getParameter("data");
		Date dataDate = null;
		String nomeHospede = (String) request.getParameter("nomeHospede");
		String tipo = (String) request.getParameter("tipo");
		
		if(tipo.equals("taxaOcupacao")){
		try {
			dataDate = format.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Tipo> tiposApartamento = ApartamentoDAO.getTiposApartamento();
		ArrayList<List<Apartamento>> listApartamentosLivres = new ArrayList();
		
		List<List<Apartamento>> listListApartamentosOcup = new ArrayList();
		List<List<Apartamento>> listListTodosApartamentos = new ArrayList();
		
		
		for(int i=0; i<tiposApartamento.size(); i++){

			listListApartamentosOcup.add(ApartamentoDAO.buscarApartamentosOcupados(tiposApartamento.get(i).getId().toString(), dataDate));
			listListTodosApartamentos.add(ApartamentoDAO.getTodosApartamentos(tiposApartamento.get(i).getId().toString()));

		}
		
		
		
		List<Integer> apartamentosOcupTotal = new ArrayList();
		
		for(int i=0; i<listListApartamentosOcup.size(); i++){
			if(listListTodosApartamentos.get(i).size() != 0 && listListApartamentosOcup.get(i).size() != 0 ){
				apartamentosOcupTotal.add(listListApartamentosOcup.get(i).size());
				apartamentosOcupTotal.add(listListTodosApartamentos.get(i).size());
			}else
			{
				apartamentosOcupTotal.add(0);
				apartamentosOcupTotal.add(0);
			}
		}
		int xd  = 10;
		
		request.getSession().setAttribute("taxaOcup", apartamentosOcupTotal);
		request.getSession().setAttribute("listTipos", tiposApartamento);
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/view/questoes/TaxaOcupacao.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else if(tipo.equals("ondeEstaHospede"))
	{
		System.out.println("Hospede: " + nomeHospede);
		
		List<Hospede> listHospedes = HospedeDAO.getHospedeByName(nomeHospede);
		
		request.getSession().setAttribute("listHospedesOndeEstou", listHospedes);
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/view/questoes/OndeEstaHospede.jsp");
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else if(tipo.equals("QualApEstaHospede"))
	{
		String hospedeOndeEstouId = request.getParameter("hospedeOndeEstou");
		
		List<BigInteger> listApartamentosId = HospedeDAO.getApartamentoByHospedeId(hospedeOndeEstouId);
		List<Apartamento> listApartamentos = new ArrayList();
		for (BigInteger idAp : listApartamentosId) {
			listApartamentos.add(ApartamentoDAO.buscarApartamento(idAp.longValue()));
		}
		request.getSession().setAttribute("listApartamentosOndeEstou", listApartamentos);
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/view/questoes/OndeEstaHospede.jsp");
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	}
	
}
