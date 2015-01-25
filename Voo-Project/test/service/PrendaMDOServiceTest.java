package service;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ManoDeObra;
import model.Prenda;
import model.PrendaManoDeObra;

import org.junit.After;
import org.junit.Test;

public class PrendaMDOServiceTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Voo-SQLite");
	EntityManager em = emf.createEntityManager();
	PrendaMDOService prendaMDOService = new PrendaMDOService(em);
	PrendaService prendaService = new PrendaService(em);
	MDOService mdoService = new MDOService(em);
	
	@After
	public void after(){
		prendaService.removeAll();
		mdoService.removeAll();		
		prendaMDOService.removeAll();		
	}
	
	@Test
	public void create() throws Exception{
		String 
			nombrePrenda = "Prenda 1",
			nombreMDO = "MDO 1";
		double
			costoHora = 20.2d,
			cantidadHoras = 2.1d;
		
		assertEquals(0, prendaService.findAll().size());
		assertEquals(0, mdoService.findAll().size());
		assertEquals(0, prendaMDOService.findAll().size());
		
		em.getTransaction().begin();
			Prenda prenda = prendaService.create(nombrePrenda);
			ManoDeObra manoDeObra = mdoService.create(nombreMDO, costoHora);
			PrendaManoDeObra prendaMDO = prendaMDOService.create(prenda, manoDeObra, cantidadHoras);			
		em.getTransaction().commit();
		
		assertEquals(1, prendaService.findAll().size());
		assertEquals(1, mdoService.findAll().size());
		assertEquals(1, prendaMDOService.findAll().size());
		
		prenda = prendaService.findByName(nombrePrenda);
		assertEquals(nombrePrenda, prenda.getNombre());
		List<PrendaManoDeObra> prendaMDOs = prenda.getPrendaManoDeObras();
		assertTrue(cantidadHoras == prendaMDOs.get(0).getCantidadHoras());
		assertEquals(nombreMDO, prenda.getPrendaManoDeObras().get(0).getManoDeObra().getNombre());
		assertTrue(costoHora == prenda.getPrendaManoDeObras().get(0).getManoDeObra().getCostoHora());
		
		manoDeObra = mdoService.findByName(nombreMDO);
		assertEquals(nombreMDO, manoDeObra.getNombre());
		assertTrue(costoHora == manoDeObra.getCostoHora());
		assertTrue(cantidadHoras == manoDeObra.getPrendaManoDeObras().get(0).getCantidadHoras());
		assertEquals(nombrePrenda, manoDeObra.getPrendaManoDeObras().get(0).getPrenda().getNombre());
		
		prendaMDO = prendaMDOService.findAll().get(0);
		assertEquals(prenda.getNombre(), prendaMDO.getPrenda().getNombre());
		assertEquals(manoDeObra.getNombre(), prendaMDO.getManoDeObra().getNombre());
		
	}
	
}
