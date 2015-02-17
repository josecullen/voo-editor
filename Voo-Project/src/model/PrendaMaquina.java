package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRENDA_MAQUINA database table.
 * 
 */
@Entity
@Table(name="PRENDA_MAQUINA")
@NamedQuery(name="PrendaMaquina.findAll", query="SELECT p FROM PrendaMaquina p")
public class PrendaMaquina implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	
	@Column(name="CANTIDAD_MINUTOS")
	private int cantidadMinutos;

	@TableGenerator(name = "prendaMaquinaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA_MAQUINA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaMaquinaGen")
	@Id @Column(name = "PRENDA_MAQUINA_ID", nullable=false)
	private int prendaMaquinaId;

	//bi-directional many-to-one association to Prenda
	@ManyToOne
	@JoinColumn(name="PRENDA_ID", referencedColumnName="PRENDA_ID")
	private Prenda prenda;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@JoinColumn(name="MAQUINA_ID", referencedColumnName="MAQUINA_ID")
	private Maquina maquina;

	public PrendaMaquina() {
	}

	public int getCantidadMinutos() {
		return this.cantidadMinutos;
	}

	public void setCantidadMinutos(int cantidadMinutos) {
		this.cantidadMinutos = cantidadMinutos;
	}

	public int getPrendaMaquinaId() {
		return this.prendaMaquinaId;
	}

	public void setPrendaMaquinaId(int prendaMaquinaId) {
		this.prendaMaquinaId = prendaMaquinaId;
	}

	public Prenda getPrenda() {
		return this.prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

}