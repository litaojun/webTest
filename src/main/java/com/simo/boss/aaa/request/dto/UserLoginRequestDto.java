/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ 
/*    */ public class UserLoginRequestDto
/*    */ {
/*    */   private short usernameLength;
/*    */   private String username;
/*    */   private String password;
/*    */ 
/*    */   public UserLoginRequestDto()
/*    */   {
/*    */   }
/*    */ 
/*    */   public UserLoginRequestDto(byte[] body)
/*    */   {
/* 29 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/* 30 */     this.usernameLength = buffer.getUnsigned();
/* 31 */     byte[] usernameBytes = new byte[this.usernameLength];
/* 32 */     byte[] passwordBytes = new byte[16];
/* 33 */     buffer.get(usernameBytes);
/* 34 */     this.username = IoBufferUtil.byteToString(usernameBytes);
/* 35 */     buffer.get(passwordBytes);
/* 36 */     this.password = IoBufferUtil.byteToString(passwordBytes);
/*    */   }
/*    */ 
/*    */   public short getUsernameLength() {
/* 40 */     return this.usernameLength;
/*    */   }
/*    */   public void setUsernameLength(short usernameLength) {
/* 43 */     this.usernameLength = usernameLength;
/*    */   }
/*    */   public String getUsername() {
/* 46 */     return this.username;
/*    */   }
/*    */   public void setUsername(String username) {
/* 49 */     this.username = username;
/*    */   }
/*    */   public String getPassword() {
/* 52 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 55 */     this.password = password;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.UserLoginRequestDto
 * JD-Core Version:    0.6.1
 */