package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Cliente;

public class ClienteService {
	EntityManager em;
	
	public ClienteService(EntityManager em) {
		this.em = em;
	}
	
	public List<Cliente> findAll(){
		return em.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
	}
	
	public Cliente create(String nombreCliente){
		Cliente cliente = new Cliente();
		cliente.setCliente(nombreCliente);
		em.persist(cliente);
		return cliente;		
	}
	
	public void remove(Cliente cliente){
		em.remove(cliente);
	}
	
	
	
}
