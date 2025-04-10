package com.ticketservice.service;

import com.ticketservice.model.Billet;
import com.ticketservice.model.Reservation;
import com.ticketservice.model.Utilisateur;
import com.ticketservice.repository.BilletRepository;
import com.ticketservice.repository.ReservationRepository;
import com.ticketservice.repository.UtilisateurRepository;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.List;

@WebService(serviceName = "ReservationService")
public class ReservationService {
    
    private ReservationRepository reservationRepository = new ReservationRepository();
    private BilletRepository billetRepository = new BilletRepository();
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    
    @WebMethod
    public boolean reserverBillet(
            @WebParam(name = "utilisateurId") Long utilisateurId,
            @WebParam(name = "billetId") Long billetId,
            @WebParam(name = "quantite") Integer quantite) {
        
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId);
            Billet billet = billetRepository.findById(billetId);
            
            if (utilisateur == null || billet == null) {
                return false;
            }
            
            if (billet.getQuantiteDisponible() < quantite) {
                return false;
            }
            
            // Créer la réservation
            Reservation reservation = new Reservation(
                utilisateur,
                billet,
                quantite,
                LocalDateTime.now(),
                "CONFIRMÉ"
            );
            
            reservationRepository.save(reservation);
            
            // Mettre à jour la quantité disponible
            billet.setQuantiteDisponible(billet.getQuantiteDisponible() - quantite);
            billetRepository.save(billet);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @WebMethod
    public boolean annulerReservation(@WebParam(name = "reservationId") Long reservationId) {
        try {
            Reservation reservation = reservationRepository.findById(reservationId);
            if (reservation == null) {
                return false;
            }
            
            // Mettre à jour le statut de la réservation
            reservationRepository.updateStatut(reservationId, "ANNULÉ");
            
            // Remettre les billets en disponibilité
            Billet billet = reservation.getBillet();
            billet.setQuantiteDisponible(billet.getQuantiteDisponible() + reservation.getQuantite());
            billetRepository.save(billet);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @WebMethod
    public List<Reservation> getReservationsUtilisateur(@WebParam(name = "utilisateurId") Long utilisateurId) {
        return reservationRepository.findByUtilisateurId(utilisateurId);
    }
}
