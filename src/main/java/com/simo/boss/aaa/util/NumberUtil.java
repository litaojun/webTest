/*     */ package com.simo.boss.aaa.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ public final class NumberUtil
/*     */ {
/*     */   private static final int DEF_DIV_SCALE = 10;
/*     */   private static final long unitKB = 1024L;
/*     */   private static final long unitMB = 1048576L;
/*     */   private static final long unitGB = 1073741824L;
/*     */ 
/*     */   public static String getRoundString(double numerical, int length)
/*     */   {
/*  32 */     DecimalFormat decimalFormat = null;
/*  33 */     if (length == 4)
/*  34 */       decimalFormat = new DecimalFormat("##,###,###.####");
/*  35 */     else if (length == 3)
/*  36 */       decimalFormat = new DecimalFormat("##,###,###.###");
/*  37 */     else if (length == 2)
/*  38 */       decimalFormat = new DecimalFormat("##,###,###.##");
/*  39 */     else if (length == 1)
/*  40 */       decimalFormat = new DecimalFormat("##,###,###.#");
/*  41 */     else if (length == 0) {
/*  42 */       decimalFormat = new DecimalFormat("##,###,###");
/*     */     }
/*  44 */     return decimalFormat.format(numerical).toString();
/*     */   }
/*     */ 
/*     */   public static double div(double v1, double v2)
/*     */   {
/*  57 */     return div(v1, v2, 10);
/*     */   }
/*     */ 
/*     */   public static double div(double v1, double v2, int scale)
/*     */   {
/*  72 */     if (scale < 0) {
/*  73 */       scale = 0;
/*     */     }
/*  75 */     if (v2 == 0.0D) {
/*  76 */       return 0.0D;
/*     */     }
/*  78 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  79 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  80 */     return b1.divide(b2, scale, 4).doubleValue();
/*     */   }
/*     */ 
/*     */   public static double add(double v1, double v2)
/*     */   {
/*  94 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/*  95 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/*  96 */     return b1.add(b2).doubleValue();
/*     */   }
/*     */ 
/*     */   public static double sub(double v1, double v2)
/*     */   {
/* 109 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/* 110 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/* 111 */     return b1.subtract(b2).doubleValue();
/*     */   }
/*     */ 
/*     */   public static double mul(double v1, double v2)
/*     */   {
/* 124 */     BigDecimal b1 = new BigDecimal(Double.toString(v1));
/* 125 */     BigDecimal b2 = new BigDecimal(Double.toString(v2));
/* 126 */     return b1.multiply(b2).doubleValue();
/*     */   }
/*     */ 
/*     */   public static String formatByPattern(double d, String pattern)
/*     */   {
/* 136 */     DecimalFormat df = new DecimalFormat(pattern);
/* 137 */     return df.format(d);
/*     */   }
/*     */ 
/*     */   public static String formatNumber(int number, int destLength, char paddedChar)
/*     */   {
/* 153 */     String oldString = String.valueOf(number);
/* 154 */     StringBuffer newString = new StringBuffer("");
/* 155 */     int oldLength = oldString.length();
/* 156 */     if (oldLength > destLength) {
/* 157 */       newString.append(oldString.substring(oldLength - destLength));
/* 158 */     } else if (oldLength == destLength) {
/* 159 */       newString.append(oldString);
/*     */     } else {
/* 161 */       for (int i = 0; i < destLength - oldLength; i++) {
/* 162 */         newString.append(paddedChar);
/*     */       }
/* 164 */       newString.append(oldString);
/*     */     }
/* 166 */     return newString.toString();
/*     */   }
/*     */ 
/*     */   public static double turnByte2Gb(long byt) {
/* 170 */     double db = round(byt / 1073741824L);
/* 171 */     return db;
/*     */   }
/*     */ 
/*     */   public static double turnGb2Byte(double gb) {
/* 175 */     double db = gb * 1073741824.0D;
/* 176 */     return round(db);
/*     */   }
/*     */ 
/*     */   public static double turnByte2Mb(long byt) {
/* 180 */     double db = round(byt / 1048576L);
/* 181 */     return db;
/*     */   }
/*     */ 
/*     */   public static double turnMb2Byte(double mb) {
/* 185 */     mb = round(mb * 1048576.0D);
/* 186 */     return mb;
/*     */   }
/*     */ 
/*     */   public static double turnByte2Mb(double byt) {
/* 190 */     double db = round(byt / 1048576.0D);
/* 191 */     return db;
/*     */   }
/*     */ 
/*     */   public static double turnByte2Kb(long kb) {
/* 195 */     double db = round(kb / 1024L);
/* 196 */     return db;
/*     */   }
/*     */ 
/*     */   public static double turnKb2Byte(double kb) {
/* 200 */     kb = round(kb * 1024.0D);
/* 201 */     return kb;
/*     */   }
/*     */ 
/*     */   public static double round(double old)
/*     */   {
/* 208 */     DecimalFormat df2 = new DecimalFormat("####");
/* 209 */     String str = df2.format(old);
/* 210 */     old = Double.parseDouble(str);
/* 211 */     return old;
/*     */   }
/*     */ 
/*     */   public static double roundNew(double old) {
/* 215 */     DecimalFormat df2 = new DecimalFormat("####.00");
/* 216 */     String str = df2.format(old);
/* 217 */     old = Double.parseDouble(str);
/* 218 */     return old;
/*     */   }
/*     */ 
/*     */   public static String toHexString(int number)
/*     */   {
/* 228 */     String str = "0x";
/* 229 */     String kk = Integer.toHexString(number);
/* 230 */     int len = 8 - kk.length();
/* 231 */     for (int i = 0; i < len; i++) {
/* 232 */       str = str + 0;
/*     */     }
/* 234 */     return (str + kk).toUpperCase();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 238 */     System.out.println(toHexString(154));
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.util.NumberUtil
 * JD-Core Version:    0.6.1
 */