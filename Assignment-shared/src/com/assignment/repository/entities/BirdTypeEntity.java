/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vigneshwaren Sunder
 */
@Entity
@Table(name = "BIRD_TYPE")
@NamedQueries({@NamedQuery(name = "BirdTypeEntity.getAllBirdTypes", query = "SELECT c FROM BirdTypeEntity c"),
               @NamedQuery(name = "BirdTypeEntity.getBirdTypeById", query = "SELECT c FROM BirdTypeEntity c where c.birdTypeId=:birdTypeId")})
public class BirdTypeEntity implements Serializable{
    
    @Column(name = "Bird_Type_Name")
    private String birdTypeName;
    @Id
    @Column(name = "Bird_Type_Id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int birdTypeId;
    
    @OneToMany(mappedBy = "birdTypeEntity", fetch = FetchType.EAGER)
    private Set<BirdEntity> birds;

   
    public String getBirdTypeName() {
        return birdTypeName;
    }

    public void setBirdTypeName(String birdTypeName) {
        this.birdTypeName = birdTypeName;
    }

   
    public int getBirdTypeId() {
        return birdTypeId;
    }

  
    public void setBirdTypeId(int birdTypeId) {
        this.birdTypeId = birdTypeId;
    }

    public Set<BirdEntity> getBirds() {
        return birds;
    }

    public void setBirds(Set<BirdEntity> birds) {
        this.birds = birds;
    }
    
    
}
