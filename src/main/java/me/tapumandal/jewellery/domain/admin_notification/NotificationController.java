package me.tapumandal.jewellery.domain.admin_notification;

import me.tapumandal.jewellery.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TapuMandal on 4/5/2021.
 * For any query ask online.tapu@gmail.com
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/notification/")
public class NotificationController {
    @Autowired
    private SimpMessagingTemplate template;

    // Initialize Notifications
    private Notification notifications = new Notification(0);

    @GetMapping(path = "/notify")
    public String getNotification() {
        //System.out.println("HELLO NOTIFICATION");

        // Increment Notification by one
        notifications.increment();
        // Push notifications to front-end
        template.convertAndSend("/topic/notification", notifications);

        return "Notifications successfully sent to Angular !";
    }
}
