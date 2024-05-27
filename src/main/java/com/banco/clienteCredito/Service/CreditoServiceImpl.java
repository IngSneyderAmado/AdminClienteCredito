package com.banco.clienteCredito.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.clienteCredito.DTO.CreditoRequestDTO;
import com.banco.clienteCredito.Entity.Cliente;
import com.banco.clienteCredito.Entity.Credito;
import com.banco.clienteCredito.Entity.Cuota;
import com.banco.clienteCredito.Repository.ClienteRepository;
import com.banco.clienteCredito.Repository.CreditoRepository;

@Service
public class CreditoServiceImpl implements ICreditoService{
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private CreditoRepository creditoRepo;

	@Override
	public List<Credito> findByCliente(Long identificacion) {
		Cliente clietneExite = clienteRepo.findByIdentificacion(identificacion);
		
		if(clietneExite == null) {
			throw new RuntimeException("Cliente no encontrado");
		}
		System.out.println(clietneExite.getCredito());
		List<Credito> creditos = clietneExite.getCredito();
		
		return creditos;
	}
	
	@Override
	public Optional<Credito> findById(Long id) {
		Optional<Credito> credito =  creditoRepo.findById(id);
		if(credito.isEmpty()) {
			throw new RuntimeException("Credito no encontrado");
		}
		return credito;
	}

	@Override
	public String save(Long identificacion, CreditoRequestDTO creditodto) {
		Cliente clietneExite = clienteRepo.findByIdentificacion(identificacion);
		Credito credito = new Credito();
		credito.setValor(creditodto.getValor());
		credito.setNumeroCuotas(creditodto.getNumeroCuotas());
		credito.setEstato(creditodto.isEstado());
		credito.setNumeroCuotasPagadas(0);
		if(clietneExite == null || clietneExite.isEstado() == false ) {
			throw new RuntimeException("Cliente no encontrado o se encuentra inactivo");
		}
		switch (clietneExite.getTipoRiesgo()) {
        case A:
            credito.setTasa(0.03);
            break;
        case B:
        	credito.setTasa(0.02);
            break;
        case C:
        	credito.setTasa(0.01); 
            break;
        default:
            throw new IllegalArgumentException("Tipo de riesgo no válido");
		}
		
		if(credito.getNumeroCuotas() > 6){
			double incremento = 0.001; 
	        double incrementoTotal = incremento * (credito.getNumeroCuotas() - 6);
	        double tasaBase = credito.getTasa() + incrementoTotal;
	        credito.setTasa(tasaBase);
		}
		double valorCuotaFija = calcularCuotaFija(credito.getValor(),credito.getNumeroCuotas(),credito.getTasa());
		credito.setValorCuotaFija(valorCuotaFija);
		credito.setValorCuotaFijaPromedio(valorCuotaFija);
		List<Cuota> cuotas = new ArrayList<>();
		for (int i = 1; i <= credito.getNumeroCuotas(); i++) {
        	Cuota cuota = new Cuota(i,valorCuotaFija,null,null,false);
        	cuotas.add(cuota);
        }
		credito.setCuotas(cuotas);
		credito.setEstato(true);
		
		creditoRepo.save(credito);
		List<Credito> creditos = clietneExite.getCredito();
		creditos.add(credito);
		clietneExite.setCredito(creditos);
		clienteRepo.save(clietneExite);
		return "credito registrado correctamente";
	}
	
	
	public double calcularCuotaFija(double montoCredito, int cantidadCuotas, double tasaInteres) {
		DecimalFormat df = new DecimalFormat("#.##");
        double totalAdeudado = montoCredito;
        double totalCuota = montoCredito / cantidadCuotas;
        double porcentajeTasa = tasaInteres / 100.0;

        for (int i = 0; i < cantidadCuotas; i++) {
        	
            double interes = totalAdeudado * porcentajeTasa;
            totalCuota += interes;
            totalAdeudado -= totalCuota;
        }
        
        String numeroString = df.format(totalCuota);
        String numeroStringModificado = numeroString.replace(',', '.');
        
        return Double.parseDouble(numeroStringModificado);
    }
	
	
	
	public Optional<Credito> detalleCredito(Long identificacion , Long idcredito) {
		Cliente clietneExite = clienteRepo.findByIdentificacion(identificacion);
		
		if(clietneExite == null) {
			throw new RuntimeException("Cliente no encontrado");
		}
		Optional<Credito> credito = creditoRepo.findById(idcredito);
		
		if(credito == null) {
			throw new RuntimeException("Credito no encontrado");
		}
		
		return credito;
	}


}
