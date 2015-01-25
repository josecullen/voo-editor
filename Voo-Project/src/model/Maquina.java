package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.CascadeType;
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


@Entity
@Table(name = "MAQUINA")
@NamedQueries({
		@NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m"),
		@NamedQuery(name = "Maquina.findByName", query = "SELECT m FROM Maquina m WHERE m.nombre = :nombre"),
		@NamedQuery(name = "Maquina.findNames", query = "SELECT m.nombre FROM Maquina m")})
public class Maquina {
		
	int id;
	private StringProperty nombre = new SimpleStringProperty(this, "nombre", "");
	private IntegerProperty costo = new SimpleIntegerProperty(this, "costo", 1000);
	private DoubleProperty energia = new SimpleDoubleProperty(this, "energía", 1);
	private DoubleProperty indiceReparacion = new SimpleDoubleProperty(this, "indiceReparacion", 0.4d);
	private IntegerProperty añosAmortizacion = new SimpleIntegerProperty(this, "añosAmortizacion", 2);
	private IntegerProperty diasAmortizacion = new SimpleIntegerProperty(this, "diasAmortizacion", 240);
	private IntegerProperty horasAmortizacion = new SimpleIntegerProperty(this, "horasAmortizacion", 8);
	private ListProperty<PrendaMaquina> prendaMaquinas = new SimpleListProperty<PrendaMaquina>
		(this, "prendaMaquinas", new ObservableListWrapper<PrendaMaquina>(new ArrayList<PrendaMaquina>()));
	
	
	@TableGenerator(name = "maquinaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MAQUINA", allocationSize = 1)
	@Id	@GeneratedValue(strategy = GenerationType.TABLE, generator = "maquinaGen")
	@Column(name = "MAQUINA_ID")
	public int getId() 			{	return id;		}
	public void setId(int id) 	{	this.id = id;	}
	
	
	@Column(name="NOMBRE")
	public String getNombre()	 			{	return this.nombreProperty().get();	}
	public void setNombre(String nombre)	{	this.nombreProperty().set(nombre);	}
	public StringProperty nombreProperty() 	{	return this.nombre;					}	
	
	@Column(name = "COSTO")
	public int getCosto() 					{	return costoProperty().get();	}
	public void setCosto(int costo) 		{	costoProperty().set(costo);		}
	public IntegerProperty costoProperty()	{	return this.costo;				}	
	
	@Column(name = "ENERGIA")
	public double getEnergia() 				{	return energiaProperty().get();	}
	public void setEnergia(double energia)	{	energiaProperty().set(energia);	}
	public DoubleProperty energiaProperty() {	return energia;					}	
	
	@Column(name = "INDICE_REPARACION")
	public double getIndiceReparacion() 					  {	return indiceReparacionProperty().get();			}
	public void setIndiceReparacion( double indiceReparacion) {	indiceReparacionProperty().set(indiceReparacion);	}
	public DoubleProperty indiceReparacionProperty() 		  {	return indiceReparacion;							}
	
	
	@Column(name = "AÑOS_AMORTIZACION")
	public int getAñosAmortizacion() 						{ return añosAmortizacionProperty().get();				}
	public void setAñosAmortizacion(int añosAmortizacion)	{ añosAmortizacionProperty().set(añosAmortizacion);		}
	public IntegerProperty añosAmortizacionProperty() 		{ return añosAmortizacion;								}
	
	
	@Column(name = "DIAS_AMORTIZACION")
	public int getDiasAmortizacion() 						{ return diasAmortizacionProperty().get();				}
	public void setDiasAmortizacion(int diasAmortizacion)	{ diasAmortizacionProperty().set(diasAmortizacion);		}
	public IntegerProperty diasAmortizacionProperty() 		{ return diasAmortizacion;								}
		
	
	@Column(name = "HORAS_AMORTIZACION")
	public int getHorasAmortizacion() 						{ return horasAmortizacionProperty().get();				}
	public void setHorasAmortizacion(int horasAmortizacion) { horasAmortizacionProperty().set(horasAmortizacion);	}
	public IntegerProperty horasAmortizacionProperty() 		{ return horasAmortizacion;								}
	
		
	@OneToMany(mappedBy = "maquina", cascade = CascadeType.REMOVE)
	public List<PrendaMaquina> getPrendaMaquinas() {
		return prendaMaquinasProperty().get();
	}
	public void setPrendaMaquinas(List<PrendaMaquina> prendaMaquinas) {
		prendaMaquinasProperty().set(new ObservableListWrapper<PrendaMaquina>(prendaMaquinas));
	}
	public ListProperty<PrendaMaquina> prendaMaquinasProperty() {
		return prendaMaquinas;
	}
	public PrendaMaquina addPrendaMaquina(PrendaMaquina prendaMaquina){
		prendaMaquinas.add(prendaMaquina);
		prendaMaquina.setMaquina(this);
		return prendaMaquina;
	}
	public PrendaMaquina removePrendaMaquina(PrendaMaquina prendaMaquina){
		prendaMaquinas.remove(prendaMaquina);
		prendaMaquina.setMaquina(null);
		return prendaMaquina;
	}
	

}
