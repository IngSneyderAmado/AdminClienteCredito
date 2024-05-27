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

import com.banco.clienteCredito.Service.ICuotaService;

@RestController
@RequestMapping("/api/cuota")
public class CuotaController {
	
	@Autowired
	private ICuotaService cuotaService;
	
	@PostMapping("/pagar")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> pagarCuota(@RequestBody Map<String, Object> body){
		int cuota = Integer.parseInt((String) body.get("cuota"));
		Long idcredito = Long.parseLong((String) body.get("idcredito")) ;
		double valor = Double.parseDouble((String) body.get("valor"));
		return ResponseEntity.ok( cuotaService.pagarCuota(cuota, idcredito, valor));
	}
	
	@PostMapping("/list")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> listCuota(@RequestBody Map<String, Object> body){
		Long idcredito = Long.parseLong((String) body.get("idcredito")) ;
		return ResponseEntity.ok( cuotaService.listarCuotas(idcredito));
	}
	

}
