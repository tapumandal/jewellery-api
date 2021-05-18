package me.tapumandal.jewellery.domain.fcm_registration_token;

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
public class FCMRegistrationTokenRepositoryImpl implements FCMRegistrationTokenRepository {

    @Autowired
    EntityManager entityManager;

    private final String modelClassName = "FCMRegistrationToken";


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(FCMRegistrationToken fcmRegistrationToken) {
        getSession().saveOrUpdate(fcmRegistrationToken);
        getSession().flush();
        getSession().clear();
        return fcmRegistrationToken.getId();
    }

    @Override
    public FCMRegistrationToken getById(int id) {

        String query = "FROM FCMRegistrationToken";
        List<FCMRegistrationToken> fcmRegistrationTokens = (List<FCMRegistrationToken>) getSession().createQuery(query).getResultList();
        return fcmRegistrationTokens.get((fcmRegistrationTokens.size()-1));
    }

    @Override
    public int update(FCMRegistrationToken fcmRegistrationToken) {

        Optional<FCMRegistrationToken> tmpEntity = Optional.ofNullable(getById(fcmRegistrationToken.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(fcmRegistrationToken);
            getSession().flush();
            getSession().clear();
        }
        return fcmRegistrationToken.getId();
    }

    @Override
    public List<FCMRegistrationToken> getAll(Pageable pageable, ListFilter listFilter) {


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
    public List<FCMRegistrationToken> getAll() {
        return (List<FCMRegistrationToken>) getSession().createQuery("from FCMRegistrationToken").getResultList();
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
    public List<FCMRegistrationToken> getByKeyAndValue(String key, String value) {
        return (List<FCMRegistrationToken>) getSession().createQuery(
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