package br.ufc.apsoo.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.ufc.apsoo.entidades.Servico;

public class ServiceDAO {

	public static boolean SaveService(String name, float value) {
		Servico servico = new Servico();

		servico.setNome(name);
		servico.setValor(value);

		Session session = null;
		Transaction tx = null;

		try {
			// aqui nós lemos as configurações do arquivo hibernate.cfg.xml
			// e deixamos o Hibernate pronto para trabalhar
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();

			// abre uma nova sessão
			session = factory.openSession();

			// inicia uma transação
			tx = session.beginTransaction();

			// vamos criar uma nova instância da classe Usuario
			// e definir valores para seus atributos
			// note que não precisamos atribuir valores para
			// o atributo id

			session.save(servico); // vamos salvar o usuário
			session.flush();

			// e salva as alterações no banco de dados
			tx.commit();
		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return true;

	}

	public static ArrayList<Servico> ListServices() {
		ArrayList<Servico> listServicos = new ArrayList();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();

			Query query = session.createQuery("from Servico order by id");
			listServicos = (ArrayList<Servico>) query.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return listServicos;
	}
	
	public static boolean EditService(long id, String newName, float newValue)
	{
		Servico servico = new Servico();
		servico.setId(id);
	
		 Session session = null;
		    Transaction tx = null;

		    try {
		      SessionFactory factory = new
		        Configuration().configure().buildSessionFactory();
		      session = factory.openSession();
		      tx = session.beginTransaction();

		      Query query = session.createQuery("from Servico where id="+ id);
				servico = (Servico) query.list().get(0);
		      
				servico.setNome(newName);
				servico.setValor(newValue);
				
				session.update(servico);
				
				tx.commit();
		    }
		    catch(Exception e) {
		      // houve algum problema? vamos retornar o banco de dados
		      // ao seu estado anterior
		      if(tx != null)
		        tx.rollback();
		      System.out.println("Erro: " + e.getMessage());
		    }
		    finally {
		      session.close();
		    }
		    return true;
		
		
	}
	
	
	public static Servico getServiceByNameAndValue(String name, float value) {
		Servico servico = new Servico();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Servico where nome='" + name + "' and valor=" + value);
			servico = (Servico) query.list().get(0);

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return servico;
	}
	
	public static Servico getServiceById(long id) {
		Servico servico = new Servico();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Servico where id=" + id);
			servico = (Servico) query.list().get(0);

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return servico;
	}
	
	
	public static boolean deleteService(long id) {
		Servico servico = new Servico();
		
		servico = getServiceById(id);
		
		Session session = null;
		Transaction tx = null;

		try {
				SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			session.delete(servico);
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
	
	@SuppressWarnings("unchecked")
	public static List<BigInteger> getServicesByIdConta(long id) {
		List<BigInteger> servicos = null;
		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createSQLQuery("select servicos_id from conta_servico where conta_id=" + id);
			servicos = query.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return servicos;
	}
}
