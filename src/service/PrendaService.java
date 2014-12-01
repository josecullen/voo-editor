package service;

import java.util.List;

import javax.persistence.EntityManager;


import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Prenda;

public class PrendaService<AnEntity> implements BasicServiceInterface<AnEntity>{
	EntityManager em;
	
	public PrendaService(EntityManager em){
		this.em = em;
	}
	
	public AnEntity create(String nombre){
		AnEntity prenda = (AnEntity)new Prenda();
		((Prenda)prenda).setNombre(nombre);
		em.persist(prenda);
		return prenda;
	}
	
	public Prenda update(Prenda prenda){
		em.refresh(prenda);
		return prenda;
	}
	

	public AnEntity find(String prendaName){
		TypedQuery<Prenda> query = em.createQuery("SELECT p FROM Prenda p WHERE p.nombre = :nombre", Prenda.class);
		Prenda prenda = null;
		
		try{
			prenda = query.setParameter("nombre", prendaName).getSingleResult();
		}catch(NoResultException e){
			prenda = null;
		}
		return (AnEntity) prenda;
	}

	@Override
	public List<AnEntity> findAll() {
		TypedQuery<Prenda> query = em.createQuery("SELECT p FROM Prenda p", Prenda.class);
		return (List<AnEntity>) query.getResultList();		
	}

	

	@Override
	public boolean remove(AnEntity entity) {
		Prenda p = em.find(Prenda.class, ((Prenda)entity).getPrendaId());
		if(p != null) em.remove(p);
		return (p != null);
			
	}

	@Override
	public EntityManager getEM() {
		return em;
	}
	
	
		
}
