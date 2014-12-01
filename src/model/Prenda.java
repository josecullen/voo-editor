package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRENDAS database table.
 * 
 */
@Entity
@Table(name="PRENDAS")
@NamedQuery(name="Prenda.findAll", query="SELECT p FROM Prenda p")
public class Prenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRENDAS_PRENDAID_GENERATOR", sequenceName="SEQ_PRENDA_ID", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRENDAS_PRENDAID_GENERATOR")
	@Column(name="PRENDA_ID")
	private long prendaId;

	private String nombre;

	//bi-directional many-to-one association to TipoPrenda
	@ManyToOne
	@JoinColumn(name="TIPO_PRENDA_ID")
	private TipoPrenda tipoPrenda;

	//bi-directional many-to-one association to PrendaManoDeObra
	@OneToMany(mappedBy="prenda", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<PrendaManoDeObra> prendaManoDeObras;

	//bi-directional many-to-one association to PrendaMaterial
	@OneToMany(mappedBy="prenda", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<PrendaMaterial> prendaMaterials;

	public Prenda() {
	}

	public long getPrendaId() {
		return this.prendaId;
	}

	public void setPrendaId(long prendaId) {
		this.prendaId = prendaId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}

	public List<PrendaManoDeObra> getPrendaManoDeObras() {
		return this.prendaManoDeObras;
	}

	public void setPrendaManoDeObras(List<PrendaManoDeObra> prendaManoDeObras) {
		this.prendaManoDeObras = prendaManoDeObras;
	}

	public PrendaManoDeObra addPrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		getPrendaManoDeObras().add(prendaManoDeObra);
		prendaManoDeObra.setPrenda(this);

		return prendaManoDeObra;
	}

	public PrendaManoDeObra removePrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		getPrendaManoDeObras().remove(prendaManoDeObra);
		prendaManoDeObra.setPrenda(null);

		return prendaManoDeObra;
	}

	public List<PrendaMaterial> getPrendaMaterials() {
		return this.prendaMaterials;
	}

	public void setPrendaMaterials(List<PrendaMaterial> prendaMaterials) {
		this.prendaMaterials = prendaMaterials;
	}

	public PrendaMaterial addPrendaMaterial(PrendaMaterial prendaMaterial) {
		getPrendaMaterials().add(prendaMaterial);
		prendaMaterial.setPrenda(this);

		return prendaMaterial;
	}

	public PrendaMaterial removePrendaMaterial(PrendaMaterial prendaMaterial) {
		getPrendaMaterials().remove(prendaMaterial);
		prendaMaterial.setPrenda(null);

		return prendaMaterial;
	}
	
	@Override
	public String toString(){
		return nombre;
	}

}