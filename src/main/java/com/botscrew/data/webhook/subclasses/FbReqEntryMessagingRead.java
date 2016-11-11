package com.botscrew.data.webhook.subclasses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FbReqEntryMessagingRead {
    private BigDecimal watermark;
    private BigDecimal seq;

}
