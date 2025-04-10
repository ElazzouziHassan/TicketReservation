package com.ticketservice;

import com.ticketservice.service.BilletService;
import com.ticketservice.service.ReservationService;
import com.ticketservice.service.UtilisateurService;
import com.ticketservice.repository.DatabaseConnection;
import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Démarrage de l'application...");
            
            // Vérifier la connexion à la base de données
            System.out.println("Test de la connexion à la base de données...");
            DatabaseConnection.getEntityManager().close();
            System.out.println("Connexion à la base de données réussie.");
            
            // Publier les services SOAP
            String baseAddress = "http://0.0.0.0:8181/";
            
            Endpoint.publish(baseAddress + "BilletService", new BilletService());
            System.out.println("BilletService publié à : " + baseAddress + "BilletService?wsdl");
            
            Endpoint.publish(baseAddress + "ReservationService", new ReservationService());
            System.out.println("ReservationService publié à : " + baseAddress + "ReservationService?wsdl");
            
            Endpoint.publish(baseAddress + "UtilisateurService", new UtilisateurService());
            System.out.println("UtilisateurService publié à : " + baseAddress + "UtilisateurService?wsdl");
            
            System.out.println("Services SOAP démarrés. Appuyez sur Ctrl+C pour arrêter.");
        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage de l'application:");
            e.printStackTrace();
        }
    }
}
