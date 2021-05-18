package me.tapumandal.jewellery.domain.appnavigation;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class NavigationRepositoryImpl implements NavigationRepository {

    @Autowired
    EntityManager entityManager;

    private final String modelClassName = "Navigation";


    @Override
    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public Navigation create(Navigation navigation) {

        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(navigation));
        deleteNavigation();
        getSession().saveOrUpdate(navigation);
        getSession().flush();
        getSession().clear();
        return getNavigation();
    }

    @Override
    public Navigation update(Navigation navigation) {

        Optional<Navigation> tmpEntity = Optional.ofNullable(getNavigation());
        getSession().clear();

        if(tmpEntity.isPresent()) {
            getSession().update(navigation);
            getSession().flush();
            getSession().clear();
        }
        return getNavigation();
    }

    @Override
    public Navigation getNavigation() {
        String query = "FROM "+modelClassName+" M WHERE M.navigation IS NOT NULL";
        return (Navigation) getSession().createQuery(query).uniqueResult();
    }
    public void deleteNavigation() {
        String stringQuery = "DELETE FROM "+modelClassName;
        Query query = getSession().createQuery(stringQuery);
        query.executeUpdate();

//        String query = "delete FROM "+modelClassName+" M WHERE M.navigation IS NOT NULL OR M.navigation IS NULL";
//        return (Navigation) getSession().createQuery(query).uniqueResult();
    }


}