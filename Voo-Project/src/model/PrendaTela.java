package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PRENDA_TELA")
@NamedQueries({
	@NamedQuery(name = "PrendaTela.findAll", query = "SELECT pt FROM PrendaTela pt"),
	@NamedQuery(name = "PrendaTela.findByJoins", query = "SELECT pt FROM PrendaTela pt WHERE pt.prenda = :prenda AND pt.tela = :tela"),
	@NamedQuery(name = "PrendaTela.findByPrenda", query = "SELECT pt FROM PrendaTela pt WHERE pt.prenda = :prenda"),
	@NamedQuery(name = "PrendaTela.findByTela", query = "SELECT pt FROM PrendaTela pt WHERE pt.tela = :tela")
})
public class PrendaTela {

	int id;
	private IntegerProperty porcentaje = new SimpleIntegerProperty(this, "porcentaje", 0);
	private Prenda prenda;
	private Tela tela;

	@TableGenerator(name = "prendaTelaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA_TELA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaTelaGen")
	@Column(name = "PRENDA_TELA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id)	{	this.id = id;	}
	
	@Column(name="PORCENTAJE")
	public final int getPorcentaje() 						{	return this.porcentajeProperty().get();		}
	public final void setPorcentaje(final int porcentaje) 	{	this.porcentajeProperty().set(porcentaje);	}
	public final IntegerProperty porcentajeProperty() 		{	return this.porcentaje;						}	
	
	@ManyToOne
	@JoinColumn(name = "PRENDA_ID", referencedColumnName = "PRENDA_ID")
	public Prenda getPrenda()				{ return prenda;		}
	public void setPrenda(Prenda prenda) 	{ this.prenda = prenda; }	
	
	@ManyToOne
	@JoinColumn(name = "TELA_ID", referencedColumnName = "TELA_ID")
	public Tela getTela()		   { return tela;	  }
	public void setTela(Tela tela) { this.tela = tela;}
	
	
	
}
