package com.banco.clienteCredito.Entity;

import java.io.Serializable;
import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cuota implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="numero_cuota")
	private int numeroCuota;
		
	@Column(name="valor_cuota")
	private double valorCuota;
	
	@Column(name="fecha_pago")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaPago;
	
	@Column(name="fecha_pagada")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaPagada;
	
	@Column(name="estado_pago")
	private boolean estadoPago;
	
	
	private Credito credito;

	public Cuota() {
		super();
	}




	public Cuota(int numeroCuota, double valorCuota, Date fechaPago, Date fechaPagada, boolean estadoPago) {
		super();
		this.numeroCuota = numeroCuota;
		this.valorCuota = valorCuota;
		this.fechaPago = fechaPago;
		this.fechaPagada = fechaPagada;
		this.estadoPago = estadoPago;
	}




	public int getNumeroCuota() {
		return numeroCuota;
	}



	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public double getValorCuota() {
		return valorCuota;
	}



	public void setValorCuota(double valorCuota) {
		this.valorCuota = valorCuota;
	}



	public Date getFechaPago() {
		return fechaPago;
	}



	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}



	public Date getFechaPagada() {
		return fechaPagada;
	}



	public void setFechaPagada(Date fechaPagada) {
		this.fechaPagada = fechaPagada;
	}



	public boolean isEstadoPago() {
		return estadoPago;
	}



	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}



	@Override
	public String toString() {
		return "{numeroCuota=" + numeroCuota  + ", valorCuota=" + valorCuota + ", fechaPago="
				+ fechaPago + ", fechaPagada=" + fechaPagada + ", estadoPago=" + estadoPago + "}";
	}
	
	
}
