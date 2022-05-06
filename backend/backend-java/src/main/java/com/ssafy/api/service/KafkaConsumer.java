package com.ssafy.api.service;

import com.ssafy.api.request.KafkaVO;
import com.ssafy.common.websocket.WebSocket;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    MessageService messageService;

    @Autowired
    CctvService cctvService;



    @KafkaListener(topics = "kafka-demo2", groupId = "kafka-demo")
    public void consume(KafkaVO kafkaVO) throws IOException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException {

        System.out.println(kafkaVO.getUrl());
        System.out.println(kafkaVO.getDetection());
        System.out.println(kafkaVO.getCameraNumber());

        // 발생한 상황 디비에 저장.
//        cctvService.saveVideo();
        // 상황 테이블에 카운트.

        // 지정 시간 내의 사람들 리스트 가져오기.
        List<User> userList = new ArrayList<User>();
        LocalDateTime curTime = LocalDateTime.now();
        userList = cctvService.getUserList(curTime);

        // for each 사람들한테 메시지 다 보내기.

        for (User user: userList) {
            messageService.sendAlert(user.getPhone());
        }


        // webSocket send
        webSocket.sendAllMessage(kafkaVO.getUrl());
    }
}
