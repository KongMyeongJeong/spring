package com.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.UserDto;
import com.app.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	Date time = new Date();
	String localTime = format.format(time);

	private final UserMapper userMapper;

	@Transactional
	public void joinUser(UserDto userDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDto.setUserPw(passwordEncoder.encode(userDto.getPassword()));
		userDto.setUserAuth("USER");
		userDto.setAppendDate(localTime);
		userDto.setUpdateDate(localTime);
		userMapper.joinUser(userDto);
	}

	// - loadUserByUsername()메서드는 SpringSecurity의 속성으로 지정한 loginProcessingUrl("/loginProcess")에 해당하는 URL이 호출될 때 수행되어진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = userMapper.getUserInfo(username);

		if (userDto == null) {
			throw new UsernameNotFoundException("User not authorized.");
		}

		return userDto;
	}

}
