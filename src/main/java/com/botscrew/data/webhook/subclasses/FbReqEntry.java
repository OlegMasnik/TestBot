package com.botscrew.data.webhook.subclasses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FbReqEntry {
    private BigDecimal id;
    private BigDecimal time;
    private List<FbReqEntryMessaging> messaging;

}
