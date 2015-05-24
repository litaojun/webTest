/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.domain.dto.agency.QueryUserInfoRequest;
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
/*    */ @Component("queryUserInfoHandler")
/*    */ public class QueryUserInfoHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JsonMsgService jsonMsgService;
/*    */ 
/*    */   public Response<Map<String, Object>> process(byte[] bytes)
/*    */   {
/* 26 */     Request request = parseJsonReq(bytes, QueryUserInfoRequest.class);
/*    */ 
/* 30 */     validReq(request);
/*    */ 
/* 32 */     String resp = this.jsonMsgService.sendMessage("agency", request);
/*    */ 
/* 35 */     Response response = (Response)JsonUtil.jsonStringToObject(new TypeReference()
/*    */     {
/*    */     }
/*    */     , resp);
/*    */ 
/* 39 */     return response;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.QueryUserInfoHandler
 * JD-Core Version:    0.6.1
 */