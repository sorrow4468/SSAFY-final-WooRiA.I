package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.ValidateEmailReq;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByEmail(String email);
	User saveUser(User user);
	public boolean verifyEmail(ValidateEmailReq validateEmailReq);
	void setUserPasswordByEmail(String email,String pw);
}
