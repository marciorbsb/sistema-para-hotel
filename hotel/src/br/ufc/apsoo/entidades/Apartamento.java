package br.ufc.apsoo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.sun.istack.internal.NotNull;

@Entity
public class Apartamento {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@OneToOne
	private Tipo tipo;
	@NotNull
	private Integer numero;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
