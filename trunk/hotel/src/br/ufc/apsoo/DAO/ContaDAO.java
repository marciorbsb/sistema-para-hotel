package br.ufc.apsoo.DAO;

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
	@SuppressWarnings("unchecked")
	public static List<Conta> buscaPorFiltrosConta(String nome, Long cpf)
	{
		
		Session session = null;
		Transaction tx = null;
		List<Conta> contas = null;
		ProjectionList projectionList = Projections.projectionList();
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
		
			projectionList.add(Projections.property("id"), "id");
			projectionList.add(Projections.property("hospede"), "hospede");
			
			Criteria criteria = session.createCriteria(Conta.class, "conta");
			criteria.createAlias("hospede", "hospede");
			if(nome!=null)
				criteria.add(Restrictions.ilike("hospede.nome", nome, MatchMode.ANYWHERE));
			if(cpf!=null)
				criteria.add(Restrictions.eq("hospede.cpf", cpf));
			
			criteria.setProjection(projectionList);
			
			criteria.setResultTransformer(Transformers.aliasToBean(Conta.class));
			
			contas = criteria.list();

		} catch (Exception e) {
			// houve algum problema? vamos retornar o banco de dados
			// ao seu estado anterior
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		return contas;
	}
	public static void deletarConta(Conta conta)
	{
		Session session = null;
		Transaction tx = null;
		
		try {

			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			
			tx=session.beginTransaction();
			
			session.delete(conta);
			
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
