package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_PRENDA database table.
 * 
 */
@Entity
@Table(name="TIPO_PRENDA")
@NamedQuery(name="TipoPrenda.findAll", query="SELECT t FROM TipoPrenda t")
public class TipoPrenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_PRENDA_TIPOPRENDAID_GENERATOR", sequenceName="SEQ_TIPO_PRENDA_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PRENDA_TIPOPRENDAID_GENERATOR")
	@Column(name="TIPO_PRENDA_ID")
	private long tipoPrendaId;

	private String nombre;

	//bi-directional many-to-one association to Prenda
	@OneToMany(mappedBy="tipoPrenda")
	private List<Prenda> prendas;

	//bi-directional many-to-one association to Talle
	@OneToMany(mappedBy="tipoPrenda")
	private List<Talle> talles;

	public TipoPrenda() {
	}

	public long getTipoPrendaId() {
		return this.tipoPrendaId;
	}

	public void setTipoPrendaId(long tipoPrendaId) {
		this.tipoPrendaId = tipoPrendaId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Prenda> getPrendas() {
		return this.prendas;
	}

	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	public Prenda addPrenda(Prenda prenda) {
		getPrendas().add(prenda);
		prenda.setTipoPrenda(this);

		return prenda;
	}

	public Prenda removePrenda(Prenda prenda) {
		getPrendas().remove(prenda);
		prenda.setTipoPrenda(null);

		return prenda;
	}

	public List<Talle> getTalles() {
		return this.talles;
	}

	public void setTalles(List<Talle> talles) {
		this.talles = talles;
	}

	public Talle addTalle(Talle talle) {
		getTalles().add(talle);
		talle.setTipoPrenda(this);

		return talle;
	}

	public Talle removeTalle(Talle talle) {
		getTalles().remove(talle);
		talle.setTipoPrenda(null);

		return talle;
	}

	@Override
	public String toString(){
		return nombre;
	}
	
}