/*    */ package com.simo.boss.aaa.agency.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.callback.CommonCallBack;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class ReleaseSimConnectHandler<T> extends CommonAgencyHandler<T>
/*    */ {
/* 26 */   private static final Logger log = Logger.getLogger(ReleaseSimConnectHandler.class);
/*    */ 
/*    */   public ReleaseSimConnectHandler(CommonCallBack callback, T request) {
/* 29 */     setCallback(callback);
/* 30 */     setRequest(request);
/*    */   }
/*    */ 
/*    */   public ReleaseSimConnectHandler()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void messageReceived(IoSession session, Object message)
/*    */     throws Exception
/*    */   {
/* 41 */     CommonJsonDto dto = (CommonJsonDto)message;
/* 42 */     String response = new String(dto.getBody());
/* 43 */     log.debug("========get release sim from agency successed=======>>>>" + response);
/* 44 */     JSONObject json = new JSONObject(response);
/* 45 */     int errorCode = ((Integer)json.get("errorCode")).intValue();
/*    */ 
/* 47 */     if (errorCode == 0)
/* 48 */       log.error("==释放卡成功==>>errorCode==>>" + errorCode + "==>>keyIndex==>>" + getCallback().getSession().getAttribute("keyIndex").toString());
/*    */     else {
/* 50 */       log.error("==释放卡失败==>>errorCode==>>" + errorCode + "==>>keyIndex==>>" + getCallback().getSession().getAttribute("keyIndex").toString());
/*    */     }
/* 52 */     getCallback().getSession().close(true);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.ReleaseSimConnectHandler
 * JD-Core Version:    0.6.1
 */