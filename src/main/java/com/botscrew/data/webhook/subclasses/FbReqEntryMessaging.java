package com.botscrew.data.webhook.subclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FbReqEntryMessaging {

    @JsonIgnore
    private FbReqEntryMessagingDelivery delivery;
    private FbRecipientOrSender sender;
    private FbRecipientOrSender recipient;
    private BigDecimal timestamp;
    private FbReqEntryMessagingMess message;
    private FbReqEntryMessagingRead read;
    private FbReqEntryMessagingPostback postback;
}
