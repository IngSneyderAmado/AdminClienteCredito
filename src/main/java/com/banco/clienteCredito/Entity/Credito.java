package com.banco.clienteCredito.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Credito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="valor")
	private double valor;
	
	@Column(name="tasa")
	private double tasa;
	
	@Column(name="numero_cuotas")
	private int numeroCuotas;
	
	@Column(name="numero_cuotas_pagas", nullable = true)
	private int numeroCuotasPagadas;
	
	@Column(name="valor_cuota_fija")
	private double valorCuotaFija;
	
	@Column(name="valor_cuota_fija_promedio")
	private double valorCuotaFijaPromedio;
	
	private Cliente cliente;
		
	@OneToMany(targetEntity = Cuota.class, cascade = CascadeType.ALL)
    private List<Cuota> cuotas;
	
	@Column(name="estado")
	private boolean estato;

	public Credito() {
		super();
	}

	

	public Credito(double valor, double tasa, int numeroCuotas, int numeroCuotasPagadas, double valorCuotaFija,
			double valorCuotaFijaPromedio, Cliente cliente, List<Cuota> cuotas, boolean estado) {
		super();
		this.valor = valor;
		this.tasa = tasa;
		this.numeroCuotas = numeroCuotas;
		this.numeroCuotasPagadas = numeroCuotasPagadas;
		this.valorCuotaFija = valorCuotaFija;
		this.valorCuotaFijaPromedio = valorCuotaFijaPromedio;
		this.cuotas = cuotas;
		this.estato = estado;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double d) {
		this.tasa = d;
	}

	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public int getNumeroCuotasPagadas() {
		return numeroCuotasPagadas;
	}

	public void setNumeroCuotasPagadas(int numeroCuotasPagadas) {
		this.numeroCuotasPagadas = numeroCuotasPagadas;
	}

	public double getValorCuotaFija() {
		return valorCuotaFija;
	}

	public void setValorCuotaFija(double valorCuotaFija) {
		this.valorCuotaFija = valorCuotaFija;
	}

	public List<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public double getValorCuotaFijaPromedio() {
		return valorCuotaFijaPromedio;
	}

	public void setValorCuotaFijaPromedio(double valorCuotaFijaPromedio) {
		this.valorCuotaFijaPromedio = valorCuotaFijaPromedio;
	}

	public boolean isEstato() {
		return estato;
	}



	public void setEstato(boolean estato) {
		this.estato = estato;
	}



	@Override
	public String toString() {
		return "{id=" + id + ", valor=" + valor + ", tasa=" + tasa + ", numeroCuotas=" + numeroCuotas
				+ ", numeroCuotasPagadas=" + numeroCuotasPagadas + ", valorCuotaFija=" + valorCuotaFija
				+ ", valorCuotaFijaPromedio=" + valorCuotaFijaPromedio  + ", cuotas=" + cuotas
				+ ", estato=" + estato + "}";
	}



	

	
	
	
}
