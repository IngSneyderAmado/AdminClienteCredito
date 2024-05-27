package com.banco.clienteCredito.Service;

import java.util.List;
import java.util.Optional;

import com.banco.clienteCredito.DTO.CreditoRequestDTO;
import com.banco.clienteCredito.Entity.Credito;

public interface ICreditoService {

	List<Credito> findByCliente(Long identificacion);

	Optional<Credito> findById(Long id);
	
	String save(Long identificacion, CreditoRequestDTO credito);
	
	Optional<Credito>  detalleCredito(Long identificacion, Long idcredito );
}
