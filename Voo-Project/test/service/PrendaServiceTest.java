package service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Prenda;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrendaServiceTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Voo-SQLite");
	EntityManager em = emf.createEntityManager();
	PrendaService prendaService = new PrendaService(em);
	MDOService mdoService = new MDOService(em);
	TipoPrendaService tipoPrendaService = new TipoPrendaService(em);
	PrendaMDOService prendaMDOService = new PrendaMDOService(em);
	
	@Before
	public void before(){
		prendaService.removeAll();
		prendaMDOService.removeAll();
		mdoService.removeAll();
		tipoPrendaService.removeAll();
	}
	
	@After
	public void after(){
		prendaService.removeAll();
		prendaMDOService.removeAll();
		mdoService.removeAll();
		tipoPrendaService.removeAll();
	}
	
	
	@Test
	public void create() {
		assertEquals(0, prendaService.findAll().size());
		String name = "Prenda Loca";
		
		em.getTransaction().begin();		
		prendaService.create(name);		
		em.getTransaction().commit();
		
		Prenda prenda = prendaService.findByName(name);
		assertEquals(name, prenda.getNombre());
		assertEquals(1, prendaService.findAll().size());
	}
	
	@Test
	public void createWithAllAssociations(){
		assertEquals(0, prendaService.findAll().size());
		String name = "Prenda Loca", manoDeObra1 = "Mano de Obra 1", manoDeObra2 = "Mano de Obra 2";
		
		em.getTransaction().begin();		
		mdoService.create(manoDeObra1);
		mdoService.create(manoDeObra2);
		prendaService.createWithAllAssociations(name);		
		em.getTransaction().commit();

		Prenda p = prendaService.findByName(name);
		assertEquals(p.getNombre(), name);
		assertTrue(p.getPrendaManoDeObras().size() == 2);
		assertTrue(p.getPrendaManoDeObras().get(0).getManoDeObra().getNombre() == manoDeObra1
				|| p.getPrendaManoDeObras().get(0).getManoDeObra().getNombre() == manoDeObra2);
		assertTrue(p.getPrendaManoDeObras().get(1).getManoDeObra().getNombre() == manoDeObra1
				|| p.getPrendaManoDeObras().get(1).getManoDeObra().getNombre() == manoDeObra2);
	}

}
