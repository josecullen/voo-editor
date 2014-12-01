package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MAQUINAS database table.
 * 
 */
@Entity
@Table(name="MAQUINAS")
@NamedQuery(name="Maquina.findAll", query="SELECT m FROM Maquina m")
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAQUINAS_MAQUINAID_GENERATOR", sequenceName="SEQ_MAQUINA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAQUINAS_MAQUINAID_GENERATOR")
	@Column(name="MAQUINA_ID")
	private long maquinaId;

	private double costo;

	private double energia;

	@Column(name="INDICE_AMORTIZACION")
	private double indiceAmortizacion;

	@Column(name="INDICE_REP_Y_RES")
	private double indiceRepYRes;

	private String modelo;

	private String nombre;

	//bi-directional many-to-one association to PrendaMaquina
	@OneToMany(mappedBy="maquina")
	private List<PrendaMaquina> prendaMaquinas;

	public Maquina() {
	}

	public long getMaquinaId() {
		return this.maquinaId;
	}

	public void setMaquinaId(long maquinaId) {
		this.maquinaId = maquinaId;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getEnergia() {
		return this.energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	public double getIndiceAmortizacion() {
		return this.indiceAmortizacion;
	}

	public void setIndiceAmortizacion(double indiceAmortizacion) {
		this.indiceAmortizacion = indiceAmortizacion;
	}

	public double getIndiceRepYRes() {
		return this.indiceRepYRes;
	}

	public void setIndiceRepYRes(double indiceRepYRes) {
		this.indiceRepYRes = indiceRepYRes;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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

	@Override
	public String toString(){
		return nombre;
	}
}