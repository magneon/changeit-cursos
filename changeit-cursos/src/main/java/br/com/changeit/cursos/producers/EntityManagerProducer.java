package br.com.changeit.cursos.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {
	
	@Produces
	@RequestScoped
	public EntityManager criarEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("changeit-cursos");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;		
	}

}
