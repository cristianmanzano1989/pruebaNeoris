package com.pruebatecnica.neoris.pruebaTecnica.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.neoris.pruebaTecnica.dto.EstadoCuenta;
import com.pruebatecnica.neoris.pruebaTecnica.entity.Cliente;
import com.pruebatecnica.neoris.pruebaTecnica.utils.ConstantesSQL;


@Repository
public interface IReporteDao extends JpaRepository<Cliente, Long> {
    @Query(value = ConstantesSQL.CONSULTA_REPORTE_MOVIMIENTO_CLIENTE, nativeQuery = true)
    List<EstadoCuenta> generateReporteEstadoCuenta(@Param("id") Long idCliente,@Param("fechaIni")LocalDate fechaInicial,@Param("fechaFin") LocalDate fechaFin);
}
