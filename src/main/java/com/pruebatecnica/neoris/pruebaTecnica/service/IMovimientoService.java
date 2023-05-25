package com.pruebatecnica.neoris.pruebaTecnica.service;

import com.pruebatecnica.neoris.pruebaTecnica.entity.Movimiento;

public interface IMovimientoService {

	public Movimiento crearMovimiento(Movimiento movimiento);
	public Movimiento findById(Long id);
	public void delete(Long id);
}
