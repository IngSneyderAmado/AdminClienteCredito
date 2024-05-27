package com.banco.clienteCredito.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.clienteCredito.Entity.Cliente;
import com.banco.clienteCredito.Entity.Credito;
import com.banco.clienteCredito.Entity.Cuota;
import com.banco.clienteCredito.Repository.CreditoRepository;
import com.banco.clienteCredito.Repository.CuotaRepository;

@Service
public class CuotaServiceImpl implements ICuotaService{
	
	@Autowired
	private CuotaRepository cuotaRepo;
	
	@Autowired
	private CreditoRepository creditoRepo;

	@Override
	public String pagarCuota(int cuota, Long creditoid , double valor) {
		Date fechaActual = Date.valueOf(LocalDate.now());
		Optional<Credito> credito = creditoRepo.findById(creditoid);
		if(credito.isEmpty()) {
			throw new RuntimeException("Credito no encontrado");
		}
		int numcuotas = credito.get().getNumeroCuotasPagadas();
		//Cuota cuotaz = cuotaRepo.findByNumeroCuotaAndCredito(cuota, credito);
		List<Cuota> cuotas = credito.get().getCuotas();
		Cuota cuotaz = cuotas.get(cuota-1);
		if(cuotaz.isEstadoPago() == true) {
			throw new RuntimeException("La Cuota ya esta paga");
		}
		cuotaz.setValorCuota(valor);
		cuotaz.setEstadoPago(true);
		cuotaz.setFechaPagada(fechaActual);
		cuotaRepo.save(cuotaz);
		credito.get().setNumeroCuotasPagadas(numcuotas+1);
		creditoRepo.save(credito.get());
		if(credito.get().getNumeroCuotas() >= credito.get().getNumeroCuotasPagadas()) {
			credito.get().setEstato(false);
			return "La cuota #"+cuotaz.getNumeroCuota()+" del credito "+ credito.get().getId()+" se pago correctamente, el credito esta cerrado";
		}
		
		return "La cuota #"+cuotaz.getNumeroCuota()+"del credito "+ credito.get().getId()+" se pago correctamente";
	}

	@Override
	public List<Cuota> listarCuotas( Long creditoid) {
		
		Optional<Credito> credito = creditoRepo.findById(creditoid);
		
		if(credito == null) {
			throw new RuntimeException("Credito no encontrado");
		}
		return credito.get().getCuotas();
	}


}
