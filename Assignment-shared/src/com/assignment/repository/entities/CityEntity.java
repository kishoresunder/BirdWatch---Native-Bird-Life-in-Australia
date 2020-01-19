/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
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
@Table(name = "CITY")
@NamedQueries({@NamedQuery(name = "CityEntity.getAllCities", query = "SELECT c FROM CityEntity c"),
        @NamedQuery(name = "CityEntity.getCityById", query = "SELECT c FROM CityEntity c where c.cityId=:cityId")})
public class CityEntity implements Serializable{
    
    @Id
    @Column(name = "City_Id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int cityId;
    @Column(name = "City_Name")
    private String cityName;

    @OneToMany(mappedBy = "cityEntityId", fetch = FetchType.EAGER)
    private Set<BirdEntity> birds;

    
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Set<BirdEntity> getBirds() {
        return birds;
    }

    public void setBirds(Set<BirdEntity> birds) {
        this.birds = birds;
    }
    
    
    
}
