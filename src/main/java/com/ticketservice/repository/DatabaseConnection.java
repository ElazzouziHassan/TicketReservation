package com.ticketservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {
    private static final String PERSISTENCE_UNIT_NAME = "ticketServicePU";
    private static EntityManagerFactory factory;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            try {
                System.out.println("Initialisation de l'EntityManagerFactory...");
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                System.out.println("EntityManagerFactory initialisée avec succès.");
            } catch (Exception e) {
                System.err.println("Erreur lors de l'initialisation de l'EntityManagerFactory:");
                e.printStackTrace();
                throw e;
            }
        }
        return factory;
    }
    
    public static EntityManager getEntityManager() {
        try {
            EntityManager em = getEntityManagerFactory().createEntityManager();
            System.out.println("EntityManager créé avec succès.");
            return em;
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de l'EntityManager:");
            e.printStackTrace();
            throw e;
        }
    }
    
    public static void closeEntityManagerFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
