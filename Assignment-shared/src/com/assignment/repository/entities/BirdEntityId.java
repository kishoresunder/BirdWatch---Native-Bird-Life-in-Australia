/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Vigneshwaren Sunder
 */
public class BirdEntityId implements Serializable{
    
    
   
    private String birdName;
    
      
   
    private int birdTypeEntity;
    
    
    
    private int cityEntityId;

    public BirdEntityId(String birdName, int birdTypeEntity, int cityEntityId) {
        this.birdName = birdName;
        this.birdTypeEntity = birdTypeEntity;
        this.cityEntityId = cityEntityId;
    }

    
    
    public String getBirdName() {
        return birdName;
    }

    public int getBirdTypeEntity() {
        return birdTypeEntity;
    }

    public int getCityEntityId() {
        return cityEntityId;
    }
    
    
    
    public boolean equals(Object object) {
        if (object instanceof BirdEntityId) {
            BirdEntityId pk = (BirdEntityId)object;
            return true;
        } else {
            return false;
        }
    }
 
    public int hashCode() {
        return 0;
    }
    
}
