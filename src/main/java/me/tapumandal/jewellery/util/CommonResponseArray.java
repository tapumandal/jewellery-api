package me.tapumandal.jewellery.util;;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseArray<Entity> {

    public boolean action;
    public HttpStatus status;
    public String message;
    public List<Entity> data;
    public MyPagenation myPagenation;

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus code) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Entity> getData() {
        return data;
    }

    public void setData(List<Entity> data) {
        this.data = data;
    }

    public MyPagenation getMyPagenation() {
        return myPagenation;
    }

    public void setMyPagenation(MyPagenation myPagenation) {
        this.myPagenation = myPagenation;
    }
}