package com.pruebatecnica.neoris.pruebaTecnica.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pruebatecnica.neoris.pruebaTecnica.entity.Cuenta;
import com.pruebatecnica.neoris.pruebaTecnica.service.ICuentaService;
import com.pruebatecnica.neoris.pruebaTecnica.utils.Constantes;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class CuentasRestController.
 */
@RestController
@RequestMapping("/CuentasRestController/api")
@Slf4j
public class CuentasRestController {
	
	/** The cuenta service. */
	@Autowired
	private ICuentaService cuentaService;

	
	/**
	 * Crear cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the response entity
	 */
	@PostMapping("/cuentas/crearCuenta")
	public ResponseEntity<?> crearCuenta(@RequestBody Cuenta cuenta) throws Exception{
		log.info("crearCuenta", cuenta);
		Map<String, Object> response = new HashMap<>();
		Cuenta cuentaNueva = null;
		
		try {
			cuentaNueva = cuentaService.crearCuenta(cuenta);
		} catch(DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al realizar el insert en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e) {
			response.put(Constantes.MENSAJE, "Error al realizar el insert en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put(Constantes.MENSAJE, "la cuenta ha sido creado con éxito!");
		response.put("cuenta", cuentaNueva);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	

	/**
	 * Update cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param id the id
	 * @return the response entity
	 * @throws Exception 
	 */
	@PutMapping("/cuentas/update/{id}")
	public ResponseEntity<?> update(@RequestBody Cuenta cuenta, @PathVariable Long id) throws Exception{
		Cuenta cuentaActual = cuentaService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Cuenta cuentaUpdated = null;
		if (cuentaActual == null) {
			response.put(Constantes.MENSAJE, "Error: no se pudo editar, el cuenta ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			cuentaActual.setNumeroCuenta(cuenta.getNumeroCuenta()!=null ? cuenta.getNumeroCuenta(): "");
			cuentaActual.setTipoCuenta(cuenta.getTipoCuenta()!=null ? cuenta.getTipoCuenta() : "");
			cuentaActual.setSaldoInicial(cuenta.getSaldoInicial()!=null ? cuenta.getSaldoInicial(): 0);
			cuentaActual.setEstado(cuenta.getEstado()!=null? cuenta.getEstado() : "");
			
			cuentaUpdated = cuentaService.crearCuenta(cuentaActual);

		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al actualizar el cuenta en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			response.put(Constantes.MENSAJE, "Error al actualizar el cuenta en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put(Constantes.MENSAJE, "La cuenta ha sido actualizada con éxito!");
		response.put("cuenta", cuentaUpdated);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
	/**
	 * Delete cuenta.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws Exception 
	 */
	@DeleteMapping("/cuentas/delete/{id}")
	public ResponseEntity<?> deleteCuenta(@PathVariable Long id) throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		try {
			cuentaService.delete(id);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al eliminar el Cuenta de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			response.put(Constantes.MENSAJE, "Error al eliminar el Cuenta de la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Constantes.MENSAJE, "Cuenta eliminada con éxito!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
