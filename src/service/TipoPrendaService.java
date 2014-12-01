package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Prenda;
import model.TipoPrenda;

public class TipoPrendaService<AnEntity> implements BasicServiceInterface<AnEntity> {

	EntityManager em;
	
	public TipoPrendaService(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<AnEntity> findAll() {
		TypedQuery<TipoPrenda> query = em.createQuery("SELECT tp FROM TipoPrenda tp", TipoPrenda.class);
		return (List<AnEntity>) query.getResultList();		
	}


	@Override
	public boolean remove(AnEntity entity) {
		TipoPrenda p = em.find(TipoPrenda.class, ((TipoPrenda)entity).getTipoPrendaId());
		if(p != null) em.remove(p);
		return (p != null);
	}

	@Override
	public AnEntity find(String name) {
		TypedQuery<TipoPrenda> query = em.createQuery("SELECT tp FROM TipoPrenda tp WHERE tp.nombre = :nombre", TipoPrenda.class);
		TipoPrenda tp = null;
		
		try{
			tp = query.setParameter("nombre", name).getSingleResult();
		}catch(NoResultException e){
			tp = null;
		}
		return (AnEntity) tp;
	}

	@Override
	public AnEntity create(String name) {
		AnEntity tipoPrenda = (AnEntity)new TipoPrenda();
		((TipoPrenda)tipoPrenda).setNombre(name);
		em.persist(tipoPrenda);
		return tipoPrenda;
	}

	@Override
	public EntityManager getEM() {
		return em;
	}


}
