package com.ssafy.api.response;

import com.ssafy.db.entity.CCTV;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CctvListRes {

    List<CCTV> cctvList;
}
