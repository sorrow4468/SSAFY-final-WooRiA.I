package com.ssafy.api.service;

import com.ssafy.api.request.SocketVO;
import com.ssafy.common.websocket.WebSocket;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class KafkaConsumer {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    MessageService messageService;

    @KafkaListener(topics = "kafka-demo2", groupId = "kafka-demo")
    public void consume(String message) throws IOException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException {

        // 지정 시간 내의 사람들한테 모두 전송.
        messageService.sendAlert("01073085445");

        // webSocket send
        webSocket.sendAllMessage(message);
    }
}
