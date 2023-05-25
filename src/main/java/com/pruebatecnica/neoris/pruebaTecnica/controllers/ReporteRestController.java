package com.pruebatecnica.neoris.pruebaTecnica.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.neoris.pruebaTecnica.dto.EstadoCuenta;
import com.pruebatecnica.neoris.pruebaTecnica.service.IReporteService;
import com.pruebatecnica.neoris.pruebaTecnica.utils.Constantes;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ReporteRestController.
 */
@RestController
@RequestMapping("/ReporteRestController/api")
@Slf4j
public class ReporteRestController {

	@Autowired
	private IReporteService reporteService;
	
	/**
	 * Generate reporte estado cuenta.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the response entity
	 */
	@GetMapping("/reportes/{id}/")
    public ResponseEntity<?> generateReporteEstadoCuenta(@PathVariable Long id,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("fecha_fin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
			Map<String, Object> response = new HashMap<>();
			// lógica para generar el reporte basado en el rango de fechas
			List<EstadoCuenta> listadoEstadoCuenta = new ArrayList<>();
			try {
				listadoEstadoCuenta = reporteService.generateReporteEstadoCuenta(id, startDate, endDate);
			} catch(DataAccessException e) {
				response.put(Constantes.MENSAJE, "Error al generar el reporte");
				response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (Exception e) {
				response.put(Constantes.MENSAJE, "Error al generar el reporte");
				response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMessage()));
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put(Constantes.MENSAJE, "El Reporte ha sido generado con éxito!");	
		response.put("estadoCuenta", listadoEstadoCuenta);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
