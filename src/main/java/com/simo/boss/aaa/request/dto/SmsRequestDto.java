/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ 
/*    */ public class SmsRequestDto
/*    */ {
/*    */   private short countryCodeLength;
/*    */   private String countryCode;
/*    */   private short phoneNumberLength;
/*    */   private String phoneNumber;
/*    */   private short smsType;
/*    */ 
/*    */   public SmsRequestDto()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SmsRequestDto(byte[] body)
/*    */   {
/* 33 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/*    */ 
/* 35 */     this.countryCodeLength = buffer.getUnsigned();
/* 36 */     byte[] coutryCodeBytes = new byte[this.countryCodeLength];
/* 37 */     buffer.get(coutryCodeBytes);
/* 38 */     this.countryCode = new String(coutryCodeBytes);
/*    */ 
/* 40 */     this.phoneNumberLength = buffer.getUnsigned();
/* 41 */     byte[] phoneNumberBytes = new byte[this.phoneNumberLength];
/* 42 */     buffer.get(phoneNumberBytes);
/* 43 */     this.phoneNumber = new String(phoneNumberBytes);
/*    */ 
/* 45 */     this.smsType = buffer.getUnsigned();
/*    */   }
/*    */ 
/*    */   public short getCountryCodeLength() {
/* 49 */     return this.countryCodeLength;
/*    */   }
/*    */   public void setCountryCodeLength(short countryCodeLength) {
/* 52 */     this.countryCodeLength = countryCodeLength;
/*    */   }
/*    */   public String getCountryCode() {
/* 55 */     return this.countryCode;
/*    */   }
/*    */   public void setCountryCode(String countryCode) {
/* 58 */     this.countryCode = countryCode;
/*    */   }
/*    */   public short getPhoneNumberLength() {
/* 61 */     return this.phoneNumberLength;
/*    */   }
/*    */   public void setPhoneNumberLength(short phoneNumberLength) {
/* 64 */     this.phoneNumberLength = phoneNumberLength;
/*    */   }
/*    */   public String getPhoneNumber() {
/* 67 */     return this.phoneNumber;
/*    */   }
/*    */   public void setPhoneNumber(String phoneNumber) {
/* 70 */     this.phoneNumber = phoneNumber;
/*    */   }
/*    */   public short getSmsType() {
/* 73 */     return this.smsType;
/*    */   }
/*    */   public void setSmsType(short smsType) {
/* 76 */     this.smsType = smsType;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.SmsRequestDto
 * JD-Core Version:    0.6.1
 */