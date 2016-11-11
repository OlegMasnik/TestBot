package com.botscrew.data.webhook.subclasses;

import lombok.Data;

@Data
public class FbRecipientMess {

    private FbRecipientAttach attachment;
    private String text;
}
