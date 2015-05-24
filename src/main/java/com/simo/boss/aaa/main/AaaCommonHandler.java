/*    */ package com.simo.boss.aaa.main;
/*    */ 
/*    */ import com.simo.boss.common.mina.CommonIoHandlerAdapter;
/*    */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*    */ import com.simo.boss.protocol.Body;
/*    */ import com.simo.boss.protocol.CommonDto;
/*    */ import com.simo.boss.protocol.dao.EncryptDao;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.protocol.domain.Encrypt;
/*    */ import com.simo.boss.protocol.domain.Terminal;
/*    */ import com.simo.boss.util.BytesUtil;
/*    */ import com.simo.boss.util.DesUtil;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class AaaCommonHandler extends CommonIoHandlerAdapter
/*    */ {
/* 33 */   private static final Logger log = Logger.getLogger(AaaCommonHandler.class);
/*    */ 
/*    */   public void messageReceived(IoSession session, Object message)
/*    */     throws Exception
/*    */   {
/* 39 */     CommonDto commonDto = (CommonDto)message;
/* 40 */     log.debug("==session.id==>>" + session.getId() + "=========AaaCommonHandler messageReceived==========");
/* 41 */     log.debug("======commonDto===BodyLength==========" + commonDto.getBodyLength());
/* 42 */     log.debug("======commonDto===Body==========" + commonDto.getBody());
/*    */ 
/* 44 */     decDecryptCommonCommonDto(session, commonDto);
/*    */ 
/* 46 */     boolean flag = commonDto.validateCheckCode();
/* 47 */     if (!flag) {
/* 48 */       session.close(true);
/* 49 */       log.error("=====消息校验失败，会话关闭=====");
/* 50 */       throw new RuntimeException("消息校验失败，会话关闭");
/*    */     }
/*    */ 
/* 53 */     Body body = new Body(commonDto.getBody());
/* 54 */     log.debug("============OrderCode============" + body.getOrderCode());
/* 55 */     CommonHandler handler = new CommonHandler(body.getOrderCode());
/* 56 */     handler.exec(session, commonDto);
/*    */   }
/*    */ 
/*    */   private CommonDto decDecryptCommonCommonDto(IoSession session, CommonDto commonDto)
/*    */   {
/* 61 */     long keyIndex = commonDto.getKeyIndex();
/* 62 */     if (keyIndex > 0L) {
/* 63 */       session.setAttribute("keyIndex", Long.valueOf(keyIndex));
/* 64 */       EncryptDao encryptDao = (EncryptDao)SpringContextUtil.getBean("encryptDao");
/* 65 */       Encrypt encrypt = encryptDao.findById(Long.valueOf(keyIndex));
/* 66 */       if (encrypt == null) {
/* 67 */         session.close(true);
/* 68 */         log.error("=====密钥索引无效，会话关闭=====");
/* 69 */         throw new RuntimeException("密钥索引无效，会话关闭");
/*    */       }
/*    */ 
/* 72 */       byte[] key = encrypt.getKeyEncrypt();
/*    */ 
/* 74 */       TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/* 75 */       Terminal terminal = terminalDao.findTerminalById(keyIndex);
/* 76 */       byte[] codes = terminal.getCode1().getBytes();
/*    */ 
/* 82 */       byte[] finalKey = DesUtil.createDecryptor(codes, key);
/* 83 */       log.debug("==========真正的key=========" + BytesUtil.outPutToString(finalKey));
/* 84 */       if ((finalKey != null) && (finalKey.length != 0)) {
/* 85 */         commonDto.decDecryptBody(finalKey);
/* 86 */         session.setAttribute("finalKey", finalKey);
/* 87 */         log.debug("**获取会话中的key成功,解密成功**");
/*    */       } else {
/* 89 */         log.error("**获取会话中的key失败,解密失败**");
/* 90 */         session.close(true);
/* 91 */         throw new RuntimeException("**获取会话中的key失败,解密失败,会话关闭**");
/*    */       }
/*    */     }
/* 94 */     return commonDto;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.main.AaaCommonHandler
 * JD-Core Version:    0.6.1
 */