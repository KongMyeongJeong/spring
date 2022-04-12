package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);	
	private final UserService userService;
	
	// 가입
	@PostMapping()
	public String sign(@RequestBody UserDto userDto) {
		userService.joinUser(userDto);
		
		return "가입성공";
	}
	
//	@GetMapping("/login")
//	public String login(Model model, Authentication authentication) {
//		UserDto userDto = (UserDto) authentication.getCredentials();
//		
//		System.err.println("userDto :: " + userDto);
//		
//		model.addAttribute("info", userDto.getUserId());
//		
//		return "성공";
//	}
	
//	@PostMapping("/login")
//	public String login(@RequestBody String id, String pw) {			
//		System.err.println(userService.loadUserByUsername(id));
//		
//		
//		return "로그인 성공";		
//	}
	
    @GetMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        UserDto userDto = (UserDto) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userDto.getUserId() +"의 "+ userDto.getUserName()+ "님");      //유저 아이디
        return "user_access";
    }
	
}
