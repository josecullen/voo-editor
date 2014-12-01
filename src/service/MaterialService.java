package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Material;

public class MaterialService<AnEntity> implements
		BasicServiceInterface<AnEntity> {

	EntityManager em;

	public MaterialService(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<AnEntity> findAll() {
		TypedQuery<Material> query = em.createQuery(
				"SELECT p FROM Material p", Material.class);
		return (List<AnEntity>) query.getResultList();
	}

	@Override
	public AnEntity create(String name) {
		AnEntity Mat = (AnEntity) new Material();
		((Material) Mat).setNombre(name);
		em.persist(Mat);
		return Mat;
	}

	@Override
	public boolean remove(AnEntity entity) {
		Material mat = em.find(Material.class,
				((Material) entity).getMaterialId());
		if (mat != null)
			em.remove(mat);
		return (mat != null);
	}

	@Override
	public AnEntity find(String name) {
		TypedQuery<Material> query = em.createQuery(
				"SELECT p FROM Material p WHERE p.nombre = :nombre",
				Material.class);
		Material mat = null;

		try {
			mat = query.setParameter("nombre", name).getSingleResult();
		} catch (NoResultException e) {
			mat = null;
		}
		return (AnEntity) mat;

	}
	@Override
	public EntityManager getEM() {
		return em;
	}
}
