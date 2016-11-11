package com.botscrew.data.webhook;

import com.botscrew.data.webhook.subclasses.FbReqEntry;
import lombok.Data;

import java.util.List;

@Data
public class FbRequestDto {

    private String object;
    private List<FbReqEntry> entry;
}
