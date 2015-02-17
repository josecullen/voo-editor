package model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

	@TableGenerator(name = "prendaMDOGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA_MDO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaMDOGen")
	@Id @Column(name = "PRENDA_MDO_ID")
	int prendaMdoId;
	


	@Column(name="CANTIDAD_HORAS")
	private double cantidadHoras;



	//bi-directional many-to-one association to Prenda
	@ManyToOne
	@JoinColumn(name="PRENDA_ID", referencedColumnName="PRENDA_ID")
	private Prenda prenda;

	//bi-directional many-to-one association to ManoDeObra
	@ManyToOne
	@JoinColumn(name="MANO_DE_OBRA_ID", referencedColumnName="MANO_DE_OBRA_ID")
	private ManoDeObra manoDeObra;

	public PrendaManoDeObra() {
	}

	public double getCantidadHoras() {
		return this.cantidadHoras;
	}

	public void setCantidadHoras(double cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public int getPrendaMdoId() {
		return this.prendaMdoId;
	}

	public void setPrendaMdoId(int prendaMdoId) {
		this.prendaMdoId = prendaMdoId;
	}

	public Prenda getPrenda() {
		return this.prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public ManoDeObra getManoDeObra() {
		return this.manoDeObra;
	}

	public void setManoDeObra(ManoDeObra manoDeObra) {
		this.manoDeObra = manoDeObra;
	}
	
	
///////////////		atributos que no son parte de la entidad 	/////////////////
	public String getNombre(){
		return getManoDeObra().getNombre();
	}
		
	public double getCostoHora(){		
		return getManoDeObra().getCostoHora();				
	}
	
	

}