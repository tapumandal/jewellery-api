package me.tapumandal.jewellery.domain.appnavigation;

import me.tapumandal.jewellery.service.Service;

import java.util.List;

public interface NavigationService extends Service<NavigationDto, Navigation> {
    public List<MenuList> getNavigation();
}
