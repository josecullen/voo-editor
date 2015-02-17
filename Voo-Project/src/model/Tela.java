package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TELA database table.
 * 
 */
@Entity
@Table(name="TELA")
@NamedQuery(name="Tela.findAll", query="SELECT t FROM Tela t")
public class Tela implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "telaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TELA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "telaGen")
	@Id @Column(name = "TELA_ID")
	int telaId;
	
	public int getTelaId() {
		return telaId;
	}

	public void setTelaId(int telaId) {
		this.telaId = telaId;
	}

	@Column(name="COSTO_METRO")
	private double costoMetro;

	@Column(length=2000000000)
	private String modelo;

	@Column(length=2000000000)
	private String tipo;

	//bi-directional many-to-one association to PrendaTela
	@OneToMany(mappedBy="tela", fetch=FetchType.EAGER)
	private List<PrendaTela> prendaTelas;

	public Tela() {
	}

	public double getCostoMetro() {
		return this.costoMetro;
	}

	public void setCostoMetro(double costoMetro) {
		this.costoMetro = costoMetro;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<PrendaTela> getPrendaTelas() {
		return this.prendaTelas;
	}

	public void setPrendaTelas(List<PrendaTela> prendaTelas) {
		this.prendaTelas = prendaTelas;
	}

	public PrendaTela addPrendaTela(PrendaTela prendaTela) {
		getPrendaTelas().add(prendaTela);
		prendaTela.setTela(this);

		return prendaTela;
	}

	public PrendaTela removePrendaTela(PrendaTela prendaTela) {
		getPrendaTelas().remove(prendaTela);
		prendaTela.setTela(null);

		return prendaTela;
	}

}