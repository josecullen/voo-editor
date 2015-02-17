package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRENDA_TELA database table.
 * 
 */
@Entity
@Table(name="PRENDA_TELA")
@NamedQuery(name="PrendaTela.findAll", query="SELECT p FROM PrendaTela p")
public class PrendaTela implements Serializable {
	private static final long serialVersionUID = 1L;

	private int porcentaje;

	@TableGenerator(name = "prendaTelaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA_TELA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaTelaGen")
	@Id @Column(name="PRENDA_TELA_ID", nullable=false)
	private int prendaTelaId;

	//bi-directional many-to-one association to Prenda
	@ManyToOne
	@JoinColumn(name="PRENDA_ID", referencedColumnName="PRENDA_ID")
	private Prenda prenda;

	//bi-directional many-to-one association to Tela
	@ManyToOne
	@JoinColumn(name="TELA_ID", referencedColumnName="TELA_ID")
	private Tela tela;

	public PrendaTela() {
	}

	public int getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getPrendaTelaId() {
		return this.prendaTelaId;
	}

	public void setPrendaTelaId(int prendaTelaId) {
		this.prendaTelaId = prendaTelaId;
	}

	public Prenda getPrenda() {
		return this.prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public Tela getTela() {
		return this.tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
	}

}