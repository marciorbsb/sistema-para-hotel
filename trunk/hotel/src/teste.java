import java.util.ArrayList;
import java.util.List;

import br.ufc.apsoo.DAO.HospedeDAO;
import br.ufc.apsoo.entidades.Reserva;


public class teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HospedeDAO hospedeDAO = new HospedeDAO();
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas = hospedeDAO.buscaPorFiltrosHospede("barroso", null);
		
		for(Reserva r: reservas){
			System.out.println("nome: "+r.getHospede().getNome()+"\n"+"tipo: "+r.getApartamento().getTipo().getNome());
		}
	}

}
