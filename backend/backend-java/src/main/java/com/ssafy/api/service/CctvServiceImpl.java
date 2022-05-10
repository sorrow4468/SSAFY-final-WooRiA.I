package com.ssafy.api.service;

import com.ssafy.api.request.KafkaVO;
import com.ssafy.db.entity.CCTV;
import com.ssafy.db.entity.Danger;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.CCTVRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CctvServiceImpl implements CctvService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CCTVRepository cctvRepository;

    @Override
    public List<User> getUserList(LocalDateTime curTime) {

        List<User> userList = userRepository.findByTime(curTime);
        return userList;
    }

    @Override
    public void saveVideo(KafkaVO kafkaVO) {
        String location;
        if(kafkaVO.getCameraNumber().equals("1")) {
            location = "신호동" +
                    "234-6" + "소정공원";
        }else if(kafkaVO.getCameraNumber().equals("2")) {
            location = "명지동" +
                    "3230-12" + "링컨공원";
        }else if(kafkaVO.getCameraNumber().equals("3")) {
            location = "범방동" +
                    "1875-4" + "금병공원";
        }else {
            location = "명지동" +
                    "3428-4" + "나뭇잎 공원";
        }

        if(kafkaVO.getDetection().equals("1")) {
            CCTV cctv = new CCTV().builder()
                    .VIDEO_URL(kafkaVO.getUrl())
                    .type(kafkaVO.getCameraNumber())
                    .LOCATION(location)
                    .danger(Danger.VIOLENCE).build();
            cctvRepository.saveAndFlush(cctv);
        }else {
            CCTV cctv = new CCTV().builder()
                    .VIDEO_URL(kafkaVO.getUrl())
                    .type(kafkaVO.getCameraNumber())
                    .LOCATION(location)
                    .danger(Danger.STUMBLE).build();
            cctvRepository.saveAndFlush(cctv);
        }

    }
}
