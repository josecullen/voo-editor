package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
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

	@TableGenerator(name = "prendaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaGen")
	@Id @Column(name = "PRENDA_ID")
	int prendaId;
	
	
	public int getPrendaId() {
		return prendaId;
	}

	public void setPrendaId(int prendaId) {
		this.prendaId = prendaId;
	}

	@Column(length=2000000000)
	private String nombre;

	//bi-directional many-to-one association to TipoPrenda
	@ManyToOne
	@JoinColumn(name="TIPO_PRENDA_ID", referencedColumnName="TIPO_PRENDA_ID")
	private TipoPrenda tipoPrenda;

	//bi-directional many-to-one association to PrendaMaquina
	@OneToMany(mappedBy="prenda", fetch=FetchType.EAGER)
	private List<PrendaMaquina> prendaMaquinas = new ArrayList<PrendaMaquina>();

	//bi-directional many-to-one association to PrendaManoDeObra
	@OneToMany(mappedBy="prenda", fetch=FetchType.EAGER)
	private List<PrendaManoDeObra> prendaManoDeObras = new ArrayList<PrendaManoDeObra>();

	//bi-directional many-to-one association to PrendaTela
	@OneToMany(mappedBy="prenda", fetch=FetchType.EAGER)
	private List<PrendaTela> prendaTelas;

	public Prenda() {
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

	public List<PrendaMaquina> getPrendaMaquinas() {
		return this.prendaMaquinas;
	}

	public void setPrendaMaquinas(List<PrendaMaquina> prendaMaquinas) {
		this.prendaMaquinas = prendaMaquinas;
	}

	public PrendaMaquina addPrendaMaquina(PrendaMaquina prendaMaquina) {
		getPrendaMaquinas().add(prendaMaquina);
		prendaMaquina.setPrenda(this);

		return prendaMaquina;
	}

	public PrendaMaquina removePrendaMaquina(PrendaMaquina prendaMaquina) {
		getPrendaMaquinas().remove(prendaMaquina);
		prendaMaquina.setPrenda(null);

		return prendaMaquina;
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

	public List<PrendaTela> getPrendaTelas() {
		return this.prendaTelas;
	}

	public void setPrendaTelas(List<PrendaTela> prendaTelas) {
		this.prendaTelas = prendaTelas;
	}

	public PrendaTela addPrendaTela(PrendaTela prendaTela) {
		getPrendaTelas().add(prendaTela);
		prendaTela.setPrenda(this);

		return prendaTela;
	}

	public PrendaTela removePrendaTela(PrendaTela prendaTela) {
		getPrendaTelas().remove(prendaTela);
		prendaTela.setPrenda(null);

		return prendaTela;
	}

	
	@Override
	public String toString() {
		return getNombre();
	}
	
}