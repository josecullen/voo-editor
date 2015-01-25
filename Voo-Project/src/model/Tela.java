package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.sun.javafx.collections.ObservableListWrapper;

@Entity
@Table(name = "TELA")
@NamedQueries({
		@NamedQuery(name = "Tela.findAll", query = "SELECT t FROM Tela t"),
		@NamedQuery(name = "Tela.findByType", query = "SELECT t FROM Tela t WHERE t.tipo = :type"),
		@NamedQuery(name = "Tela.findTypes", query = "SELECT DISTINCT t.tipo FROM Tela t"),
		@NamedQuery(name = "Tela.findModelsByType", query = "SELECT t.modelo FROM Tela t WHERE t.tipo  = :tipo"),
		@NamedQuery(name = "Tela.findByTypeAndModel", query = "SELECT t FROM Tela t WHERE t.tipo = :tipo AND t.modelo = :modelo")})
public class Tela {
	
	int id;
	private StringProperty tipo = new SimpleStringProperty(this, "tipo", "");
	private DoubleProperty costoMetro = new SimpleDoubleProperty(this, "costoMetro", 0.0d);
	private StringProperty modelo = new SimpleStringProperty(this, "modelo", "");
	private ListProperty<PrendaTela> prendaTelas = new SimpleListProperty<PrendaTela>
		(this, "prendaTelas", new ObservableListWrapper<PrendaTela>(new ArrayList<PrendaTela>()));
	
	@TableGenerator(name = "telaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TELA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "telaGen")
	@Column(name = "TELA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id)	{	this.id = id;	}
	
	@Column(name="TIPO")
	public String getTipo()					{	return tipo.get();		}
	public void setTipo(String tipo)		{	this.tipo.set(tipo);	}
	public StringProperty tipoProperty()	{	return tipo;			}
		
	@Column(name="MODELO")
	public String getModelo()				{	return modelo.get();	}
	public void setModelo(String modelo)	{	this.modelo.set(modelo);}
	public StringProperty modeloProperty()	{	return modelo;			}
		
	@Column(name="COSTO_METRO")
	public double getCostoMetro()				{	return costoMetro.get();			}
	public void setCostoMetro(double costoMetro){	this.costoMetro.set(costoMetro);	}
	public DoubleProperty costoMetroProperty()	{	return costoMetro;					}	
	
	@OneToMany(mappedBy = "tela", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public List<PrendaTela> getPrendaTelas() {
		return this.prendaTelasProperty().get();
	}
	public void setPrendaTelas(List<PrendaTela> prendaTelas) {
		this.prendaTelasProperty().set(new ObservableListWrapper<PrendaTela>(prendaTelas));
	}
	public PrendaTela addPrendaTela(PrendaTela prendaTela){
		prendaTelas.add(prendaTela);
		prendaTela.setTela(this);
		return prendaTela;
	}
	public PrendaTela removePrendaTela(PrendaTela prendaTela){
		prendaTelas.remove(prendaTela);
		prendaTela.setPrenda(null);
		return prendaTela;
	}
	public ListProperty<PrendaTela> prendaTelasProperty() {
		return this.prendaTelas;
	}
	
	
}
