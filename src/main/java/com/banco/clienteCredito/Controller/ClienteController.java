package com.banco.clienteCredito.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.banco.clienteCredito.DTO.ClienteRequestDTO;
import com.banco.clienteCredito.Service.IClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void saveCliente(@RequestBody ClienteRequestDTO cliente) {
		clienteService.save(cliente);
	}
	

	@PostMapping("/update/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> saveCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO cliente) {
		return ResponseEntity.ok( clienteService.update(id,cliente));
	}
	
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteCliente(@PathVariable Long id) {
		 clienteService.eliminar(id);
	}
	
	@PostMapping("/estado/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> saveEstadoCliente(@PathVariable Long id, @RequestBody Map<String, Object> body) {
		boolean estado = (boolean) body.get("estado");
		return ResponseEntity.ok( clienteService.saveEstado(id,estado));
	}
	
}
