package me.tapumandal.jewellery.entity;

import lombok.Data;

/**
 * Created by TapuMandal on 4/5/2021.
 * For any query ask online.tapu@gmail.com
 */


@Data
public class Notification {
    private int count;

    public Notification(int count) {
        this.count = count;
    }
    public void increment() {
        this.count++;
    }
}
