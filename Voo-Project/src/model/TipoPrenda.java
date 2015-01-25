package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.sun.javafx.collections.ObservableListWrapper;

/**
 * The persistent class for the TIPO_PRENDA database table.
 * 
 */
@Entity
@Table(name = "TIPO_PRENDA")
@NamedQueries({
		@NamedQuery(name = "TipoPrenda.findAll", query = "SELECT t FROM TipoPrenda t"),
		@NamedQuery(name = "TipoPrenda.findByName", query = "SELECT p FROM TipoPrenda p WHERE p.nombre = :nombre") })
public class TipoPrenda implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private StringProperty nombre;
	private ListProperty<Talle> talles = 
			new SimpleListProperty<Talle>(this, "talles", new ObservableListWrapper<Talle>(new ArrayList<Talle>()));
	private ListProperty<Prenda> prendas = 
			new SimpleListProperty<Prenda>(this, "prendas", new ObservableListWrapper<Prenda>(new ArrayList<Prenda>()));

	@TableGenerator(name = "tipoPrendaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TIPO_PRENDA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tipoPrendaGen")
	@Column(name = "TIPO_PRENDA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id) 	{	this.id = id;	}	

	@Column(name = "NOMBRE")
	public String getNombre() 				{	return this.nombre.get();	}
	public void setNombre(String nombre)	{	this.nombre.set(nombre);	}
	public StringProperty nombreProperty()	{	return nombre;}
	
	@OneToMany(mappedBy = "tipoPrenda")
	public List<Talle> getTalles() {
		return talles;
	}
	public void setTalles(List<Talle> talles) {
		this.talles.set(new ObservableListWrapper<Talle>(talles));
	}
	public Talle addTalle(Talle talle) {
		talles.add(talle);
		talle.setTipoPrenda(this);
		return talle;
	}
	public Talle removeTalle(Talle talle) {
		getTalles().remove(talle);
		talle.setTipoPrenda(null);
		return talle;
	}
	public ListProperty<Talle> tallesProperty(){
		return talles;
	}

	
	@OneToMany(mappedBy = "tipoPrenda")
	public List<Prenda> getPrendas() {
		return prendas;
	}
	public void setPrendas(List<Prenda> prendas) {
		this.prendas.set(new ObservableListWrapper<Prenda>(prendas));
	}
	public Prenda addPrenda(Prenda prenda) {
		prendas.add(prenda);
		prenda.setTipoPrenda(this);
		return prenda;
	}
	public Prenda removePrenda(Prenda prenda) {
		prendas.remove(prenda);
		prenda.setTipoPrenda(null);
		return prenda;
	}
	public ListProperty<Prenda> prendaProperty(){
		return prendas;
	}


}