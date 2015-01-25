package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Talle;
import model.TipoPrenda;

public class TalleService {
	EntityManager em;
	
	public TalleService(EntityManager em) {
		this.em = em;
	}
	
	public List<Talle> findAll(){
		return em.createNamedQuery("Talle.findAll", Talle.class).getResultList();
	}
	
	public List<Talle> findByTipoPrenda(TipoPrenda tipoPrenda){
		return em.createNamedQuery("Talle.findByTipoPrenda", Talle.class)
				.setParameter("tipoPrenda", tipoPrenda).getResultList();
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(Talle talle : findAll()) em.remove(talle);
		em.getTransaction().commit();
	}
	
	public Talle create(String nombre, String genero, double metrosTela, TipoPrenda tipoPrenda){
		Talle talle = new Talle();
		talle.setNombre(nombre);
		talle.setGenero(genero);
		talle.setMetrosTela(metrosTela);
		talle.setTipoPrenda(tipoPrenda);
		em.persist(talle);
		tipoPrenda.addTalle(talle);
		return talle;
	}
	
}
