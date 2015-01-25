package model;

import java.io.Serializable;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

import com.sun.javafx.collections.ObservableListWrapper;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the TIPO_DE_VENTAS database table.
 * 
 */
@Entity
@Table(name="TIPO_DE_VENTAS")
@NamedQuery(name="TipoDeVenta.findAll", query="SELECT t FROM TipoDeVenta t")
public class TipoDeVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private StringProperty tipoDeVenta = new SimpleStringProperty(this, "tipoDeVenta", "");
	private ListProperty<Venta> ventas = new SimpleListProperty<Venta>
		(this, "ventas", new ObservableListWrapper<Venta>(new ArrayList<Venta>()));;
	
	@TableGenerator(name = "tipoDeVentaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TIPO_DE_VENTAS", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tipoDeVentaGen")
	@Column(name = "TIPO_DE_VENTA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id) 	{	this.id = id;	}	

	@Column(name="TIPO_DE_VENTA")
	public String getTipoDeVenta() 					{	return this.tipoDeVenta.get();		}
	public void setTipoDeVenta(String tipoDeVenta) 	{	this.tipoDeVenta.set(tipoDeVenta);	}
	public StringProperty tipoDeVentaProperty()		{	return tipoDeVenta;					}
	
	@OneToMany(mappedBy="tipoDeVenta")
	public List<Venta> getVentas() 			    {	return this.ventas;	}
	public void setVentas(List<Venta> ventas)   {	this.ventas.set(new ObservableListWrapper<Venta>(ventas));	}
	public ListProperty<Venta> ventasProperty()	{	return ventas;	}
	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setTipoDeVenta(this);
		return venta;
	}
	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setTipoDeVenta(null);
		return venta;
	}	
	@Override
	public String toString() {
		return tipoDeVenta.get();
	}
		
	

}