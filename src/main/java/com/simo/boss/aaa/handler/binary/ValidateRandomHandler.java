/*     */ package com.simo.boss.aaa.handler.binary;
/*     */ 
/*     */ import com.simo.boss.aaa.service.TerminalAuthorityFacade;
/*     */ import com.simo.boss.common.util.Util;
/*     */ import com.simo.boss.domain.dto.BaseResp;
/*     */ import com.simo.boss.domain.dto.agency.Response;
import com.simo.boss.enumerate.ErrorConstant;
/*     */ import com.simo.boss.enumerate.ErrorConstant.ConsultationEncrypted;
/*     */ import com.simo.boss.exception.BusinessException;
/*     */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*     */ import com.simo.boss.util.BytesUtil;
/*     */ import com.simo.boss.util.CommonTime;
/*     */ import com.simo.boss.util.DesUtil;
/*     */ import com.simo.boss.util.IoBufferUtil;

/*     */ import java.math.BigInteger;

/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("validateRandomHandler")
/*     */ public class ValidateRandomHandler extends BaseMessageHandler
/*     */ {
/*  33 */   private static final Logger log = Logger.getLogger(ValidateRandomHandler.class);
/*     */ 
/*     */   @Autowired
/*     */   private TerminalAuthorityFacade terminalAuthorityFacade;
/*     */ 
/*     */   public Response<BaseResp> process(IoSession session, byte[] bytes)
/*     */   {
/*  45 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(bytes);
/*     */ 
/*  47 */     long tmpTerminalId = buffer.getUnsignedInt();
/*     */ 
/*  50 */     short randEncryptedLength = buffer.getUnsigned();
/*     */ 
/*  53 */     byte[] randEncrypted = new byte[randEncryptedLength];
/*  54 */     buffer.get(randEncrypted);
/*  55 */     buffer.flip();
/*     */ 
/*  57 */     byte[] random = (byte[])session.getAttribute("FirstKDC");
/*  58 */     byte[] desKey = (byte[])session.getAttribute("desKey");
/*     */ 
/*  61 */     byte[] ret = DesUtil.createEncryptor(desKey, random);
/*  62 */     if (log.isDebugEnabled())
/*     */     {
/*  64 */       log.debug("[terminalId:" + tmpTerminalId + "]randEncrypted length:" + randEncryptedLength);
/*     */ 
/*  66 */       log.debug("random------>" + BytesUtil.outPutToString(random));
/*  67 */       log.debug("desKey------>" + BytesUtil.outPutToString(desKey));
/*  68 */       log.debug("terminal randEncrypted------>" + BytesUtil.outPutToString(randEncrypted));
/*     */ 
/*  70 */       log.debug("server encrypted result------>" + BytesUtil.outPutToString(ret));
/*     */     }
/*     */ 
/*  75 */     boolean flag = BytesUtil.compareBytes(randEncrypted, ret);
/*  76 */     if (!flag)
/*     */     {
/*  78 */       log.error("[terminalId:" + tmpTerminalId + "] request [random:" + random + ",randEncrypted:" + randEncrypted + "]");
/*     */ 
/*  80 */       throw new BusinessException(ErrorConstant.ConsultationEncrypted.ConsulateFail.getValue(), "加密结果不一致");
/*     */     }
/*     */ 
/*  87 */     long terminalId = ((Long)session.getAttribute("terminlId")).longValue();
/*  88 */     long keyIndex = terminalId;
/*     */ 
/*  90 */     String code1 = (String)session.getAttribute("code1");
/*  91 */     byte[] kdc = createKDC(session, code1, keyIndex);
/*     */ 
/*  94 */     this.terminalAuthorityFacade.saveEncryptKey(terminalId, kdc);
/*     */ 
/*  96 */     final byte[] rtnBodyBytes = wrapResp(keyIndex, kdc);
/*     */ 
/*  98 */     BaseResp baseResp = new BaseResp()
/*     */     {
/*     */       public byte[] toBytes() {
/* 101 */         return rtnBodyBytes;
/*     */       }
/*     */     };
/* 104 */     Response resp = new Response();
/* 105 */     resp.setResult(baseResp);
/* 106 */     return resp;
/*     */   }
/*     */ 
/*     */   private byte[] createKDC(IoSession session, String code, long keyIndex)
/*     */   {
/* 119 */     byte[] codes = code.getBytes();
/* 120 */     byte[] desKey = new byte[8];
/* 121 */     for (int i = 0; i < desKey.length; i++) {
/* 122 */       desKey[i] = codes[i];
/*     */     }
/*     */ 
/* 125 */     BigInteger big = Util.bigRandomInteger(16);
/* 126 */     byte[] random = Util.bigRandomIntByte(big, 16);
/*     */ 
/* 129 */     byte[] key = DesUtil.createEncryptor(desKey, random);
/* 130 */     return key;
/*     */   }
/*     */ 
/*     */   private byte[] wrapResp(long keyIndex, byte[] kdc) {
/* 134 */     CommonTime time = new CommonTime();
/* 135 */     IoBuffer ib = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*     */ 
/* 137 */     ib.putInt((int)keyIndex);
/* 138 */     ib.put((byte)kdc.length);
/* 139 */     ib.put(kdc);
/* 140 */     ib.putShort(time.getYear());
/* 141 */     ib.put(time.getMonth());
/* 142 */     ib.put(time.getDay());
/* 143 */     ib.put(time.getHour());
/* 144 */     ib.put(time.getMinute());
/* 145 */     ib.put(time.getSecond());
/* 146 */     ib.flip();
/* 147 */     byte[] returnBodyBytes = IoBufferUtil.ioBufferToByte(ib);
/* 148 */     ib.free();
/* 149 */     return returnBodyBytes;
/*     */   }
/*     */ 
/*     */   protected Response<BaseResp> process(byte[] bytes)
/*     */   {
/* 155 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.binary.ValidateRandomHandler
 * JD-Core Version:    0.6.1
 */