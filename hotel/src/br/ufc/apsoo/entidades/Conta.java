package br.ufc.apsoo.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.sun.istack.internal.NotNull;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@NotNull
	private Hospede hospede;
	@NotNull
	private Date dataInicio;
	@NotNull
	private Date dataFim;
	@OneToMany
	@NotNull
	private List<Apartamento> apartamentos;
	@ManyToMany
	private List<Servico> servicos;
	@Transient
	private String total;
	
	public String getTotal() {
		Float valor = new Float(0);
		for(Servico servico: servicos){
			valor = valor + servico.getValor();
		}
		total = valor.toString();
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
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
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}
	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}
	
	

}
