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
@Named(value = "city")
public class City {
    private int cityId;
    @NotNull(message = "Please enter the City Name")
    @Pattern(regexp ="[a-zA-Z][a-zA-Z ]+",message = "The City Name name should have alphabets alone")
    private String cityName;

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
    
    
}
