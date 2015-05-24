/*    */ package com.simo.boss.aaa.callback;
/*    */ 
/*    */ import com.simo.boss.protocol.CommonDto;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class CommonSimoCallBack extends CommonCallBack
/*    */ {
/* 22 */   private static final Logger log = Logger.getLogger(CommonSimoCallBack.class);
/*    */ 
/*    */   public CommonSimoCallBack(IoSession session) {
/* 25 */     setSession(session);
/*    */   }
/*    */ 
/*    */   public void doCallBack(Object responseCommonDto) {
/* 29 */     CommonDto dto = null;
/* 30 */     if ((null != responseCommonDto) && ((responseCommonDto instanceof CommonDto))) {
/* 31 */       dto = (CommonDto)responseCommonDto;
/*    */     } else {
/* 33 */       log.error("**返回参数错误**CommonDto");
/* 34 */       throw new RuntimeException("****返回参数类型错误**CommonDto****");
/*    */     }
/* 36 */     getSession().write(decEncryptCommonSimoDto(dto));
/*    */   }
/*    */ 
/*    */   private CommonDto decEncryptCommonSimoDto(CommonDto dto) {
/* 40 */     long keyIndex = dto.getKeyIndex();
/* 41 */     log.debug("=======keyIndex========" + keyIndex);
/*    */ 
/* 43 */     if (keyIndex != 0L)
/*    */     {
/* 47 */       byte[] keys = (byte[])getSession().getAttribute("finalKey");
/* 48 */       if ((keys != null) && (keys.length > 0)) {
/* 49 */         dto.decEncryptBody(keys);
/* 50 */         log.debug("=======加密成功========");
/*    */       } else {
/* 52 */         log.error("**获取会话中的key失败,加密失败**");
/* 53 */         getSession().close(true);
/* 54 */         throw new RuntimeException("**获取会话中的key失败,加密失败,会话关闭**");
/*    */       }
/*    */     } else {
/* 57 */       log.debug("=======消息未加密========");
/*    */     }
/* 59 */     return dto;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.callback.CommonSimoCallBack
 * JD-Core Version:    0.6.1
 */