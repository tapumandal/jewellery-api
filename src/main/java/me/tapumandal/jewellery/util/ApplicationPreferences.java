package me.tapumandal.jewellery.util;

import me.tapumandal.jewellery.entity.User;
import me.tapumandal.jewellery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.prefs.Preferences;


@Component
public class ApplicationPreferences {

    @Autowired
    UserService userService;

    private static Preferences preferences;

    private static User user;

    public void saveUserByUsername(String username) {
        User u = userService.getUserByUserName(username);
        preferences = Preferences.userRoot().node(this.getClass().getName());
        preferences.put("firstName", u.getFirstName());
        preferences.put("lastName", u.getLastName());
        preferences.put("userId", String.valueOf(u.getId()));
        preferences.put("phoneNumberCode", u.getPhoneNumberCode());
        preferences.put("phoneNumber", u.getPhoneNumber());
        preferences.put("role", u.getRole());

    }

    public static User getUser(){
        user = new User();
        preferences = Preferences.userRoot().node(ApplicationPreferences.class.getName());
        user.setFirstName(preferences.get("firstName", ""));
        user.setLastName(preferences.get("lastName", ""));
        user.setId(Integer.parseInt(preferences.get("userId", "0")));
        user.setPhoneNumberCode(preferences.get("phoneNumberCode", null));
        user.setPhoneNumber(preferences.get("phoneNumber", null));
        user.setRole(preferences.get("role", null));
        return user;
    }
}
