package com.banco.clienteCredito.Service;

import java.util.List;

import com.banco.clienteCredito.Entity.Cuota;

public interface ICuotaService {

	String pagarCuota(int cuota, Long creditoid, double valor);
	
	List<Cuota> listarCuotas( Long creditoid);
	
}
