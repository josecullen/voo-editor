package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TALLE database table.
 * 
 */
@Entity
@Table(name="TALLE")
@NamedQuery(name="Talle.findAll", query="SELECT t FROM Talle t")
public class Talle implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "talleGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TALLE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "talleGen")
	@Id @Column(name="TALLE_ID", nullable=false)
	private int talleId;
	
	
	@Column(length=2000000000)
	private String genero;

	@Column(name="METROS_TELA")
	private double metrosTela;

	@Column(length=2000000000)
	private String nombre;

	

	//bi-directional many-to-one association to TipoPrenda
	@ManyToOne
	@JoinColumn(name="TIPO_PRENDA_ID", referencedColumnName="TIPO_PRENDA_ID")
	private TipoPrenda tipoPrenda;

	public Talle() {
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getMetrosTela() {
		return this.metrosTela;
	}

	public void setMetrosTela(double metrosTela) {
		this.metrosTela = metrosTela;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTalleId() {
		return this.talleId;
	}

	public void setTalleId(int talleId) {
		this.talleId = talleId;
	}

	public TipoPrenda getTipoPrenda() {
		return this.tipoPrenda;
	}

	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}

}