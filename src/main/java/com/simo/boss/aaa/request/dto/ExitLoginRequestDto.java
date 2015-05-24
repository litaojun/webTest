/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ 
/*    */ public class ExitLoginRequestDto
/*    */ {
/*    */   private short usernameLength;
/*    */   private String username;
/*    */   private short tokenLength;
/*    */   private String token;
/*    */ 
/*    */   public ExitLoginRequestDto()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ExitLoginRequestDto(byte[] body)
/*    */   {
/* 31 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/* 32 */     this.usernameLength = buffer.getUnsigned();
/* 33 */     byte[] usernameBytes = new byte[this.usernameLength];
/* 34 */     buffer.get(usernameBytes);
/* 35 */     this.username = IoBufferUtil.byteToString(usernameBytes);
/*    */ 
/* 37 */     this.tokenLength = buffer.getUnsigned();
/* 38 */     byte[] tokenBytes = new byte[this.tokenLength];
/* 39 */     buffer.get(tokenBytes);
/* 40 */     this.token = IoBufferUtil.byteToString(tokenBytes);
/*    */   }
/*    */ 
/*    */   public short getUsernameLength() {
/* 44 */     return this.usernameLength;
/*    */   }
/*    */ 
/*    */   public void setUsernameLength(short usernameLength) {
/* 48 */     this.usernameLength = usernameLength;
/*    */   }
/*    */ 
/*    */   public String getUsername() {
/* 52 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 56 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public short getTokenLength() {
/* 60 */     return this.tokenLength;
/*    */   }
/*    */ 
/*    */   public void setTokenLength(short tokenLength) {
/* 64 */     this.tokenLength = tokenLength;
/*    */   }
/*    */ 
/*    */   public String getToken() {
/* 68 */     return this.token;
/*    */   }
/*    */ 
/*    */   public void setToken(String token) {
/* 72 */     this.token = token;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.ExitLoginRequestDto
 * JD-Core Version:    0.6.1
 */