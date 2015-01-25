package model;

import java.io.Serializable;

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


/**
 * The persistent class for the VENTA_PRENDA database table.
 * 
 */
@Entity
@Table(name="VENTA_PRENDA")
@NamedQueries({
	@NamedQuery(name="VentaPrenda.findAll", query="SELECT v FROM VentaPrenda v"),
	@NamedQuery(name= "VentaPrenda.findByJoins", query = "SELECT v FROM VentaPrenda v WHERE v.prenda = :prenda AND v.venta = :venta")
})

public class VentaPrenda implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private IntegerProperty beneficio = new SimpleIntegerProperty(this, "beneficio", 0);
	private IntegerProperty cantidad = new SimpleIntegerProperty(this, "cantidad", 0);	
	private Prenda prenda;
	private Venta venta;

	@TableGenerator(name = "ventaPrendaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "VENTA_PRENDA", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ventaPrendaGen")
	@Column(name = "VENTA_PRENDA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id)	{	this.id = id;	}
		
	@Column(name = "BENEFICIO")
	public int getBeneficio() 					{	return beneficioProperty().get();	}
	public void setBeneficio(int beneficio) 	{	beneficioProperty().set(beneficio);	}
	public IntegerProperty beneficioProperty()	{	return beneficio;					}
	
	@Column(name = "CANTIDAD")
	public int getCantidad() 					{	return cantidadProperty().get();		}
	public void setCantidad(int cantidad) 		{	this.cantidadProperty().set(cantidad);	}
	public IntegerProperty cantidadProperty()	{	return cantidad;						}
	
	@ManyToOne
	@JoinColumn(name = "PRENDA_ID", referencedColumnName = "PRENDA_ID")
	public Prenda getPrenda() 				{	return prenda;			}
	public void setPrenda(Prenda prenda) 	{	this.prenda = prenda;	}
	
	@ManyToOne
	@JoinColumn(name = "VENTA_ID", referencedColumnName = "VENTA_ID")
	public Venta getVenta() 			{	return venta;		}
	public void setVenta(Venta venta) 	{	this.venta = venta;	}

}