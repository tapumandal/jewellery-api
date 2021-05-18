package me.tapumandal.jewellery.domain.business_settings;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessSettingsServiceImpl implements BusinessSettingsService {

    @Autowired
    BusinessSettingsRepository businessSettingsRepository;

    private BusinessSettings businessSettings;

    public BusinessSettingsServiceImpl(){}

    public BusinessSettingsServiceImpl(BusinessSettings businessSettings){
        this.businessSettings = businessSettings;
    }

    @Override
    public BusinessSettings create(BusinessSettingsDto businessSettingsDto) {

        BusinessSettings entity = new BusinessSettings(businessSettingsDto);

        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(entity));
//        try{
            int id = businessSettingsRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
        return businessSettingsRepository.getById(id);
    }

    @Override
    public BusinessSettings update(BusinessSettingsDto businessSettingsDto) {


        BusinessSettings entity = new BusinessSettings(businessSettingsDto);

//        try{
            int id = businessSettingsRepository.update(entity);
//        }catch (Exception e){
//            return null;
//        }
        return businessSettingsRepository.getById(id);
    }


    @Override
    public List<BusinessSettings> getAll(Pageable pageable, ListFilter listFilter) {
        return null;
    }

    @Override
    public BusinessSettings getById(int id) {
        return businessSettingsRepository.getById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public BusinessSettings getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<BusinessSettings> getAllByValue(String kye, String value) {
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
