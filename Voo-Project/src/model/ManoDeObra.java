package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MANO_DE_OBRA database table.
 * 
 */
@Entity
@Table(name="MANO_DE_OBRA")
@NamedQuery(name="ManoDeObra.findAll", query="SELECT m FROM ManoDeObra m")
public class ManoDeObra implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "mdoGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "MANO_DE_OBRA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "mdoGen")
	@Id  @Column( name = "MANO_DE_OBRA_ID")
	int manoDeObraId;
	
	@Column(name="COSTO_HORA")
	private double costoHora;

	@Column(length=2000000000)
	private String nombre;

	//bi-directional many-to-one association to PrendaManoDeObra
	@OneToMany(mappedBy="manoDeObra", fetch=FetchType.EAGER)
	private List<PrendaManoDeObra> prendaManoDeObras;

	public int getManoDeObraId() {
		return manoDeObraId;
	}

	public void setManoDeObraId(int manoDeObraId) {
		this.manoDeObraId = manoDeObraId;
	}

	public ManoDeObra() {
	}

	public double getCostoHora() {
		return this.costoHora;
	}

	public void setCostoHora(double costoHora) {
		this.costoHora = costoHora;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PrendaManoDeObra> getPrendaManoDeObras() {
		return this.prendaManoDeObras;
	}

	public void setPrendaManoDeObras(List<PrendaManoDeObra> prendaManoDeObras) {
		this.prendaManoDeObras = prendaManoDeObras;
	}

	public PrendaManoDeObra addPrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		getPrendaManoDeObras().add(prendaManoDeObra);
		prendaManoDeObra.setManoDeObra(this);

		return prendaManoDeObra;
	}

	public PrendaManoDeObra removePrendaManoDeObra(PrendaManoDeObra prendaManoDeObra) {
		getPrendaManoDeObras().remove(prendaManoDeObra);
		prendaManoDeObra.setManoDeObra(null);

		return prendaManoDeObra;
	}

	@Override
	public String toString() {
		return getNombre();
	}
}