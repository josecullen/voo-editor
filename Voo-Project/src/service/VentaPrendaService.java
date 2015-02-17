package service;

import java.util.List;

import javax.persistence.EntityManager;

import model.Prenda;
import model.Venta;
import model.VentaPrenda;

public class VentaPrendaService {
	EntityManager em;
	
	public VentaPrendaService(EntityManager em) {
		this.em = em;
	}
	
	public List<VentaPrenda> findAll(){
		return em.createNamedQuery("VentaPrenda.findAll", VentaPrenda.class).getResultList();
	}
	
	public VentaPrenda findByJoins(Venta venta, Prenda prenda){
		return em.createNamedQuery("VentaPrenda.findByJoins", VentaPrenda.class)
				.setParameter("venta", venta)
				.setParameter("prenda", prenda)
				.getResultList().get(0);
	}
	
	public void removeByJoins(Venta venta, Prenda prenda){
		VentaPrenda ventaPrenda = findByJoins(venta, prenda);
//		venta.removeVentaPrenda(ventaPrenda);
		em.remove(ventaPrenda);
	}
	
//	public VentaPrenda setCantidad(VentaPrenda ventaPrenda, int cantidad){
//		VentaPrenda vp = em.find(VentaPrenda.class, ventaPrenda.getId());
//		vp.setCantidad(cantidad);
//		return vp;
//	}
	
//	public VentaPrenda setBeneficio(VentaPrenda ventaPrenda, int beneficio){
//		VentaPrenda vp = em.find(VentaPrenda.class, ventaPrenda.getId());
//		vp.setBeneficio(beneficio);
//		return vp;
//	}
	
	public VentaPrenda create(Venta venta, Prenda prenda){
		VentaPrenda ventaPrenda = new VentaPrenda();
//		ventaPrenda.setPrenda(prenda);
//		ventaPrenda.setVenta(venta);
//		venta.addVentaPrenda(ventaPrenda);
		em.persist(ventaPrenda);
		return ventaPrenda;
	}
	
	
}
