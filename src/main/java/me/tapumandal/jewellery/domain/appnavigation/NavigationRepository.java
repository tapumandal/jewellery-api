package me.tapumandal.jewellery.domain.appnavigation;

import org.hibernate.Session;

public interface NavigationRepository {

    public Session getSession();
    public Navigation create(Navigation navigation);
    public Navigation update(Navigation navigation);
    public Navigation getNavigation();
}
