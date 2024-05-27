package com.banco.clienteCredito.Service;

import java.util.List;

import com.banco.clienteCredito.DTO.ClienteRequestDTO;
import com.banco.clienteCredito.Entity.Cliente;

public interface IClienteService {
	
	List<Cliente> findAll();
	
	Cliente findByIdentificador(Long identificador);
	
	String save(ClienteRequestDTO cliente);
	
	String saveEstado(Long identificador , boolean estado);

	String update(Long identificador, ClienteRequestDTO cliente);

	void eliminar(Long id);
	
	
}
