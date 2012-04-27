package br.ufc.apsoo.DAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.ufc.apsoo.DAO.config.PostGresMapConfig;
import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Endereco;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Telefone;

public class ApartamentoDAO {
	
	public static boolean addApartamento(String diaria, String numero, String tipo)
	{
		Apartamento ap = new Apartamento();

		//ap.setDiaria(Float.parseFloat(diaria));
		ap.setNumero(Integer.parseInt(numero));
		//ap.setTipo(tipo);
		

		Session session = null;
		Transaction tx = null;

		try {
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(ap); // vamos salvar o usuário
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return true;
		
	}

	public static List<Apartamento> buscarApartamentos(String tipoApartamento){

		Map hm = new HashMap(4);

        hm.put("tipoApartamento", tipoApartamento);
        
		try {
			return (List<Apartamento>) PostGresMapConfig.getSqlMapClient().queryForList("buscarApartamentos", Long.parseLong(tipoApartamento));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Apartamento buscarApartamento(Long id){
		try {
			return (Apartamento) PostGresMapConfig.getSqlMapClient().queryForObject("getApartamento", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
