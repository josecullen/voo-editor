package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRENDA_MANO_DE_OBRA database table.
 * 
 */
@Entity
@Table(name="PRENDA_MANO_DE_OBRA")
@NamedQuery(name="PrendaManoDeObra.findAll", query="SELECT p FROM PrendaManoDeObra p")
public class PrendaManoDeObra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRENDA_MANO_DE_OBRA_PRENDAMANODEOBRAID_GENERATOR", sequenceName="SEQ_TDAI_PRENDA_MANO_DE_OBRA7", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRENDA_MANO_DE_OBRA_PRENDAMANODEOBRAID_GENERATOR")
	@Column(name="PRENDA_MANO_DE_OBRA_ID")
	private long prendaManoDeObraId;

	@Column(name="CANTIDAD_HORAS")
	private double cantidadHoras;

	//bi-directional many-to-one association to ManoDeObra
	@ManyToOne
	@JoinColumn(name="MANO_DE_OBRA_ID")
	private ManoDeObra manoDeObra;

	//bi-directional many-to-one association to Prenda
	@ManyToOne
	@JoinColumn(name="PRENDA_ID")
	private Prenda prenda;

	public PrendaManoDeObra() {
	}

	public long getPrendaManoDeObraId() {
		return this.prendaManoDeObraId;
	}

	public void setPrendaManoDeObraId(long prendaManoDeObraId) {
		this.prendaManoDeObraId = prendaManoDeObraId;
	}

	public double getCantidadHoras() {
		return this.cantidadHoras;
	}

	public void setCantidadHoras(double cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public ManoDeObra getManoDeObra() {
		return this.manoDeObra;
	}

	public void setManoDeObra(ManoDeObra manoDeObra) {
		this.manoDeObra = manoDeObra;
	}

	public Prenda getPrenda() {
		return this.prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

}