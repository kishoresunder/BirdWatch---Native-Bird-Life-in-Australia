/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Kishore Kumar Sunder
 */
@RequestScoped
@Named(value = "bird")
public class Bird {
    
    @NotNull(message = "Please enter the bird name")
    @Pattern(regexp ="[a-zA-Z][a-zA-Z ]+",message = "The bird name should have alphabets alone")
    private String birdName;
    @NotNull(message = "Please select the bird type")
    private int birdTypeId;
    @NotNull(message = "Please enter the habitat")
    private String habitat;
    @NotNull(message = "Please enter the description")
    private String description;
    @NotNull(message = "Please enter the average count")
    private int averageCount;
    
    private int cityId;

    public String getBirdName() {
        return birdName;
    }

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }

    public int getBirdTypeId() {
        return birdTypeId;
    }

    public void setBirdTypeId(int birdTypeId) {
        this.birdTypeId = birdTypeId;
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

    public int getAverageCount() {
        return averageCount;
    }

    public void setAverageCount(int averageCount) {
        this.averageCount = averageCount;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
            
    
    
}
