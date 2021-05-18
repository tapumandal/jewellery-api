package me.tapumandal.jewellery.domain.fcm_registration_token;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCMRegistrationTokenServiceImpl implements FCMRegistrationTokenService {

    @Autowired
    FCMRegistrationTokenRepository fcmRegistrationTokenRepository;

    private FCMRegistrationToken fcmRegistrationToken;

    public FCMRegistrationTokenServiceImpl(){}

    public FCMRegistrationTokenServiceImpl(FCMRegistrationToken fcmRegistrationToken){
        this.fcmRegistrationToken = fcmRegistrationToken;
    }

    @Override
    public FCMRegistrationToken create(FCMRegistrationTokenDto fcmRegistrationTokenDto) {

        FCMRegistrationToken entity = new FCMRegistrationToken(fcmRegistrationTokenDto);

        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(entity));
//        try{
            int id = fcmRegistrationTokenRepository.create(entity);
//        }catch (Exception e){
//            return null;
//        }
        return fcmRegistrationTokenRepository.getById(id);
    }

    @Override
    public FCMRegistrationToken update(FCMRegistrationTokenDto fcmRegistrationTokenDto) {


        FCMRegistrationToken entity = new FCMRegistrationToken(fcmRegistrationTokenDto);

//        try{
            int id = fcmRegistrationTokenRepository.update(entity);
//        }catch (Exception e){
//            return null;
//        }
        return fcmRegistrationTokenRepository.getById(id);
    }


    @Override
    public List<FCMRegistrationToken> getAll(Pageable pageable, ListFilter listFilter) {
        return null;
    }

    @Override
    public FCMRegistrationToken getById(int id) {
        return fcmRegistrationTokenRepository.getById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public FCMRegistrationToken getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<FCMRegistrationToken> getAllByValue(String kye, String value) {
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
