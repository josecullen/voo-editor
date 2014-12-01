package test.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Prenda;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.PrendaService;

public class PrendaServiceTest {
	static String nombrePrenda = "prenda test";
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("VooEditor");
	static EntityManager em = emf.createEntityManager(); 
	PrendaService<Prenda> ps = new PrendaService<Prenda>(em);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		em.getTransaction().begin();
		Prenda prenda = ps.create(nombrePrenda);
		em.getTransaction().commit();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemove() throws Exception{
		Prenda prenda = ps.find(nombrePrenda);
		
		assertTrue(prenda != null);
		
		em.getTransaction().begin();
			ps.remove(prenda);
		em.getTransaction().commit();
		
		prenda = ps.find(nombrePrenda);
		
		assertTrue(prenda == null);
	}
	
	@Test
	public void testRemoveInCascade() throws Exception{
		Prenda prenda = ps.find(nombrePrenda);
		
		
	}

}
