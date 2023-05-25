package com.pruebatecnica.neoris.pruebaTecnica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pruebatecnica.neoris.pruebaTecnica.dao.IClienteDao;
import com.pruebatecnica.neoris.pruebaTecnica.entity.Cliente;


@Service
public class ClienteServiceImpl implements IClienteService{


	@Autowired
	private IClienteDao clienteDao;
	

	@Override
	@Transactional
	public Cliente crearCliente(Cliente cliente) {
		return clienteDao.save(cliente);
	}


	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);	
	}

}
