package me.tapumandal.jewellery.domain.cloud_messaging;

import java.util.Map;

/**
 * Created by TapuMandal on 4/24/2021.
 * For any query ask online.tapu@gmail.com
 */

public class ConsumerMessage {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;

    public String getSubject() {
        return subject == null ? "" : subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getImage() {
        return image == null ? "" : image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
