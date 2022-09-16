package controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

	private static final String PERSISTENCE_UNIT_NAME = "persistenceConfig";
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return emf;
	}
}