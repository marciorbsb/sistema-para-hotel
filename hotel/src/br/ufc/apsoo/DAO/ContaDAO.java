package br.ufc.apsoo.DAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Conta;

public class ContaDAO {

	public static Conta getContaById(long idConta)
	{
		Session session = null;
		Transaction tx = null;
		Conta conta = new Conta();
		
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from Conta where id=" + idConta);
			conta = (Conta)query.list().get(0);
			return conta;

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
}
