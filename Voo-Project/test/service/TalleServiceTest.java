package service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.TipoPrenda;

import org.junit.After;
import org.junit.Test;

public class TalleServiceTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Voo-SQLite");
	EntityManager em = emf.createEntityManager();
	TalleService talleService = new TalleService(em);
	TipoPrendaService tipoPrendaService = new TipoPrendaService(em);
	
	@After
	public void after(){
		talleService.removeAll();
		tipoPrendaService.removeAll();
	}
	
	@Test
	public void create() {
		String nombrePrenda = "Nueva Prenda";
		
		em.getTransaction().begin();
		TipoPrenda tipoPrenda = tipoPrendaService.create(nombrePrenda);
		talleService.create("M", "F", 2.2d, tipoPrenda);
		talleService.create("S", "M", 2.7d, tipoPrenda);
		em.getTransaction().commit();
		
		tipoPrenda = tipoPrendaService.findAll().get(0);
		assertEquals(2, tipoPrenda.getTalles().size());
		assertEquals(nombrePrenda, tipoPrenda.getNombre());
		
	}

}
