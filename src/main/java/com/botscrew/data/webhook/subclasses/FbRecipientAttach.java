package com.botscrew.data.webhook.subclasses;

import lombok.Data;

@Data
public class FbRecipientAttach {

    private String type;
    private FbRecipientPayload payload;
}
