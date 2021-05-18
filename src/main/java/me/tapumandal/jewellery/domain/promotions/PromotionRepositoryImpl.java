package me.tapumandal.jewellery.domain.promotions;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Repository
@Transactional
public class PromotionRepositoryImpl implements PromotionRepository {

    @Autowired
    EntityManager entityManager;


    private final String modelClassName = "Promotion";


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public int create(Promotion promotion) {

        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(promotion));
        getSession().saveOrUpdate(promotion);
        getSession().flush();
        getSession().clear();
        return promotion.getId();
    }

    @Override
    public int update(Promotion promotion) {


        getSession().update(promotion);
        getSession().flush();
        getSession().clear();

        return promotion.getId();
    }

    @Override
    public List<Promotion> getAll(Pageable pageable, ListFilter listFilter) {


        String query = "FROM Promotion P ORDER BY P.id DESC";
        Query resQuery =  getSession().createQuery(query);

        return getFromDB(pageable, resQuery).getResultList();
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


    @Override
    public Promotion getById(int id) {
        String query = "FROM "+modelClassName+" U WHERE U.id = "+id+"";
        return (Promotion) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public List<Promotion> getByKeyAndValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return 0;
    }

    @Override
    public Promotion applyPromotion(String code) {

//        Timestamp today = new Timestamp(System.currentTimeMillis());
//        System.out.println("TODAY: "+today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strToday = sdf.format(Calendar.getInstance().getTime());
        //System.out.println("PROMO DATE strToday: "+strToday);
        Date today = Date.valueOf(strToday);
        //System.out.println("PROMO DATE today: "+today);

        String query = "FROM "+modelClassName+" M WHERE M.code = '"+code+"' AND DATE('"+today+"') BETWEEN M.startDate AND M.expireDate AND M.active = 1";
        return (Promotion) getSession().createQuery(query).uniqueResult();
    }

    @Override
    public int getAllEntityCount(Pageable pageable, String flag) {

        String query = "FROM Promotion P";
        Query resQuery =  getSession().createQuery(query);

        return resQuery.getResultList().size();
    }


}