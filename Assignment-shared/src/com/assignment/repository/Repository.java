package com.assignment.repository;
import com.assignment.repository.entities.AdminEntity;
import com.assignment.repository.entities.BirdEntity;
import com.assignment.repository.entities.BirdEntityId;
import com.assignment.repository.entities.BirdTypeEntity;
import com.assignment.repository.entities.CityEntity;
import com.assignment.repository.entities.EventInformation;
import java.util.List;
import javax.ejb.Remote;
/**
 *
 * @author Kishore Kumar Sunder
 */
@Remote
public interface Repository{
    
    
    public void addAdmin(AdminEntity admin) throws Exception;
    
    public List<String> getEvents(String userName) throws Exception;
    public void setEvent(String name, String desc, String date,String street, String city, int postCode) throws Exception;
    
    public void addBirdTypes(BirdTypeEntity birdTypes) throws Exception;
    
    public void addCityTypes(CityEntity cityTypes) throws Exception;
    
    public void addBirdInfo(BirdEntity bird) throws Exception;
     
     public BirdTypeEntity getBirdById(int birdType) throws Exception;
     
     public CityEntity getCityById(int cityId) throws Exception;
   
    public List<BirdTypeEntity> getBirdTypes() throws Exception;
    
    public List<CityEntity> getCityTypes() throws Exception;
    
    public List<BirdEntity> getAllBirdDetails() throws Exception;
    
    public List<AdminEntity> getAllAdminDetails() throws Exception;
    
    public void removeBirds(BirdEntity birdEntity) throws Exception;
    
    public void editBirds(BirdEntity birdEntity) throws Exception;
    
    public void updateBird(BirdEntity bird) throws Exception;
    
    public void updateCity(CityEntity cityId) throws Exception;
    
    public List<BirdEntity> searchByBirdName(String birdName) throws Exception;
    
    public List<BirdEntity> searchByCity(int cityId) throws Exception;
    
    public List<BirdEntity> searchByBirdId(int birdId) throws Exception;
    
    public List<BirdEntity> searchBirds(String birdName,int cityId) throws Exception;
    
    public BirdEntity searchBirdsByNameCity(String birdName,int cityId) throws Exception;
    
    public void setEvent(EventInformation events) throws Exception;
    
    public void remCityVal(CityEntity cityEntity) throws Exception;
    
    
}
