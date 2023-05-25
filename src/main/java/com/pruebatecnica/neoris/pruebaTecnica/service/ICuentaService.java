package com.pruebatecnica.neoris.pruebaTecnica.service;

import com.pruebatecnica.neoris.pruebaTecnica.entity.Cuenta;

public interface ICuentaService {

	public Cuenta crearCuenta(Cuenta cliente) throws Exception;
	public Cuenta findById(Long id) throws Exception;
	public void delete(Long id) throws Exception;
}
