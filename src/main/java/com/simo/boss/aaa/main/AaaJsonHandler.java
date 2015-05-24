/*    */ package com.simo.boss.aaa.main;
/*    */ 
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.common.mina.CommonIoHandlerAdapter;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class AaaJsonHandler extends CommonIoHandlerAdapter
/*    */ {
/* 27 */   private static Logger log = LoggerFactory.getLogger(AaaJsonHandler.class);
/*    */ 
/*    */   public void messageReceived(IoSession session, Object message)
/*    */     throws JSONException
/*    */   {
/* 38 */     System.out.println("message:" + message);
/* 39 */     CommonJsonDto jsonDto = (CommonJsonDto)message;
/* 40 */     byte[] body = jsonDto.getBody();
/* 41 */     String msg = new String(body);
/* 42 */     log.debug("==session.id==>>" + session.getId() + "=========AaaJsonHandler messageReceived==========" + msg);
/* 43 */     JSONObject json = new JSONObject(msg);
/*    */ 
/* 45 */     String method = json.getString("method");
/* 46 */     log.debug("=========method======" + method);
/* 47 */     if (("".equals(method)) || (null == method)) {
/* 48 */       log.error("=========received error=========" + msg);
/* 49 */       return;
/*    */     }
/* 51 */     CommonJsonHandler handler = CommonJsonHandler.getInstance(method);
/* 52 */     if (null == handler) {
/* 53 */       throw new RuntimeException(" handler is null!!!");
/*    */     }
/* 55 */     handler.exec(session, jsonDto);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.main.AaaJsonHandler
 * JD-Core Version:    0.6.1
 */