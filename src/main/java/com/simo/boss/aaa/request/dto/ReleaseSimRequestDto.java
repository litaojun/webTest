/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ 
/*    */ public class ReleaseSimRequestDto
/*    */ {
/*    */   private short tokenLength;
/*    */   private String token;
/*    */   private short reason;
/*    */ 
/*    */   public ReleaseSimRequestDto()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ReleaseSimRequestDto(byte[] body)
/*    */   {
/* 32 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/* 33 */     this.tokenLength = buffer.getUnsigned();
/* 34 */     if (this.tokenLength > 0) {
/* 35 */       byte[] tokenBytes = new byte[this.tokenLength];
/* 36 */       buffer.get(tokenBytes);
/* 37 */       this.token = new String(tokenBytes);
/*    */     }
/* 39 */     this.reason = buffer.getUnsigned();
/*    */   }
/*    */ 
/*    */   public short getTokenLength() {
/* 43 */     return this.tokenLength;
/*    */   }
/*    */   public void setTokenLength(short tokenLength) {
/* 46 */     this.tokenLength = tokenLength;
/*    */   }
/*    */   public String getToken() {
/* 49 */     return this.token;
/*    */   }
/*    */   public void setToken(String token) {
/* 52 */     this.token = token;
/*    */   }
/*    */   public short getReason() {
/* 55 */     return this.reason;
/*    */   }
/*    */   public void setReason(short reason) {
/* 58 */     this.reason = reason;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.ReleaseSimRequestDto
 * JD-Core Version:    0.6.1
 */