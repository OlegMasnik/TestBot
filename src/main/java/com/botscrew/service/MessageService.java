package com.botscrew.service;

import com.botscrew.data.webhook.subclasses.FbReqEntryMessagingMess;
import com.botscrew.model.FbUser;

public interface MessageService {

    void save(final FbReqEntryMessagingMess messaging, final FbUser user, final String appId);

    void save(String text, String user, String app);
}
