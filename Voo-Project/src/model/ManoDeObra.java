package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
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
 * The persistent class for the MANO_DE_OBRA database table.
 * 
 */
@Entity
@Table(name = "MANO_DE_OBRA")
@NamedQueries({
		@NamedQuery(name = "ManoDeObra.findAll", query = "SELECT m FROM ManoDeObra m"),
		@NamedQuery(name = "ManoDeObra.findByName", query = "SELECT m FROM ManoDeObra m WHERE m.nombre = :nombre") 
})
public class ManoDeObra implements Serializable {
	private static final long serialVersionUID = 1L;
	private DoubleProperty costoHora = new SimpleDoubleProperty(this,"costoHora", 0.0d);
	private StringProperty nombre = new SimpleStringProperty(this, "nombre", "");
	private ListProperty<PrendaManoDeObra> prendaManoDeObras = 
			new SimpleListProperty<PrendaManoDeObra>(this, "prendaManoDeObras", new ObservableListWrapper<PrendaManoDeObra>(new ArrayList<PrendaManoDeObra>()));
	int id;

	@TableGenerator(name = "mdoGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MANO_DE_OBRA", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "mdoGen")
	@Column(name = "MANO_DE_OBRA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id)	{	this.id = id;	}

	@Column(name = "COSTO_HORA")
	public double getCostoHora() 				{	return this.costoHora.get();	}
	public void setCostoHora(double costoHora) 	{	this.costoHora.set(costoHora);	}
	public DoubleProperty costoHoraProperty()  	{	return costoHora;				}
 
	@Column(name = "NOMBRE")
	public String getNombre() 				{	return this.nombre.get();	}
	public void setNombre(String nombre) 	{	this.nombre.set(nombre);	}
	public StringProperty nombreProperty() 	{	return nombre;				}

	@OneToMany(mappedBy = "manoDeObra")
	public List<PrendaManoDeObra> getPrendaManoDeObras() {
		return prendaManoDeObras;
	}
	public void setPrendaManoDeObras(List<PrendaManoDeObra> prendaManoDeObras) {
		this.prendaManoDeObras.set(new ObservableListWrapper<PrendaManoDeObra>(prendaManoDeObras));
	}
	public PrendaManoDeObra addPrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		prendaManoDeObras.add(prendaManoDeObra);
		prendaManoDeObra.setManoDeObra(this);
		return prendaManoDeObra;
	}
	public PrendaManoDeObra removePrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		prendaManoDeObras.remove(prendaManoDeObra);
		prendaManoDeObra.setManoDeObra(null);
		return prendaManoDeObra;
	}

}