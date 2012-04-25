package br.ufc.apsoo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.internal.NotNull;

@Entity
public class Apartamento {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String tipo;
	@NotNull
	private Integer numero;
	@NotNull
	private Float diaria;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Float getDiaria() {
		return diaria;
	}
	public void setDiaria(Float diaria) {
		this.diaria = diaria;
	}

}
