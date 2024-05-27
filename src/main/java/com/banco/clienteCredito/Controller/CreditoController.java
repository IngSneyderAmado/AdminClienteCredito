package com.banco.clienteCredito.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banco.clienteCredito.DTO.ClienteRequestDTO;
import com.banco.clienteCredito.DTO.CreditoRequestDTO;
import com.banco.clienteCredito.Entity.Credito;
import com.banco.clienteCredito.Service.ICreditoService;

@RestController
@RequestMapping("/api/credito")
public class CreditoController {
	
	@Autowired
	private ICreditoService creditoService;
	
	@PostMapping("/list")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> saveEstadoCliente(@RequestBody Map<String, Object> body ) {
		Long identificacion = Long.parseLong((String) body.get("identificacion")) ;
		return ResponseEntity.ok( creditoService.findByCliente(identificacion));
	}
	
	@PostMapping("/detalle")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> detalleCredito(@RequestBody Map<String, Object> body ) {
		Long identificacion = Long.parseLong((String) body.get("identificacion")) ;
		Long idcredito = Long.parseLong((String) body.get("idcredito")) ;
		return ResponseEntity.ok( creditoService.detalleCredito(identificacion, idcredito));
	}
	
	
	@PostMapping("/create/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void saveCliente(@PathVariable String id, @RequestBody  CreditoRequestDTO credito) {
		Long identificacion = Long.parseLong((String)id) ;
		creditoService.save(identificacion,credito);
	}

}
