package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.ManoDeObra;
import model.Maquina;
import model.Prenda;

public class PrendaService {
	EntityManager em;
	PrendaTelaService prendaTelaService;
	
	public PrendaService(EntityManager em) {
		this.em = em;
		prendaTelaService =  new PrendaTelaService(em);
	}
	
	public List<Prenda> findAll(){
		return em.createNamedQuery("Prenda.findAll", Prenda.class).getResultList();
	}
	
	public Prenda findByName(String nombre){
		return em.createNamedQuery("Prenda.findByName", Prenda.class)
				.setParameter("nombre", nombre)
				.getResultList()
				.get(0);
	}
	
	public void removeAll(){
		em.getTransaction().begin();
		for(Prenda p : findAll())
			em.remove(p);
		em.getTransaction().commit();		
	}
	
	public void removeByName(String nombre){
		Prenda prenda = findByName(nombre);
		prendaTelaService.removeByPrenda(prenda);
		em.remove(prenda);
	}
	
	public Prenda create(String nombre){
		Prenda p = new Prenda();
		p.setNombre(nombre);
		em.persist(p);
		return p;
	}
	
	public Prenda createWithAllAssociations(String nombre){
		Prenda p = new Prenda();
		p.setNombre(nombre);
		MDOService mdoService = new MDOService(em);
		MaquinaService maquinaService = new MaquinaService(em);
		PrendaMDOService prendaMDOService = new PrendaMDOService(em);
		PrendaMaquinaService prendaMaquinaService = new PrendaMaquinaService(em);
		em.persist(p);

		for(ManoDeObra manoDeObra : mdoService.findAll())
			prendaMDOService.create(p, manoDeObra, 1);
		for(Maquina maquina : maquinaService.findAll())
			prendaMaquinaService.create(p, maquina, 30);
			
		return p;
	}
	
}
