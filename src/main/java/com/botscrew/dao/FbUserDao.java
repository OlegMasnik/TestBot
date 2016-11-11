package com.botscrew.dao;

import com.botscrew.model.FbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FbUserDao extends JpaRepository<FbUser, String> {

}

