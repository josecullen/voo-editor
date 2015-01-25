package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.ManoDeObra;

public class MDOService {
	EntityManager em;
	
	public MDOService(EntityManager em) {
		this.em = em;
	}
	
	public List<ManoDeObra> findAll(){
		return em.createNamedQuery("ManoDeObra.findAll", ManoDeObra.class).getResultList();
	}
	
	public ManoDeObra findByName(String nombre){
		return em.createNamedQuery("ManoDeObra.findByName", ManoDeObra.class)
				.setParameter("nombre", nombre)
				.getResultList()
				.get(0);
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(ManoDeObra mdo : findAll())
			em.remove(mdo);
		em.getTransaction().commit();		
	}
	
	public void removeByName(String nombre){
		ManoDeObra mdo = findByName(nombre);
		em.remove(mdo);
	}
	
	public ManoDeObra create(String nombre){
		return create(nombre, 0.0d);
	}
	
	public ManoDeObra create(String nombre, double costoHora){
		ManoDeObra mdo = new ManoDeObra();
		mdo.setNombre(nombre);
		mdo.setCostoHora(costoHora);
		em.persist(mdo);
		return mdo;
	}
	
	
	
}
