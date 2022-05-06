package com.ssafy.api.service;

import com.ssafy.db.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CctvService {

    List<User> getUserList(LocalDateTime curTime);
}
