/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.mbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vigneshwaren Sunder
 */
@Named(value = "webServiceUser")
@SessionScoped
public class WebServiceUser implements Serializable {

    private String eventName;
    private String eventDate;
     private String description;
    private String street;
     private String city;
    private int postCode;
    private boolean isAdded;
    private EventResource userWebService;

    public void setUserWebService() {
        this.userWebService = new EventResource();
        userWebService.setPostNameForm(getEventName(),getEventDate(),getDescription(),getStreet(),getCity(),getPostCode());
        
        this.setIsAdded(true);
    }

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

    public boolean isIsAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    
    static class EventResource {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/Assignment/webresources";

        public EventResource() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("user");
        }

        public <T> T getEvents() throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("getAll");
            return (T) resource;
        }

        public void setEventBean() throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null);
        }

        public String putEvents() throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("saveAll");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        }
        
        public void setPostNameForm(String eventName,String date,String description,String street,String city,int postCode) throws ClientErrorException {
            Form form = new Form();
            form.param("eventName", eventName);
            form.param("date", date);
            form.param("description", description);
            form.param("city", city);
            form.param("street", street);
            form.param("postCode", String.valueOf(postCode));
            webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
            
        }

        public void close() {
            client.close();
        }
    }
  
}
