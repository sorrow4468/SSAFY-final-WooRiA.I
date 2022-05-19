package com.ssafy.api.service;

import com.ssafy.api.request.SetTimeReq;
import com.ssafy.api.request.ValidateEmailReq;
import com.ssafy.db.entity.Gender;
import com.ssafy.db.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	UserRepositorySupport userRepositorySupport;

	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setEmail(userRegisterInfo.getEmail());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		System.out.println("password"+ userRegisterInfo.getPassword());
		user.setPassword(bCryptPasswordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getNickname());
		user.setRole("ROLE_USER");
		user.setPhone(userRegisterInfo.getPhone());

		return userRepository.saveAndFlush(user);
	}



	@Override
	public User getUserByEmail(String email) {

		User user = userRepository.findUserByEmail(email);
		return user;
	}

	@Override
	public User saveUser(User user) {
		User userEntity = userRepository.save(user);
		return userEntity;
	}

	@Override
	public boolean verifyEmail(ValidateEmailReq validateEmailReq) {

		if (! userRepository.existsByEmail(validateEmailReq.getEmail())) {
			return true;
		}

		return false;
	}

	@Override
	@Transactional
	public void setUserPasswordByEmail(String email, String pw) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		userRepository.updatePassword(email,bCryptPasswordEncoder.encode(pw));

	}

	@Override
	@Transactional
	public void setTimer(String email, SetTimeReq setTimeReq) {
		LocalDateTime startTime = setTimeReq.getStartTime().toInstant() // Date -> Instant
				.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
				.toLocalDateTime();

		LocalDateTime endTime = setTimeReq.getEndTime().toInstant() // Date -> Instant
				.atZone(ZoneId.systemDefault()) // Instant -> ZonedDateTime
				.toLocalDateTime();


		userRepository.setUserTimer(email,startTime,endTime);
	}

}
