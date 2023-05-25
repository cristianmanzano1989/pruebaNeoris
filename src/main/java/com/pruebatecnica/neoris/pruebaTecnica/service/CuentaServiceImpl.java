package com.pruebatecnica.neoris.pruebaTecnica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.neoris.pruebaTecnica.dao.ICuentaDao;
import com.pruebatecnica.neoris.pruebaTecnica.entity.Cuenta;

@Service
public class CuentaServiceImpl implements ICuentaService {

	
	@Autowired
	private ICuentaDao cuentaDao;
	
	@Override
	@Transactional
	public Cuenta crearCuenta(Cuenta cliente) throws Exception {
		Cuenta cuenta = null;
		try {
			cuenta = cuentaDao.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return cuenta;
	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta findById(Long id) throws Exception{
		return  cuentaDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) throws Exception {
		cuentaDao.deleteById(id);
		
	}

}
