package com.pruebatecnica.neoris.pruebaTecnica.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "movimiento")
@Data
public class Movimiento implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento")
	private Long idMovimiento;
	
	@Column(name="fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "tipo_movimiento", nullable = false)
	private String tipoMovimiento;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "saldo", nullable = false)
	private Double saldo;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cuenta", foreignKey = @ForeignKey(name="FK_CUENTA_MOVIMIENTO"))
	private Cuenta cuenta;
}
