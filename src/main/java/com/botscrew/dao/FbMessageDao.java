package com.botscrew.dao;

import com.botscrew.model.FbMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FbMessageDao extends JpaRepository<FbMessage, Integer> {

}
