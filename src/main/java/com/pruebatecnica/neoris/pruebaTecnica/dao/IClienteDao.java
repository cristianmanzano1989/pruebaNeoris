package com.pruebatecnica.neoris.pruebaTecnica.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.neoris.pruebaTecnica.entity.Cliente;

@Repository
public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
