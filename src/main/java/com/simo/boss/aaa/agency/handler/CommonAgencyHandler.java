/*    */ package com.simo.boss.aaa.agency.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.callback.CommonCallBack;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import java.nio.charset.Charset;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.service.IoHandlerAdapter;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public abstract class CommonAgencyHandler<T> extends IoHandlerAdapter
/*    */ {
/* 27 */   private static final Logger log = Logger.getLogger(CommonAgencyHandler.class);
/*    */   private CommonCallBack callback;
/*    */   private T request;
/*    */ 
/*    */   public CommonCallBack getCallback()
/*    */   {
/* 33 */     return this.callback;
/*    */   }
/*    */   public void setCallback(CommonCallBack callback) {
/* 36 */     this.callback = callback;
/*    */   }
/*    */   public T getRequest() {
/* 39 */     return this.request;
/*    */   }
/*    */   public void setRequest(T request) {
/* 42 */     this.request = request;
/*    */   }
/*    */ 
/*    */   public void sessionOpened(IoSession session)
/*    */     throws Exception
/*    */   {
/* 48 */     CommonJsonDto dto = new CommonJsonDto();
/* 49 */     dto.setBody(JsonUtil.objectToJsonString(getRequest()).getBytes(Charset.forName("UTF-8")));
/* 50 */     session.write(dto);
/* 51 */     log.debug("=============send messages to agency successed==============");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.CommonAgencyHandler
 * JD-Core Version:    0.6.1
 */