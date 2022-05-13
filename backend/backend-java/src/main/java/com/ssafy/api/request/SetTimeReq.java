package com.ssafy.api.request;

import lombok.Data;

import java.util.Date;

@Data
public class SetTimeReq {

    Date StartTime;
    Date endTime;
}
