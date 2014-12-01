package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MATERIAL database table.
 * 
 */
@Entity
@NamedQuery(name="Material.findAll", query="SELECT m FROM Material m")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MATERIAL_MATERIALID_GENERATOR", sequenceName="SEQ_MATERIAL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATERIAL_MATERIALID_GENERATOR")
	@Column(name="MATERIAL_ID")
	private long materialId;

	@Column(name="COSTO_METRO")
	private double costoMetro;

	private String modelo;

	private String nombre;

	//bi-directional many-to-one association to PrendaMaterial
	@OneToMany(mappedBy="material")
	private List<PrendaMaterial> prendaMaterials;

	public Material() {
	}

	public long getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}

	public double getCostoMetro() {
		return this.costoMetro;
	}

	public void setCostoMetro(double costoMetro) {
		this.costoMetro = costoMetro;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PrendaMaterial> getPrendaMaterials() {
		return this.prendaMaterials;
	}

	public void setPrendaMaterials(List<PrendaMaterial> prendaMaterials) {
		this.prendaMaterials = prendaMaterials;
	}

	public PrendaMaterial addPrendaMaterial(PrendaMaterial prendaMaterial) {
		getPrendaMaterials().add(prendaMaterial);
		prendaMaterial.setMaterial(this);

		return prendaMaterial;
	}

	public PrendaMaterial removePrendaMaterial(PrendaMaterial prendaMaterial) {
		getPrendaMaterials().remove(prendaMaterial);
		prendaMaterial.setMaterial(null);

		return prendaMaterial;
	}
	
	@Override
	public String toString(){
		return nombre;
	}

}