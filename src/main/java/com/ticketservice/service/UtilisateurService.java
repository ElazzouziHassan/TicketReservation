package com.ticketservice.service;

import com.ticketservice.model.Utilisateur;
import com.ticketservice.repository.UtilisateurRepository;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "UtilisateurService")
public class UtilisateurService {
    
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    
    @WebMethod
    public boolean inscrireUtilisateur(
            @WebParam(name = "email") String email,
            @WebParam(name = "nom") String nom,
            @WebParam(name = "prenom") String prenom,
            @WebParam(name = "motDePasse") String motDePasse) {
        
        try {
            System.out.println("Tentative d'inscription pour l'email: " + email);
            
            // Vérifier si l'email existe déjà
            Utilisateur existingUser = utilisateurRepository.findByEmail(email);
            if (existingUser != null) {
                System.out.println("Échec: Email déjà utilisé: " + email);
                return false;
            }
            
            Utilisateur utilisateur = new Utilisateur(email, nom, prenom, motDePasse);
            utilisateurRepository.save(utilisateur);
            System.out.println("Inscription réussie pour: " + email + " avec ID: " + utilisateur.getId());
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de l'inscription de l'utilisateur: " + email);
            e.printStackTrace();
            return false;
        }
    }
    
    @WebMethod
    public Utilisateur connecterUtilisateur(
            @WebParam(name = "email") String email,
            @WebParam(name = "motDePasse") String motDePasse) {
        
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
            return utilisateur;
        }
        return null;
    }
    
    @WebMethod
    public Utilisateur getUtilisateurById(@WebParam(name = "utilisateurId") Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId);
    }
}
