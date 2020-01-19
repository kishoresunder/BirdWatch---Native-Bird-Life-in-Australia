/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vigneshwaren Sunder
 */
@Entity
@Table(name = "BIRD")
public class Bird implements Serializable {
    
    private String birdName;
    private int birdTypeId;
    private String habitat;
    private String description;
    private int avgCount;
    private int cityId;

    @Id
    @Column(name = "Bird_Name")
    public String getBirdName() {
        return birdName;
    }

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }

    @Id
    @Column(name = "Bird_Type_Id")
    public int getBirdTypeId() {
        return birdTypeId;
    }

    public void setBirdTypeId(int birdTypeId) {
        this.birdTypeId = birdTypeId;
    }

    @Column(name = "Bird_Habitat")
    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @Column(name = "Bird_Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "Avg_Count")
    public int getAvgCount() {
        return avgCount;
    }

    public void setAvgCount(int avgCount) {
        this.avgCount = avgCount;
    }

    @Column(name = "City_Id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
   
 }
