package me.tapumandal.jewellery.domain.cloud_messaging;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class CloudMessagingDto implements Serializable {

    @SerializedName("id")
    protected int id;

    protected String notificationTitle = "";
    protected String notificationText = "";
    protected String notificationImage = "";
    protected String notificationNameOptional = "";
}
