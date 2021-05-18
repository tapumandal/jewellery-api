package me.tapumandal.jewellery.domain.address;
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
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    EntityManager entityManager;

    private final String modelClassName = "Address";

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Address address) {

        getSession().saveOrUpdate(address);
        getSession().flush();
        getSession().clear();
        return address.getId();
    }

    @Override
    public int update(Address address) {

        Optional<Address> tmpEntity = Optional.ofNullable(getById(address.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(address);
            getSession().flush();
            getSession().clear();
        }
        return address.getId();
    }

    @Override
    public List<Address> getAll(Pageable pageable, ListFilter listFilter) {


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
        String query = "FROM "+modelClassName+" C WHERE C.isDeleted = 0 ORDER BY id DESC";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Address getById(int id) {

        String query = "FROM "+modelClassName+" C WHERE C.id = "+id+" AND C.isDeleted = 0";
        return (Address) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Address> getByKeyAndValue(String key, String value) {
        return (List<Address>) getSession().createQuery(
                "from Address where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Address> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Address address = tmpEntity.get();
            address.setActive(false);
            address.setDeleted(true);
            update(address);
            return true;
        }else{
            return false;
        }
    }


}