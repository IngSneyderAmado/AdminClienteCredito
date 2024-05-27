package com.banco.clienteCredito.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banco.clienteCredito.Entity.Cliente;
import com.banco.clienteCredito.Entity.Credito;

@Repository
public interface CreditoRepository extends CrudRepository<Credito, Long> {
	
	 List<Credito> findByCliente(Cliente cliente);
	 
	 
	 Optional<Credito> findById(Long id);

}
