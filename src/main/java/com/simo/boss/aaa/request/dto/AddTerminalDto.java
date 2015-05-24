/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ public class AddTerminalDto
/*    */ {
/*    */   private long id;
/*    */   private String terminalSn;
/*    */   private String code1;
/*    */   private int status;
/*    */ 
/*    */   public long getId()
/*    */   {
/* 26 */     return this.id;
/*    */   }
/*    */   public void setId(long id) {
/* 29 */     this.id = id;
/*    */   }
/*    */   public String getTerminalSn() {
/* 32 */     return this.terminalSn;
/*    */   }
/*    */   public void setTerminalSn(String terminalSn) {
/* 35 */     this.terminalSn = terminalSn;
/*    */   }
/*    */   public String getCode1() {
/* 38 */     return this.code1;
/*    */   }
/*    */   public void setCode1(String code1) {
/* 41 */     this.code1 = code1;
/*    */   }
/*    */   public int getStatus() {
/* 44 */     return this.status;
/*    */   }
/*    */   public void setStatus(int status) {
/* 47 */     this.status = status;
/*    */   }
/*    */ 
/*    */   public boolean validate() {
/* 51 */     if ((null == this.terminalSn) || (null == this.code1))
/* 52 */       return false;
/* 53 */     return true;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.AddTerminalDto
 * JD-Core Version:    0.6.1
 */