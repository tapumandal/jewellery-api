package me.tapumandal.jewellery.domain.ref_code;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.repository.UserRepository;
import me.tapumandal.jewellery.util.ApplicationPreferences;
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
public class RefCodeRewardRepositoryImpl implements RefCodeRewardRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    private final String modelClassName = "RefCodeReward";

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(RefCodeReward refCodeReward) {

        getSession().saveOrUpdate(refCodeReward);
        getSession().flush();
        getSession().clear();
        return refCodeReward.getId();
    }

    @Override
    public int update(RefCodeReward refCodeReward) {

        Optional<RefCodeReward> tmpEntity = Optional.ofNullable(getById(refCodeReward.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(refCodeReward);
            getSession().flush();
            getSession().clear();
        }
        return refCodeReward.getId();
    }

    @Override
    public List<RefCodeReward> getAll(Pageable pageable, ListFilter listFilter) {


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
        String query = "FROM RefCodeReward C WHERE C.isDeleted = 0 ORDER BY id DESC";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public RefCodeReward getById(int id) {

        String query = "FROM RefCodeReward C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (RefCodeReward) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public RefCodeReward getRefCodeRewardFirstTime(int id) {

        String query = "FROM RefCodeReward C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (RefCodeReward) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<RefCodeReward> getByKeyAndValue(String key, String value) {
        return (List<RefCodeReward>) getSession().createQuery(
                "from RefCodeReward where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<RefCodeReward> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            RefCodeReward refCodeReward = tmpEntity.get();
            refCodeReward.setActive(false);
            refCodeReward.setDeleted(true);
            update(refCodeReward);
            return true;
        }else{
            return false;
        }
    }


    @Override
    public RefCodeReward getRefCodeByCode(String refCode){
        int userId = ApplicationPreferences.getUser().getId();
        User user = userRepository.getById(userId);

        String query = "FROM "+modelClassName+" R WHERE R.code = '"+refCode+"' AND R.code != '"+user.getRefCodeReward().getCode()+"' AND R.isActive = 1";
        return (RefCodeReward) getSession().createQuery(query).uniqueResult();
    }

}