package me.tapumandal.jewellery.domain.product;

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
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Product product) {

        //System.out.println("Repository Before Save: ");
        //System.out.println(new Gson().toJson(product));

        getSession().saveOrUpdate(product);
        getSession().flush();
        getSession().clear();

        //System.out.println("Repository After Save: ");
        //System.out.println(new Gson().toJson(product));
        return product.getId();
    }

    @Override
    public int update(Product product) {

        Optional<Product> tmpEntity = Optional.ofNullable(getById(product.getId()));
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(product);
            getSession().flush();
            getSession().clear();
        }
        return product.getId();
    }

    @Override
    public List<Product> getAll(Pageable pageable, ListFilter listFilter) {


        String query = "FROM Product P WHERE P.isDeleted = 0 AND (P.categories LIKE '%"+listFilter.getCategoryName()+"%' OR P.company LIKE '%"+listFilter.getCategoryName()+"%') ORDER BY P."+listFilter.getSortBy()+" "+listFilter.getSortType();
        Query resQuery =  getSession().createQuery(query);

        return getFromDB(pageable, resQuery).getResultList();
    }

    @Override
    public List<ProductBusiness> getAllBusiness(Pageable pageable, String flag, String selectedParentMenu) {
        flag = flag.trim();
        String query = "FROM ProductBusiness P WHERE P.isDeleted = 0 AND P.categories LIKE '%"+selectedParentMenu+"%' ORDER BY P.sortPriority DESC, P.unit DESC, P.name ASC";
        Query resQuery =  getSession().createQuery(query);

        return getFromDB(pageable, resQuery).getResultList();
    }

    @Override
    public int getAllBusinessEntityCount(Pageable pageable, String flag) {
        flag = flag.trim();
        String query = "FROM ProductBusiness P WHERE P.quantity > 0 AND P.isDeleted = 0 AND (P.categories LIKE '%"+flag+"%' OR P.company LIKE '%"+flag+"%')  ORDER BY P.unit DESC, P.name ASC";
        Query resQuery =  getSession().createQuery(query);

        return resQuery.getResultList().size();
    }

    @Override
    public List<ProductBusiness> searchProduct(Pageable pageable, String searchString) {
        searchString = searchString.trim();
        String query = "FROM ProductBusiness P\n" +
                "WHERE name LIKE '%"+searchString+"%'\n" +
                "ORDER BY\n" +
                "  CASE\n" +
                "    WHEN name LIKE '"+searchString+"%' THEN 1\n" +
                "    WHEN name LIKE '%"+searchString+"' THEN 3\n" +
                "    ELSE 2\n" +
                "  END";
        Query resQuery =  getSession().createQuery(query);

        return getFromDB(pageable, resQuery).getResultList();
    }

    @Override
    public int searchProductEntityCount(Pageable pageable, String searchString) {
        searchString = searchString.trim();
        String query = "FROM ProductBusiness P\n" +
                "WHERE name LIKE '%"+searchString+"%'\n" +
                "ORDER BY\n" +
                "  CASE\n" +
                "    WHEN name LIKE '"+searchString+"%' THEN 1\n" +
                "    WHEN name LIKE '%"+searchString+"' THEN 3\n" +
                "    ELSE 2\n" +
                "  END";
        Query resQuery =  getSession().createQuery(query);

        return resQuery.getResultList().size();
    }


    private Query getFromDB(Pageable pageable, Query resQuery){

        int pageNum = pageable.getPageNumber();
        if(pageNum<1){
            pageNum = 1;
        }

        resQuery.setFirstResult((pageNum-1)*pageable.getPageSize());
        resQuery.setMaxResults(pageable.getPageSize());
        return resQuery;
    }

    private Query getQuery(){
        String query = "FROM Product P WHERE P.quantity > 0 AND P.isDeleted = 0 ORDER BY P.sortPriority DESC";
        Query resQuery =  getSession().createQuery(query);

        return resQuery;
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

        String query = "FROM Product P WHERE P.isDeleted = 0 AND (P.categories LIKE '%"+listFilter.getCategoryName()+"%' OR P.company LIKE '%"+listFilter.getCategoryName()+"%') ORDER BY P."+listFilter.getSortBy()+" "+listFilter.getSortType();
        Query resQuery =  getSession().createQuery(query);

        return resQuery.getResultList().size();
    }

    @Override
    public Product getById(int id) {

        String query = "FROM Product P WHERE P.id = "+id+" AND P.isDeleted = 0";
        return (Product) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public Product getByName(String name) {

        String query = "FROM Product P WHERE P.name = '"+name+"' AND P.isDeleted = 0";
        return (Product) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Product> getByKeyAndValue(String key, String value) {
        return (List<Product>) getSession().createQuery(
                "from Product where "+key+" = :value"
        ).setParameter("value", value)
                .getResultList();
    }

    @Override
    public boolean delete(int id) {

        Optional<Product> tmpEntity = Optional.ofNullable(getById(id));
        if(tmpEntity.isPresent()){
            Product product = tmpEntity.get();
            product.setActive(false);
            product.setDeleted(true);
            update(product);
            return true;
        }else{
            return false;
        }
    }

}