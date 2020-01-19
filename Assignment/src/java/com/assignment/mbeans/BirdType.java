/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.mbeans;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Kishore Kumar Sunder
 */
@RequestScoped
@Named(value = "birdType")
public class BirdType {
    
    @NotNull(message = "Please enter the bird Type")
    @Pattern(regexp ="[a-zA-Z][a-zA-Z ]+",message = "The bird Type name should have alphabets alone")
    private String birdTypeName;
    private int birdTypeId;
    private List<String> birdTypeLst;

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

    public List<String> getBirdTypeLst() {
        return birdTypeLst;
    }

    public void setBirdTypeLst(List<String> birdTypeLst) {
       
        this.birdTypeLst = birdTypeLst;
    }

    
    
     
}
