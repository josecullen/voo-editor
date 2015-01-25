package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.javafx.collections.ObservableListWrapper;


/**
 * The persistent class for the VENTAS database table.
 * 
 */
@Entity
@Table(name="VENTAS")
@NamedQueries({
	@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v"),
	@NamedQuery(name="Venta.findById", query= "SELECT v FROM Venta v WHERE v.id = :ventaId")
})

public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private GregorianCalendar fechaVenta;
	private Cliente cliente;	
	private TipoDeVenta tipoDeVenta;
	private ListProperty<VentaPrenda> ventaPrendas = new SimpleListProperty<VentaPrenda>
		(this, "ventaPrendas", new ObservableListWrapper<VentaPrenda>(new ArrayList<VentaPrenda>()));

	@TableGenerator(name = "ventaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "VENTA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ventaGen")
	@Column(name = "VENTA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id) 	{	this.id = id;	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_VENTA")
	public GregorianCalendar getFechaVenta() 				{	return this.fechaVenta;			}
	public void setFechaVenta(GregorianCalendar fechaVenta) {	this.fechaVenta = fechaVenta;	}

	@ManyToOne
	@JoinColumn(name="CLIENTE_ID", referencedColumnName="CLIENTE_ID")
	public Cliente getCliente() 			{	return this.cliente;	}
	public void setCliente(Cliente cliente)	{	this.cliente = cliente;	}
	
	@ManyToOne
	@JoinColumn(name="TIPO_DE_VENTA_ID", referencedColumnName="TIPO_DE_VENTA_ID")
	public TipoDeVenta getTipoDeVenta() 				{	return this.tipoDeVenta;		}
	public void setTipoDeVenta(TipoDeVenta tipoDeVenta)	{	this.tipoDeVenta = tipoDeVenta;	}	
	
	@OneToMany(mappedBy = "venta", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public List<VentaPrenda> getVentaPrendas() {
		return ventaPrendas.get();
	}
	public void setVentaPrendas(List<VentaPrenda> ventaPrendas) {
		this.ventaPrendas.set(new ObservableListWrapper<VentaPrenda>(ventaPrendas));
	}
	public VentaPrenda addVentaPrenda(VentaPrenda ventaPrenda) {
		ventaPrendas.add(ventaPrenda);
		ventaPrenda.setVenta(this);		
		return ventaPrenda;
	}
	public VentaPrenda removeVentaPrenda(VentaPrenda ventaPrenda) {
		ventaPrendas.remove(ventaPrenda);
		ventaPrenda.setVenta(null);
		return ventaPrenda;
	}
	public ListProperty<VentaPrenda> ventaPrendasProperty(){
		return ventaPrendas;		
	}
	
	
	
	

}