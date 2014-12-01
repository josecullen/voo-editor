package view.list;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import service.BasicServiceInterface;
import service.MaquinaService;
import service.PrendaService;
import model.Maquina;
import model.Prenda;
import ar.com.josecullen.interfaces.comp.Configurable;
import ar.com.josecullen.util.RapidFrame;
import ar.josecullen.components.panel.JMCListTitlePanel;

public class EntidadConfigurable<AnEntity> implements Configurable<AnEntity>{
	
	EntityManager em; 

	BasicServiceInterface<AnEntity> service;
	
	
	public EntidadConfigurable(BasicServiceInterface<AnEntity> service) {
		this.service = service;  
		em = service.getEM();
	}
	
	@Override
	public void editValue(Object value) {
		//TODO
	}

	@Override
	public void addValue(Object value) {
		em.getTransaction().begin();
		service.create(value.toString());
		em.getTransaction().commit();
	}

	@Override
	public void removeValue(Object value) {
		em.getTransaction().begin();
		service.remove((AnEntity)value);
		em.getTransaction().commit();
	}

	@Override
	public List<AnEntity> getList() {		
		return service.findAll();
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setValue(Object value) {}

	
	
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VooEditor");
		EntityManager em = emf.createEntityManager(); 
		
		BasicServiceInterface<Maquina> ms = new MaquinaService<Maquina>(em);
		RapidFrame frame = new RapidFrame(new JMCListTitlePanel<Maquina>("Máquinas", new EntidadConfigurable<Maquina>(ms)));
		
	}
	
}
