/*    */ package com.simo.boss.protocol.domain;
/*    */ 
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tb_encrypt")
/*    */ public class Encrypt
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @Column(name="id")
/*    */   private Long keyIndex;
/*    */ 
/*    */   @Column(name="key_encrypt")
/*    */   private byte[] keyEncrypt;
/*    */ 
/*    */   @Column(name="create_time")
/*    */   private Date createTime;
/*    */ 
/*    */   @Column(name="status")
/*    */   private Integer status;
/*    */ 
/*    */   public Long getKeyIndex()
/*    */   {
/* 39 */     return this.keyIndex;
/*    */   }
/*    */   public void setKeyIndex(Long keyIndex) {
/* 42 */     this.keyIndex = keyIndex;
/*    */   }
/*    */   public byte[] getKeyEncrypt() {
/* 45 */     return this.keyEncrypt;
/*    */   }
/*    */   public void setKeyEncrypt(byte[] keyEncrypt) {
/* 48 */     this.keyEncrypt = keyEncrypt;
/*    */   }
/*    */   public Date getCreateTime() {
/* 51 */     return this.createTime;
/*    */   }
/*    */   public void setCreateTime(Date createTime) {
/* 54 */     this.createTime = createTime;
/*    */   }
/*    */   public Integer getStatus() {
/* 57 */     return this.status;
/*    */   }
/*    */   public void setStatus(Integer status) {
/* 60 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.domain.Encrypt
 * JD-Core Version:    0.6.1
 */