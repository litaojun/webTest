/*    */ package com.simo.boss.aaa.service;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.QueryUserInfoRequestDto;
/*    */ import com.simo.boss.domain.dto.agency.QueryUserRequest;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
import com.simo.boss.enumerate.ErrorConstant;
/*    */ import com.simo.boss.enumerate.ErrorConstant.QueryUserInfo;
/*    */ import com.simo.boss.exception.BusinessException;
/*    */ import com.simo.boss.protocol.client.service.JsonMsgService;
/*    */ import com.simo.boss.protocol.dao.TokenDao;
/*    */ import com.simo.boss.protocol.domain.Token;
/*    */ import com.simo.boss.util.JsonUtil;

/*    */ import java.util.Map;

/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("userInfoFacade")
/*    */ public class UserInfoFacadeImpl
/*    */   implements UserInfoFacade
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TokenDao tokenDao;
/*    */ 
/*    */   @Autowired
/*    */   private JsonMsgService jsonMsgService;
/*    */ 
/*    */   public Map<String, String> queryUserInfo(QueryUserInfoRequestDto dto)
/*    */   {
/* 34 */     Token t = this.tokenDao.findByToken(dto.getToken());
/* 35 */     if ((null == t) || (t.getStatus().intValue() != 0) || (null == t.getUserId()) || (0L == t.getUserId().longValue())) {
/* 36 */       throw new BusinessException(67108866, "toke无效");
/*    */     }
/* 38 */     Long userId = t.getUserId();
/* 39 */     QueryUserRequest requestDto = new QueryUserRequest();
/* 40 */     requestDto.setUserId(userId);
/* 41 */     requestDto.setLang(dto.getLang());
/* 42 */     requestDto.setItemArray(dto.getItemArray());
/*    */ 
/* 44 */     Response response = queryUserInfoToAgency(requestDto);
/* 45 */     Map map = (Map)response.getResult();
/* 46 */     if (response.getErrorCode() != 0) {
/* 47 */       throw new BusinessException(ErrorConstant.QueryUserInfo.QueryUserInfoError.getValue(), "query.user.info.to.agency.error");
/*    */     }
/* 49 */     return map;
/*    */   }
/*    */ 
/*    */   private Response<Map> queryUserInfoToAgency(QueryUserRequest requestDto)
/*    */   {
/* 54 */     Response response = null;
/*    */     try {
/* 56 */       Request request = new Request();
/* 57 */       request.setId(1L);
/* 58 */       request.setMethod("queryUserInfo");
/* 59 */       request.setParams(requestDto);
/* 60 */       String respMsg = this.jsonMsgService.sendMessage("agency", request);
/*    */ 
/* 62 */       response = JsonUtil.jsonToResponse(respMsg, Map.class);
/*    */     }
/*    */     catch (Exception e) {
/* 65 */       e.printStackTrace();
/* 66 */       throw new BusinessException(ErrorConstant.QueryUserInfo.QueryUserInfoError.getValue(), "query.user.info.to.agency.error");
/*    */     }
/*    */ 
/* 69 */     return response;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.UserInfoFacadeImpl
 * JD-Core Version:    0.6.1
 */