/*     */ package com.simo.boss.protocol.handler;
/*     */ 
/*     */ import com.simo.boss.common.util.Util;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.protocol.Body;
/*     */ import com.simo.boss.protocol.CommonDto;
/*     */ import com.simo.boss.protocol.dao.EncryptDao;
/*     */ import com.simo.boss.protocol.domain.Encrypt;
/*     */ import com.simo.boss.util.BytesUtil;
/*     */ import com.simo.boss.util.CommonTime;
/*     */ import com.simo.boss.util.DesUtil;
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Date;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ 
/*     */ public class ValidateRandomHandler
/*     */   implements ICommonHandler
/*     */ {
/*  37 */   private static final Logger log = Logger.getLogger(ValidateRandomHandler.class);
/*     */ 
/*     */   public void process(IoSession session, CommonDto commonDto)
/*     */   {
/*  46 */     CommonDto returnCommonDto = new CommonDto();
/*     */ 
/*  48 */     long keyIndex = commonDto.getKeyIndex();
/*  49 */     long terminlId = ((Long)session.getAttribute("terminlId")).longValue();
/*  50 */     returnCommonDto.setKeyIndex(keyIndex);
/*  51 */     keyIndex = terminlId;
/*  52 */     Body body = new Body(commonDto.getBody());
/*  53 */     byte[] subBodyBytes = body.getSubBody();
/*  54 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(subBodyBytes);
/*  55 */     short randEncryptedLength = buffer.getUnsigned();
/*  56 */     log.debug("======randEncrypted length======>>" + randEncryptedLength);
/*  57 */     byte[] randEncrypted = new byte[randEncryptedLength];
/*     */ 
/*  59 */     buffer.get(randEncrypted);
/*  60 */     buffer.flip();
/*  61 */     log.debug("======randEncrypted buffer======>>" + buffer);
/*  62 */     byte[] random = (byte[])session.getAttribute("FirstKDC");
/*  63 */     byte[] desKey = (byte[])session.getAttribute("desKey");
/*     */ 
/*  65 */     byte[] ret = DesUtil.createEncryptor(desKey, random);
/*  66 */     log.debug("======random======>>" + BytesUtil.outPutToString(random));
/*  67 */     log.debug("======desKey======>>" + BytesUtil.outPutToString(desKey));
/*  68 */     log.debug("======randEncrypted======>>" + BytesUtil.outPutToString(randEncrypted));
/*  69 */     log.debug("======ret======>>" + BytesUtil.outPutToString(ret));
/*  70 */     boolean flag = BytesUtil.compareBytes(randEncrypted, ret);
/*  71 */     if (!flag) {
/*  72 */       log.error("====随机数加密确认失败，会话关闭====");
/*  73 */       session.close(true);
/*  74 */       return;
/*     */     }
/*  76 */     String code1 = (String)session.getAttribute("code1");
/*  77 */     byte[] kdc = createKDC(session, code1, keyIndex);
/*  78 */     CommonTime time = new CommonTime();
/*  79 */     Body returnBody = new Body();
/*  80 */     returnBody.setOrderCode(4);
/*  81 */     IoBuffer ib = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*  82 */     ib.putInt((int)keyIndex);
/*  83 */     ib.put((byte)kdc.length);
/*  84 */     ib.put(kdc);
/*  85 */     ib.putShort(time.getYear());
/*  86 */     ib.put(time.getMonth());
/*  87 */     ib.put(time.getDay());
/*  88 */     ib.put(time.getHour());
/*  89 */     ib.put(time.getMinute());
/*  90 */     ib.put(time.getSecond());
/*  91 */     ib.flip();
/*  92 */     byte[] returnBodyBytes = IoBufferUtil.ioBufferToByte(ib);
/*  93 */     returnBody.setSubBody(returnBodyBytes);
/*     */ 
/*  95 */     returnBody.countCheckCode();
/*  96 */     returnCommonDto.setBody(returnBody.toBytes());
/*  97 */     returnCommonDto.setBodyLength(returnBody.toBytes().length);
/*  98 */     session.write(returnCommonDto);
/*     */   }
/*     */ 
/*     */   private byte[] createKDC(IoSession session, String code, long keyIndex)
/*     */   {
/* 111 */     byte[] codes = code.getBytes();
/* 112 */     byte[] desKey = new byte[8];
/* 113 */     for (int i = 0; i < desKey.length; i++) {
/* 114 */       desKey[i] = codes[i];
/*     */     }
/* 116 */     log.debug("code==>>" + BytesUtil.outPutToString(codes));
/*     */ 
/* 118 */     BigInteger big = Util.bigRandomInteger(16);
/* 119 */     byte[] random = Util.bigRandomIntByte(big, 16);
/* 120 */     log.debug("random==>>" + BytesUtil.outPutToString(random));
/*     */ 
/* 140 */     byte[] key = DesUtil.createEncryptor(desKey, random);
/* 141 */     log.debug("=====临时密钥=====>>" + BytesUtil.outPutToString(key));
/*     */ 
/* 143 */     EncryptDao encryptDao = (EncryptDao)SpringContextUtil.getBean("encryptDao");
/* 144 */     Encrypt encrypt = new Encrypt();
/* 145 */     encrypt.setKeyIndex(new Long(keyIndex));
/* 146 */     encrypt.setCreateTime(new Date());
/* 147 */     encrypt.setKeyEncrypt(key);
/* 148 */     encryptDao.saveOrUpdateEncrypt(encrypt);
/* 149 */     return key;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.handler.ValidateRandomHandler
 * JD-Core Version:    0.6.1
 */