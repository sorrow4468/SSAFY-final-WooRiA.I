package com.ssafy.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.api.request.CCTVListReq;
import com.ssafy.api.request.ValidateEmailReq;
import com.ssafy.api.response.CctvListRes;
import com.ssafy.api.service.CctvService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Api(value = "CCTV API", tags = {"CCTV"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/cctv")
public class CctvController {

    @Autowired
    CctvService cctvService;

    //이메일 중복검사
    @PostMapping("/find/list")
    @ApiOperation(value = "상황 발생 리스트를 확인한다", notes = "상황 발생 리스트를 확인한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<CctvListRes> getCCTVList(@RequestBody @ApiParam(value="달력의 LocalDate", required = true) CCTVListReq cctvListReq)  {

        CctvListRes cctvList = cctvService.getCCTVList(cctvListReq);

        return new ResponseEntity<CctvListRes>(cctvList, HttpStatus.OK);

    }
}
