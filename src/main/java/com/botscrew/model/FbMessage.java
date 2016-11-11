package com.botscrew.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "message")
public class FbMessage {

    @Id
    @GeneratedValue
    private Integer id;
    @Lob
    private String text;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "app_id")
    private String appId;

    public FbMessage() {
    }

    public FbMessage(String text, String userId, String appId) {
        this.text = text;
        this.userId = userId;
        this.appId = appId;
    }
}
