/*    */ package com.simo.boss.aaa.util;
/*    */ 
/*    */ public final class ConversionUtil
/*    */ {
/*    */   public static short byteToShort(byte value)
/*    */   {
/* 20 */     short s = (short)(value & 0xFFFF);
/* 21 */     return s;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.util.ConversionUtil
 * JD-Core Version:    0.6.1
 */