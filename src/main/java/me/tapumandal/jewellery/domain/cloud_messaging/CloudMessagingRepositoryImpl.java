package me.tapumandal.jewellery.domain.cloud_messaging;

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
public class CloudMessagingRepositoryImpl implements CloudMessagingRepository {

    @Autowired
    EntityManager entityManager;

    private final String modelClassName = "FCMRegistrationToken";


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(CloudMessaging cloudMessaging) {
        getSession().saveOrUpdate(cloudMessaging);
        getSession().flush();
        getSession().clear();
        return cloudMessaging.getId();
    }

    @Override
    public CloudMessaging getById(int id) {

        String query = "FROM FCMRegistrationToken";
        return (CloudMessaging) getSession().createQuery(query).uniqueResult();
    }


    @Override
    public int update(CloudMessaging cloudMessaging) {

        Optional<CloudMessaging> tmpEntity = Optional.ofNullable(getById(cloudMessaging.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(cloudMessaging);
            getSession().flush();
            getSession().clear();
        }
        return cloudMessaging.getId();
    }

    @Override
    public List<CloudMessaging> getAll(Pageable pageable, ListFilter listFilter) {


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
        String query = "FROM FCMRegistrationToken ";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }




    @Override
    public List<CloudMessaging> getByKeyAndValue(String key, String value) {
        return (List<CloudMessaging>) getSession().createQuery(
                "from FCMRegistrationToken where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

//        List<FCMRegistrationToken> tmpEntity = getAll(null);
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