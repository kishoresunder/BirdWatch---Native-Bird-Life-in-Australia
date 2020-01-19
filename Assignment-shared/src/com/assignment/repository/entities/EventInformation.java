/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.repository.entities;

import java.util.Date;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Vigneshwaren Sunder
 */
@Singleton
@LocalBean
@Entity
@Table(name = "Events")
@NamedQueries({@NamedQuery(name = "EventInformation.getAllEvent", query = "SELECT c FROM EventInformation c where c.eventName=:eventName")})
public class EventInformation {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Id
    @Column(name = "Event_Name")
    private String eventName;
    @Column(name = "Event_Date")
    private String eventDate;
    @Column(name = "Event_Description")
    private String description;
    @Column(name = "Street")
    private String street;
    @Column(name = "City")
    private String city;
    @Column(name = "PostCode")
    private int postCode;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

   
    
}
