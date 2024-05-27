package com.banco.clienteCredito.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.clienteCredito.DTO.ClienteRequestDTO;
import com.banco.clienteCredito.Entity.Cliente;
import com.banco.clienteCredito.Entity.tipoCliente;
import com.banco.clienteCredito.Entity.tipoRiesgo;
import com.banco.clienteCredito.Repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteRepo.findAll();
	}

	@Override
	public Cliente findByIdentificador(Long identificador) {
		// TODO Auto-generated method stub
		return (Cliente) clienteRepo.findByIdentificacion(identificador);
	}

	@Override
	public String save(ClienteRequestDTO clientedto) {
		Cliente cliente = null;
		if (clientedto.getBanco() == null || clientedto.getCuenta() == null || clientedto.getFechaNacimiento() == null
				|| clientedto.getGenero() == null || clientedto.getTipoCliente() == null
				|| clientedto.getIdentificacion() == null || clientedto.getNombre() == null
				|| clientedto.getTipoCuenta() == null) {
			throw new RuntimeException("Faltan Campos");
		}

		LocalDate fechaActual = LocalDate.now();
		Cliente clienteRxite = clienteRepo.findByIdentificacion(clientedto.getIdentificacion());
		if(clienteRxite != null ) {
			throw new RuntimeException("El cliente ya existe");
		}

		if (clientedto.getTipoCliente().equals(tipoCliente.EMPLEADO) && clientedto.getInicioContrato() == null) {
			throw new RuntimeException("Es necesario que digite la fecha del inicio del contrato");
		}
		if (clientedto.getTipoCliente().equals(tipoCliente.INDEPENDIENTE)) {
			cliente = new Cliente(clientedto.getIdentificacion(), clientedto.getNombre(),
					clientedto.getFechaNacimiento(), clientedto.getGenero(), clientedto.getCuenta(),
					clientedto.getTipoCuenta(), clientedto.getBanco(), null, clientedto.isEstado(),
					clientedto.getTipoCliente());
			cliente.setTipoRiesgo(tipoRiesgo.A);
		} else {
			cliente = new Cliente(clientedto.getIdentificacion(), clientedto.getNombre(),
					clientedto.getFechaNacimiento(), clientedto.getGenero(), clientedto.getCuenta(),
					clientedto.getTipoCuenta(), clientedto.getBanco(), clientedto.getInicioContrato(),
					clientedto.isEstado(), clientedto.getTipoCliente());
			LocalDate inicioContrato = cliente.getInicioContrato().toLocalDate();
			Period periodo = Period.between(inicioContrato , fechaActual);

			if ( periodo.getYears() < 1) {
				cliente.setTipoRiesgo(tipoRiesgo.A);
			} else if (periodo.getYears() <= 2) {
				cliente.setTipoRiesgo(tipoRiesgo.B);
			} else {
				cliente.setTipoRiesgo(tipoRiesgo.C);
			}
		}
		
		clienteRepo.save(cliente);

		return "Cliente guardado Correctamente ";

	}

	@Override
	public String saveEstado(Long identificador, boolean estado) {
		System.out.println(estado);
		Cliente cliente = clienteRepo.findByIdentificacion(identificador);
		if (cliente == null) {
			throw new RuntimeException("Cliente no encontrado");
		}
		cliente.setEstado(estado);
		clienteRepo.save(cliente);
		if (cliente.isEstado() == false) {
			return "El cliente se desactivo correctamente ";
		}
		return "El cliente se activo correctamente ";
	}

	@Override
	public String update(Long identificador, ClienteRequestDTO cliente) {
		Cliente clienteExistente = clienteRepo.findByIdentificacion(identificador);

		if (cliente == null || clienteExistente == null) {
			throw new RuntimeException("No se encontro el cliente");
		}
		if (cliente.getTipoCliente().equals(tipoCliente.EMPLEADO) && cliente.getInicioContrato() == null) {
			throw new RuntimeException("Es necesario que digite la fecha del inicio del contrato");
		}
		clienteExistente.setBanco(cliente.getBanco());
		clienteExistente.setCuenta(cliente.getCuenta());
		clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());
		clienteExistente.setGenero(cliente.getGenero());
		clienteExistente.setInicioContrato(cliente.getInicioContrato());
		
		clienteExistente.setNombre(cliente.getNombre());
		clienteExistente.setTipoCliente(cliente.getTipoCliente());
		clienteExistente.setTipoCuenta(cliente.getTipoCuenta());
		if (clienteExistente.getTipoCliente().equals(tipoCliente.INDEPENDIENTE)) {
			clienteExistente.setTipoRiesgo(tipoRiesgo.A);
		} else {
			LocalDate fechaActual = LocalDate.now();
			LocalDate inicioContrato = clienteExistente.getInicioContrato().toLocalDate();
			Period periodo = Period.between(inicioContrato, fechaActual);

			if ( periodo.getYears() < 1) {
				clienteExistente.setTipoRiesgo(tipoRiesgo.A);
			} else if (periodo.getYears() <= 2) {
				clienteExistente.setTipoRiesgo(tipoRiesgo.B);
			} else {
				clienteExistente.setTipoRiesgo(tipoRiesgo.C);
			}
		}

		clienteRepo.save(clienteExistente);

		return "Cliente actualizado Correctamente ";
	}

	@Override
	public void eliminar(Long identificador) {
		Cliente cliente = clienteRepo.findByIdentificacion(identificador);
		if (cliente != null) {
			clienteRepo.delete(cliente);
		} else {
			throw new RuntimeException("Cliente no encontrado");
		}
	}

}
