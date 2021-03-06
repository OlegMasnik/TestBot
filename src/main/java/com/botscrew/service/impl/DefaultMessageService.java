package com.botscrew.service.impl;

import com.botscrew.dao.FbMessageDao;
import com.botscrew.data.webhook.subclasses.FbReqEntryMessagingMess;
import com.botscrew.model.FbMessage;
import com.botscrew.model.FbUser;
import com.botscrew.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMessageService implements MessageService {

    @Autowired
    private FbMessageDao fbMessageDao;

    @Override
    public void save(final FbReqEntryMessagingMess messaging, final FbUser user, final String appId) {
        if (messaging != null) {
            save(messaging.getText(), user.getId(), appId);
        }
    }

    @Override
    public void save(String text, String user, String app) {
        if (text != null && user != null && app != null) {
            FbMessage message = new FbMessage();
            message.setText(text);
            message.setUserId(user);
            message.setAppId(app);
            fbMessageDao.save(message);
        }
    }
}
