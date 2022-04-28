package com.ssafy.api.service;

import com.ssafy.api.request.SocketVO;
import com.ssafy.common.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    @Autowired
    private WebSocket webSocket;

    @KafkaListener(topics = "kafka-demo2", groupId = "kafka-demo")
    public void consume(String message) throws IOException {
        System.out.println(String.format("Consumed message : %s", message));

        webSocket.sendAllMessage(message);
    }
}
