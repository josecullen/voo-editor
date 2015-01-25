package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.TipoDeVenta;

public class TipoVentaService {
	EntityManager em;
	
	public TipoVentaService(EntityManager em) {
		this.em = em;
	}
	
	public List<TipoDeVenta> findAll(){
		return em.createNamedQuery("TipoDeVenta.findAll", TipoDeVenta.class).getResultList();
	}
	
	public TipoDeVenta create(String nombreTipoDeVenta){
		TipoDeVenta tipoDeVenta = new TipoDeVenta();
		tipoDeVenta.setTipoDeVenta(nombreTipoDeVenta);
		em.persist(tipoDeVenta);
		return tipoDeVenta;
	}
	
	public void remove(TipoDeVenta tipoDeVenta){
		em.remove(tipoDeVenta);
	}
	
	
}
