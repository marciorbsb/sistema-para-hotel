package br.ufc.apsoo.DAO;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.buf.TimeStamp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import sun.security.util.BigInt;

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
			session.save(ap); // vamos salvar o usu�rio
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
			Query query = session.createQuery("from Apartamento where tipo_id=" + Integer.parseInt(tipoApartamento));
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
	
	public static List<Apartamento> buscarApartamentos(String tipoApartamento, Date date_ini, Date date_fim)
	{
		ArrayList<Apartamento> listApartamentos = new ArrayList();
		Session session = null;
		Transaction tx = null;
		Timestamp ts_ini = new java.sql.Timestamp(date_ini.getTime());
		Timestamp ts_fim = new java.sql.Timestamp(date_fim.getTime());
		    
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			/*Formato timestamp salvo banco "2012-05-10 00:00:00" */
			Query query = session.createSQLQuery("select ap.id from apartamento as ap inner join reserva as re on ap.id = re.apartamento_id where '"+ ts_ini +"' not between re.datainicio and re.datafim and ap.tipo_id = 1 and '" + ts_fim +"' not between re.datainicio and re.datafim");
			//listApartamentos = (ArrayList<Apartamento>) query.list();
			List<BigInteger> listIdsAp = query.list();
			/*
			 * 
			 * select * from apartamento as ap inner join reserva as re on 
ap.id = re.apartamento_id where '2012-05-29 00:00:00' not between 
re.datainicio and re.datafim and ap.tipo_id = 1 and '2012-05-30 00:00:00' 
not between re.datainicio and re.datafim

select ap.id from apartamento as ap inner join reserva as re on ap.id = re.apartamento_id where '" + ts + "' > re.datafim and ap.tipo_id =" + tipoApartamento + "and re.datainicio != '" + ts + "'"

			 * */
			for (BigInteger id : listIdsAp) {
				listApartamentos.add(ApartamentoDAO.buscarApartamento(id.longValue()));
			}
			
		return listApartamentos;
		
	}	
}
