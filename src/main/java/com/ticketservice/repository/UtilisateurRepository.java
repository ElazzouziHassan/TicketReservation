package com.ticketservice.repository;

import com.ticketservice.model.Utilisateur;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UtilisateurRepository {
    
    public Utilisateur findById(Long id) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.find(Utilisateur.class, id);
        } finally {
            em.close();
        }
    }
    
    public Utilisateur findByEmail(String email) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            TypedQuery<Utilisateur> query = em.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class);
            query.setParameter("email", email);
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    public void save(Utilisateur utilisateur) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            if (utilisateur.getId() == null) {
                System.out.println("Persistance d'un nouvel utilisateur: " + utilisateur.getEmail());
                em.persist(utilisateur);
            } else {
                System.out.println("Mise à jour d'un utilisateur existant: " + utilisateur.getEmail());
                em.merge(utilisateur);
            }
            em.getTransaction().commit();
            System.out.println("Opération de sauvegarde réussie pour: " + utilisateur.getEmail());
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde de l'utilisateur: " + utilisateur.getEmail());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }
}
