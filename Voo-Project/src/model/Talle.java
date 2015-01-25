package model;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the TALLE database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Talle.findAll", query = "SELECT t FROM Talle t"),
		@NamedQuery(name = "Talle.findByTipoPrenda", query = "SELECT t FROM Talle t WHERE t.tipoPrenda = :tipoPrenda") })
public class Talle implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private StringProperty nombre = new SimpleStringProperty(this, "nombre", "");
	private StringProperty genero = new SimpleStringProperty(this, "genero", "");
	private DoubleProperty metrosTela = new SimpleDoubleProperty(this,	"metrosTela", 0.0d);
	private TipoPrenda tipoPrenda;

	@TableGenerator(name = "talleGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TALLE", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "talleGen")
	@Column(name = "TALLE_ID")
	public int getId() 			{	return id;		}
	public void setId(int id) 	{	this.id = id;	}
	
	@Column(name = "METROS_TELA")
	public double getMetrosTela() 					{	return metrosTela.get();			}
	public void setMetrosTela(double metrosTela)	{	this.metrosTela.set(metrosTela);	}
	public DoubleProperty metrosTelaProperty()		{	return metrosTela;					}

	@ManyToOne
	@JoinColumn(name = "TIPO_PRENDA_ID", referencedColumnName = "TIPO_PRENDA_ID")
	public TipoPrenda getTipoPrenda() 					{	return this.tipoPrenda;			}
	public void setTipoPrenda(TipoPrenda tipoPrenda)	{	this.tipoPrenda = tipoPrenda;	}
	
	@Column(name = "genero")
	public String getGenero() 			{	return genero.get();		}
	public void setGenero(String genero){	this.genero.set(genero);	}

	@Column(name = "nombre")
	public String getNombre() 				{	return this.nombreProperty().get();	}
	public void setNombre(String nombre)	{	this.nombreProperty().set(nombre);	}
	public StringProperty nombreProperty() 	{	return nombre;						}
}