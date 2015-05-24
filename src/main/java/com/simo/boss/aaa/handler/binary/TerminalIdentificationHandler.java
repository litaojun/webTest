/*     */ package com.simo.boss.aaa.handler.binary;
/*     */ 
/*     */ import com.simo.boss.aaa.service.TerminalAuthorityFacade;
/*     */ import com.simo.boss.common.util.Util;
/*     */ import com.simo.boss.domain.dto.BaseResp;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.protocol.domain.Terminal;
/*     */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*     */ import com.simo.boss.util.BytesUtil;
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import java.math.BigInteger;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("terminalIdentificationHandler")
/*     */ public class TerminalIdentificationHandler extends BaseMessageHandler
/*     */ {
/*  30 */   private static final Logger log = Logger.getLogger(TerminalIdentificationHandler.class);
/*     */ 
/*     */   @Autowired
/*     */   private TerminalAuthorityFacade terminalAuthorityFacade;
/*     */ 
/*     */   public Response<BaseResp> process(IoSession session, byte[] bytes)
/*     */   {
/*  38 */     String sn = getTerminalSN(bytes);
/*  39 */     log.debug("terminal authority:sn is " + sn);
/*     */ 
/*  41 */     Terminal terminal = this.terminalAuthorityFacade.authenticate(sn);
/*     */ 
/*  43 */     final long terminalId = terminal.getId().longValue();
/*  44 */     session.setAttribute("terminlId", Long.valueOf(terminalId));
/*  45 */     session.setAttribute("keyIndex", Long.valueOf(terminalId));
/*     */ 
/*  48 */     byte[] random = createRand(session, terminal.getCode1());
/*     */ 
/*  50 */     Response resp = new Response();
/*  51 */     BaseResp baseResp = new BaseResp()
/*     */     {
/*     */       public byte[] toBytes()
/*     */       {
/*  55 */         IoBuffer ib = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*     */ 
/*  57 */         ib.putUnsignedInt(terminalId);
/*  58 */         ib.put((byte)8);
/*  59 */         ib.put("aaa".getBytes());
/*  60 */         ib.flip();
/*  61 */         byte[] returnBodyBytes = IoBufferUtil.ioBufferToByte(ib);
/*  62 */         ib.free();
/*  63 */         return returnBodyBytes;
/*     */       }
/*     */     };
/*  66 */     resp.setResult(baseResp);
/*  67 */     return resp;
/*     */   }
/*     */ 
/*     */   private String getTerminalSN(byte[] bytes)
/*     */   {
/*  72 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(bytes);
/*     */     try
/*     */     {
/*  75 */       short snLength = buffer.getUnsigned();
/*     */ 
/*  77 */       if (snLength < 1) {
/*  78 */         throw new RuntimeException("sn's length[" + snLength + "] required!");
/*     */       }
/*     */ 
/*  82 */       byte[] snBytes = new byte[snLength];
/*  83 */       buffer.get(snBytes);
/*     */ 
/*  85 */       String sn = new String(snBytes);
/*  86 */       return sn;
/*     */     } finally {
/*  88 */       if (buffer != null)
/*  89 */         buffer.free();
/*     */     }
/*     */   }
/*     */ 
/*     */   private byte[] createRand(IoSession session, String code)
/*     */   {
/* 102 */     byte[] codes = code.getBytes();
/* 103 */     byte[] desKey = new byte[8];
/* 104 */     for (int i = 0; i < desKey.length; i++) {
/* 105 */       desKey[i] = codes[i];
/*     */     }
/*     */ 
/* 108 */     BigInteger big = Util.bigRandomInteger(16);
/* 109 */     byte[] random = Util.bigRandomIntByte(big, 16);
/*     */ 
/* 111 */     if (log.isDebugEnabled()) {
/* 112 */       log.debug("terminal generate random:" + BytesUtil.outPutToString(random) + " for code1:" + code);
/*     */     }
/*     */ 
/* 116 */     session.setAttribute("FirstKDC", random);
/* 117 */     session.setAttribute("code1", code);
/* 118 */     session.setAttribute("desKey", desKey);
/*     */ 
/* 120 */     return random;
/*     */   }
/*     */ 
/*     */   protected Response<BaseResp> process(byte[] bytes)
/*     */   {
/* 125 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.binary.TerminalIdentificationHandler
 * JD-Core Version:    0.6.1
 */