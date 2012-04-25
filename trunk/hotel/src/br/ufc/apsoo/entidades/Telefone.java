package br.ufc.apsoo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Telefone {
	@Id
	@GeneratedValue
	private Long id;
	private Long fixo;
	private Long celular;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFixo() {
		return fixo;
	}
	public void setFixo(Long fixo) {
		this.fixo = fixo;
	}
	public Long getCelular() {
		return celular;
	}
	public void setCelular(Long celular) {
		this.celular = celular;
	}
	
	
}
