/*    */ package com.simo.boss.aaa.agency.handler;
/*    */ 
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import java.nio.charset.Charset;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.service.IoHandlerAdapter;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class HeartBeatHandler<T> extends IoHandlerAdapter
/*    */ {
/* 27 */   private static final Logger log = Logger.getLogger(HeartBeatHandler.class);
/*    */   private T request;
/*    */ 
/*    */   public HeartBeatHandler(T request)
/*    */   {
/* 31 */     this.request = request;
/*    */   }
/*    */ 
/*    */   public void sessionOpened(IoSession session)
/*    */     throws Exception
/*    */   {
/* 37 */     CommonJsonDto dto = new CommonJsonDto();
/* 38 */     dto.setBody(JsonUtil.objectToJsonString(getRequest()).getBytes(Charset.forName("UTF-8")));
/* 39 */     session.write(dto);
/* 40 */     log.debug("=============send messages to heartBeat successed==============");
/*    */   }
/*    */ 
/*    */   public T getRequest() {
/* 44 */     return this.request;
/*    */   }
/*    */ 
/*    */   public void setRequest(T request) {
/* 48 */     this.request = request;
/*    */   }
/*    */ 
/*    */   public void messageReceived(IoSession session, Object message)
/*    */     throws Exception
/*    */   {
/* 55 */     log.debug("=============receive messages to heartBeat successed==============");
/* 56 */     CommonJsonDto jsonDto = (CommonJsonDto)message;
/* 57 */     log.info("========HeartBeatHandler===========>>" + new String(jsonDto.getBody()));
/* 58 */     session.close(true);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.HeartBeatHandler
 * JD-Core Version:    0.6.1
 */