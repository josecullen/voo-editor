package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRENDA_MATERIAL database table.
 * 
 */
@Entity
@Table(name="PRENDA_MATERIAL")
@NamedQuery(name="PrendaMaterial.findAll", query="SELECT p FROM PrendaMaterial p")
public class PrendaMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRENDA_MATERIAL_PRENDAMATERIALID_GENERATOR", sequenceName="SEQ_PRENDA_MATERIAL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRENDA_MATERIAL_PRENDAMATERIALID_GENERATOR")
	@Column(name="PRENDA_MATERIAL_ID")
	private long prendaMaterialId;

	@Column(name="PORCENTAJE_MATERIAL")
	private BigDecimal porcentajeMaterial;

	//bi-directional many-to-one association to Material
	@ManyToOne
	@JoinColumn(name="MATERIAL_ID")
	private Material material;

	//bi-directional many-to-one association to Prenda
	@ManyToOne
	@JoinColumn(name="PRENDA_ID")
	private Prenda prenda;

	public PrendaMaterial() {
	}

	public long getPrendaMaterialId() {
		return this.prendaMaterialId;
	}

	public void setPrendaMaterialId(long prendaMaterialId) {
		this.prendaMaterialId = prendaMaterialId;
	}

	public BigDecimal getPorcentajeMaterial() {
		return this.porcentajeMaterial;
	}

	public void setPorcentajeMaterial(BigDecimal porcentajeMaterial) {
		this.porcentajeMaterial = porcentajeMaterial;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Prenda getPrenda() {
		return this.prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

}