package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Prenda;
import model.PrendaTela;
import model.Tela;

public class PrendaTelaService {
	EntityManager em;
	
	public PrendaTelaService(EntityManager em) {
		this.em = em;
	}
	
	public List<PrendaTela> findAll(){
		return em.createNamedQuery("PrendaTela.findAll", PrendaTela.class).getResultList();
	}
	
	public PrendaTela findByJoins(Prenda prenda, Tela tela){
		List<PrendaTela> prendaTelas = em.createNamedQuery("PrendaTela.findByJoins", PrendaTela.class)
					.setParameter("prenda", prenda)
					.setParameter("tela", tela)
					.getResultList();
		if(prendaTelas.size() > 0){
			return prendaTelas.get(0);
		}else{
			return null;
		}		
	}
	
	public List<PrendaTela> findByPrenda(Prenda prenda){
		return em.createNamedQuery("PrendaTela.findByPrenda", PrendaTela.class).setParameter("prenda", prenda).getResultList();
	}
	
	public void removeByPrenda(Prenda prenda){
		List<PrendaTela> prendaTelas = findByPrenda(prenda);
		for(PrendaTela prendaTela : prendaTelas){
			em.remove(prendaTela);
		}
	}
	
	public List<PrendaTela> findByTela(Tela tela){
		return em.createNamedQuery("PrendaTela.findByTela", PrendaTela.class).setParameter("tela", tela).getResultList();
	}
	
	public void removeByTela(Tela tela){
		List<PrendaTela> prendaTelas = findByTela(tela);
		for(PrendaTela prendaTela : prendaTelas)
			em.remove(prendaTela);
	}
	
	
	public boolean exist(Prenda prenda, Tela tela){
		return findByJoins(prenda, tela) != null;
	}
	
	public PrendaTela create(Prenda prenda, Tela tela, int porcentaje){
		PrendaTela prendaTela = new PrendaTela();
		prendaTela.setPrenda(prenda);
		prendaTela.setTela(tela);
		prendaTela.setPorcentaje(porcentaje);
		em.persist(prendaTela);
		
		prenda.addPrendaTela(prendaTela);
		
		return prendaTela;
	}
	
	public PrendaTela updatePorcentaje(Prenda prenda, Tela tela, int porcentaje){
		PrendaTela prendaTela = findByJoins(prenda, tela);
		if(prendaTela != null){
			prendaTela.setPorcentaje(porcentaje);
		}else{
			return null;
		}
		return prendaTela;		
	}
	
	
	
	
	
	
}
