package com.ticketservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {
    private static final String PERSISTENCE_UNIT_NAME = "ticketServicePU";
    private static EntityManagerFactory factory;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
    
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
    
    public static void closeEntityManagerFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
