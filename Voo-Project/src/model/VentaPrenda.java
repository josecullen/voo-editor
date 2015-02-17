package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the VENTA_PRENDA database table.
 * 
 */
@Entity
@Table(name="VENTA_PRENDA")
@NamedQuery(name="VentaPrenda.findAll", query="SELECT v FROM VentaPrenda v")
public class VentaPrenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "ventaPrendaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "VENTA_PRENDA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ventaPrendaGen")
	@Id @Column(name="VENTA_PRENDA_ID", nullable=false)
	private int ventaPrendaId;

	
	
	private int beneficio;

	private int cantidad;

	@Column(name="PRENDA_ID")
	private int prendaId;

	@Column(name="VENTA_ID")
	private int ventaId;

	
	
	public VentaPrenda() {
	}

	public int getBeneficio() {
		return this.beneficio;
	}

	public void setBeneficio(int beneficio) {
		this.beneficio = beneficio;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrendaId() {
		return this.prendaId;
	}

	public void setPrendaId(int prendaId) {
		this.prendaId = prendaId;
	}

	public int getVentaId() {
		return this.ventaId;
	}

	public void setVentaId(int ventaId) {
		this.ventaId = ventaId;
	}

	public int getVentaPrendaId() {
		return this.ventaPrendaId;
	}

	public void setVentaPrendaId(int ventaPrendaId) {
		this.ventaPrendaId = ventaPrendaId;
	}

}