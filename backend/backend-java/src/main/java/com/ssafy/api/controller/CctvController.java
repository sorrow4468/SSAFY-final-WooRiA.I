package com.ssafy.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "CCTV API", tags = {"CCTV"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/cctv")
public class CctvController {


}
