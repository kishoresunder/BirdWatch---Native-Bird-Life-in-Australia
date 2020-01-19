/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.mbeans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Kishore Kumar Sunder
 */
@RequestScoped
@Named(value = "admin")
public class Admin {

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "This is not a valid email")
    private String adminEmail;
    @NotNull(message = "Name cannot be null")
    private String adminName;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "8 character long with atleast one capital letter, one small letter, a special character and a number")
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Confirm Password cannot be null")
    private String confirmPassword;

    @NotNull(message = "Select a user type")
    private String userType;
    
    
    private List<Admin> adminLst;

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Admin> getAdminLst() {
        return adminLst;
    }

    public void setAdminLst(List<Admin> adminLst) {
        this.adminLst = adminLst;
    }
    
    

    public void submit() {
        for(Admin user:getAdminLst()){
            if(user.getAdminName().equals(adminName)){
                System.out.println("User " + user.getAdminEmail()+ " submitted");
            }
        }
    }
}
