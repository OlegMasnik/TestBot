package com.botscrew.data.webhook.subclasses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FbReqEntryMessagingDelivery {

    private List<String> mids;
    private BigDecimal watermark;
    private BigDecimal seq;
}
