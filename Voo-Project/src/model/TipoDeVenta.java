package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TIPO_DE_VENTAS database table.
 * 
 */
@Entity
@Table(name="TIPO_DE_VENTAS")
@NamedQuery(name="TipoDeVenta.findAll", query="SELECT t FROM TipoDeVenta t")
public class TipoDeVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Column(name="TIPO_DE_VENTA", length=2000000000)
	private String tipoDeVenta;

	
	@TableGenerator(name = "tipoVentaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "TIPO_VENTA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tipoVentaGen")
	@Id @Column(name="TIPO_DE_VENTA_ID", nullable=false)
	private int tipoDeVentaId;

	public TipoDeVenta() {
	}

	public String getTipoDeVenta() {
		return this.tipoDeVenta;
	}

	public void setTipoDeVenta(String tipoDeVenta) {
		this.tipoDeVenta = tipoDeVenta;
	}

	public int getTipoDeVentaId() {
		return this.tipoDeVentaId;
	}

	public void setTipoDeVentaId(int tipoDeVentaId) {
		this.tipoDeVentaId = tipoDeVentaId;
	}

}