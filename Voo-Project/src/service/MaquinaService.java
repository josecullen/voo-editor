package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Maquina;

public class MaquinaService {
	EntityManager em;
	
	public MaquinaService(EntityManager em) {
		this.em = em;
	}
	
	public List<Maquina> findAll(){
		return em.createNamedQuery("Maquina.findAll", Maquina.class).getResultList();
	}
	
	public Maquina findByName(String nombre){
		return em.createNamedQuery("Maquina.findByName", Maquina.class)
				.setParameter("nombre", nombre)
				.getResultList()
				.get(0);
	}
	
	public List<String> findNames(){
		return em.createNamedQuery("Maquina.findNames", String.class).getResultList();
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(Maquina m : findAll())
			em.remove(m);
		em.getTransaction().commit();		
	}
	
	public void removeByName(String nombre){
		Maquina m = findByName(nombre);
		em.remove(m);
	}
	
	public Maquina create(String nombre){
		Maquina maquina = new Maquina();
		maquina.setNombre(nombre);
		em.persist(maquina);
		return maquina;
	}

	
	
	
	
}
