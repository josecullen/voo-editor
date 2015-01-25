package service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.ManoDeObra;

import org.junit.After;
import org.junit.Test;

public class MDOServiceTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Voo-SQLite");
	EntityManager em = emf.createEntityManager();
	MDOService mdoService = new MDOService(em);
	
	
	@After
	public void after(){
		mdoService.removeAll();
	}
	
	@Test
	public void test() {
		em.getTransaction().begin();		
		mdoService.create("Costurero", 20.2d);		
		em.getTransaction().commit();
		
		ManoDeObra mdo = mdoService.findByName("Costurero");
		assertEquals("Costurero", mdo.getNombre());
		assertTrue(mdo.getCostoHora() == 20.2d);
	}

}
