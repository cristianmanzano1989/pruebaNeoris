package com.pruebatecnica.neoris.pruebaTecnica.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Persona{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(nullable = false, unique = true)
	private Long identificacion;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String genero;
	@Column(nullable = false)
	private String edad;
	private String direccion;
	private String telefono;
}
