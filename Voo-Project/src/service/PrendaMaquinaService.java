package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Maquina;
import model.Prenda;
import model.PrendaMaquina;

public class PrendaMaquinaService {
	EntityManager em;
	
	public PrendaMaquinaService(EntityManager em) {
		this.em = em;
	}
	
	public List<PrendaMaquina> findAll(){
		return em.createNamedQuery("PrendaMaquina.findAll", PrendaMaquina.class).getResultList();
	}
	
	public PrendaMaquina findByJoins(Prenda prenda, Maquina maquina){
		return em.createNamedQuery("PrendaMaquina.findByJoins", PrendaMaquina.class)
				.setParameter("prenda", prenda)
				.setParameter("maquina", maquina)
				.getResultList().get(0);
	}
	
	public void remove(PrendaMaquina prendaMaquina){
		removeByJoins(prendaMaquina.getPrenda(), prendaMaquina.getMaquina());
	}
	
	public void removeByJoins(Prenda prenda, Maquina maquina){
		PrendaMaquina prendaMaquina = findByJoins(prenda, maquina);
		prenda.removePrendaMaquina(prendaMaquina);
		maquina.removePrendaMaquina(prendaMaquina);
		em.remove(prendaMaquina);
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(PrendaMaquina prendaMaquina : findAll()) em.remove(prendaMaquina);
		em.getTransaction().commit();		
	}
	
	public PrendaMaquina create(Prenda prenda, Maquina maquina, int cantidadMinutos){
		PrendaMaquina prendaMaquina = new PrendaMaquina();
		prendaMaquina.setPrenda(prenda);
		prendaMaquina.setMaquina(maquina);
		prendaMaquina.setCantidadMinutos(cantidadMinutos);
		em.persist(prendaMaquina);
		prenda.addPrendaMaquina(prendaMaquina);
		maquina.addPrendaMaquina(prendaMaquina);
		return prendaMaquina;
	}
	
}
