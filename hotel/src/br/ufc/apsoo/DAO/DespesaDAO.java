package br.ufc.apsoo.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.*;

import br.ufc.apsoo.entidades.Conta;
import br.ufc.apsoo.entidades.Despesa;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Servico;

public class DespesaDAO {

	public static void salvarDespesa(long idConta, ArrayList<Long> idServico)
	{
		Session session = null;
		Transaction tx = null;

		Conta conta = ContaDAO.getContaById(idConta);
		ArrayList<BigInteger> listIdServicosBd  = new ArrayList<BigInteger>();
		ArrayList<Servico> listServicos = new ArrayList<Servico>();
		
		try {
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Query querySQL = session.createSQLQuery("select servicos_id from conta_servico cs where conta_id=" + conta.getId());
			listIdServicosBd = (ArrayList<BigInteger>) querySQL.list();
			
			for (BigInteger id : listIdServicosBd) {
				listServicos.add(ServiceDAO.getServiceById(id.longValue()));
			}
			
			for (Long id : idServico) {
				listServicos.add(ServiceDAO.getServiceById(id));
			}
			
			/*Antes de dar o Update, tem que buscar os produtos já cadastrados para essa conta pois sobrescreve*/
			conta.setServicos(listServicos);
			session.update(conta);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public static HashMap<Hospede, HashMap<Conta, Servico>> listDespesas()
	{
		Session session = null;
		Transaction tx = null;
		ArrayList<BigInteger> listIdsConta = new ArrayList<>();
		ArrayList<BigInteger> listIdsServicos = new ArrayList<>();
		
		ArrayList<Hospede> listHospede = new ArrayList<>();
		ArrayList<Servico> listServico = new ArrayList<>();
		
		HashMap<Hospede, HashMap<Conta, Servico>> listHospedeServico = new HashMap<>();
		
		try {
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createSQLQuery("select conta_id from conta_servico");
			listIdsConta =  (ArrayList<BigInteger>) query.list();
			
			query = session.createSQLQuery("select servicos_id from conta_servico");
			listIdsServicos =  (ArrayList<BigInteger>) query.list();
			
			
			for (int i=0; i<listIdsConta.size(); i++) {
				listHospede.add(ContaDAO.getContaById(listIdsConta.get(i).longValue()).getHospede());
				listServico.add(ServiceDAO.getServiceById(listIdsServicos.get(i).longValue()));
			}
			
			for(int i=0; i<listHospede.size(); i++){
				HashMap<Conta, Servico> mapCS = new HashMap<>();
				mapCS.put(ContaDAO.getContaById(listIdsConta.get(i).longValue()), listServico.get(i));
				listHospedeServico.put(listHospede.get(i), mapCS);
			}
			
			return listHospedeServico;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Erro: " + e.getMessage());
		} finally {
			session.close();
		}
		
		return null;
	}
	
	public static void estornarDespesa(long idConta, long idServico)
	{
		Session session = null;
		Transaction tx = null;

		Conta conta = ContaDAO.getContaById(idConta);
		ArrayList<BigInteger> listIdServicosBd  = new ArrayList<BigInteger>();
		ArrayList<Servico> listServicos = new ArrayList<Servico>();
		
		try {
			SessionFactory factory = new Configuration().configure()
					.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Query querySQL = session.createSQLQuery("delete from conta_servico where conta_id=" + idConta + " and servicos_id=" + idServico);

			int row = querySQL.executeUpdate();
			
			if(row==0)
			{
				System.out.println("erro sql query");
			}
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
