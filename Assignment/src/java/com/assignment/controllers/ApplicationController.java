/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.controllers;

import com.assignment.mbeans.Admin;
import com.assignment.repository.Repository;
import com.assignment.repository.entities.EventInformation;
import com.assignment.repository.entities.AdminEntity;
import com.assignment.repository.entities.BirdEntity;
import com.assignment.repository.entities.BirdEntityId;
import com.assignment.repository.entities.BirdTypeEntity;
import com.assignment.repository.entities.CityEntity;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

/**
 *
 * @author Kishore Kumar Sunder
 */

@ManagedBean(name = "applicationController")
@SessionScoped
public class ApplicationController {

    @EJB
    private Repository repository;
    @EJB
    EventInformation bean;

    private BirdEntity bird;
    private CityEntity cityType;
    private AdminEntity admin;

    private String searchByName;

    private List<BirdEntity> birdList;
    
    private List<AdminEntity> userLst;
    private List<Admin> adminLst;
    private String adminName;
    private Admin adminManagedBean;

    public List<Admin> getAdminLst() {
        return adminLst;
    }

    public void setAdminLst(List<Admin> adminLst) {
        this.adminLst = adminLst;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Admin getAdminManagedBean() {
        return adminManagedBean;
    }

    public void setAdminManagedBean(Admin adminManagedBean) {
        this.adminManagedBean = adminManagedBean;
    }
    
    

    
    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }

    public CityEntity getCityType() {
        return cityType;
    }

    public void setCityType(CityEntity cityType) {
        this.cityType = cityType;
    }

    
    
    public BirdEntity getBird() {
        return bird;
    }

    public void setBird(BirdEntity bird) {
        this.bird = bird;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public List<BirdEntity> getBirdList() {
        return birdList;
    }

    public void setBirdList(List<BirdEntity> birdList) {
        this.birdList = birdList;
    }

    public List<AdminEntity> getUserLst() {
        return userLst;
    }

    public void setUserLst(List<AdminEntity> userLst) {
        this.userLst = userLst;
    }
    
    

//    @Inject
//    private AdminEntity adminEntity;
//    @Inject
//    BirdTypeEntity birdTypeEntity;
    
    public void addAdmin(com.assignment.mbeans.Admin localAdmin) throws NoSuchAlgorithmException {
        System.out.println("inside add admin" + localAdmin.getAdminEmail());

        String encoded = sha256(localAdmin.getPassword());
        System.out.println("passowrd" + encoded);
        localAdmin.setPassword(encoded);
        AdminEntity admin = convertAdminToEntity(localAdmin);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            repository.addAdmin(admin);
        } catch (Exception ex) {
        }
    }

    public void addBirdTypes(com.assignment.mbeans.BirdType localBirdType) {
        System.out.println("Inside controller : addBirdTypes()" + localBirdType.getBirdTypeName());
        BirdTypeEntity birdType = convertBirdTypesToEntity(localBirdType);
        try {
            repository.addBirdTypes(birdType);
            FacesContext.getCurrentInstance().getExternalContext().redirect("adminHome.xhtml");
        } catch (Exception ex) {
        }
    }

    public void addCityTypes(com.assignment.mbeans.City localCity) {
        System.out.println("Inside controller : addCityTypes()" + localCity.getCityName());
        CityEntity cityTypeEntity = convertCityTypesToEntity(localCity);
        try {
            repository.addCityTypes(cityTypeEntity);
            FacesContext.getCurrentInstance().getExternalContext().redirect("adminHome.xhtml");
        } catch (Exception ex) {
        }
    }

    public void addBirdInformation(com.assignment.mbeans.Bird localBirdInfo) throws Exception {
        System.out.println("Inside controller : addBirdInformation()");
        BirdTypeEntity birdType = repository.getBirdById(localBirdInfo.getBirdTypeId());
        CityEntity cityType = repository.getCityById(localBirdInfo.getCityId());
        BirdEntity birdInfo = convertBirdInfoToEntity(localBirdInfo, birdType, cityType);
        repository.addBirdInfo(birdInfo);
        FacesContext.getCurrentInstance().getExternalContext().redirect("adminHome.xhtml");
        try {
            repository.addBirdInfo(birdInfo);
        } catch (Exception ex) {
        }
    }

    public List<com.assignment.repository.entities.BirdTypeEntity> getAllBirdTypes() throws Exception {
        System.out.println("Inside controller : getAllBirdTypes()");
        try {
            return repository.getBirdTypes();
        } catch (Exception ex) {

        }

        return null;
    }

    public List<com.assignment.repository.entities.CityEntity> getAllCityTypes() throws Exception {
        System.out.println("Inside controller : getAllCityTypes()");
        try {
            return repository.getCityTypes();
        } catch (Exception ex) {

        }

        return null;
    }

    public List<BirdEntity> getBirdDetails() {
        System.out.println("Inside controller : getBirdDetails()");
        try {
            List<BirdEntity> birdDetailsLst = repository.getAllBirdDetails();
            return birdDetailsLst;
        } catch (Exception ex) {
            // Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void fetchUserInfo() {
        System.out.println("Inside controller : getUserDetails()");
        try {
            
            List<AdminEntity> adminDetailsLst = repository.getAllAdminDetails();
            this.setUserLst(adminDetailsLst);
            List<Admin> adminLst = convertEntityToManaged(adminDetailsLst);
//            //this.getAdmin().setAdminName(adminDetailsLst.get(0).getAdminName());
//             this.setAdmin(adminDetailsLst.get(0));
             this.setAdminLst(adminLst);
             FacesContext.getCurrentInstance().getExternalContext().redirect("myProfile.xhtml");
        } catch (Exception ex) {
            // Logger.getLogger(PropertyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void removeProperty(BirdEntity birdEntity) {
        System.out.println("Inside controller : removeProperty()" + birdEntity.getBirdName());
        try {
            //remove this property from db via EJB
            repository.removeBirds(birdEntity);
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bird has been deleted succesfully"));     
        } catch (Exception ex) {

        }
    }
    
     public void removeCityTypes(CityEntity cityEntity) {
        System.out.println("Inside controller : removeProperty()" + cityEntity.getCityName());
        try {
            
            repository.remCityVal(cityEntity);
            
        } catch (Exception ex) {

        }
    }
    
     public void editCityTypes(CityEntity cityEntity) {
        System.out.println("Inside controller : edit()" + cityEntity.getCityName());
        try {
            List<CityEntity> cityEntityLst = repository.getCityTypes();
            for (CityEntity city : cityEntityLst) {
                if (city.getCityName().equals(cityEntity.getCityName())) {
                    System.out.println("Inside controller : edit(): match found");
                    this.cityType = city;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("editCityTypes.xhtml");
                }
            }
        } catch (Exception ex) {
        }

    }
     
   
    public void editBirds(BirdEntity birdEntity) {
        System.out.println("Inside controller : edit()" + birdEntity.getBirdName());
        try {
            List<BirdEntity> birdDetailsLst = repository.getAllBirdDetails();
            for (BirdEntity bird : birdDetailsLst) {
                if (bird.getBirdName().equals(birdEntity.getBirdName()) && bird.getBirdTypeEntity().getBirdTypeId() == birdEntity.getBirdTypeEntity().getBirdTypeId()
                        && bird.getCityEntityId().getCityId() == birdEntity.getCityEntityId().getCityId()) {
                    System.out.println("Inside controller : edit(): match found");
                    this.bird = bird;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("editBirds.xhtml");
                }
            }
        } catch (Exception ex) {
        }

    }

    public void updateBird(BirdEntity bird) {
        System.out.println("Inside controller : updateBird" + bird.getBirdName());
        try {
            repository.updateBird(bird);
            FacesContext.getCurrentInstance().getExternalContext().redirect("displayBirds.xhtml");
        } catch (Exception ex) {
        }
    }
    
    public void updateCity(CityEntity c) {
        System.out.println("Inside controller : updateBird: " + c.getCityName());
        try {
            repository.updateCity(c);
            FacesContext.getCurrentInstance().getExternalContext().redirect("modifyCityTypes.xhtml");
        } catch (Exception ex) {
        }
    }

    public void searchBird(String birdName, int cityId) {
        System.out.println("Inside controller : searchBird()" + birdName + "," + cityId);
        List<BirdEntity> birdLst = new ArrayList<BirdEntity>();
        if (!birdName.isEmpty() && 0 == cityId) {
            searchBirdsByName(birdName);
        } else if (birdName.isEmpty() && cityId != 0) {
            searchBirdsByCity(cityId);
        } else {
            try {
                System.out.println("Inside controller : before impl calling searchBird()");
                birdLst = repository.searchBirds(birdName, cityId);
                this.setBirdList(birdLst);
            } catch (Exception ex) {
                // Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void searchBirdsByName(String birdName) {
        System.out.println("Inside controller : searchByName()" + birdName);
        try {
            List<BirdEntity> birdLst = repository.searchByBirdName(birdName);
            this.setBirdList(birdLst);
        } catch (Exception ex) {
            //Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchBirdsByCity(int cityId) {
        System.out.println("Inside controller : searchBirdsByCity()" + cityId);
        try {
            List<BirdEntity> birdLst = repository.searchByCity(cityId);
            this.setBirdList(birdLst);
        } catch (Exception ex) {
            //Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BirdEntity> searchBirdsById(int value) {

        System.out.println("Inside controller : searchBirdsById()" + value);
        List<BirdEntity> birdLst = new ArrayList<BirdEntity>();
        try {
            birdLst = repository.searchByBirdId(value);
            // return birdLst;
            //this.setBirdList(birdLst);
            //FacesContext.getCurrentInstance().getExternalContext().redirect("searchBirds.xhtml");
        } catch (Exception ex) {
            //Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return birdLst;
    }

    public String searchBirdLst(String birdName, int cityId) {
        System.out.println("Inside controller : searchBirdLst()" + birdName);
        this.searchBird(birdName, cityId);
        return "searchBirds";
    }

    public String returnSearchBirdLst(String birdName, int cityId) {
        System.out.println("Inside controller : returnSearchBirdLst()" + birdName);
        try {
            this.setBird(repository.searchBirdsByNameCity(birdName, cityId));
        } catch (Exception ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "user/viewBirds?faces-redirect=true";
    }

    public String fetchBirdLst() {
        System.out.println("Inside controller : fetchBirdLst()");
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        int value = Integer.parseInt(params.get("action"));
        birdList = this.searchBirdsById(value);
        return "searchBirds";
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("../index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    private AdminEntity convertAdminToEntity(com.assignment.mbeans.Admin localAdmin) {
        try {
            AdminEntity adminEntity = new AdminEntity();
            String adminEmail = localAdmin.getAdminEmail();
            String adminName = localAdmin.getAdminName();
            String password = localAdmin.getPassword();
            adminEntity.setAdminEmail(adminEmail);
            adminEntity.setAdminName(adminName);
            adminEntity.setPassword(password);
            adminEntity.setUserType(localAdmin.getUserType());
            System.out.println("inside convert" + adminEntity.getAdminEmail());
            return adminEntity;
        } catch (Exception ex) {
        }
        return null;
    }

    private BirdTypeEntity convertBirdTypesToEntity(com.assignment.mbeans.BirdType localBirdType) {
        try {
            BirdTypeEntity birdTypeEntity = new BirdTypeEntity();
            String birdName = localBirdType.getBirdTypeName();
            birdTypeEntity.setBirdTypeName(birdName);
            System.out.println("inside convert" + birdTypeEntity.getBirdTypeName());
            return birdTypeEntity;
        } catch (Exception ex) {
        }
        return null;
    }

    private CityEntity convertCityTypesToEntity(com.assignment.mbeans.City localCityName) {
        try {
            CityEntity cityEntity = new CityEntity();
            String cityName = localCityName.getCityName();
            cityEntity.setCityName(cityName);
            System.out.println("inside city convert" + cityEntity.getCityName());
            return cityEntity;
        } catch (Exception ex) {
        }
        return null;
    }

    private BirdEntity convertBirdInfoToEntity(com.assignment.mbeans.Bird localBirdInfo, BirdTypeEntity birdType, CityEntity city) {
        BirdEntity birdInfo = new BirdEntity();
        birdInfo.setBirdName(localBirdInfo.getBirdName());
        birdInfo.setBirdTypeEntity(birdType);
        birdInfo.setHabitat(localBirdInfo.getHabitat());
        birdInfo.setDescription(localBirdInfo.getDescription());
        birdInfo.setAvgCount(localBirdInfo.getAverageCount());
        birdInfo.setCityEntityId(city);
        return birdInfo;
    }
    
    private List<Admin> convertEntityToManaged(List<AdminEntity> adminEntityLst){
        List<Admin> adminLst = new ArrayList<Admin>();
        
        for(AdminEntity entity : adminEntityLst){
            Admin admin = new Admin();
            admin.setAdminEmail(entity.getAdminEmail());
            admin.setAdminName(entity.getAdminName());
            admin.setPassword(entity.getPassword());
            admin.setUserType(entity.getUserType());
            adminLst.add(admin);
        }
        return adminLst;
    }
    
     public void submit() {
        for(Admin user:this.getAdminLst()){
            Admin admin = new Admin();
            if(user.getAdminEmail().equals(adminName)){
                admin.setAdminEmail(user.getAdminEmail());
                admin.setAdminName(user.getAdminName());
                admin.setUserType(user.getUserType());
                this.setAdminManagedBean(admin);
            }
        }
    }
}
