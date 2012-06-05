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
import br.ufc.apsoo.entidades.Tipo;

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
		Query query = session
				.createSQLQuery("select apartamento.id from tipo, apartamento where " +
						" apartamento.tipo_id=tipo.id and "+" apartamento.tipo_id = " + tipoApartamento + " and " +
						"apartamento.id not in (select ap.id from apartamento ap, reserva re "+
						"where ap.id = re.apartamento_id and (('" + ts_ini + "' between re.datainicio" +
						" and re.datafim) or ('" + ts_fim + "' between re.datainicio and re.datafim) " +
						"or (re.datainicio between '" + ts_ini + "' and '" + ts_fim + "') or " +
						"(re.datafim between '" + ts_ini + "' and '" + ts_fim + "')) group by ap.id) " +
						" and apartamento.id not in( select ap.id from apartamento ap, conta co, " +
						" conta_apartamento cp where co.id=cp.conta_id and cp.apartamentos_id=ap.id and " +
						" (('" + ts_ini + "' between co.datainicio and co.datafim) or ('" + ts_fim + "' " +
						"between co.datainicio and co.datafim) or " +
						"(co.datainicio between '" + ts_ini + "' and '" + ts_fim + "') or " +
						" (co.datafim between '" + ts_ini + "' and '" + ts_fim + "')) group by ap.id)");
			//Query query = session.createSQLQuery("select distinct ap.id from apartamento as ap inner join reserva as re on ap.id = re.apartamento_id where '"+ ts_ini +"' not between re.datainicio and re.datafim and ap.tipo_id = "+ tipoApartamento  + "and '" + ts_fim +"' not between re.datainicio and re.datafim");
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
	

	public static List<Apartamento> buscarApartamentosOcupados(String tipoApartamento, Date data)
	{
		ArrayList<Apartamento> listApartamentos = new ArrayList();
		Session session = null;
		Transaction tx = null;
		Timestamp ts_ini = new java.sql.Timestamp(data.getTime());
		    
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			/*Formato timestamp salvo banco "2012-05-10 00:00:00" */
		Query query = session
				.createSQLQuery("select apartamento.id from tipo, apartamento where " +
						 "apartamento.tipo_id=tipo.id and  apartamento.tipo_id = " + tipoApartamento  + " and " +  
						"apartamento.id in( select ap.id from apartamento ap, conta co, " +  
						"conta_apartamento cp where co.id=cp.conta_id and cp.apartamentos_id=ap.id and " +  
						"'" + data + "'  between co.datainicio and co.datafim group by ap.id)");
			//Query query = session.createSQLQuery("select distinct ap.id from apartamento as ap inner join reserva as re on ap.id = re.apartamento_id where '"+ ts_ini +"' not between re.datainicio and re.datafim and ap.tipo_id = "+ tipoApartamento  + "and '" + ts_fim +"' not between re.datainicio and re.datafim");
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
	
	public static List<Tipo> getTiposApartamento()
	{
		Session session = null;
		Transaction tx = null;
		List<Tipo> tipos = new ArrayList();
		
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
		Query query = session.createQuery("from Tipo");
		tipos = query.list();
		
		return tipos;
	}
	
	public static List<Apartamento> getTodosApartamentos(String tipo)
	{
		Session session = null;
		Transaction tx = null;
		List<Apartamento> apartamentos = new ArrayList();
		
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
		Query query = session.createQuery("from Apartamento where tipo_id = " + tipo);
		apartamentos = query.list();
		
		return apartamentos;
	}
}
