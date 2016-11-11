package com.botscrew.data.webhook;

import com.botscrew.data.webhook.subclasses.FbRecipientMess;
import com.botscrew.data.webhook.subclasses.FbRecipientOrSender;
import lombok.Data;

@Data
public class FbFromButtonDto {
    private FbRecipientOrSender recipient;
    private FbRecipientMess message;
}
