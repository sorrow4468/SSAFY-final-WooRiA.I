package com.ssafy.api.service;

import com.ssafy.api.request.KafkaVO;
import com.ssafy.db.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface CctvService {

    List<User> getUserList(LocalDateTime curTime);

    void saveVideo(KafkaVO kafkaVO);
}
