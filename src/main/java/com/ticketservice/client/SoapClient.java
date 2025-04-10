package com.ticketservice.client;

import com.ticketservice.model.Billet;
import com.ticketservice.model.Reservation;
import com.ticketservice.model.Utilisateur;
import com.ticketservice.service.BilletService;
import com.ticketservice.service.ReservationService;
import com.ticketservice.service.UtilisateurService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;


public class SoapClient {
    
    public static void main(String[] args) throws Exception {
        // URLs des services SOAP
        URL billetUrl = new URL("http://0.0.0.0:8181/BilletService?wsdl");
        URL reservationUrl = new URL("http://0.0.0.0:8181/ReservationService?wsdl");
        URL utilisateurUrl = new URL("http://0.0.0.0:8181/UtilisateurService?wsdl");
        
        // QNames pour les services
        QName billetQName = new QName("http://service.ticketservice.com/", "BilletService");
        QName reservationQName = new QName("http://service.ticketservice.com/", "ReservationService");
        QName utilisateurQName = new QName("http://service.ticketservice.com/", "UtilisateurService");
        
        // Création des services
        Service billetService = Service.create(billetUrl, billetQName);
        Service reservationService = Service.create(reservationUrl, reservationQName);
        Service utilisateurService = Service.create(utilisateurUrl, utilisateurQName);
        
        // Obtention des ports
        BilletService billetPort = billetService.getPort(BilletService.class);
        ReservationService reservationPort = reservationService.getPort(ReservationService.class);
        UtilisateurService utilisateurPort = utilisateurService.getPort(UtilisateurService.class);
        
        // Test des services
        System.out.println("=== Test des services SOAP ===");
        
        // Test inscription utilisateur
        boolean inscriptionOk = utilisateurPort.inscrireUtilisateur(
            "test@example.com", "Dupont", "Jean", "password123");
        System.out.println("Inscription utilisateur: " + (inscriptionOk ? "OK" : "Échec"));
        
        // Test connexion utilisateur
        Utilisateur utilisateur = utilisateurPort.connecterUtilisateur("test@example.com", "password123");
        System.out.println("Connexion utilisateur: " + (utilisateur != null ? "OK" : "Échec"));
        
        if (utilisateur != null) {
            // Test récupération des billets
            List<Billet> billets = billetPort.getBillets();
            System.out.println("Nombre de billets disponibles: " + billets.size());
            
            if (!billets.isEmpty()) {
                // Test réservation de billet
                boolean reservationOk = reservationPort.reserverBillet(
                    utilisateur.getId(), billets.get(0).getId(), 2);
                System.out.println("Réservation de billet: " + (reservationOk ? "OK" : "Échec"));
                
                // Test récupération des réservations
                List<Reservation> reservations = reservationPort.getReservationsUtilisateur(utilisateur.getId());
                System.out.println("Nombre de réservations: " + reservations.size());
                
                if (!reservations.isEmpty()) {
                    // Test annulation de réservation
                    boolean annulationOk = reservationPort.annulerReservation(reservations.get(0).getId());
                    System.out.println("Annulation de réservation: " + (annulationOk ? "OK" : "Échec"));
                }
            }
        }
    }
}
