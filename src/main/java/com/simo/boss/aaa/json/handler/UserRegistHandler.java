/*    */ package com.simo.boss.aaa.json.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.agency.handler.AgencyFactory;
/*    */ import com.simo.boss.aaa.agency.handler.CommonAgencyHandler;
/*    */ import com.simo.boss.aaa.agency.handler.CommonAgencyTransmitHandler;
/*    */ import com.simo.boss.aaa.callback.CommonJsonCallBack;
/*    */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.UserRegistRequest;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.codehaus.jackson.type.TypeReference;
/*    */ 
/*    */ public class UserRegistHandler extends CommonJsonHandler
/*    */ {
/* 32 */   private static final Logger log = Logger.getLogger(UserRegistHandler.class);
/*    */ 
/*    */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*    */   {
/* 42 */     log.debug("=================UserRegistHandler=====================");
/* 43 */     byte[] body = commonJsonDto.getBody();
/* 44 */     String jsonString = new String(body);
/* 45 */     Request request = (Request)JsonUtil.jsonStringToObject(new TypeReference()
/*    */     {
/*    */     }
/*    */     , jsonString);
/* 46 */     ((UserRegistRequest)request.getParams()).setRegistChannel(1);
/* 47 */     CommonJsonCallBack callback = new CommonJsonCallBack(session);
/* 48 */     CommonAgencyHandler handler = new CommonAgencyTransmitHandler(callback, request);
/* 49 */     AgencyFactory factory = new AgencyFactory(handler);
/* 50 */     factory.handler();
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.UserRegistHandler
 * JD-Core Version:    0.6.1
 */