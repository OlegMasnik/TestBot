package com.botscrew.data.webhook.subclasses;

import lombok.Data;

import java.util.List;

@Data
public class FbRecipientPayload {

    private String template_type;
    private String text;
    private List<FbRecipientPayloadBtn> buttons;
}
