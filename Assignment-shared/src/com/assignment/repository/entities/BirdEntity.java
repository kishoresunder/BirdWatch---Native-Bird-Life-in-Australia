/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Vigneshwaren Sunder
 */
@Entity
@IdClass(BirdEntityId.class)
@Table(name = "BIRD")
@NamedQueries({@NamedQuery(name = "BirdEntity.getAllBirdDetails", query = "SELECT c FROM BirdEntity c"),
    @NamedQuery(name = "BirdEntity.getBirdByName", query="SELECT a FROM BirdEntity a"),
    @NamedQuery(name = "BirdEntity.getAllBirdByCityId", query="SELECT a FROM BirdEntity a where a.cityEntityId=:cityId")
})
public class BirdEntity implements Serializable {
    
    @Id
    @Column(name = "Bird_Name")
    private String birdName;
    
    @Id    
    @ManyToOne
    private BirdTypeEntity birdTypeEntity;
    
    @Column(name = "Bird_Habitat")
    private String habitat;
    
    @Column(name = "Bird_Description")
    private String description;
    
    @Column(name = "Avg_Count")
    private int avgCount;
    
    @Id
    @ManyToOne
    private CityEntity cityEntityId;

    
    public String getBirdName() {
        return birdName;
    }

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }

    
   

    
    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public int getAvgCount() {
        return avgCount;
    }

    public void setAvgCount(int avgCount) {
        this.avgCount = avgCount;
    }

    public BirdTypeEntity getBirdTypeEntity() {
        return birdTypeEntity;
    }

    public void setBirdTypeEntity(BirdTypeEntity birdTypeEntity) {
        this.birdTypeEntity = birdTypeEntity;
    }

    public CityEntity getCityEntityId() {
        return cityEntityId;
    }

    public void setCityEntityId(CityEntity cityEntityId) {
        this.cityEntityId = cityEntityId;
    }

    
 }
