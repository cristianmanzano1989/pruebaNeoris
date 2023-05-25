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
import com.pruebatecnica.neoris.pruebaTecnica.entity.Movimiento;
import com.pruebatecnica.neoris.pruebaTecnica.service.IMovimientoService;
import com.pruebatecnica.neoris.pruebaTecnica.utils.Constantes;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class MovimientosRestController.
 */
@RestController
@RequestMapping("/movimientosRestController/api")
@Slf4j
public class MovimientosRestController {

	
	/** The movimiento service. */
	@Autowired
	private IMovimientoService movimientoService;
	
	/**
	 * Crear cuenta.
	 *
	 * @param movimiento the movimiento
	 * @return the response entity
	 */
	@PostMapping("/movimientos/crearMovimiento")
	public ResponseEntity<?> crearMovimiento(@RequestBody Movimiento movimiento){
		log.info("crearMovimiento", movimiento);
		Map<String, Object> response = new HashMap<>();
		Movimiento movimientoNuevo = null;
		
		try {
			if(Constantes.TIPO_DEBITO.equalsIgnoreCase(movimiento.getTipoMovimiento())) {
				if(movimiento.getCuenta().getSaldoInicial() == 0) {
					response.put(Constantes.MENSAJE, "Saldo No disponible");
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}else {
					movimiento.setSaldo(movimiento.getCuenta().getSaldoInicial()-movimiento.getValor());
					movimientoNuevo = movimientoService.crearMovimiento(movimiento);
				}
			}else if (Constantes.TIPO_CREDITO.equalsIgnoreCase(movimiento.getTipoMovimiento())){
				movimiento.setSaldo(movimiento.getCuenta().getSaldoInicial()+ movimiento.getValor());
				movimientoNuevo = movimientoService.crearMovimiento(movimiento);
			}
			else {
				response.put(Constantes.MENSAJE, "El tipo de movimiento no es tipo de movimiento Válido, solo se permiten los valores [Débito ó Crédito]");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}	
			
		} catch(DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al realizar el insert en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put(Constantes.MENSAJE, "el movimiento ha sido creado con éxito!");
		response.put("movimiento", movimientoNuevo);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update movimiento.
	 *
	 * @param movimiento the movimiento
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/movimientos/update/{id}")
	public ResponseEntity<?> updateMovimiento(@RequestBody Movimiento movimiento, @PathVariable Long id){
		Movimiento movimientoActual = movimientoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Movimiento movimientoUpdated = null;
		if (movimientoActual == null) {
			response.put(Constantes.MENSAJE, "Error: no se pudo editar, el Movimiento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			movimientoActual.setFecha(movimiento.getFecha()!=null ? movimiento.getFecha(): null);
			movimientoActual.setTipoMovimiento(movimiento.getTipoMovimiento()!=null ? movimiento.getTipoMovimiento() : "");
			movimientoActual.setValor(movimiento.getValor()!=null ? movimiento.getValor(): 0);
			movimientoActual.setSaldo(movimiento.getSaldo()!=null? movimiento.getSaldo() : 0);
			
			movimientoUpdated = movimientoService.crearMovimiento(movimientoActual);

		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al actualizar el cuenta en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put(Constantes.MENSAJE, "El movimiento ha sido actualizado con éxito!");
		response.put("movimiento", movimientoUpdated);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	
	/**
	 * Delete movimiento.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/movimientos/delete/{id}")
	public ResponseEntity<?> deleteMovimiento(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			movimientoService.delete(id);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al eliminar el movimiento de la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Constantes.MENSAJE, "Movimiento Eliminado con éxito!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
