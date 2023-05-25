package com.pruebatecnica.neoris.pruebaTecnica.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Persona implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String estado;
	
	// Relación uno a muchos con la tabla Cuenta
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "cliente")
	private List<Cuenta> cuentas; 

}
