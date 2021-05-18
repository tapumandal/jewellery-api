package me.tapumandal.jewellery.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseSingle<Entity> {

    public boolean action;
    public HttpStatus status;
    public String message;
    public Entity data;

    public CommonResponseSingle(){};

    public CommonResponseSingle(boolean action, HttpStatus status, String message, Entity data) {
        this.action = action;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public void setCode(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Entity getData() {
        return data;
    }

    public void setData(Entity data) {
        this.data = data;
    }



}