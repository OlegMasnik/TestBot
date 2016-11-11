package com.botscrew.data.webhook.subclasses;

import lombok.Data;

@Data
public class FbRecipientPayloadBtn {

    private String type;
    private String title;
    private String payload;
}
