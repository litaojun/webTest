/*    */ package com.simo.boss.aaa.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Random;
/*    */ import org.apache.commons.codec.binary.Base64;
/*    */ 
/*    */ public class AaaUtil
/*    */ {
/*    */   public static String getToken()
/*    */   {
/* 24 */     Random r = new Random();
/* 25 */     byte[] dataArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
/* 26 */     r.nextBytes(dataArray);
/*    */ 
/* 28 */     return new String(Base64.encodeBase64(dataArray));
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 32 */     System.out.println(getToken());
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.util.AaaUtil
 * JD-Core Version:    0.6.1
 */