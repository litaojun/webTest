/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ public class ValidTokenRequestDto
/*    */ {
/*    */   private String token;
/*    */   private long userId;
/*    */   private long terminalId;
/*    */   private int userPackageId;
/*    */   private int simPackageId;
/*    */   private int queryFrom;
/*    */ 
/*    */   public boolean validRequest()
/*    */   {
/* 29 */     if (this.queryFrom == 0) {
/* 30 */       if ((this.userId == 0L) || (this.terminalId <= 0L) || (this.userPackageId == 0) || (this.simPackageId == 0)) {
/* 31 */         return false;
/*    */       }
/* 33 */       return true;
/*    */     }
/*    */ 
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   public String getToken()
/*    */   {
/* 47 */     return this.token;
/*    */   }
/*    */ 
/*    */   public void setToken(String token) {
/* 51 */     this.token = token;
/*    */   }
/*    */ 
/*    */   public long getUserId() {
/* 55 */     return this.userId;
/*    */   }
/*    */ 
/*    */   public void setUserId(long userId) {
/* 59 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   public long getTerminalId()
/*    */   {
/* 64 */     return this.terminalId;
/*    */   }
/*    */ 
/*    */   public void setTerminalId(long terminalId) {
/* 68 */     this.terminalId = terminalId;
/*    */   }
/*    */ 
/*    */   public int getUserPackageId() {
/* 72 */     return this.userPackageId;
/*    */   }
/*    */ 
/*    */   public void setUserPackageId(int userPackageId) {
/* 76 */     this.userPackageId = userPackageId;
/*    */   }
/*    */ 
/*    */   public int getSimPackageId() {
/* 80 */     return this.simPackageId;
/*    */   }
/*    */ 
/*    */   public void setSimPackageId(int simPackageId) {
/* 84 */     this.simPackageId = simPackageId;
/*    */   }
/*    */ 
/*    */   public int getQueryFrom() {
/* 88 */     return this.queryFrom;
/*    */   }
/*    */ 
/*    */   public void setQueryFrom(int queryFrom) {
/* 92 */     this.queryFrom = queryFrom;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.ValidTokenRequestDto
 * JD-Core Version:    0.6.1
 */