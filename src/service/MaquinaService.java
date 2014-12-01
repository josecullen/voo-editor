package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Maquina;

public class MaquinaService<AnEntity> implements BasicServiceInterface<AnEntity> {
	EntityManager em;

	public MaquinaService(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<AnEntity> findAll() {
		TypedQuery<Maquina> query = em.createQuery(
				"SELECT p FROM Maquina p", Maquina.class);
		return (List<AnEntity>) query.getResultList();
	}

	@Override
	public AnEntity create(String name) {
		AnEntity maq = (AnEntity) new Maquina();
		((Maquina) maq).setNombre(name);
		em.persist(maq);
		return maq;
	}

	@Override
	public boolean remove(AnEntity entity) {
		Maquina maq = em.find(Maquina.class,
				((Maquina) entity).getMaquinaId());
		if (maq != null)
			em.remove(maq);
		return (maq != null);
	}

	@Override
	public AnEntity find(String name) {
		TypedQuery<Maquina> query = em.createQuery(
				"SELECT p FROM Maquina p WHERE p.nombre = :nombre",
				Maquina.class);
		Maquina maq = null;

		try {
			maq = query.setParameter("nombre", name).getSingleResult();
		} catch (NoResultException e) {
			maq = null;
		}
		return (AnEntity) maq;

	}
	
	@Override
	public EntityManager getEM() {
		return em;
	}
}
