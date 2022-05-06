package com.ssafy.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.api.request.MessagesRequestDto;
import com.ssafy.api.response.SendSmsResponseDto;
import org.apache.http.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MessageService {
    public SendSmsResponseDto sendSms(String recipientPhoneNumber) throws ParseException, JsonProcessingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, URISyntaxException;

    public String makeSignature(Long time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException;
    public boolean isVerify(String recipientPhoneNumber, String certificationNumber);
    public boolean verifySms(MessagesRequestDto messageRequest);
}
