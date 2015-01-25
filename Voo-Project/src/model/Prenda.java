package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

import com.sun.javafx.collections.ObservableListWrapper;

/**
 * The persistent class for the PRENDAS database table.
 * 
 */
@Entity
@Table(name = "PRENDAS")
@NamedQueries({
		@NamedQuery(name = "Prenda.findAll", query = "SELECT p FROM Prenda p"),
		@NamedQuery(name = "Prenda.findByName", query = "SELECT p FROM Prenda p WHERE p.nombre = :nombre") 
})
public class Prenda implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	private TipoPrenda tipoPrenda;
	private StringProperty nombre = new SimpleStringProperty(this, "nombre", "");
	private ListProperty<PrendaManoDeObra> prendaManoDeObras = new SimpleListProperty<PrendaManoDeObra>
		(this, "prendaManoDeObras", new ObservableListWrapper<PrendaManoDeObra>(new ArrayList<PrendaManoDeObra>()));
	private ListProperty<PrendaTela> prendaTelas = new SimpleListProperty<PrendaTela>
		(this, "prendaTelas", new ObservableListWrapper<PrendaTela>(new ArrayList<PrendaTela>()));
	private ListProperty<PrendaMaquina> prendaMaquinas = new SimpleListProperty<PrendaMaquina>
		(this, "prendaMaquinas", new ObservableListWrapper<PrendaMaquina>(new ArrayList<PrendaMaquina>()));

	@TableGenerator(name = "prendaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "PRENDA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "prendaGen")
	@Column(name = "PRENDA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id)	{	this.id = id;	}

	
	@ManyToOne
	@JoinColumn(name = "TIPO_PRENDA_ID", referencedColumnName = "TIPO_PRENDA_ID")
	public TipoPrenda getTipoPrenda() 					{	return this.tipoPrenda;			}
	public void setTipoPrenda(TipoPrenda tipoPrenda)	{	this.tipoPrenda = tipoPrenda;	}


	@Column(name = "NOMBRE")
	public String getNombre() 			{	return nombre.get();		}
	public void setNombre(String nombre){	this.nombre.set(nombre);	}

	
	@OneToMany(mappedBy = "prenda", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public List<PrendaManoDeObra> getPrendaManoDeObras() {
		return prendaManoDeObras.get();
	}
	public void setPrendaManoDeObras(List<PrendaManoDeObra> prendaManoDeObras) {
		this.prendaManoDeObras.set(new ObservableListWrapper<PrendaManoDeObra>(prendaManoDeObras));		
	}
	public PrendaManoDeObra addPrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		prendaManoDeObras.add(prendaManoDeObra);		
		prendaManoDeObra.setPrenda(this);
		return prendaManoDeObra;
	}
	public PrendaManoDeObra removePrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		prendaManoDeObras.remove(prendaManoDeObra);
		prendaManoDeObra.setManoDeObra(null);
		return prendaManoDeObra;
	}
	public ListProperty<PrendaManoDeObra> prendaManoDeObrasProperty(){
		return prendaManoDeObras;
	}
	
	
	@OneToMany(mappedBy = "prenda", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public final List<PrendaTela> getPrendaTelas() {
		return this.prendaTelasProperty().get();
	}
	public final void setPrendaTelas(final List<PrendaTela> prendaTelas) {
		this.prendaTelasProperty().set(new ObservableListWrapper<PrendaTela>(prendaTelas));
	}
	public final PrendaTela addPrendaTela(PrendaTela prendaTela){
		prendaTelas.add(prendaTela);
		prendaTela.setPrenda(this);
		return prendaTela;
	}
	public final PrendaTela removePrendaTela(PrendaTela prendaTela){
		prendaTelas.remove(prendaTela);
		prendaTela.setPrenda(null);
		return prendaTela;
	}
	public final ListProperty<PrendaTela> prendaTelasProperty() {
		return prendaTelas;
	}	
	
	
	@OneToMany(mappedBy = "prenda", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public final List<PrendaMaquina> getPrendaMaquinas() {
		return this.prendaMaquinasProperty().get();
	}
	public final void setPrendaMaquinas(final List<PrendaMaquina> prendaMaquinas) {
		this.prendaMaquinasProperty().set(new ObservableListWrapper<PrendaMaquina>(prendaMaquinas));
	}
	public final PrendaMaquina addPrendaMaquina(PrendaMaquina prendaMaquina){
		prendaMaquinas.add(prendaMaquina);
		prendaMaquina.setPrenda(this);
		return prendaMaquina;
	}
	public final PrendaMaquina removePrendaMaquina(PrendaMaquina prendaMaquina){
		prendaMaquinas.remove(prendaMaquina);
		prendaMaquina.setPrenda(null);
		return prendaMaquina;
	}	
	public final ListProperty<PrendaMaquina> prendaMaquinasProperty() {
		return this.prendaMaquinas;
	}
		
	@Override
	public String toString() {
		return nombre.get();
	}

	

	
}