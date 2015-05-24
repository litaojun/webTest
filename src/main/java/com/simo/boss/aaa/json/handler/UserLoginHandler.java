/*    */ package com.simo.boss.aaa.json.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.agency.handler.AgencyFactory;
/*    */ import com.simo.boss.aaa.agency.handler.CommonAgencyHandler;
/*    */ import com.simo.boss.aaa.agency.handler.CommonAgencyTransmitHandler;
/*    */ import com.simo.boss.aaa.callback.CommonCallBack;
/*    */ import com.simo.boss.aaa.callback.CommonJsonCallBack;
/*    */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.domain.dto.agency.LoginRequest;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.codehaus.jackson.type.TypeReference;
/*    */ 
/*    */ public class UserLoginHandler extends CommonJsonHandler
/*    */ {
/*    */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*    */   {
/* 41 */     byte[] body = commonJsonDto.getBody();
/* 42 */     String jsonString = new String(body);
/* 43 */     Request request = (Request)JsonUtil.jsonStringToObject(new TypeReference()
/*    */     {
/*    */     }
/*    */     , jsonString);
/* 44 */     ((LoginRequest)request.getParams()).setLoginChannel(1);
/* 45 */     CommonCallBack callback = new CommonJsonCallBack(session);
/* 46 */     CommonAgencyHandler handler = new CommonAgencyTransmitHandler(callback, request);
/* 47 */     AgencyFactory factory = new AgencyFactory(handler);
/* 48 */     factory.handler();
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.UserLoginHandler
 * JD-Core Version:    0.6.1
 */