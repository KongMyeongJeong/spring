package com.app.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.service.UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserService useService;

	
	/**
	 * .antMatchers()
	 * : 페이지에 접근할 수 있는 권한을 설정한다.
	 * .loginPage
	 * : 로그인 페이지
	 * .loginProcessingUrl
	 * : 구현한 로그인 페이지
	 * defaultSuccessUrl
	 * : 로그인 성공 시 제공할 페이지
	 * failureUrl
	 * : 로그인 실패 시 제공할 페이지
	 * csrf().disable()
	 * : 사이트 간 요청 위조(Cross-Site Request Forgery) 공격 방지 기능 키기		
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests().antMatchers("/login", "/sign", "/access_denied", "/resources/**").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
			.antMatchers("/userAccess").hasRole("USER")
			.antMatchers("/userAccess").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/loginProcess")
				.defaultSuccessUrl("/user_access")
			.failureUrl("/access_denied") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
			.and().csrf().disable(); //로그인 창	
	}

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(useService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
	
	

}
