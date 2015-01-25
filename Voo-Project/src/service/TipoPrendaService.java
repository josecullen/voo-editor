package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.TipoPrenda;

public class TipoPrendaService {
	EntityManager em;
	
	public TipoPrendaService(EntityManager em) {
		this.em = em;
	}
	
	public List<TipoPrenda> findAll(){
		return em.createNamedQuery("TipoPrenda.findAll", TipoPrenda.class).getResultList();
	}
	
	public TipoPrenda findByName(String nombre){
		return em.createNamedQuery("TipoPrenda.findByName", TipoPrenda.class).setParameter("nombre", nombre).getResultList().get(0);
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(TipoPrenda tipoPrenda : findAll()) em.remove(tipoPrenda);
		em.getTransaction().commit();
	}
	
	public void removeByName(String nombre){
		TipoPrenda tipoPrenda = findByName(nombre);
		em.remove(tipoPrenda);				
	}
	
	public TipoPrenda create(String nombre){
		TipoPrenda tipoPrenda = new TipoPrenda();
		tipoPrenda.setNombre(nombre);
		em.persist(tipoPrenda);
		return tipoPrenda;
	}
	
	
	
	
}
