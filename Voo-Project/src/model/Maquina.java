package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MAQUINA database table.
 * 
 */
@Entity
@Table(name="MAQUINA")
@NamedQuery(name="Maquina.findAll", query="SELECT m FROM Maquina m")
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "maquinaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MAQUINA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "maquinaGen")
	@Id @Column(name = "MAQUINA_ID")
	int maquinaId;
	
	public int getMaquinaId() {
		return maquinaId;
	}

	public void setMaquinaId(int maquinaId) {
		this.maquinaId = maquinaId;
	}

	@Column(name="AÑOS_AMORTIZACION")
	private int añosAmortizacion;

	private int costo;

	@Column(name="DIAS_AMORTIZACION")
	private int diasAmortizacion;

	private double energia;

	@Column(name="HORAS_AMORTIZACION")
	private int horasAmortizacion;

	@Column(name="INDICE_REPARACION")
	private double indiceReparacion;

	@Column(length=2000000000)
	private String nombre;

	//bi-directional many-to-one association to PrendaMaquina
	@OneToMany(mappedBy="maquina", fetch=FetchType.EAGER)
	private List<PrendaMaquina> prendaMaquinas;

	public Maquina() {
	}

	public int getAñosAmortizacion() {
		return this.añosAmortizacion;
	}

	public void setAñosAmortizacion(int añosAmortizacion) {
		this.añosAmortizacion = añosAmortizacion;
	}

	public int getCosto() {
		return this.costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getDiasAmortizacion() {
		return this.diasAmortizacion;
	}

	public void setDiasAmortizacion(int diasAmortizacion) {
		this.diasAmortizacion = diasAmortizacion;
	}

	public double getEnergia() {
		return this.energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	public int getHorasAmortizacion() {
		return this.horasAmortizacion;
	}

	public void setHorasAmortizacion(int horasAmortizacion) {
		this.horasAmortizacion = horasAmortizacion;
	}

	public double getIndiceReparacion() {
		return this.indiceReparacion;
	}

	public void setIndiceReparacion(double indiceReparacion) {
		this.indiceReparacion = indiceReparacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PrendaMaquina> getPrendaMaquinas() {
		return this.prendaMaquinas;
	}

	public void setPrendaMaquinas(List<PrendaMaquina> prendaMaquinas) {
		this.prendaMaquinas = prendaMaquinas;
	}

	public PrendaMaquina addPrendaMaquina(PrendaMaquina prendaMaquina) {
		getPrendaMaquinas().add(prendaMaquina);
		prendaMaquina.setMaquina(this);

		return prendaMaquina;
	}

	public PrendaMaquina removePrendaMaquina(PrendaMaquina prendaMaquina) {
		getPrendaMaquinas().remove(prendaMaquina);
		prendaMaquina.setMaquina(null);

		return prendaMaquina;
	}

}