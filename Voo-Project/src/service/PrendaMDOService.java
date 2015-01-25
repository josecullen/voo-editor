package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.ManoDeObra;
import model.Prenda;
import model.PrendaManoDeObra;

public class PrendaMDOService {
	EntityManager em;
	
	public PrendaMDOService(EntityManager em) {
		this.em = em;
	}
	
	public List<PrendaManoDeObra> findAll(){
		return em.createNamedQuery("PrendaManoDeObra.findAll", PrendaManoDeObra.class).getResultList();
	}
	
	public PrendaManoDeObra findByJoins(Prenda prenda, ManoDeObra manoDeObra){
		return em.createNamedQuery("PrendaManoDeObra.findByJoins", PrendaManoDeObra.class)
				.setParameter("prenda", prenda)
				.setParameter("manoDeObra", manoDeObra)
				.getResultList().get(0);
	}
	
	public void remove(PrendaManoDeObra pmdo){
		removeByJoins(pmdo.getPrenda(), pmdo.getManoDeObra());
	}
	
	public void removeByJoins(Prenda prenda, ManoDeObra manoDeObra){
		PrendaManoDeObra prendaManoDeObra = findByJoins(prenda, manoDeObra);
		prenda.removePrendaManoDeObra(prendaManoDeObra);
		manoDeObra.removePrendaManoDeObra(prendaManoDeObra);
		em.remove(prendaManoDeObra);
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(PrendaManoDeObra pmdo : findAll()) em.remove(pmdo);
		em.getTransaction().commit();		
	}
	
	public PrendaManoDeObra create(Prenda prenda, ManoDeObra manoDeObra, double cantidadHoras){
		PrendaManoDeObra pmdo = new PrendaManoDeObra();
		pmdo.setPrenda(prenda);
		pmdo.setManoDeObra(manoDeObra);
		pmdo.setCantidadHoras(cantidadHoras);
		em.persist(pmdo);
		prenda.addPrendaManoDeObra(pmdo);
		manoDeObra.addPrendaManoDeObra(pmdo);
		return pmdo;
	}
	
}
