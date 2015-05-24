package com.simo.boss.aaa.service;

import com.simo.boss.aaa.request.dto.QueryUserInfoRequestDto;
import java.util.Map;

public abstract interface UserInfoFacade
{
  public abstract Map<String, String> queryUserInfo(QueryUserInfoRequestDto paramQueryUserInfoRequestDto);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.UserInfoFacade
 * JD-Core Version:    0.6.1
 */