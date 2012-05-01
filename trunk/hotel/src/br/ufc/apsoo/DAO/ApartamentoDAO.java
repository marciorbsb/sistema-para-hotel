package br.ufc.apsoo.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.ufc.apsoo.entidades.Apartamento;

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

        
        ArrayList<Apartamento> listApartamentos = new ArrayList();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			/*
			Query queryTipo = session.createQuery("from Tipo where id="+Integer.parseInt(tipoApartamento));
			Tipo tipo = (Tipo) queryTipo.list().get(0);
			*/
			Query query = session.createQuery("from Apartamento where tipo=" + Integer.parseInt(tipoApartamento));
			listApartamentos = (ArrayList<Apartamento>) query.list();
			int x = 0;
			return listApartamentos;
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
	
	public static Apartamento buscarApartamento(Long id){
		Session session = null;
		Transaction tx = null;
Apartamento ap = new Apartamento();
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from Apartamento where id=" + id);
			ap = (Apartamento)query.list().get(0);
			return ap;
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
