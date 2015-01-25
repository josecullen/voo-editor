package model;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the PRENDA_MANO_DE_OBRA database table.
 * 
 */
@Entity
@Table(name = "PRENDA_MANO_DE_OBRA")
@NamedQueries({
		@NamedQuery(name = "PrendaManoDeObra.findAll", query = "SELECT p FROM PrendaManoDeObra p"),
		@NamedQuery(name = "PrendaManoDeObra.findByJoins", query = "SELECT p FROM PrendaManoDeObra p WHERE p.prenda = :prenda AND p.manoDeObra = :manoDeObra")
})
public class PrendaManoDeObra implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private DoubleProperty 	cantidadHoras = new SimpleDoubleProperty(this, "cantidadHoras", 0.0d);
	private ManoDeObra 		manoDeObra;
	private Prenda 			prenda;

	@TableGenerator(name = "pmdoGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA_MDO", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pmdoGen")
	@Column(name = "PRENDA_MDO_ID")
	public int getId() 			{	return id;		}
	public void setId(int id) 	{	this.id = id;	}
	
	@Column(name = "CANTIDAD_HORAS")
	public double getCantidadHoras() 					{	return cantidadHoras.get();				}
	public void setCantidadHoras(double cantidadHoras) 	{	this.cantidadHoras.set(cantidadHoras);	}
	public DoubleProperty cantidadHorasProperty()		{	return cantidadHoras;					}
	
	@ManyToOne	@JoinColumn(name = "MANO_DE_OBRA_ID", referencedColumnName = "MANO_DE_OBRA_ID")
	public ManoDeObra getManoDeObra() 					{	return this.manoDeObra;			}
	public void setManoDeObra(ManoDeObra manoDeObra) 	{	this.manoDeObra = manoDeObra;	}

	@ManyToOne(fetch = FetchType.EAGER)	@JoinColumn(name = "PRENDA_ID", referencedColumnName = "PRENDA_ID")
	public Prenda getPrenda() 							{	return this.prenda;		}
	public void setPrenda(Prenda prenda) 				{	this.prenda = prenda;	}
	

}