package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the VALORES_POR_DEFECTO database table.
 * 
 */
@Entity
@Table(name="VALORES_POR_DEFECTO")
@NamedQuery(name="ValoresPorDefecto.findAll", query="SELECT v FROM ValoresPorDefecto v")
public class ValoresPorDefecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "valoresPorDefectoGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "VALORES_POR_DEFECTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "valoresPorDefectoGen")
	@Id @Column(name="VALORES_POR_DEFECTO_ID", nullable=false)
	private int valoresPorDefectoId;
	
	@Column(name="AÑOS_AMORTIZACION")
	private int añosAmortizacion;

	private int beneficio;

	@Column(name="COSTO_MAQUINA")
	private int costoMaquina;

	@Column(name="COSTO_MDO")
	private int costoMdo;

	@Column(name="COSTO_METRO_TELA")
	private double costoMetroTela;

	@Column(name="DIAS_AMORTIZACION")
	private int diasAmortizacion;

	@Column(name="HORAS_AMORTIZACION")
	private int horasAmortizacion;

	

	public ValoresPorDefecto() {
	}

	public int getAñosAmortizacion() {
		return this.añosAmortizacion;
	}

	public void setAñosAmortizacion(int añosAmortizacion) {
		this.añosAmortizacion = añosAmortizacion;
	}

	public int getBeneficio() {
		return this.beneficio;
	}

	public void setBeneficio(int beneficio) {
		this.beneficio = beneficio;
	}

	public int getCostoMaquina() {
		return this.costoMaquina;
	}

	public void setCostoMaquina(int costoMaquina) {
		this.costoMaquina = costoMaquina;
	}

	public int getCostoMdo() {
		return this.costoMdo;
	}

	public void setCostoMdo(int costoMdo) {
		this.costoMdo = costoMdo;
	}

	public double getCostoMetroTela() {
		return this.costoMetroTela;
	}

	public void setCostoMetroTela(double costoMetroTela) {
		this.costoMetroTela = costoMetroTela;
	}

	public int getDiasAmortizacion() {
		return this.diasAmortizacion;
	}

	public void setDiasAmortizacion(int diasAmortizacion) {
		this.diasAmortizacion = diasAmortizacion;
	}

	public int getHorasAmortizacion() {
		return this.horasAmortizacion;
	}

	public void setHorasAmortizacion(int horasAmortizacion) {
		this.horasAmortizacion = horasAmortizacion;
	}

	public int getValoresPorDefectoId() {
		return this.valoresPorDefectoId;
	}

	public void setValoresPorDefectoId(int valoresPorDefectoId) {
		this.valoresPorDefectoId = valoresPorDefectoId;
	}

}