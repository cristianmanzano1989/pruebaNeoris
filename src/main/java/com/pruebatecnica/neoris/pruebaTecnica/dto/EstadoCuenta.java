package com.pruebatecnica.neoris.pruebaTecnica.dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EstadoCuenta {

	private Date fecha;
	private String nombre;
	private String numero_cuenta;
	private String tipo_cuenta;
	private Double saldo_inicial;
	private String estado;
	private Double valor;
	private Double saldo;
}
