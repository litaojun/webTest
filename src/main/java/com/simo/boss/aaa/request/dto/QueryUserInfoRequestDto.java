/*    */ package com.simo.boss.aaa.request.dto;
/*    */ 
/*    */ import com.simo.boss.util.IoBufferUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.mina.core.buffer.IoBuffer;
/*    */ 
/*    */ public class QueryUserInfoRequestDto
/*    */ {
/*    */   private short tokenLength;
/*    */   private String token;
/*    */   private short langLength;
/*    */   private String lang;
/*    */   private short itemCount;
/*    */   private List<Short> itemArray;
/*    */ 
/*    */   public QueryUserInfoRequestDto(byte[] body)
/*    */   {
/* 20 */     IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
/* 21 */     this.tokenLength = buffer.getUnsigned();
/* 22 */     if (this.tokenLength > 0) {
/* 23 */       byte[] tokenBytes = new byte[this.tokenLength];
/* 24 */       buffer.get(tokenBytes);
/* 25 */       this.token = new String(tokenBytes);
/*    */     }
/*    */ 
/* 28 */     this.langLength = buffer.getUnsigned();
/* 29 */     if (this.langLength > 0) {
/* 30 */       byte[] langBytes = new byte[this.langLength];
/* 31 */       buffer.get(langBytes);
/* 32 */       this.lang = new String(langBytes);
/*    */     }
/* 34 */     this.itemCount = buffer.getUnsigned();
/* 35 */     if (this.itemCount > 0) {
/* 36 */       this.itemArray = new ArrayList();
/* 37 */       for (int i = 0; i < this.itemCount; i++) {
/* 38 */         this.itemArray.add(Short.valueOf(buffer.getUnsigned()));
/*    */       }
/*    */     }
/* 41 */     buffer.flip();
/*    */   }
/*    */ 
/*    */   public short getTokenLength() {
/* 45 */     return this.tokenLength;
/*    */   }
/*    */ 
/*    */   public void setTokenLength(short tokenLength) {
/* 49 */     this.tokenLength = tokenLength;
/*    */   }
/*    */ 
/*    */   public String getToken() {
/* 53 */     return this.token;
/*    */   }
/*    */ 
/*    */   public void setToken(String token) {
/* 57 */     this.token = token;
/*    */   }
/*    */ 
/*    */   public short getLangLength() {
/* 61 */     return this.langLength;
/*    */   }
/*    */ 
/*    */   public void setLangLength(short langLength) {
/* 65 */     this.langLength = langLength;
/*    */   }
/*    */ 
/*    */   public String getLang() {
/* 69 */     return this.lang;
/*    */   }
/*    */ 
/*    */   public void setLang(String lang) {
/* 73 */     this.lang = lang;
/*    */   }
/*    */ 
/*    */   public short getItemCount() {
/* 77 */     return this.itemCount;
/*    */   }
/*    */ 
/*    */   public void setItemCount(short itemCount) {
/* 81 */     this.itemCount = itemCount;
/*    */   }
/*    */ 
/*    */   public List<Short> getItemArray() {
/* 85 */     return this.itemArray;
/*    */   }
/*    */ 
/*    */   public void setItemArray(List<Short> itemArray) {
/* 89 */     this.itemArray = itemArray;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.request.dto.QueryUserInfoRequestDto
 * JD-Core Version:    0.6.1
 */