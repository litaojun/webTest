/*    */ package com.simo.boss.protocol.domain;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tb_terminal")
/*    */ public class Terminal
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   @Id
/*    */   @Column(name="id")
/*    */   private Long id;
/*    */ 
/*    */   @Column(name="sn", unique=true)
/*    */   private String sn;
/*    */ 
/*    */   @Column(name="code1")
/*    */   private String code1;
/*    */ 
/*    */   @Column(name="status")
/*    */   private Integer status;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 44 */     return this.id;
/*    */   }
/*    */   public void setId(Long id) {
/* 47 */     this.id = id;
/*    */   }
/*    */   public String getSn() {
/* 50 */     return this.sn;
/*    */   }
/*    */   public void setSn(String sn) {
/* 53 */     this.sn = sn;
/*    */   }
/*    */   public String getCode1() {
/* 56 */     return this.code1;
/*    */   }
/*    */   public void setCode1(String code1) {
/* 59 */     this.code1 = code1;
/*    */   }
/*    */   public Integer getStatus() {
/* 62 */     return this.status;
/*    */   }
/*    */   public void setStatus(Integer status) {
/* 65 */     this.status = status;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.domain.Terminal
 * JD-Core Version:    0.6.1
 */