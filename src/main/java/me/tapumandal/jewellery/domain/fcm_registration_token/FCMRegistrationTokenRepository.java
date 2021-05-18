package me.tapumandal.jewellery.domain.fcm_registration_token;

import me.tapumandal.jewellery.repository.Repository;

import java.util.List;

public interface FCMRegistrationTokenRepository  extends Repository<FCMRegistrationToken> {
    public List<FCMRegistrationToken> getAll();
}
