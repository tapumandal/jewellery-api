package me.tapumandal.jewellery.domain.business_settings;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BusinessSettingsRepositoryImpl implements BusinessSettingsRepository {

    @Autowired
    EntityManager entityManager;

    private final String modelClassName = "BusinessSettings";


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(BusinessSettings businessSettings) {
        delete();

        getSession().saveOrUpdate(businessSettings);
        getSession().flush();
        getSession().clear();
        return businessSettings.getId();
    }

    @Override
    public BusinessSettings getById(int id) {

        String query = "FROM BusinessSettings";
        return (BusinessSettings) getSession().createQuery(query).uniqueResult();
    }

    private boolean delete(){

//        String query = "DELETE FROM BusinessSettings";
//        System.out.println(query);
//        Query resQuery =  getSession().createQuery(query);
//        getSession().createSQLQuery(query).executeUpdate();

        Query qq = getSession().createQuery("DELETE DiscountTypeCondition WHERE 1 = 1");
        qq.executeUpdate();

        Query q = getSession().createQuery("DELETE BusinessSettings WHERE 1 = 1");
        q.executeUpdate();

        getSession().flush();
        getSession().clear();

//        List<BusinessSettings> tmpEntity = getAll(null);
//
//        if (tmpEntity.size()>0) {
//            getSession().delete(tmpEntity);
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }


    @Override
    public int update(BusinessSettings businessSettings) {

        Optional<BusinessSettings> tmpEntity = Optional.ofNullable(getById(businessSettings.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(businessSettings);
            getSession().flush();
            getSession().clear();
        }
        return businessSettings.getId();
    }

    @Override
    public List<BusinessSettings> getAll(Pageable pageable, ListFilter listFilter) {


        Query resQuery = getQuery();

        int pageNum = pageable.getPageNumber();
        if(pageNum<1){
            pageNum = 1;
        }

        resQuery.setFirstResult((pageNum-1)*pageable.getPageSize());
        resQuery.setMaxResults(pageable.getPageSize());
        return resQuery.getResultList();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        Query resQuery = getQuery();

        MyPagenation myPagenation = new MyPagenation();

        myPagenation.setTotalElement(resQuery.getResultList().size());
        return myPagenation;
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return 0;
    }

    private Query getQuery(){
        String query = "FROM BusinessSettings ";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }




    @Override
    public List<BusinessSettings> getByKeyAndValue(String key, String value) {
        return (List<BusinessSettings>) getSession().createQuery(
                "from BusinessSettings where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

//        List<BusinessSettings> tmpEntity = getAll(null);
//
//        if (tmpEntity.size()>0) {
//            getSession().delete(tmpEntity);
//            return true;
//        }else{
//            return false;
//        }
        return false;
    }


}