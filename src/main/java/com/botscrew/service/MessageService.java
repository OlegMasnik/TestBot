package com.botscrew.service;

import com.botscrew.data.webhook.subclasses.FbReqEntryMessaging;
import com.botscrew.model.FbUser;

public interface MessageService {

    void save(final FbReqEntryMessaging messaging, final FbUser user, final String appId);

    void save(String text, String user, String app);
}
