package br.ufc.apsoo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import sun.security.util.BigInt;

import com.sun.istack.internal.NotNull;

@Entity
public class Apartamento {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private int tipo;
	@NotNull
	private Integer numero;
	private boolean disponivel;
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
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
