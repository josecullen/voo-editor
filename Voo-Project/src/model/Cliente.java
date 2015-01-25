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
 * The persistent class for the CLIENTES database table.
 * 
 */
@Entity
@Table(name="CLIENTES")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private StringProperty cliente = new SimpleStringProperty(this, "cliente", "");
	private ListProperty<Venta> ventas = new SimpleListProperty<Venta>(this, "ventas", new ObservableListWrapper<Venta>(new ArrayList<Venta>()));
	
	@TableGenerator(name = "clienteGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "CLIENTE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "clienteGen")
	@Column(name = "CLIENTE_ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	@Column(name = "CLIENTE")
	public String getCliente() {				return cliente.get();		}
	public void setCliente(String cliente) {	this.cliente.set(cliente);	}
	public StringProperty clienteProperty(){	return cliente;				}
	
	
	@OneToMany(mappedBy="cliente")
	public List<Venta> getVentas() {
		return this.ventas;
	}
	public void setVentas(List<Venta> ventas) {
		this.ventas.set(new ObservableListWrapper<Venta>(ventas));
	}
	public Venta addVenta(Venta venta) {
		ventas.add(venta);
		venta.setCliente(this);
		return venta;
	}
	public Venta removeVenta(Venta venta) {
		ventas.remove(venta);
		venta.setCliente(null);
		return venta;
	}
	public ListProperty<Venta> ventasProperty(){
		return ventas;
	}
	
	
	@Override
	public String toString() {
		return cliente.get();
	}
	

}