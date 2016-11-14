package com.botscrew.service;

import com.botscrew.model.FbUser;

public interface UserService {

    void save(final FbUser fbUser);

    void update(final FbUser fbUser);

    FbUser getByIdOrCreateFromFacebook(final String id);
}
