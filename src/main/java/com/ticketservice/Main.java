package com.ticketservice;

import com.ticketservice.service.BilletService;
import com.ticketservice.service.ReservationService;
import com.ticketservice.service.UtilisateurService;
import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        String baseAddress = "http://0.0.0.0:8181/";
        
        Endpoint.publish(baseAddress + "BilletService", new BilletService());
        System.out.println("BilletService publié à : " + baseAddress + "BilletService?wsdl");
        
        Endpoint.publish(baseAddress + "ReservationService", new ReservationService());
        System.out.println("ReservationService publié à : " + baseAddress + "ReservationService?wsdl");
        
        Endpoint.publish(baseAddress + "UtilisateurService", new UtilisateurService());
        System.out.println("UtilisateurService publié à : " + baseAddress + "UtilisateurService?wsdl");
        
        System.out.println("Services SOAP démarrés. Appuyez sur Ctrl+C pour arrêter.");
    }
}
