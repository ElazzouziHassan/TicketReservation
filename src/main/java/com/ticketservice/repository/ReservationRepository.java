package com.ticketservice.repository;

import com.ticketservice.model.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationRepository {
    
    public Reservation findById(Long id) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Reservation> findByUtilisateurId(Long utilisateurId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            TypedQuery<Reservation> query = em.createQuery(
                "SELECT r FROM Reservation r WHERE r.utilisateur.id = :utilisateurId", Reservation.class);
            query.setParameter("utilisateurId", utilisateurId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void save(Reservation reservation) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            if (reservation.getId() == null) {
                em.persist(reservation);
            } else {
                em.merge(reservation);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void updateStatut(Long reservationId, String nouveauStatut) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            Reservation reservation = em.find(Reservation.class, reservationId);
            if (reservation != null) {
                reservation.setStatut(nouveauStatut);
                em.merge(reservation);
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
