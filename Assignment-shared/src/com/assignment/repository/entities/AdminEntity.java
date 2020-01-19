package com.assignment.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Kishore Kumar Sunder
 */
@Entity
@Table(name = "ADMIN")
@NamedQueries({
    @NamedQuery(name = "Admin.getAllAdmins", query = "SELECT c FROM AdminEntity c")})
public class AdminEntity implements Serializable {

   

    @Id
    @Column(name = "Admin_Email")
    private String adminEmail;
    @Column(name = "Admin_Name")
    private String adminName;
    @Column(name = "Password")
    private String password;

    @Column(name="UserType")
    private String userType;
   
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
    
    

}
