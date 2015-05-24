/*    */ package com.simo.boss.aaa.agency.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.callback.CommonCallBack;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class CommonAgencyTransmitHandler<T> extends CommonAgencyHandler<T>
/*    */ {
/* 25 */   private static final Logger log = Logger.getLogger(CommonAgencyTransmitHandler.class);
/*    */ 
/*    */   public CommonAgencyTransmitHandler(CommonCallBack callBack, T request) {
/* 28 */     setCallback(callBack);
/* 29 */     setRequest(request);
/*    */   }
/*    */ 
/*    */   public void messageReceived(IoSession session, Object message)
/*    */     throws Exception
/*    */   {
/* 36 */     log.debug("============messages from agency===========>>" + new String(((CommonJsonDto)message).getBody()));
/*    */ 
/* 38 */     getCallback().getSession().write(message);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.CommonAgencyTransmitHandler
 * JD-Core Version:    0.6.1
 */