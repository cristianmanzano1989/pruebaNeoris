package com.pruebatecnica.neoris.pruebaTecnica.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "cuenta", uniqueConstraints = @UniqueConstraint(name = "numero_cuenta", columnNames = "numero_cuenta"))
@Data
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	private Long idCuenta;
	
	@Column(name = "numero_cuenta", unique = true)
	private String numeroCuenta;
	
	@Column(name = "tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name = "saldo_inicial")
	private Double saldoInicial;
	
	private String estado;
	
	// Relación muchos a uno con la tabla Cliente
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente" , foreignKey = @ForeignKey(name="FK_CLIENTE_CUENTA"))
	private Cliente cliente;

	// Relación uno a muchos con la tabla Movimiento
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "cuenta")
	private List<Movimiento> movimientos; 
}
