package com.pruebatecnica.neoris.pruebaTecnica.utils;


/**
 * The Class ConstantesSQL.
 */
public class ConstantesSQL {

	
	public static final String CONSULTA_REPORTE_MOVIMIENTO_CLIENTE = "select m.fecha,c.nombre,ct.numero_cuenta,ct.tipo_cuenta,ct.saldo_inicial,ct.estado,m.valor,m.saldo \r\n"
																	+ "FROM db_prueba_neoris.cliente c \r\n"
																	+ "INNER JOIN db_prueba_neoris.cuenta ct ON c.id = ct.id_cliente \r\n"
																	+ "INNER JOIN movimiento m ON m.id_cuenta = ct.id_cuenta\r\n"
																	+ "where m.fecha between :fechaIni and :fechaFin and c.id = :id";
}
