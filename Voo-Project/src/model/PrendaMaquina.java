package model;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
 * The persistent class for the PRENDA_MAQUINA database table.
 * 
 */
@Entity
@Table(name = "PRENDA_MAQUINA")
@NamedQueries({
		@NamedQuery(name = "PrendaMaquina.findAll", query = "SELECT p FROM PrendaMaquina p"),
		@NamedQuery(name = "PrendaMaquina.findByJoins", query = "SELECT p FROM PrendaMaquina p WHERE p.prenda = :prenda AND p.maquina = :maquina")

})
public class PrendaMaquina implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private Maquina maquina;
	private Prenda prenda;
	private IntegerProperty cantidadMinutos = new SimpleIntegerProperty(this, "cantidadMinutos", 30);

	@TableGenerator(name = "prendaMaquinaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA_MAQUINA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaMaquinaGen")
	@Column(name = "PRENDA_MAQUINA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id)	{	this.id = id;	}

	@ManyToOne
	@JoinColumn(name = "MAQUINA_ID", referencedColumnName = "MAQUINA_ID")
	public Maquina getMaquina() 			{	return this.maquina;	}
	public void setMaquina(Maquina maquina)	{	this.maquina = maquina;	}

	@Column(name = "CANTIDAD_MINUTOS")
	public int getCantidadMinutos() 					{	return cantidadMinutos.get();				}
	public void setCantidadMinutos(int cantidadMinutos) {	this.cantidadMinutos.set(cantidadMinutos);	}
	public IntegerProperty cantidadMinutosProperty() 	{	return cantidadMinutos;						}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRENDA_ID", referencedColumnName = "PRENDA_ID")
	public Prenda getPrenda() 				{	return this.prenda;		}
	public void setPrenda(Prenda prenda) 	{	this.prenda = prenda;	}
	


	

}