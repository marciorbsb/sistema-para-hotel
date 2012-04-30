package br.ufc.apsoo.DAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.ufc.apsoo.entidades.Servico;
import br.ufc.apsoo.entidades.Tipo;

public class TipoDAO {
	public static Tipo getTipoById(int idTipo) {
		Tipo tipo = new Tipo();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Tipo where id=" + idTipo);
			tipo = (Tipo) query.list().get(0);
			return tipo;		
			} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
}
