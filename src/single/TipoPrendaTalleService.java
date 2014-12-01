package single;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Talle;
import model.TipoPrenda;
import service.BasicServiceInterface;

public class TipoPrendaTalleService<AnEntity> implements BasicServiceInterface<AnEntity>{

	EntityManager em;
	TipoPrenda tipoPrenda;
	
	public TipoPrendaTalleService(EntityManager em, TipoPrenda tipoPrenda){
		this.em = em;
		this.tipoPrenda = tipoPrenda;
	}
	
	
	
	@Override
	public List<AnEntity> findAll() {
		//TypedQuery<Talle> query = em.createQuery("SELECT tp.talle");
		return null;
	}

	@Override
	public AnEntity create(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(AnEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AnEntity find(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public EntityManager getEM() {
		// TODO Auto-generated method stub
		return null;
	}

}
