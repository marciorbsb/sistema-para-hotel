package br.ufc.apsoo.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Reserva;

public class ReservaDAO {
	public static void reservar(Hospede hospede, Apartamento apartamento){		

		Session session = null;
		Transaction tx = null;

		try {
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			
			tx=session.beginTransaction();
			Query query = session.createQuery("from Hospede where cpf="+hospede.getCpf());
			Hospede h = (Hospede) query.list().get(0);
			Reserva reserva = new Reserva();
			reserva.setHospede(hospede);
			reserva.setApartamento(apartamento);
			session.save(reserva);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
	}
}
