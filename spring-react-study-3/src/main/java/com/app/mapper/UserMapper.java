package com.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.app.dto.UserDto;

//@Mapper : Marker interface for MyBatis mappers
@Mapper
public interface UserMapper {
    // 로그인
    UserDto getUserInfo(String userId);

    // 회원가입
    void joinUser(UserDto userDto);
}
