package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Tela;

public class TelaService {
	EntityManager em;
	PrendaTelaService prendaTelaService;
	
	public TelaService(EntityManager em) {
		this.em = em;
		prendaTelaService = new PrendaTelaService(em);
	}
	
	public List<Tela> findAll(){
		return em.createNamedQuery("Tela.findAll", Tela.class).getResultList();
	}

	public List<Tela> findByType(String type){
		return em.createNamedQuery("Tela.findByType", Tela.class).setParameter("type", type).getResultList();
	}
	
	public List<String> findTypes(){
		return em.createNamedQuery("Tela.findTypes", String.class).getResultList();
	}
	
	public List<String> findModelsByType(String tipo){
		return em.createNamedQuery("Tela.findModelsByType", String.class).setParameter("tipo", tipo).getResultList();
	}
	
	public Tela findByTypeAndModel(String tipo, String modelo){
		return em.createNamedQuery("Tela.findByTypeAndModel", Tela.class)
				.setParameter("tipo", tipo)
				.setParameter("modelo", modelo)
				.getResultList().get(0);
				
	}
	
	public void remove(Tela tela){
		prendaTelaService.removeByTela(tela);
		em.remove(tela);		
	}
	
	public void removeType(String tipo){		
		for(Tela tela : findByType(tipo)){
			em.remove(tela);
		}
	}
	
	public Tela create(String tipo, String modelo, double costoMetro){
		Tela tela = new Tela();
		tela.setCostoMetro(costoMetro);
		tela.setTipo(tipo);
		tela.setModelo(modelo);
		em.persist(tela);
		return tela;
	}
	
	public List<Tela> updateNombre(String oldValue, String newValue){
		List<Tela> telas = findByType(oldValue);
		for(Tela tela : telas)
			tela.setTipo(newValue);
		return telas;
	}
	
	
}
