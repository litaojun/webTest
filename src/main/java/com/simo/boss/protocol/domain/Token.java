/*     */ package com.simo.boss.protocol.domain;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tb_token")
/*     */ public class Token
/*     */ {
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.AUTO)
/*     */   private Long id;
/*     */ 
/*     */   @Column(name="value")
/*     */   private String value;
/*     */ 
/*     */   @Column(name="user_id")
/*     */   private Long userId;
/*     */ 
/*     */   @Column(name="user_package_id")
/*     */   private Long userPackageId;
/*     */ 
/*     */   @Column(name="sim_id")
/*     */   private String simId;
/*     */ 
/*     */   @Column(name="sim_package_id")
/*     */   private Long simPackageId;
/*     */ 
/*     */   @Column(name="terminal_id")
/*     */   private Long terminalId;
/*     */ 
/*     */   @Column(name="status")
/*     */   private Integer status;
/*     */ 
/*     */   @Column(name="create_time")
/*     */   private Date createTime;
/*     */ 
/*     */   @Column(name="nullity_time")
/*     */   private Date nullityTime;
/*     */ 
/*     */   @Column(name="sn")
/*     */   private String sn;
/*     */ 
/*     */   @Column(name="release_by")
/*     */   private Integer releaseBy;
/*     */ 
/*     */   public Long getId()
/*     */   {
/*  58 */     return this.id;
/*     */   }
/*     */   public void setId(Long id) {
/*  61 */     this.id = id;
/*     */   }
/*     */   public String getValue() {
/*  64 */     return this.value;
/*     */   }
/*     */   public void setValue(String value) {
/*  67 */     this.value = value;
/*     */   }
/*     */   public Long getUserId() {
/*  70 */     return this.userId;
/*     */   }
/*     */   public void setUserId(Long userId) {
/*  73 */     this.userId = userId;
/*     */   }
/*     */   public Long getUserPackageId() {
/*  76 */     return this.userPackageId;
/*     */   }
/*     */   public void setUserPackageId(Long userPackageId) {
/*  79 */     this.userPackageId = userPackageId;
/*     */   }
/*     */   public String getSimId() {
/*  82 */     return this.simId;
/*     */   }
/*     */   public void setSimId(String simId) {
/*  85 */     this.simId = simId;
/*     */   }
/*     */   public Long getSimPackageId() {
/*  88 */     return this.simPackageId;
/*     */   }
/*     */   public void setSimPackageId(Long simPackageId) {
/*  91 */     this.simPackageId = simPackageId;
/*     */   }
/*     */   public Long getTerminalId() {
/*  94 */     return this.terminalId;
/*     */   }
/*     */   public void setTerminalId(Long terminalId) {
/*  97 */     this.terminalId = terminalId;
/*     */   }
/*     */   public Integer getStatus() {
/* 100 */     return this.status;
/*     */   }
/*     */   public void setStatus(Integer status) {
/* 103 */     this.status = status;
/*     */   }
/*     */   public Date getCreateTime() {
/* 106 */     return this.createTime;
/*     */   }
/*     */   public void setCreateTime(Date createTime) {
/* 109 */     this.createTime = createTime;
/*     */   }
/*     */   public Date getNullityTime() {
/* 112 */     return this.nullityTime;
/*     */   }
/*     */   public void setNullityTime(Date nullityTime) {
/* 115 */     this.nullityTime = nullityTime;
/*     */   }
/*     */   public String getSn() {
/* 118 */     return this.sn;
/*     */   }
/*     */   public void setSn(String sn) {
/* 121 */     this.sn = sn;
/*     */   }
/*     */   public Integer getReleaseBy() {
/* 124 */     return this.releaseBy;
/*     */   }
/*     */   public void setReleaseBy(Integer releaseBy) {
/* 127 */     this.releaseBy = releaseBy;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.domain.Token
 * JD-Core Version:    0.6.1
 */