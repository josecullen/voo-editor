package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the VENTAS database table.
 * 
 */
@Entity
@Table(name="VENTAS")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@TableGenerator(name = "ventaGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "VENTA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ventaGen")
	@Id@Column(name="VENTA_ID", nullable=false)
	private int ventaId;
	
	
	@Column(name="CLIENTE_ID")
	private int clienteId;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_VENTA")
	private Date fechaVenta;

	@Column(name="TIPO_DE_VENTA_ID")
	private int tipoDeVentaId;

	

	public Venta() {
	}

	public int getClienteId() {
		return this.clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public int getTipoDeVentaId() {
		return this.tipoDeVentaId;
	}

	public void setTipoDeVentaId(int tipoDeVentaId) {
		this.tipoDeVentaId = tipoDeVentaId;
	}

	public int getVentaId() {
		return this.ventaId;
	}

	public void setVentaId(int ventaId) {
		this.ventaId = ventaId;
	}

}