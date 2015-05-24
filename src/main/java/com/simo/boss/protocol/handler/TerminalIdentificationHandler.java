/*     */ package com.simo.boss.protocol.handler;
/*     */ 
/*     */ import com.simo.boss.common.util.Util;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.protocol.Body;
/*     */ import com.simo.boss.protocol.CommonDto;
/*     */ import com.simo.boss.protocol.dao.TerminalDao;
/*     */ import com.simo.boss.protocol.domain.Terminal;
/*     */ import com.simo.boss.util.BytesUtil;
/*     */ import com.simo.boss.util.DesUtil;
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import java.math.BigInteger;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ 
/*     */ public class TerminalIdentificationHandler
/*     */   implements ICommonHandler
/*     */ {
/*  34 */   private static final Logger log = Logger.getLogger(TerminalIdentificationHandler.class);
/*     */ 
/*     */   public void process(IoSession session, CommonDto commonDto)
/*     */   {
/*  43 */     CommonDto returnDto = new CommonDto();
/*     */ 
/*  45 */     long keyIndex = commonDto.getKeyIndex();
/*     */ 
/*  47 */     Body body = new Body(commonDto.getBody());
/*     */ 
/*  49 */     byte[] bodyBytes = body.getSubBody();
/*  50 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(bodyBytes);
/*     */ 
/*  52 */     short snLength = buffer.getUnsigned();
/*  53 */     String sn = null;
/*  54 */     if (snLength > 0) {
/*  55 */       byte[] snBytes = new byte[snLength];
/*  56 */       buffer.get(snBytes);
/*     */ 
/*  58 */       sn = new String(snBytes);
/*  59 */       log.debug("============sn===>>" + sn);
/*     */     } else {
/*  61 */       log.error("======sn.length===<=0");
/*     */     }
/*     */ 
/*  64 */     TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/*  65 */     Terminal terminal = terminalDao.findTerminalBySn(sn);
/*     */ 
/*  68 */     String code1 = terminal.getCode1();
/*  69 */     long terminlId = terminal.getId().longValue();
/*  70 */     session.setAttribute("terminlId", Long.valueOf(terminlId));
/*     */ 
/*  72 */     byte[] random = createRand(session, code1);
/*     */ 
/*  74 */     Body returnBody = new Body();
/*     */ 
/*  76 */     returnBody.setOrderCode(2);
/*  77 */     IoBuffer ib = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*  78 */     ib.put((byte)random.length);
/*  79 */     ib.put(random);
/*  80 */     ib.flip();
/*  81 */     byte[] returnBodyBytes = IoBufferUtil.ioBufferToByte(ib);
/*     */ 
/*  83 */     returnBody.setSubBody(returnBodyBytes);
/*     */ 
/*  85 */     returnBody.countCheckCode();
/*     */ 
/*  87 */     returnDto.setBody(returnBody.toBytes());
/*  88 */     returnDto.setBodyLength(returnBody.toBytes().length);
/*  89 */     returnDto.setKeyIndex(keyIndex);
/*     */ 
/*  91 */     session.write(returnDto);
/*     */   }
/*     */ 
/*     */   private byte[] createRand(IoSession session, String code)
/*     */   {
/* 103 */     byte[] codes = code.getBytes();
/* 104 */     byte[] desKey = new byte[8];
/* 105 */     for (int i = 0; i < desKey.length; i++) {
/* 106 */       desKey[i] = codes[i];
/*     */     }
/* 108 */     log.debug("code==>>" + BytesUtil.outPutToString(codes));
/*     */ 
/* 110 */     BigInteger big = Util.bigRandomInteger(16);
/* 111 */     byte[] random = Util.bigRandomIntByte(big, 16);
/*     */ 
/* 113 */     log.debug("random==>>" + BytesUtil.outPutToString(random));
/*     */ 
/* 115 */     session.setAttribute("FirstKDC", random);
/* 116 */     session.setAttribute("code1", code);
/* 117 */     session.setAttribute("desKey", desKey);
/* 118 */     log.debug("======des createEncryptor=====>>" + BytesUtil.outPutToString(DesUtil.createEncryptor(desKey, random)));
/* 119 */     return random;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.handler.TerminalIdentificationHandler
 * JD-Core Version:    0.6.1
 */