/*     */ package com.simo.boss.aaa.request.dto;
/*     */ 
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ 
/*     */ public class UserRegistRequestDto
/*     */ {
/*     */   private short countryCodeLength;
/*     */ 
/*     */   @NotNull(message="The country code can not be empty!")
/*     */   private String countryCode;
/*     */   private short phoneLength;
/*     */ 
/*     */   @NotNull(message="The phone number can not be empty!")
/*     */   private String phone;
/*     */ 
/*     */   @NotNull(message="The password can not be empty!")
/*     */   private String password;
/*     */   private short phonePasscodeLength;
/*     */ 
/*     */   @NotNull(message="The verification code can not be empty!")
/*     */   private String phonePasscode;
/*     */   private short nameLength;
/*     */   private String name;
/*     */   private short emailLength;
/*     */   private String email;
/*     */ 
/*     */   public UserRegistRequestDto()
/*     */   {
/*     */   }
/*     */ 
/*     */   public UserRegistRequestDto(byte[] body)
/*     */   {
/*  43 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/*     */ 
/*  45 */     this.countryCodeLength = buffer.getUnsigned();
/*  46 */     byte[] countryCodes = new byte[this.countryCodeLength];
/*  47 */     buffer.get(countryCodes);
/*  48 */     this.countryCode = new String(countryCodes);
/*     */ 
/*  50 */     this.phoneLength = buffer.getUnsigned();
/*  51 */     byte[] phoneNumbers = new byte[this.phoneLength];
/*  52 */     buffer.get(phoneNumbers);
/*  53 */     this.phone = new String(phoneNumbers);
/*     */ 
/*  55 */     byte[] passwords = new byte[16];
/*  56 */     buffer.get(passwords);
/*  57 */     this.password = new String(passwords);
/*     */ 
/*  59 */     this.phonePasscodeLength = buffer.getUnsigned();
/*  60 */     byte[] phonePasscodes = new byte[this.phonePasscodeLength];
/*  61 */     buffer.get(phonePasscodes);
/*  62 */     this.phonePasscode = new String(phonePasscodes);
/*     */ 
/*  64 */     this.nameLength = buffer.getUnsigned();
/*  65 */     byte[] names = new byte[this.nameLength];
/*  66 */     buffer.get(names);
/*  67 */     this.name = new String(names);
/*     */ 
/*  69 */     this.emailLength = buffer.getUnsigned();
/*  70 */     byte[] emails = new byte[this.emailLength];
/*  71 */     buffer.get(emails);
/*  72 */     this.email = new String(emails);
/*     */   }
/*     */ 
/*     */   public short getCountryCodeLength() {
/*  76 */     return this.countryCodeLength;
/*     */   }
/*     */ 
/*     */   public void setCountryCodeLength(short countryCodeLength) {
/*  80 */     this.countryCodeLength = countryCodeLength;
/*     */   }
/*     */ 
/*     */   public String getCountryCode() {
/*  84 */     return this.countryCode;
/*     */   }
/*     */ 
/*     */   public void setCountryCode(String countryCode) {
/*  88 */     this.countryCode = countryCode;
/*     */   }
/*     */ 
/*     */   public String getPassword() {
/*  92 */     return this.password;
/*     */   }
/*     */ 
/*     */   public short getPhoneLength() {
/*  96 */     return this.phoneLength;
/*     */   }
/*     */ 
/*     */   public void setPhoneLength(short phoneLength) {
/* 100 */     this.phoneLength = phoneLength;
/*     */   }
/*     */ 
/*     */   public String getPhone() {
/* 104 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone) {
/* 108 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/* 112 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public short getPhonePasscodeLength() {
/* 116 */     return this.phonePasscodeLength;
/*     */   }
/*     */ 
/*     */   public void setPhonePasscodeLength(short phonePasscodeLength) {
/* 120 */     this.phonePasscodeLength = phonePasscodeLength;
/*     */   }
/*     */ 
/*     */   public String getPhonePasscode() {
/* 124 */     return this.phonePasscode;
/*     */   }
/*     */ 
/*     */   public void setPhonePasscode(String phonePasscode) {
/* 128 */     this.phonePasscode = phonePasscode;
/*     */   }
/*     */ 
/*     */   public short getNameLength() {
/* 132 */     return this.nameLength;
/*     */   }
/*     */ 
/*     */   public void setNameLength(short nameLength) {
/* 136 */     this.nameLength = nameLength;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 140 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 144 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public short getEmailLength() {
/* 148 */     return this.emailLength;
/*     */   }
/*     */ 
/*     */   public void setEmailLength(short emailLength) {
/* 152 */     this.emailLength = emailLength;
/*     */   }
/*     */ 
/*     */   public String getEmail() {
/* 156 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/* 160 */     this.email = email;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.UserRegistRequestDto
 * JD-Core Version:    0.6.1
 */