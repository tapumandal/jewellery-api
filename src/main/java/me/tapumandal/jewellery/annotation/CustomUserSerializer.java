package me.tapumandal.jewellery.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import me.tapumandal.jewellery.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomUserSerializer extends StdSerializer<List<User>> {

    public CustomUserSerializer() {
        this(null);
    }

    public CustomUserSerializer(Class<List<User>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<User> user,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<User> usr = new ArrayList<>();
        for (User u : user) {
            usr.add(u);
        }
        generator.writeObject(usr);
    }
}
