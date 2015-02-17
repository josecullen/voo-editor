package service;

import java.util.List;

public interface BasicServices<Entidad> {
	List<Entidad> findAll();
	Entidad create(String nombre);
}
