/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.domain.dto.agency.LoginRequest;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.client.service.JsonMsgService;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import java.util.Map;
/*    */ import org.codehaus.jackson.type.TypeReference;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("userLoginHandler")
/*    */ public class UserLoginHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JsonMsgService jsonMsgService;
/*    */ 
/*    */   public Response<Map<String, Object>> process(byte[] bytes)
/*    */   {
/* 26 */     Request request = parseJsonReq(bytes, LoginRequest.class);
/*    */ 
/* 29 */     validReq(request);
/*    */ 
/* 31 */     String resp = this.jsonMsgService.sendMessage("agency", request);
/*    */ 
/* 34 */     Response response = (Response)JsonUtil.jsonStringToObject(new TypeReference()
/*    */     {
/*    */     }
/*    */     , resp);
/*    */ 
/* 38 */     return response;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.UserLoginHandler
 * JD-Core Version:    0.6.1
 */