package com.banco.clienteCredito.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.clienteCredito.Entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	Cliente findByIdentificacion(Long identificacion);
	

}
