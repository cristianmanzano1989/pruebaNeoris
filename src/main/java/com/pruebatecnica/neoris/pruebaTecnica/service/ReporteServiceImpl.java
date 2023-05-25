package com.pruebatecnica.neoris.pruebaTecnica.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica.neoris.pruebaTecnica.dao.IReporteDao;
import com.pruebatecnica.neoris.pruebaTecnica.dto.EstadoCuenta;
import com.pruebatecnica.neoris.pruebaTecnica.utils.ConstantesSQL;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.type.StandardBasicTypes;


@Service
public class ReporteServiceImpl implements IReporteService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private IReporteDao reporteDao;

	@Override
	public List<EstadoCuenta> generateReporteEstadoCuenta(Long id, LocalDate startDate, LocalDate endDate) throws Exception{
		
        Query query = entityManager.createNativeQuery(ConstantesSQL.CONSULTA_REPORTE_MOVIMIENTO_CLIENTE);
        query.setParameter("fechaIni", startDate);
        query.setParameter("fechaFin", endDate);
        query.setParameter("id", id);
        query.unwrap(NativeQuery.class)
                .addScalar("fecha", StandardBasicTypes.DATE)
                .addScalar("nombre", StandardBasicTypes.STRING)
                .addScalar("numero_cuenta", StandardBasicTypes.STRING)
                .addScalar("tipo_cuenta", StandardBasicTypes.STRING)
                .addScalar("saldo_inicial", StandardBasicTypes.DOUBLE)
                .addScalar("estado", StandardBasicTypes.STRING)
                .addScalar("valor", StandardBasicTypes.DOUBLE)
                .addScalar("saldo", StandardBasicTypes.DOUBLE)
                .setResultTransformer(Transformers.aliasToBean(EstadoCuenta.class));
        return query.getResultList();
	}
	


}
