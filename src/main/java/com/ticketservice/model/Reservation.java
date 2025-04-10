package com.ticketservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "billet_id", nullable = false)
    private Billet billet;
    
    @Column(nullable = false)
    private Integer quantite;
    
    @Column(nullable = false)
    private LocalDateTime dateReservation;
    
    @Column(nullable = false)
    private String statut; // "CONFIRMÉ", "ANNULÉ", etc.
    
    // Constructors
    public Reservation() {}
    
    public Reservation(Utilisateur utilisateur, Billet billet, Integer quantite, LocalDateTime dateReservation, String statut) {
        this.utilisateur = utilisateur;
        this.billet = billet;
        this.quantite = quantite;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public Billet getBillet() {
        return billet;
    }
    
    public void setBillet(Billet billet) {
        this.billet = billet;
    }
    
    public Integer getQuantite() {
        return quantite;
    }
    
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }
    
    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
}
