/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vigneshwaren Sunder
 */
@Entity
@Table(name = "BIRD_TYPE")
public class BirdType {
    
    
    private String birdTypeName;
    private String birdTypeId;

    @Column(name = "Bird_Type_Name")
    public String getBirdTypeName() {
        return birdTypeName;
    }

    public void setBirdTypeName(String birdTypeName) {
        this.birdTypeName = birdTypeName;
    }

    @Id
    @Column(name = "Bird_Type_Id")
    public String getBirdTypeId() {
        return birdTypeId;
    }

  
    public void setBirdTypeId(String birdTypeId) {
        this.birdTypeId = birdTypeId;
    }
    
    
}
