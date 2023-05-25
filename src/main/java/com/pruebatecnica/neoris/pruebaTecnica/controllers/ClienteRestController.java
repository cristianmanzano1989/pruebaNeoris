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

import com.pruebatecnica.neoris.pruebaTecnica.entity.Cliente;
import com.pruebatecnica.neoris.pruebaTecnica.service.IClienteService;
import com.pruebatecnica.neoris.pruebaTecnica.utils.Constantes;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class ClienteRestController.
 */
@RestController
@RequestMapping("/clienteRestController/api")

/** The Constant log. */
@Slf4j
public class ClienteRestController {
	
	/** The cliente service. */
	@Autowired
	private IClienteService clienteService;
	
	/**
	 * Crear cliente.
	 *
	 * @param cliente the cliente
	 * @return the response entity
	 */
	@PostMapping("/clientes/crearCliente")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente){
		log.info("crearCliente: "+ cliente.getPassword());
		Map<String, Object> response = new HashMap<>();
		Cliente clienteNew = null;
		try {
			clienteNew = clienteService.crearCliente(cliente);
		} catch(DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al realizar el insert en la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put(Constantes.MENSAJE, "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	/**
	 * Update cliente.
	 *
	 * @param cliente the cliente
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/clientes/update/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente clienteActual = clienteService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Cliente clienteUpdated = null;
		if (clienteActual == null) {
			response.put(Constantes.MENSAJE, "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			clienteActual.setPassword(cliente.getPassword()!=null ? cliente.getPassword(): "");
			clienteActual.setNombre(cliente.getNombre()!=null ? cliente.getNombre() : "");
			clienteActual.setDireccion(cliente.getDireccion()!=null ? cliente.getDireccion(): "");
			clienteActual.setEdad(cliente.getEdad()!=null? cliente.getEdad() : "");
			clienteActual.setGenero(cliente.getGenero()!=null ? cliente.getGenero() : "");
			clienteActual.setTelefono(cliente.getTelefono()!=null ? cliente.getTelefono(): "");
			clienteActual.setEstado(cliente.getEstado()!=null ? cliente.getEstado(): "");
			
			clienteUpdated = clienteService.crearCliente(clienteActual);

		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put(Constantes.MENSAJE, "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteUpdated);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	/**
	 * Delete cliente.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/clientes/delete/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
		    clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put(Constantes.MENSAJE, "Error al eliminar el cliente de la base de datos");
			response.put(Constantes.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(Constantes.MENSAJE, "El cliente eliminado con éxito!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
