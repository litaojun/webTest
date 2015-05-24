/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class HeartBeatRequestDto
/*    */ {
/*    */   private String token;
/*    */   private Date applyTime;
/*    */   private Integer heartBeatInterval;
/*    */   private Integer delayTime;
/*    */ 
/*    */   public Integer getDelayTime()
/*    */   {
/* 28 */     return this.delayTime;
/*    */   }
/*    */   public void setDelayTime(Integer delayTime) {
/* 31 */     this.delayTime = delayTime;
/*    */   }
/*    */   public String getToken() {
/* 34 */     return this.token;
/*    */   }
/*    */   public void setToken(String token) {
/* 37 */     this.token = token;
/*    */   }
/*    */   public Date getApplyTime() {
/* 40 */     return this.applyTime;
/*    */   }
/*    */   public void setApplyTime(Date applyTime) {
/* 43 */     this.applyTime = applyTime;
/*    */   }
/*    */   public Integer getHeartBeatInterval() {
/* 46 */     return this.heartBeatInterval;
/*    */   }
/*    */   public void setHeartBeatInterval(Integer heartBeatInterval) {
/* 49 */     this.heartBeatInterval = heartBeatInterval;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.HeartBeatRequestDto
 * JD-Core Version:    0.6.1
 */