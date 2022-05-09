package com.ssafy.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.request.MessagesRequestDto;
import com.ssafy.api.request.SmsRequestDto;
import com.ssafy.api.response.SendSmsResponseDto;
import com.ssafy.db.repository.SmsCertificationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    @Value("${naver.serviceid}")
    private String serviceId;

    @Value("${naver.accesskey}")
    private String ak;

    @Value("${naver.secretkey}")
    private String sk;

    private final SmsCertificationRepository smsCertificationRepository;

    @Override
    public SendSmsResponseDto sendSms(String recipientPhoneNumber) throws ParseException, JsonProcessingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
        Long time = System.currentTimeMillis();
        List<MessagesRequestDto> messages = new ArrayList<>(); // 보내는 사람에게 내용을 보냄.

        String randomNumber = makeRandomNumber();

        String content = "인증번호는 " + randomNumber + " 입니다";

        messages.add(new MessagesRequestDto(recipientPhoneNumber,content)); // content부분이 내용임 // 전체 json에 대해 메시지를 만든다.
        SmsRequestDto smsRequestDto = new SmsRequestDto("SMS", "COMM", "82", "01073085445", content, messages); // 쌓아온 바디를 json 형태로 변환시켜준다.
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto); // 헤더에서 여러 설정값들을 잡아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", ak); // 제일 중요한 signature 서명하기.
        String sig = makeSignature(time);
        System.out.println("sig -> " + sig);
        headers.set("x-ncp-apigw-signature-v2", sig); // 위에서 조립한 jsonBody와 헤더를 조립한다.
        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
        System.out.println(body.getBody()); // restTemplate로 post 요청을 보낸다. 별 일 없으면 202 코드 반환된다.
        RestTemplate restTemplate = new RestTemplate();
        SendSmsResponseDto sendSmsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+serviceId+"/messages"), body, SendSmsResponseDto.class);
        System.out.println(sendSmsResponseDto.getStatusCode());

        smsCertificationRepository.createSmsCertification(recipientPhoneNumber, randomNumber);
        return sendSmsResponseDto;
    }

    @Override
    public SendSmsResponseDto sendAlert(String recipientPhoneNumber) throws ParseException, JsonProcessingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException {
        Long time = System.currentTimeMillis();
        List<MessagesRequestDto> messages = new ArrayList<>(); // 보내는 사람에게 내용을 보냄.



        String content = "아이가 위험 합니다!";

        messages.add(new MessagesRequestDto(recipientPhoneNumber,content)); // content부분이 내용임 // 전체 json에 대해 메시지를 만든다.
        SmsRequestDto smsRequestDto = new SmsRequestDto("SMS", "COMM", "82", "01073085445", content, messages); // 쌓아온 바디를 json 형태로 변환시켜준다.
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsRequestDto); // 헤더에서 여러 설정값들을 잡아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", ak); // 제일 중요한 signature 서명하기.
        String sig = makeSignature(time);
        System.out.println("sig -> " + sig);
        headers.set("x-ncp-apigw-signature-v2", sig); // 위에서 조립한 jsonBody와 헤더를 조립한다.
        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
        System.out.println(body.getBody()); // restTemplate로 post 요청을 보낸다. 별 일 없으면 202 코드 반환된다.
        RestTemplate restTemplate = new RestTemplate();
        SendSmsResponseDto sendSmsResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+serviceId+"/messages"), body, SendSmsResponseDto.class);
        System.out.println(sendSmsResponseDto.getStatusCode());

        return sendSmsResponseDto;
    }

    @Override
    public boolean verifySms(MessagesRequestDto messageRequest) {
        if (isVerify(messageRequest.getTo(), messageRequest.getNumber())) {
            return false;
        }
        smsCertificationRepository.removeSmsCertification(messageRequest.getTo());
        return true;
    }

    @Override
    public boolean isVerify(String recipientPhoneNumber, String certificationNumber) {
        return !(smsCertificationRepository.hasKey(recipientPhoneNumber) &&
                smsCertificationRepository.getSmsCertification(recipientPhoneNumber)
                        .equals(certificationNumber));
    }

    private String makeRandomNumber() {
        int authNo = (int)(Math.random() * (9999 - 1000 + 1)) + 1000;

        return Integer.toString(authNo);
    }


    @Override
    public String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String space = " "; // one space
        String newLine = "\n"; // new line
        String method = "POST"; // method
        String url = "/sms/v2/services/"+serviceId+"/messages"; // url (include query string)
        String timestamp = time.toString(); // current timestamp (epoch)
        String accessKey = ak; // access key id (from portal or Sub Account)
        String secretKey = sk;
        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256"); mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);
        return encodeBase64String; }

}
