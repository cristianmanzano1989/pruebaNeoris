package com.pruebatecnica.neoris.pruebaTecnica.service;

import java.time.LocalDate;
import java.util.List;
import com.pruebatecnica.neoris.pruebaTecnica.dto.EstadoCuenta;

public interface IReporteService {

	public List<EstadoCuenta> generateReporteEstadoCuenta(Long id, LocalDate startDate, LocalDate endDate) throws Exception;
}
