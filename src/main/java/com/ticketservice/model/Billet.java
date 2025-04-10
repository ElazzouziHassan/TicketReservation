package com.ticketservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "billets")
public class Billet implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "evenement_id", nullable = false)
    private Evenement evenement;
    
    @Column(nullable = false)
    private String categorie;
    
    @Column(nullable = false)
    private BigDecimal prix;
    
    @Column(nullable = false)
    private Integer quantiteDisponible;
    
    // Constructors
    public Billet() {}
    
    public Billet(Evenement evenement, String categorie, BigDecimal prix, Integer quantiteDisponible) {
        this.evenement = evenement;
        this.categorie = categorie;
        this.prix = prix;
        this.quantiteDisponible = quantiteDisponible;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Evenement getEvenement() {
        return evenement;
    }
    
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    
    public String getCategorie() {
        return categorie;
    }
    
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    public BigDecimal getPrix() {
        return prix;
    }
    
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    
    public Integer getQuantiteDisponible() {
        return quantiteDisponible;
    }
    
    public void setQuantiteDisponible(Integer quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }
}
