package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CLIENTES database table.
 * 
 */
@Entity
@Table(name="CLIENTES")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
@TableGenerator(name = "clienteGen", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "CLIENTE", allocationSize = 1)

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@Column(length=2000000000)
	private String cliente;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "clienteGen")
	@Column(name="CLIENTE_ID", nullable=false)
	private int clienteId;

	public Cliente() {
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getClienteId() {
		return this.clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

}