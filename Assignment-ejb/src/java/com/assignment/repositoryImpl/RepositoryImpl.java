package com.assignment.repositoryImpl;

import com.assignment.repository.Repository;
import com.assignment.repository.entities.AdminEntity;
import com.assignment.repository.entities.BirdEntity;
import com.assignment.repository.entities.BirdEntityId;
import com.assignment.repository.entities.BirdTypeEntity;
import com.assignment.repository.entities.CityEntity;
import com.assignment.repository.entities.EventInformation;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kishore Kumar Sunder
 */
@Stateless
public class RepositoryImpl implements Repository {

    @PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;

    @Override
    public void addAdmin(AdminEntity admin) throws Exception {
        System.out.println("Inside RepositoryImpl: addAdmin() " + admin.getAdminEmail());
        //List<AdminEntity> adminLst =  entityManager.createNamedQuery("Admin.getAllAdmin").getResultList(); 
        entityManager.persist(admin);
    }

    @Override
    public void addBirdTypes(BirdTypeEntity birdTypes) throws Exception {
        System.out.println("Inside RepositoryImpl: addBirdTypes() " + birdTypes.getBirdTypeName());
        //List<AdminEntity> adminLst =  entityManager.createNamedQuery(AdminEntity.GET_ALL_QUERY_NAME).getResultList(); 
        entityManager.persist(birdTypes);
    }

    @Override
    public List<String> getEvents(String eventName) throws Exception {
        System.out.println("Inside RepositoryImpl: getEvents()");
        List<String> eventLst = new ArrayList<String>();
        List<EventInformation> eventEntityLst = entityManager.createNamedQuery("EventInformation.getAllEvent").setParameter("eventName", eventName).getResultList();

        eventLst.add(eventEntityLst.get(0).getEventName());
        eventLst.add(eventEntityLst.get(0).getEventDate());
        eventLst.add(eventEntityLst.get(0).getDescription());
        eventLst.add(eventEntityLst.get(0).getStreet());
        eventLst.add(eventEntityLst.get(0).getCity());
        eventLst.add(String.valueOf(eventEntityLst.get(0).getPostCode()));

        return eventLst;
    }

    @Override
    public void addCityTypes(CityEntity cityTypes) throws Exception {
        System.out.println("Inside RepositoryImpl: addBirdTypes() " + cityTypes.getCityName());
        entityManager.persist(cityTypes);
    }

    @Override
    public void addBirdInfo(BirdEntity bird) throws Exception {
        System.out.println("Inside RepositoryImpl: addBirdInfo() " + bird.getBirdTypeEntity().getBirdTypeId());
        entityManager.persist(bird);
        System.out.println("Inside RepositoryImpl: addBirdInfo(): persist done");
    }

    @Override
    public BirdTypeEntity getBirdById(int birdTypeId) throws Exception {
        System.out.println("Inside RepositoryImpl: getBirdById() " + birdTypeId);
        List<BirdTypeEntity> birdEntityLst = entityManager.createNamedQuery("BirdTypeEntity.getBirdTypeById").setParameter("birdTypeId", birdTypeId).getResultList();
        System.out.println("Inside RepositoryImpl: getBirdById(): After fetching the value " + birdEntityLst.get(0));
        return birdEntityLst.get(0);
    }

    @Override
    public CityEntity getCityById(int cityId) throws Exception {
        System.out.println("Inside RepositoryImpl: getCityById() " + cityId);
        List<CityEntity> cityEntityLst = entityManager.createNamedQuery("CityEntity.getCityById").setParameter("cityId", cityId).getResultList();
        System.out.println("Inside RepositoryImpl: getCityById(): After fetching the value " + cityEntityLst.get(0));
        return cityEntityLst.get(0);
    }

    @Override
    public List<BirdTypeEntity> getBirdTypes() throws Exception {
        System.out.println("Inside RepositoryImpl: getBirdTypes()");
        List<BirdTypeEntity> birdEntityLst = entityManager.createNamedQuery("BirdTypeEntity.getAllBirdTypes").getResultList();
        System.out.println("Inside RepositoryImpl: getBirdTypes(): After fetching the values " + birdEntityLst.get(0).getBirdTypeName());
        return birdEntityLst;
    }

    @Override
    public List<CityEntity> getCityTypes() throws Exception {
        System.out.println("Inside RepositoryImpl: getCityTypes()");
        List<CityEntity> cityLst = entityManager.createNamedQuery("CityEntity.getAllCities").getResultList();
        System.out.println("Inside RepositoryImpl: getCityTypes(): After fetching the value " + cityLst.get(0).getCityName());
        return cityLst;
    }

    @Override
    public List<BirdEntity> getAllBirdDetails() throws Exception {
        System.out.println("Inside RepositoryImpl: getAllBirdDetails()");
        return entityManager.createNamedQuery("BirdEntity.getAllBirdDetails").getResultList();
    }

    @Override
    public void removeBirds(BirdEntity birdEntity) throws Exception {
        System.out.println("Inside RepositoryImpl: removeBirds(): ");
        BirdEntityId id = new BirdEntityId(birdEntity.getBirdName(), birdEntity.getBirdTypeEntity().getBirdTypeId(), birdEntity.getCityEntityId().getCityId());

        BirdEntity birdEntityVal = entityManager.find(BirdEntity.class, id);
        System.out.println("Inside RepositoryImpl: removeBirds(): " + birdEntityVal.getBirdName());
        System.out.println("Inside RepositoryImpl: removeBirds(): " + birdEntityVal.getDescription());
        entityManager.remove(birdEntityVal);
        System.out.println("Inside RepositoryImpl: removeBirds(): after removal" + birdEntityVal.getBirdName());

    }

    @Override
    public void editBirds(BirdEntity birdEntity) throws Exception {
        System.out.println("Inside RepositoryImpl: editBirds(): ");
    }

    @Override
    public void updateBird(BirdEntity bird) throws Exception {
        System.out.println("Inside RepositoryImpl: updateBird(): ");
        entityManager.merge(bird);
        System.out.println("Inside RepositoryImpl: update done ");
    }

    @Override
    public void updateCity(CityEntity cityId) throws Exception {
        System.out.println("Inside RepositoryImpl: cityEntity(): ");
        entityManager.merge(cityId);
        System.out.println("Inside RepositoryImpl: update done ");
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public List<BirdEntity> searchByBirdName(String name) throws Exception {
        System.out.println("Inside RepositoryImpl: searchByName(): ");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BirdEntity> criteriaQuery = builder.createQuery(BirdEntity.class);
        Root<BirdEntity> c = criteriaQuery.from(BirdEntity.class);
        criteriaQuery.select(c).where(builder.equal(c.get("birdName"), name));
        List<BirdEntity> birdEntityLst = entityManager.createQuery(criteriaQuery).getResultList();
        return birdEntityLst;
    }

    @Override
    public List<BirdEntity> searchByCity(int cityId) throws Exception {
        System.out.println("Inside RepositoryImpl: searchByCity(): ");
        List<CityEntity> city = entityManager.createNamedQuery("CityEntity.getCityById").setParameter("cityId", cityId).getResultList();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BirdEntity> criteriaQuery = builder.createQuery(BirdEntity.class);
        Root<BirdEntity> c = criteriaQuery.from(BirdEntity.class);
        criteriaQuery.select(c).where(builder.equal(c.get("cityEntityId"), city.get(0)));
        List<BirdEntity> birdEntityLst = entityManager.createQuery(criteriaQuery).getResultList();
        return birdEntityLst;
    }

    @Override
    public List<BirdEntity> searchBirds(String name, int cityId) throws Exception {
        System.out.println("Inside RepositoryImpl: searchBirds(): ");
        List<CityEntity> city = entityManager.createNamedQuery("CityEntity.getCityById").setParameter("cityId", cityId).getResultList();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BirdEntity> criteriaQuery = builder.createQuery(BirdEntity.class);
        Root<BirdEntity> c = criteriaQuery.from(BirdEntity.class);
        Predicate orClause = builder.and(builder.equal(c.get("cityEntityId"), city.get(0)),
                builder.equal(c.get("birdName"), name));
        criteriaQuery.select(c).where(
                orClause
        );
        List<BirdEntity> birdEntityLst = entityManager.createQuery(criteriaQuery).getResultList();
        return birdEntityLst;
    }

    @Override
    public BirdEntity searchBirdsByNameCity(String name, int cityId) throws Exception {
        System.out.println("Inside RepositoryImpl: searchBirds(): ");
        List<CityEntity> city = entityManager.createNamedQuery("CityEntity.getCityById").setParameter("cityId", cityId).getResultList();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BirdEntity> criteriaQuery = builder.createQuery(BirdEntity.class);
        Root<BirdEntity> c = criteriaQuery.from(BirdEntity.class);
        Predicate andClause = builder.and(builder.equal(c.get("cityEntityId"), city.get(0)),
                builder.equal(c.get("birdName"), name));
        criteriaQuery.select(c).where(
                andClause
        );
        List<BirdEntity> birdEntityLst = entityManager.createQuery(criteriaQuery).getResultList();
        return birdEntityLst.get(0);
    }

    @Override
    public List<BirdEntity> searchByBirdId(int birdTypeId) throws Exception {
        System.out.println("Inside RepositoryImpl: searchByBirdId(): ");
        List<BirdTypeEntity> birdType = entityManager.createNamedQuery("BirdTypeEntity.getBirdTypeById").setParameter("birdTypeId", birdTypeId).getResultList();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BirdEntity> criteriaQuery = builder.createQuery(BirdEntity.class);
        Root<BirdEntity> c = criteriaQuery.from(BirdEntity.class);
        criteriaQuery.select(c).where(builder.equal(c.get("birdTypeEntity"), birdType.get(0)));
        List<BirdEntity> birdEntityLst = entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println("Inside RepositoryImpl: after search(): ");
        return birdEntityLst;
    }

    @Override
    public void setEvent(String name, String desc, String date, String street, String city, int postCode) throws Exception {
        System.out.println("Inside RepositoryImpl: addAdmin() " + name);
        EventInformation event = new EventInformation();
        event.setEventName(name);
        event.setDescription(desc);
        event.setEventDate(date);
        event.setStreet(street);
        event.setCity(city);
        event.setPostCode(postCode);
        entityManager.persist(event);
        System.out.println("Inside RepositoryImpl: addAdmin(): persist mande ");
    }

    @Override
    public void setEvent(EventInformation events) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdminEntity> getAllAdminDetails() throws Exception {
        System.out.println("Inside RepositoryImpl:getAllAdminDetails() ");
        List<AdminEntity> adminEntity = entityManager.createNamedQuery("Admin.getAllAdmins").getResultList();
        System.out.println("Inside RepositoryImpl: " + adminEntity.get(0).getAdminEmail());
        return adminEntity;
    }

    @Override
    public void remCityVal(CityEntity cid) throws Exception {
        System.out.println("Inside RepositoryImpl: removeCity(): "+cid);
        CityEntity cityEntityVal = entityManager.find(CityEntity.class, cid.getCityId());
        
        List<BirdEntity> birdLst = entityManager.createNamedQuery("BirdEntity.getAllBirdByCityId").setParameter("cityId", cid).getResultList();
        for(BirdEntity bird:birdLst){
        removeBirds(bird);
        }
        System.out.println("Inside RepositoryImpl: removeCity(): " + cityEntityVal.getCityName());
        
        
        entityManager.remove(cityEntityVal);
        System.out.println("Inside RepositoryImpl: removeCity(): after removal" + cityEntityVal.getCityName());
    }

}
