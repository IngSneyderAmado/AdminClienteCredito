package com.banco.clienteCredito.DTO;

import java.sql.Date;

import com.banco.clienteCredito.Entity.Genero;
import com.banco.clienteCredito.Entity.TipoCuenta;
import com.banco.clienteCredito.Entity.tipoCliente;
import com.banco.clienteCredito.Entity.tipoRiesgo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteRequestDTO {
	
	
	private Long identificacion;
	
	private String nombre;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	
	private Genero genero;
	
	private String cuenta;
	
	private TipoCuenta tipoCuenta;
	
	private String banco;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date inicioContrato;
	
	private boolean estado;
	
	private tipoCliente tipoCliente;
	
	

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Date getInicioContrato() {
		return inicioContrato;
	}

	public void setInicioContrato(Date inicioContrato) {
		this.inicioContrato = inicioContrato;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public tipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(tipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	

}
