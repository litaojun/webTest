/*     */ package com.simo.boss.aaa.request.dto;
/*     */ 
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ 
/*     */ public class ApplySimRequestDto
/*     */ {
/*     */   private short usernameLength;
/*     */   private String userName;
/*     */   private short passwordLength;
/*     */   private byte[] password;
/*  28 */   private short mccLength = 3;
/*     */   private String mcc;
/*     */   private short mncLength;
/*     */   private String mnc;
/*     */   private short supportedNetwork;
/*     */   private short supportedService;
/*     */ 
/*     */   public ApplySimRequestDto()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ApplySimRequestDto(byte[] body)
/*     */   {
/*  39 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/*  40 */     this.usernameLength = buffer.getUnsigned();
/*  41 */     if (this.usernameLength > 0) {
/*  42 */       byte[] usernameBytes = new byte[this.usernameLength];
/*  43 */       buffer.get(usernameBytes);
/*  44 */       this.userName = new String(usernameBytes);
/*     */     }
/*     */ 
/*  47 */     this.passwordLength = buffer.getUnsigned();
/*  48 */     if (this.passwordLength > 0) {
/*  49 */       byte[] passwordBytes = new byte[this.passwordLength];
/*  50 */       buffer.get(passwordBytes);
/*  51 */       this.password = passwordBytes;
/*     */     }
/*  53 */     byte[] mccBytes = new byte[this.mccLength];
/*  54 */     buffer.get(mccBytes);
/*  55 */     this.mcc = new String(mccBytes);
/*     */ 
/*  57 */     this.mncLength = buffer.getUnsigned();
/*  58 */     if (this.mncLength > 0) {
/*  59 */       byte[] mncBytes = new byte[this.mncLength];
/*  60 */       buffer.get(mncBytes);
/*  61 */       this.mnc = new String(mncBytes);
/*     */     }
/*     */ 
/*  64 */     this.supportedNetwork = buffer.getUnsigned();
/*  65 */     this.supportedService = buffer.getUnsigned();
/*  66 */     buffer.flip();
/*     */   }
/*     */ 
/*     */   public short getUsernameLength() {
/*  70 */     return this.usernameLength;
/*     */   }
/*     */   public void setUsernameLength(short usernameLength) {
/*  73 */     this.usernameLength = usernameLength;
/*     */   }
/*     */   public String getUserName() {
/*  76 */     return this.userName;
/*     */   }
/*     */   public void setUserName(String userName) {
/*  79 */     this.userName = userName;
/*     */   }
/*     */   public short getPasswordLength() {
/*  82 */     return this.passwordLength;
/*     */   }
/*     */   public void setPasswordLength(short passwordLength) {
/*  85 */     this.passwordLength = passwordLength;
/*     */   }
/*     */   public byte[] getPassword() {
/*  88 */     return this.password;
/*     */   }
/*     */   public void setPassword(byte[] password) {
/*  91 */     this.password = password;
/*     */   }
/*     */   public String getMcc() {
/*  94 */     return this.mcc;
/*     */   }
/*     */   public void setMcc(String mcc) {
/*  97 */     this.mcc = mcc;
/*     */   }
/*     */   public short getMncLength() {
/* 100 */     return this.mncLength;
/*     */   }
/*     */   public void setMncLength(short mncLength) {
/* 103 */     this.mncLength = mncLength;
/*     */   }
/*     */   public String getMnc() {
/* 106 */     return this.mnc;
/*     */   }
/*     */   public void setMnc(String mnc) {
/* 109 */     this.mnc = mnc;
/*     */   }
/*     */   public short getSupportedNetwork() {
/* 112 */     return this.supportedNetwork;
/*     */   }
/*     */   public void setSupportedNetwork(short supportedNetwork) {
/* 115 */     this.supportedNetwork = supportedNetwork;
/*     */   }
/*     */ 
/*     */   public short getMccLength() {
/* 119 */     return this.mccLength;
/*     */   }
/*     */ 
/*     */   public void setMccLength(short mccLength) {
/* 123 */     this.mccLength = mccLength;
/*     */   }
/*     */ 
/*     */   public short getSupportedService() {
/* 127 */     return this.supportedService;
/*     */   }
/*     */ 
/*     */   public void setSupportedService(short supportedService) {
/* 131 */     this.supportedService = supportedService;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.ApplySimRequestDto
 * JD-Core Version:    0.6.1
 */