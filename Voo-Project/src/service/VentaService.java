package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Venta;

public class VentaService {
	EntityManager em;
	
	public VentaService(EntityManager em) {
		this.em = em;
	}
	
//	public Venta create(){
//		Venta venta = new Venta();
//		venta.setFechaVenta(new GregorianCalendar());
//		em.persist(venta);
//		return venta;
//	}
	
	public List<Venta> findAll(){
		return em.createNamedQuery("Venta.findAll", Venta.class).getResultList();
	}
	
	public Venta findById(int ventaId){
		return em.createNamedQuery("Venta.findById", Venta.class).setParameter("ventaId", ventaId).getSingleResult();
	}
	
//	public Venta setCliente(Venta venta, Cliente cliente){
//		Venta ventaEditada = em.find(Venta.class, venta.getId());
//		ventaEditada.setCliente(cliente);
//		return ventaEditada;
//	}
	
	
}
