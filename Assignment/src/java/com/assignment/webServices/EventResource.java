/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.webServices;

import com.assignment.mbeans.Admin;
import com.assignment.repository.Repository;
import com.assignment.repository.entities.EventInformation;
import com.assignment.repository.entities.AdminEntity;
import com.assignment.repository.entities.BirdEntity;
import com.assignment.repository.entities.BirdTypeEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author Vigneshwaren Sunder
 */
@Path("user")
@ManagedBean(name = "userResource")
@SessionScoped
public class EventResource {

    @EJB
    EventInformation bean;
    @EJB
    Repository repository;

    List<EventInformation> event;

    public List<EventInformation> getEvent() {
        return event;
    }

    public void setEvent(List<EventInformation> event) {
        this.event = event;
    }


    /**
     * Creates a new instance of UserResource
     */
    public EventResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.assignment.webServices.UserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        //return "<html><body><h1> Hello " + bean.getName() + "," + bean.getUserRole() + "! </h1></body></html>";
        return null;
    }

    @GET
    @Path("/getAll")
    public Response getEvents(@DefaultValue("kishore") @QueryParam("name") String userName,@Context HttpServletRequest request, @Context HttpServletResponse response) throws Exception {
        if(!userName.isEmpty() || !userName.equals("")){
            List<String> event = repository.getEvents(userName);
            if(event != null){
            bean.setEventName(event.get(0));
            bean.setEventDate(event.get(1));
            bean.setDescription(event.get(2));
            bean.setStreet(event.get(3));
            bean.setCity(event.get(4));
            bean.setPostCode(Integer.parseInt(event.get(5)));
            }
            String myJsfPage = "/userEdit.xhtml";
            String contextPath = request.getContextPath();
            try {
                response.sendRedirect(contextPath + myJsfPage);
            } catch (IOException ex) {
                Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.status(Status.ACCEPTED).build();

    }

    

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public String setName(@FormParam("eventName") String eventName, @FormParam("date") String eventDate,
            @FormParam("description") String description, @FormParam("city") String city,@FormParam("street") String street,
            @FormParam("postCode") int postCode) {
        bean.setEventName(eventName);
        bean.setEventDate(eventDate);
        bean.setDescription(description);
        bean.setCity(city);
        bean.setStreet(street);
        bean.setPostCode(postCode);
        try {
            repository.setEvent(bean.getEventName(),bean.getDescription(),bean.getEventDate(),bean.getStreet(),bean.getCity(),bean.getPostCode());
        } catch (Exception ex) {
            Logger.getLogger(EventResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Added";
    }

    public EventInformation getBean() {
        return bean;
    }

    public void setBean(EventInformation bean) {
        this.bean = bean;
    }

}
