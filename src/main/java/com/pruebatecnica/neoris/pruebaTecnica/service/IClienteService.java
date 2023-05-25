package com.pruebatecnica.neoris.pruebaTecnica.service;


import com.pruebatecnica.neoris.pruebaTecnica.entity.Cliente;

public interface IClienteService {

	public Cliente crearCliente(Cliente cliente);
	public Cliente findById(Long id);
	public void delete(Long id);
}
