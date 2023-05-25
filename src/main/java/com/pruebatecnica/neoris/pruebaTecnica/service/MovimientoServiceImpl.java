package com.pruebatecnica.neoris.pruebaTecnica.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebatecnica.neoris.pruebaTecnica.dao.IMovimientoDao;
import com.pruebatecnica.neoris.pruebaTecnica.entity.Movimiento;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

	
	@Autowired
	private IMovimientoDao movimientoDao;
	
	@Override
	@Transactional
	public Movimiento crearMovimiento(Movimiento movimiento) {
		return movimientoDao.save(movimiento);
	}

	@Override
	@Transactional(readOnly = true)
	public Movimiento findById(Long id) {
		return movimientoDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		movimientoDao.deleteById(id);
	}


}
