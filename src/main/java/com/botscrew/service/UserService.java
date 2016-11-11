package com.botscrew.service;

import com.botscrew.model.FbUser;

public interface UserService {

    void save(final FbUser fbUser);

    void update(final FbUser fbUser);

    void enable(final FbUser user);

    void disable(final FbUser user);

    FbUser getByIdOrCreateFromFacebook(final String id);
}
