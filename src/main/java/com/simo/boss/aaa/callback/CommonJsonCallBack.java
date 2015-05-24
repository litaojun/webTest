/*    */ package com.simo.boss.aaa.callback;
/*    */ 
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class CommonJsonCallBack extends CommonCallBack
/*    */ {
/* 22 */   private static final Logger log = Logger.getLogger(CommonJsonCallBack.class);
/*    */ 
/* 24 */   public CommonJsonCallBack(IoSession session) { setSession(session); }
/*    */ 
/*    */ 
/*    */   public void doCallBack(Object responseCommonDto)
/*    */   {
/* 33 */     if ((responseCommonDto instanceof CommonJsonDto)) {
/* 34 */       getSession().write(responseCommonDto);
/*    */     } else {
/* 36 */       log.error("**返回参数错误**responseCommonDto");
/* 37 */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.callback.CommonJsonCallBack
 * JD-Core Version:    0.6.1
 */