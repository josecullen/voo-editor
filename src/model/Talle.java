package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TALLES database table.
 * 
 */
@Entity
@Table(name="TALLES")
@NamedQuery(name="Talle.findAll", query="SELECT t FROM Talle t")
public class Talle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TALLES_TALLEID_GENERATOR", sequenceName="SEQ_TALLE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TALLES_TALLEID_GENERATOR")
	@Column(name="TALLE_ID")
	private long talleId;

	@Column(name="CANTIDAD_MATERIAL_METROS")
	private double cantidadMaterialMetros;

	private String talle;

	//bi-directional many-to-one association to TipoPrenda
	@ManyToOne
	@JoinColumn(name="TIPO_PRENDA_ID")
	private TipoPrenda tipoPrenda;

	public Talle() {
	}

	public long getTalleId() {
		return this.talleId;
	}

	public void setTalleId(long talleId) {
		this.talleId = talleId;
	}

	public double getCantidadMaterialMetros() {
		return this.cantidadMaterialMetros;
	}

	public void setCantidadMaterialMetros(double cantidadMaterialMetros) {
		this.cantidadMaterialMetros = cantidadMaterialMetros;
	}

	public String getTalle() {
		return this.talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	
	public String toString(){
		return talle;
	}

}