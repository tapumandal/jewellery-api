package me.tapumandal.jewellery.repository.implementation;

import me.tapumandal.jewellery.util.MyPagenation;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.entity.Warehouse;
import me.tapumandal.jewellery.repository.WarehouseRepository;
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
public class WarehouseRepositoryImpl implements WarehouseRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Warehouse warehouse) {
        getSession().saveOrUpdate(warehouse);
        getSession().flush();
        getSession().clear();
        return warehouse.getId();
    }

    @Override
    public int update(Warehouse warehouse) {

        Optional<Warehouse> tmpEntity = Optional.ofNullable(getById(warehouse.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(warehouse);
            getSession().flush();
            getSession().clear();
        }
        return warehouse.getId();
    }

    @Override
    public List<Warehouse> getAll(Pageable pageable, ListFilter listFilter) {


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
        String query = "FROM Warehouse M WHERE M.isDeleted = 0";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
    }

    @Override
    public Warehouse getById(int id) {

        String query = "FROM Warehouse M WHERE M.id = "+id+" AND M.isDeleted = 0";
        return (Warehouse) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Warehouse> getByKeyAndValue(String key, String value) {
        return (List<Warehouse>) getSession().createQuery(
                "from Warehouse where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Warehouse> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Warehouse warehouse = tmpEntity.get();
            warehouse.setActive(false);
            warehouse.setDeleted(true);
            update(warehouse);
            return true;
        }else{
            return false;
        }
    }

}