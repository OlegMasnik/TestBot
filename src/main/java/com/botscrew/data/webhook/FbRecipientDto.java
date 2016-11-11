package com.botscrew.data.webhook;

import com.botscrew.data.webhook.subclasses.FbRecipientMess;
import com.botscrew.data.webhook.subclasses.FbRecipientOrSender;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FbRecipientDto {

    private FbRecipientOrSender recipient;
    private FbRecipientMess message;
    private BigDecimal timestamp;
}
