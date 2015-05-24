/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ 
/*    */ public class QuerySimRequestDto
/*    */ {
/*    */   private long userId;
/*    */   private short tokenLength;
/*    */   private String token;
/*    */   private long simId;
/*    */ 
/*    */   public QuerySimRequestDto()
/*    */   {
/*    */   }
/*    */ 
/*    */   public QuerySimRequestDto(byte[] body)
/*    */   {
/* 32 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/* 33 */     this.userId = buffer.getUnsignedInt();
/*    */ 
/* 35 */     this.tokenLength = buffer.getUnsigned();
/* 36 */     byte[] tokenBytes = new byte[this.tokenLength];
/* 37 */     buffer.get(tokenBytes);
/* 38 */     this.token = new String(tokenBytes);
/*    */ 
/* 40 */     this.simId = buffer.getUnsignedInt();
/*    */   }
/*    */ 
/*    */   public long getUserId() {
/* 44 */     return this.userId;
/*    */   }
/*    */   public void setUserId(long userId) {
/* 47 */     this.userId = userId;
/*    */   }
/*    */   public short getTokenLength() {
/* 50 */     return this.tokenLength;
/*    */   }
/*    */   public void setTokenLength(short tokenLength) {
/* 53 */     this.tokenLength = tokenLength;
/*    */   }
/*    */   public String getToken() {
/* 56 */     return this.token;
/*    */   }
/*    */   public void setToken(String token) {
/* 59 */     this.token = token;
/*    */   }
/*    */   public long getSimId() {
/* 62 */     return this.simId;
/*    */   }
/*    */   public void setSimId(long simId) {
/* 65 */     this.simId = simId;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.QuerySimRequestDto
 * JD-Core Version:    0.6.1
 */