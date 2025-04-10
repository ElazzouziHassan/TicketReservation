package com.ticketservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "evenements")
public class Evenement implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private LocalDateTime dateHeure;
    
    @Column(nullable = false)
    private String lieu;
    
    @Column(nullable = false)
    private Integer capaciteMax;
    
    // Constructors
    public Evenement() {}
    
    public Evenement(String nom, String description, LocalDateTime dateHeure, String lieu, Integer capaciteMax) {
        this.nom = nom;
        this.description = description;
        this.dateHeure = dateHeure;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }
    
    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }
    
    public String getLieu() {
        return lieu;
    }
    
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public Integer getCapaciteMax() {
        return capaciteMax;
    }
    
    public void setCapaciteMax(Integer capaciteMax) {
        this.capaciteMax = capaciteMax;
    }
}
