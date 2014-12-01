package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRENDA_MAQUINA database table.
 * 
 */
@Entity
@Table(name="PRENDA_MAQUINA")
@NamedQuery(name="PrendaMaquina.findAll", query="SELECT p FROM PrendaMaquina p")
public class PrendaMaquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRENDA_MAQUINA_PRENDAMAQUINAID_GENERATOR", sequenceName="SEQ_PRENDA_MAQUINA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRENDA_MAQUINA_PRENDAMAQUINAID_GENERATOR")
	@Column(name="PRENDA_MAQUINA_ID")
	private long prendaMaquinaId;

	@Column(name="CANTIDAD_HORAS")
	private double cantidadHoras;

	@Column(name="PRENDA_ID")
	private BigDecimal prendaId;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@JoinColumn(name="MAQUINA_ID")
	private Maquina maquina;

	public PrendaMaquina() {
	}

	public long getPrendaMaquinaId() {
		return this.prendaMaquinaId;
	}

	public void setPrendaMaquinaId(long prendaMaquinaId) {
		this.prendaMaquinaId = prendaMaquinaId;
	}

	public double getCantidadHoras() {
		return this.cantidadHoras;
	}

	public void setCantidadHoras(double cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public BigDecimal getPrendaId() {
		return this.prendaId;
	}

	public void setPrendaId(BigDecimal prendaId) {
		this.prendaId = prendaId;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

}