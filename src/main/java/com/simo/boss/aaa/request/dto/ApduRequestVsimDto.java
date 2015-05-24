/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ public class ApduRequestVsimDto
/*    */ {
/*    */   private long simbankAddress;
/*    */   private long bladeLogicalAddress;
/*    */   private long simAddress;
/*    */   private short simType;
/*    */   private String apdu;
/*    */ 
/*    */   public long getSimbankAddress()
/*    */   {
/* 27 */     return this.simbankAddress;
/*    */   }
/*    */   public void setSimbankAddress(long simbankAddress) {
/* 30 */     this.simbankAddress = simbankAddress;
/*    */   }
/*    */   public long getBladeLogicalAddress() {
/* 33 */     return this.bladeLogicalAddress;
/*    */   }
/*    */   public void setBladeLogicalAddress(long bladeLogicalAddress) {
/* 36 */     this.bladeLogicalAddress = bladeLogicalAddress;
/*    */   }
/*    */   public long getSimAddress() {
/* 39 */     return this.simAddress;
/*    */   }
/*    */   public void setSimAddress(long simAddress) {
/* 42 */     this.simAddress = simAddress;
/*    */   }
/*    */   public short getSimType() {
/* 45 */     return this.simType;
/*    */   }
/*    */   public void setSimType(short simType) {
/* 48 */     this.simType = simType;
/*    */   }
/*    */   public String getApdu() {
/* 51 */     return this.apdu;
/*    */   }
/*    */   public void setApdu(String apdu) {
/* 54 */     this.apdu = apdu;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.ApduRequestVsimDto
 * JD-Core Version:    0.6.1
 */