package com.botscrew.service.impl;

import com.botscrew.dao.FbUserDao;
import com.botscrew.enums.UserState;
import com.botscrew.model.FbUser;
import com.botscrew.service.BotMessengerService;
import com.botscrew.service.UserService;
import com.botscrew.util.MyRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private FbUserDao fbUserDao;

    @Override
    public void save(FbUser fbUser) {
        fbUserDao.save(fbUser);
    }

    @Override
    public void update(FbUser fbUser) {
        fbUserDao.save(fbUser);
    }

    @Override
    public FbUser getByIdOrCreateFromFacebook(String id) {
        FbUser fbUser = fbUserDao.findOne(id);
        if (fbUser == null) {
            fbUser = getFromFacebook(id);
            fbUser.setId(id);
            fbUser.setState(UserState.GET_ADDRESS);
            fbUserDao.save(fbUser);
        }
        return fbUserDao.findOne(id);
    }

    private FbUser getFromFacebook(final String id) {
        final String uri = "https://graph.facebook.com/v2.6/" + id + "?fields=first_name,last_name,gender&access_token=" + BotMessengerService.FB_MESSENGER_BOT_ACCESS_TOKEN;
        System.out.println(uri);
        try {
            return MyRestTemplate.getRestTemplate().getForObject(new URI(uri), FbUser.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
