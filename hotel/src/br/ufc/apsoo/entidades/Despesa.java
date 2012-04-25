package br.ufc.apsoo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.internal.NotNull;


public class Despesa {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	@NotNull
	private List<Servico> servicos;
	
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
