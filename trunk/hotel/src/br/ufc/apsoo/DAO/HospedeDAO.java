package br.ufc.apsoo.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Conta;
import br.ufc.apsoo.entidades.Endereco;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Reserva;
import br.ufc.apsoo.entidades.Telefone;

public class HospedeDAO {

	public static boolean addHospede(String name, String cpf, String rua, String bairro, String cidade, String cep, String numberFix, String mobile)
	{
		Hospede hospede = new Hospede();

		hospede.setNome(name);
		hospede.setCpf(Long.parseLong(cpf));
		
		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		
		hospede.setEndereco(endereco);
		
		Telefone telefone = new Telefone();
		telefone.setFixo(Long.parseLong(numberFix));
		telefone.setCelular(Long.parseLong(mobile));
		
		hospede.setTelefone(telefone);
		

		Session session = null;
		Transaction tx = null;

		try {
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(telefone);
			session.save(endereco);
			session.save(hospede); // vamos salvar o usuário
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
	
	public static boolean doCheckIn(Hospede hospede, Apartamento apartamento)
	{
		Reserva reserva = new Reserva();
		
		reserva.setApartamento(apartamento);
		reserva.setHospede(hospede);
		
		return true;
		
	}
	
	public static ArrayList<Hospede> listHospedes()
	{
		ArrayList<Hospede> listHospedes = new ArrayList();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Hospede");
			listHospedes = (ArrayList<Hospede>) query.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return listHospedes;
	}
	
	public static ArrayList<Conta> listContaHospedesAtivos()
	{
		ArrayList<Conta> listContas = new ArrayList();

		Session session = null;
		Transaction tx = null;

		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Conta");
			listContas = (ArrayList<Conta>) query.list();
			
			return listContas;

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
	
	public static Hospede getHospedeById(long id)
	{
		
		Session session = null;
		Transaction tx = null;
		Hospede hospede = new Hospede();
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Hospede where id=" + id);
			hospede = (Hospede) query.list().get(0);

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return hospede;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List<Reserva> buscaPorFiltrosHospede(String nome, Long cpf)
	{
		
		Session session = null;
		Transaction tx = null;
		List<Hospede> hospedes = new ArrayList();
		List<Reserva> reservas = null;
		ProjectionList projectionList = Projections.projectionList();
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
		
			projectionList.add(Projections.property("id"), "id");
			projectionList.add(Projections.property("hospede"), "hospede");
			projectionList.add(Projections.property("apartamento"), "apartamento");
			
			Criteria criteria = session.createCriteria(Reserva.class, "reserva");
			criteria.createAlias("hospede", "hospede");
			criteria.createAlias("apartamento", "apartamento");
			criteria.createAlias("apartamento.tipo", "tipo");
			if(nome!=null)
				criteria.add(Restrictions.ilike("hospede.nome", nome, MatchMode.ANYWHERE));
			if(cpf!=null)
				criteria.add(Restrictions.eq("hospede.cpf", cpf));
			
			criteria.setProjection(projectionList);
			
			criteria.setResultTransformer(Transformers.aliasToBean(Reserva.class));
			
			reservas = criteria.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return reservas;
	}
	
	public static void save(Conta conta)
	{
		
		Session session = null;
		Transaction tx = null;
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(conta);
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
	}
	
	public static List<Hospede> getHospedeByName(String nome)
	{
		Session session = null;
		Transaction tx = null;
		List<Hospede> listHospede =  new ArrayList();
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from Hospede where nome like '" + nome + "%'" );
			listHospede = (List<Hospede>) query.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return listHospede;
		
	}
	//Retorna o ID dos apartamentos
	public static List<BigInteger> getApartamentoByHospedeId(String idHospede)
	{
		Session session = null;
		Transaction tx = null;
		List<BigInteger> listApartamentoId =  new ArrayList();
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();

			Query query = session.createSQLQuery("select apartamentos_id from conta_apartamento ca," +
					" conta co where co.hospede_id = " + idHospede + " and co.id = ca.conta_id");
			listApartamentoId = (List<BigInteger>) query.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}

		
	return listApartamentoId;
	}
	
}
