package me.tapumandal.jewellery.domain.appnavigation;

import com.google.gson.Gson;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    NavigationRepository navigationRepository;

    private Navigation navigation;

    public NavigationServiceImpl(){}

    public NavigationServiceImpl(Navigation navigation){
        this.navigation = navigation;
    }

    @Override
    public Navigation create(NavigationDto navigationDto) {

        Navigation entity = new Navigation(navigationDto);

        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(entity));
//        try{
            return navigationRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
    }

    @Override
    public Navigation update(NavigationDto navigationDto) {


        Navigation entity = new Navigation(navigationDto);

//        try{
            return navigationRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
    }

    @Override
    public List<MenuList> getNavigation() {

        Navigation navigation =  navigationRepository.getNavigation();
        List<MenuList> menuLists = new Gson().fromJson(navigation.getNavigation(), List.class);


        return menuLists;
    }

    @Override
    public List<Navigation> getAll(Pageable pageable, ListFilter listFilter) {
        return null;
    }

    @Override
    public Navigation getById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Navigation getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Navigation> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
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

}
