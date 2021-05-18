package me.tapumandal.jewellery.domain.cloud_messaging;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import me.tapumandal.jewellery.domain.fcm_registration_token.FCMRegistrationToken;
import me.tapumandal.jewellery.domain.fcm_registration_token.FCMRegistrationTokenRepository;
import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CloudMessagingServiceImpl implements CloudMessagingService {

    @Autowired
    CloudMessagingRepository cloudMessagingRepository;

    @Autowired
    FirebaseMessaging firebaseMessaging;

    @Autowired
    FCMRegistrationTokenRepository fcmRegistrationTokenRepository;

    CloudMessaging cloudMessaging;

    public CloudMessagingServiceImpl(){}

    public CloudMessagingServiceImpl(FirebaseMessaging firebaseMessaging){
        this.firebaseMessaging = firebaseMessaging;
    }

    public CloudMessagingServiceImpl(CloudMessaging cloudMessaging){
        this.cloudMessaging = cloudMessaging;
    }

    @Override
    public CloudMessaging create(CloudMessagingDto cloudMessagingDto) {

        CloudMessaging cloudMessage = new CloudMessaging(cloudMessagingDto);

        //System.out.println("SERVICE: ");
        //System.out.println(new Gson().toJson(cloudMessage));

        List<FCMRegistrationToken> fcmRegistrationTokenList = fcmRegistrationTokenRepository.getAll();



        for (FCMRegistrationToken fcmToken: fcmRegistrationTokenList) {

            try {
                sendNotification(cloudMessage, fcmToken.getFcmRegistrationToken());
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        }

//        try{
            int id = cloudMessagingRepository.create(cloudMessage);
//        }catch (Exception e){
//            return null;
//        }
        return cloudMessage;
    }



    public String sendNotification(CloudMessaging note, String token) throws FirebaseMessagingException {

        //System.out.println(token);

        Notification notification = Notification
                .builder()
                .setTitle(note.getNotificationTitle())
                .setBody(note.getNotificationText())
                .setImage(note.getNotificationImage())
                .build();
        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        return firebaseMessaging.send(message);
    }

    @Override
    public CloudMessaging update(CloudMessagingDto fcmRegistrationTokenDto) {


        CloudMessaging entity = new CloudMessaging(fcmRegistrationTokenDto);

//        try{
            int id = cloudMessagingRepository.update(entity);
//        }catch (Exception e){
//            return null;
//        }
        return cloudMessagingRepository.getById(id);
    }


    @Override
    public List<CloudMessaging> getAll(Pageable pageable, ListFilter listFilter) {
        return null;
    }

    @Override
    public CloudMessaging getById(int id) {
        return cloudMessagingRepository.getById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public CloudMessaging getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<CloudMessaging> getAllByValue(String kye, String value) {
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

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("super-haat-firebase-adminsdk-jmvqp-1579a96ba0.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "Super Haat");
        return FirebaseMessaging.getInstance(app);
    }

}
