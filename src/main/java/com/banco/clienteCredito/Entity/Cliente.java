package com.banco.clienteCredito.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="identificacion")
	private Long identificacion;
	
	@Column(name="nombre")
	private String nombre;

	@Column(name="fecha_nacimiento")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	
	@Column(name="genero")
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Column(name="cuenta")
	private String cuenta;
	
	@Column(name="tipo_cuenta")
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipoCuenta;
	
	@Column(name="banco")
	private String banco;
	
	@Column(name="inicio_contrato")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date inicioContrato;
	
	@Column(name="estado")
	private boolean estado;
	
	@Column(name="tipo_cliente")
	@Enumerated(EnumType.STRING)
	private tipoCliente tipoCliente;
	
	@Column(name="tipo_riesgo",nullable = true)
	@Enumerated(EnumType.STRING)
	private tipoRiesgo tipoRiesgo;
		
	@OneToMany(targetEntity = Credito.class, cascade = CascadeType.ALL )
    private List<Credito> credito;
	
	public Cliente() {
		super();
	}

	public Cliente(Long identificacion, String nombre, Date fechaNacimiento, Genero genero, String cuenta,
			TipoCuenta tipoCuenta, String banco, Date inicioContrato, boolean estado,
			com.banco.clienteCredito.Entity.tipoCliente tipoCliente) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.cuenta = cuenta;
		this.tipoCuenta = tipoCuenta;
		this.banco = banco;
		if(tipoCliente.equals(tipoCliente.EMPLEADO)) {
			this.inicioContrato = inicioContrato;
		}
		this.estado = estado;
		this.tipoCliente = tipoCliente;
	}

	
	

	public Long getIdentificacion() {
		return identificacion;
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
	
	

	public tipoRiesgo getTipoRiesgo() {
		return tipoRiesgo;
	}

	public void setTipoRiesgo(tipoRiesgo tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public List<Credito> getCredito() {
		return credito;
	}

	public void setCredito(List<Credito> credito) {
		this.credito = credito;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", identificacion=" + identificacion + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + ", genero=" + genero + ", cuenta=" + cuenta + ", tipoCuenta=" + tipoCuenta
				+ ", banco=" + banco + ", inicioContrato=" + inicioContrato + ", estado=" + estado + ", tipoCliente="
				+ tipoCliente + ", tipoRiesgo=" + tipoRiesgo + "}";
	}
	
	
		
}
