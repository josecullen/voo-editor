package test.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Prenda;
import model.TipoPrenda;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.PrendaService;
import service.TipoPrendaService;

public class TipoPrendaServiceTest {
	static String nombreTipoPrenda = "tipo prenda test";
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("VooEditor");
	static EntityManager em = emf.createEntityManager(); 
	TipoPrendaService<TipoPrenda> tps = new TipoPrendaService(em);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		em.getTransaction().begin();
		TipoPrenda tipoPrenda = tps.create(nombreTipoPrenda);
		em.getTransaction().commit();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void testCreate() throws Exception{
		
	}
	
	
	
}
