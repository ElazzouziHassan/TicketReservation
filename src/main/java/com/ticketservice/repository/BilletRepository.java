package com.ticketservice.repository;

import com.ticketservice.model.Billet;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BilletRepository {
    
    public List<Billet> findAll() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            TypedQuery<Billet> query = em.createQuery("SELECT b FROM Billet b", Billet.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Billet findById(Long id) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.find(Billet.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Billet> findByEvenementId(Long evenementId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            TypedQuery<Billet> query = em.createQuery(
                "SELECT b FROM Billet b WHERE b.evenement.id = :evenementId", Billet.class);
            query.setParameter("evenementId", evenementId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void save(Billet billet) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            if (billet.getId() == null) {
                em.persist(billet);
            } else {
                em.merge(billet);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void updateQuantiteDisponible(Long billetId, Integer nouvelleQuantite) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            Billet billet = em.find(Billet.class, billetId);
            if (billet != null) {
                billet.setQuantiteDisponible(nouvelleQuantite);
                em.merge(billet);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
