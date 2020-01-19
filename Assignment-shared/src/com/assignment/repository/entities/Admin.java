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
    @NamedQuery(name = Admin.GET_ALL_QUERY_NAME, query = "SELECT p FROM Admin p")})
public class Admin implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "getAdmin";

    private String adminEmail;
    private String adminName;
    private String password;

    @Id
    @Column(name = "Admin_Email")
    public String getAdminEmail() {
        return adminEmail;
    }

    
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Column(name = "Admin_Name")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  

}
