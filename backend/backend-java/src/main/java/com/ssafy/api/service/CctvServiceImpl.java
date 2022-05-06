package com.ssafy.api.service;

import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CctvServiceImpl implements CctvService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUserList(LocalDateTime curTime) {

        List<User> userList = userRepository.findByTime(curTime);
        return userList;
    }
}
