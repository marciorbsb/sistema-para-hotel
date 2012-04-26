package br.ufc.apsoo.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import sun.security.util.BigInt;

import br.ufc.apsoo.entidades.Apartamento;
import br.ufc.apsoo.entidades.Endereco;
import br.ufc.apsoo.entidades.Hospede;
import br.ufc.apsoo.entidades.Reserva;
import br.ufc.apsoo.entidades.Servico;
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
			session.save(hospede); // vamos salvar o usuário
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
	
	public static boolean doCheckIn(Hospede hospede, Apartamento apartamento)
	{
		Reserva reserva = new Reserva();
		
		reserva.setApartamento(apartamento);
		reserva.setHospede(hospede);
		
		return true;
		
	}
	
}
