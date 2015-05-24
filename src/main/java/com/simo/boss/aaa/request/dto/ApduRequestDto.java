/*     */ package com.simo.boss.aaa.request.dto;
/*     */ 
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ 
/*     */ public class ApduRequestDto
/*     */ {
/*     */   private short tokenLength;
/*     */   private String token;
/*     */   private long vsimAddress;
/*     */   private long simbankAddress;
/*     */   private long bladeLogicalAddress;
/*     */   private long simAddress;
/*     */   private short simType;
/*     */   private long apduLength;
/*     */   private String apdu;
/*     */ 
/*     */   public ApduRequestDto()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ApduRequestDto(byte[] body)
/*     */   {
/*  36 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/*  37 */     this.tokenLength = buffer.getUnsigned();
/*  38 */     if (this.tokenLength > 0) {
/*  39 */       byte[] tokenBytes = new byte[this.tokenLength];
/*  40 */       buffer.get(tokenBytes);
/*  41 */       this.token = new String(tokenBytes);
/*     */     }
/*  43 */     this.vsimAddress = buffer.getUnsignedInt();
/*  44 */     this.simbankAddress = buffer.getUnsignedInt();
/*  45 */     this.bladeLogicalAddress = buffer.getUnsignedInt();
/*  46 */     this.simAddress = buffer.getUnsignedInt();
/*  47 */     this.simType = buffer.getUnsigned();
/*     */ 
/*  49 */     this.apduLength = buffer.getUnsignedInt();
/*  50 */     if (this.apduLength > 0L) {
/*  51 */       byte[] apduBytes = new byte[(int)this.apduLength];
/*  52 */       buffer.get(apduBytes);
/*  53 */       this.apdu = new String(apduBytes);
/*     */     }
/*  55 */     buffer.flip();
/*     */   }
/*     */ 
/*     */   public short getTokenLength() {
/*  59 */     return this.tokenLength;
/*     */   }
/*     */ 
/*     */   public void setTokenLength(short tokenLength) {
/*  63 */     this.tokenLength = tokenLength;
/*     */   }
/*     */ 
/*     */   public String getToken() {
/*  67 */     return this.token;
/*     */   }
/*     */ 
/*     */   public void setToken(String token) {
/*  71 */     this.token = token;
/*     */   }
/*     */ 
/*     */   public long getVsimAddress() {
/*  75 */     return this.vsimAddress;
/*     */   }
/*     */ 
/*     */   public void setVsimAddress(long vsimAddress) {
/*  79 */     this.vsimAddress = vsimAddress;
/*     */   }
/*     */ 
/*     */   public long getSimbankAddress() {
/*  83 */     return this.simbankAddress;
/*     */   }
/*     */ 
/*     */   public void setSimbankAddress(long simbankAddress) {
/*  87 */     this.simbankAddress = simbankAddress;
/*     */   }
/*     */ 
/*     */   public long getBladeLogicalAddress() {
/*  91 */     return this.bladeLogicalAddress;
/*     */   }
/*     */ 
/*     */   public void setBladeLogicalAddress(long bladeLogicalAddress) {
/*  95 */     this.bladeLogicalAddress = bladeLogicalAddress;
/*     */   }
/*     */ 
/*     */   public long getSimAddress() {
/*  99 */     return this.simAddress;
/*     */   }
/*     */ 
/*     */   public void setSimAddress(long simAddress) {
/* 103 */     this.simAddress = simAddress;
/*     */   }
/*     */ 
/*     */   public short getSimType() {
/* 107 */     return this.simType;
/*     */   }
/*     */ 
/*     */   public void setSimType(short simType) {
/* 111 */     this.simType = simType;
/*     */   }
/*     */ 
/*     */   public long getApduLength() {
/* 115 */     return this.apduLength;
/*     */   }
/*     */ 
/*     */   public void setApduLength(long apduLength) {
/* 119 */     this.apduLength = apduLength;
/*     */   }
/*     */ 
/*     */   public String getApdu() {
/* 123 */     return this.apdu;
/*     */   }
/*     */ 
/*     */   public void setApdu(String apdu) {
/* 127 */     this.apdu = apdu;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.ApduRequestDto
 * JD-Core Version:    0.6.1
 */