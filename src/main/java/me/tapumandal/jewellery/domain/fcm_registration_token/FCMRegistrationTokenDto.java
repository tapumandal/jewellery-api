package me.tapumandal.jewellery.domain.fcm_registration_token;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Data
@Component
public class FCMRegistrationTokenDto implements Serializable {

    @SerializedName("id")
    protected int id;

    protected int userId;

    protected String fcmRegistrationToken = "";
}
