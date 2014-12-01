package test.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ManoDeObra;
import model.Prenda;
import model.PrendaManoDeObra;
import model.TipoPrenda;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.PrendaService;

public class PrendaTest {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("VooEditor");
	static EntityManager em = emf.createEntityManager(); 
	PrendaService<Prenda> ps = new PrendaService<Prenda>(em);
	
	static String nombrePrenda = "prenda test";
	
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
		TypedQuery<Prenda> query = em.createQuery("SELECT p FROM Prenda p WHERE p.nombre = :nombre", Prenda.class);
		Prenda p = query.setParameter("nombre", nombrePrenda).getSingleResult();
		if(p != null){
			em.getTransaction().begin();
			em.remove(p);	
			em.getTransaction().commit();
		}
		
	}

	
	@Test
	public void testFindByName(){
		Prenda p = ps.find(nombrePrenda);
		assertEquals(nombrePrenda, p.getNombre());		
	}
	
	@Test
	public void updateNombre(){
				
		TypedQuery<String> query = em.createQuery("SELECT p.nombre FROM Prenda p WHERE p.nombre = :nombre", String.class);
		String nombre = query.setParameter("nombre", nombrePrenda).getSingleResult();
		
		
		em.getTransaction().begin();
			Prenda p = ps.find(nombrePrenda);
			assertEquals(p.getNombre(), nombre);
	
			// Cambiamos el nombre de la instancia de prenda
			p.setNombre("otro nombre");
			
			assertNotEquals(p.getNombre(), nombre);
			assertEquals(p.getNombre(), query.setParameter("nombre", "otro nombre").getSingleResult());
			
			p.setNombre(nombrePrenda);
			assertEquals(p.getNombre(), query.setParameter("nombre", nombrePrenda).getSingleResult());
		em.getTransaction().commit();
		
		em.getTransaction().begin();
			p.setNombre("otro nombre");
		em.getTransaction().rollback();	
		
		p = ps.find(nombrePrenda);
		assertEquals(p.getNombre(), query.setParameter("nombre", nombrePrenda).getSingleResult());
	}
	
	@Test
	public void updateTipoPrenda(){
		TypedQuery<TipoPrenda> query = em.createQuery("SELECT p.tipoPrenda FROM Prenda p WHERE p.nombre = :nombre", TipoPrenda.class);
		TipoPrenda tipoPrenda = query.setParameter("nombre", nombrePrenda).getSingleResult();
		
		assertNull(tipoPrenda);
		
		TipoPrenda tipoPrendaNoAsignado = em.find(TipoPrenda.class, 1l);
		Prenda p = ps.find(nombrePrenda);
		
		assertNull(p.getTipoPrenda());
		
		// SIN GENERAR LA TRANSACCIÓN NI HACER COMMIT, NO VALE
		p.setTipoPrenda(tipoPrendaNoAsignado);
		
		assertEquals(tipoPrendaNoAsignado, p.getTipoPrenda());
		assertNotEquals(tipoPrendaNoAsignado, tipoPrenda);
		
		tipoPrenda = query.getSingleResult();
		assertNotEquals(tipoPrendaNoAsignado, tipoPrenda);
		
		// GENERANDO LA TRANSACCIÓN Y HACIENDO COMMIT, VALE
		em.getTransaction().begin();
			p.setTipoPrenda(tipoPrendaNoAsignado);
		em.getTransaction().commit();
		
		tipoPrenda = query.getSingleResult();
		assertEquals(tipoPrendaNoAsignado, tipoPrenda);

	}
	
	@Test
	public void testUpdateManoDeObra() throws Exception{
		Prenda p = ps.find(nombrePrenda);
		PrendaManoDeObra pmdo = new PrendaManoDeObra();
		
		assertNotNull(pmdo);
		assertEquals(0, p.getPrendaManoDeObras().size());
		
		em.getTransaction().begin();
			em.persist(pmdo);
			p.addPrendaManoDeObra(pmdo);
		em.getTransaction().commit();
		
		assertEquals(1, p.getPrendaManoDeObras().size());

		p = ps.find(nombrePrenda);
		assertEquals(1, p.getPrendaManoDeObras().size());
		
		
	}
	

}
