package com.banco.clienteCredito.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.clienteCredito.Entity.Credito;
import com.banco.clienteCredito.Entity.Cuota;

@Repository
public interface CuotaRepository extends CrudRepository<Cuota, Integer>{
	
	Cuota findByNumeroCuotaAndCredito(int numeroCuota,Optional<Credito> credito);
	
}
