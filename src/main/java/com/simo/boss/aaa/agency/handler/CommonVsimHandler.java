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
/*    */ public abstract class CommonVsimHandler<T> extends IoHandlerAdapter
/*    */ {
/* 29 */   private static final Logger log = Logger.getLogger(CommonVsimHandler.class);
/*    */   private CommonCallBack callback;
/*    */   private T request;
/*    */ 
/*    */   public void sessionOpened(IoSession session)
/*    */     throws Exception
/*    */   {
/* 37 */     CommonJsonDto dto = new CommonJsonDto();
/* 38 */     dto.setBody(JsonUtil.objectToJsonString(getRequest()).getBytes(Charset.forName("UTF-8")));
/* 39 */     session.write(dto);
/* 40 */     log.debug("=============send messages to vsim successed==============");
/*    */   }
/*    */ 
/*    */   public CommonCallBack getCallback() {
/* 44 */     return this.callback;
/*    */   }
/*    */ 
/*    */   public void setCallback(CommonCallBack callback) {
/* 48 */     this.callback = callback;
/*    */   }
/*    */ 
/*    */   public T getRequest() {
/* 52 */     return this.request;
/*    */   }
/*    */ 
/*    */   public void setRequest(T request) {
/* 56 */     this.request = request;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.CommonVsimHandler
 * JD-Core Version:    0.6.1
 */