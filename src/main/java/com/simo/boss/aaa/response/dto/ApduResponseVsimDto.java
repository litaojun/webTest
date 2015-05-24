/*    */ package com.simo.boss.aaa.response.dto;
/*    */ 
/*    */ public class ApduResponseVsimDto
/*    */ {
/*    */   private long simbankAddress;
/*    */   private long bladeLogicalAddress;
/*    */   private long simAddress;
/*    */   private String apdu;
/*    */ 
/*    */   public long getSimbankAddress()
/*    */   {
/* 26 */     return this.simbankAddress;
/*    */   }
/*    */   public void setSimbankAddress(long simbankAddress) {
/* 29 */     this.simbankAddress = simbankAddress;
/*    */   }
/*    */   public long getBladeLogicalAddress() {
/* 32 */     return this.bladeLogicalAddress;
/*    */   }
/*    */   public void setBladeLogicalAddress(long bladeLogicalAddress) {
/* 35 */     this.bladeLogicalAddress = bladeLogicalAddress;
/*    */   }
/*    */   public long getSimAddress() {
/* 38 */     return this.simAddress;
/*    */   }
/*    */   public void setSimAddress(long simAddress) {
/* 41 */     this.simAddress = simAddress;
/*    */   }
/*    */   public String getApdu() {
/* 44 */     return this.apdu;
/*    */   }
/*    */   public void setApdu(String apdu) {
/* 47 */     this.apdu = apdu;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.response.dto.ApduResponseVsimDto
 * JD-Core Version:    0.6.1
 */