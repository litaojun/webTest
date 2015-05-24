/*     */ package com.simo.boss.aaa.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class DateUtil
/*     */ {
/*  27 */   private static Logger log = Logger.getLogger(DateUtil.class);
/*     */ 
/*  32 */   private static final String[] FORMATS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "HH:mm", "HH:mm:ss", "yyyy-MM" };
/*     */ 
/*     */   public static Date convert(String str) {
/*  35 */     if ((str != null) && (str.trim().length() > 0)) {
/*  36 */       String s = str.trim();
/*  37 */       if ((s.length() > 10) && (s.charAt(10) == 'T')) {
/*  38 */         s = str.replace('T', ' ');
/*     */       }
/*  40 */       for (String format : FORMATS) {
/*  41 */         if (s.length() == format.length()) {
/*     */           try {
/*  43 */             log.debug(new StringBuilder().append("convert ").append(str).append(" to date format ").append(format).toString());
/*  44 */             Date date = new SimpleDateFormat(format).parse(str);
/*  45 */             log.debug(new StringBuilder().append("****").append(date).append("****").toString());
/*  46 */             return date;
/*     */           } catch (ParseException e) {
/*  48 */             log.warn(e.getMessage());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date getFirstDayOfWeek(Date date)
/*     */   {
/*  65 */     if (date == null) {
/*  66 */       return null;
/*     */     }
/*  68 */     Calendar weekStart = Calendar.getInstance();
/*  69 */     weekStart.setTime(date);
/*  70 */     weekStart.set(7, 2);
/*  71 */     return weekStart.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getNow()
/*     */   {
/*  80 */     return Calendar.getInstance().getTime();
/*     */   }
/*     */ 
/*     */   public static Date getSmallDate(Date date1, Date date2)
/*     */   {
/*  91 */     return date1.compareTo(date2) < 0 ? date1 : date2;
/*     */   }
/*     */ 
/*     */   public static Date getBigDate(Date date1, Date date2)
/*     */   {
/* 102 */     return date1.compareTo(date2) > 0 ? date1 : date2;
/*     */   }
/*     */ 
/*     */   public static Date addMonth2Date(int monthAmount, Date date)
/*     */   {
/* 115 */     Date newDate = null;
/* 116 */     if (date != null) {
/* 117 */       Calendar calendar = Calendar.getInstance();
/* 118 */       calendar.setTime(date);
/* 119 */       calendar.add(2, monthAmount);
/* 120 */       newDate = calendar.getTime();
/*     */     }
/* 122 */     return newDate;
/*     */   }
/*     */ 
/*     */   public static Date addDay2Date(int dayAmount, Date date)
/*     */   {
/* 135 */     Date newDate = null;
/* 136 */     if (date != null) {
/* 137 */       Calendar calendar = Calendar.getInstance();
/* 138 */       calendar.setTime(date);
/* 139 */       calendar.add(5, dayAmount);
/* 140 */       newDate = calendar.getTime();
/*     */     }
/* 142 */     return newDate;
/*     */   }
/*     */ 
/*     */   public static Date addHour2Date(int hourAmount, Date date)
/*     */   {
/* 155 */     Date newDate = null;
/* 156 */     if (date != null) {
/* 157 */       Calendar calendar = Calendar.getInstance();
/* 158 */       calendar.setTime(date);
/* 159 */       calendar.add(11, hourAmount);
/* 160 */       newDate = calendar.getTime();
/*     */     }
/* 162 */     return newDate;
/*     */   }
/*     */ 
/*     */   public static Date addMinute2Date(int minuteAmount, Date date)
/*     */   {
/* 175 */     Date newDate = null;
/* 176 */     if (date != null) {
/* 177 */       Calendar calendar = Calendar.getInstance();
/* 178 */       calendar.setTime(date);
/* 179 */       calendar.add(12, minuteAmount);
/* 180 */       newDate = calendar.getTime();
/*     */     }
/* 182 */     return newDate;
/*     */   }
/*     */ 
/*     */   public static String formatDate2NN(Date date)
/*     */   {
/* 193 */     StringBuffer dateBuffer = new StringBuffer("");
/* 194 */     if (date != null) {
/* 195 */       Calendar calendar = Calendar.getInstance();
/* 196 */       calendar.setTime(date);
/* 197 */       dateBuffer.append(new StringBuilder().append(calendar.get(1)).append("-").toString());
/* 198 */       int month = calendar.get(2) + 1;
/* 199 */       if (month < 10)
/* 200 */         dateBuffer.append(new StringBuilder().append("0").append(month).append("-").toString());
/*     */       else {
/* 202 */         dateBuffer.append(new StringBuilder().append(month).append("-").toString());
/*     */       }
/* 204 */       int day = calendar.get(5);
/* 205 */       if (day < 10)
/* 206 */         dateBuffer.append(new StringBuilder().append("0").append(day).append(" ").toString());
/*     */       else {
/* 208 */         dateBuffer.append(new StringBuilder().append(day).append(" ").toString());
/*     */       }
/*     */     }
/* 211 */     return dateBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String formatDateTime2NN(Date date)
/*     */   {
/* 222 */     StringBuffer dateBuffer = new StringBuffer("");
/* 223 */     if (date != null) {
/* 224 */       Calendar calendar = Calendar.getInstance();
/* 225 */       calendar.setTime(date);
/* 226 */       dateBuffer.append(new StringBuilder().append(calendar.get(1)).append("-").toString());
/* 227 */       int month = calendar.get(2) + 1;
/* 228 */       if (month < 10)
/* 229 */         dateBuffer.append(new StringBuilder().append("0").append(month).append("-").toString());
/*     */       else {
/* 231 */         dateBuffer.append(new StringBuilder().append(month).append("-").toString());
/*     */       }
/* 233 */       int day = calendar.get(5);
/* 234 */       if (day < 10)
/* 235 */         dateBuffer.append(new StringBuilder().append("0").append(day).append(" ").toString());
/*     */       else {
/* 237 */         dateBuffer.append(new StringBuilder().append(day).append(" ").toString());
/*     */       }
/* 239 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(11), 2, '0')).append(":").toString());
/* 240 */       dateBuffer.append(NumberUtil.formatNumber(calendar.get(12), 2, '0'));
/*     */     }
/* 242 */     return dateBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String formatFullDateTime2NN(Date date)
/*     */   {
/* 253 */     StringBuffer dateBuffer = new StringBuffer("");
/* 254 */     if (date != null) {
/* 255 */       Calendar calendar = Calendar.getInstance();
/* 256 */       calendar.setTime(date);
/* 257 */       dateBuffer.append(new StringBuilder().append(calendar.get(1)).append("-").toString());
/* 258 */       int month = calendar.get(2) + 1;
/* 259 */       if (month < 10)
/* 260 */         dateBuffer.append(new StringBuilder().append("0").append(month).append("-").toString());
/*     */       else {
/* 262 */         dateBuffer.append(new StringBuilder().append(month).append("-").toString());
/*     */       }
/* 264 */       int day = calendar.get(5);
/* 265 */       if (day < 10)
/* 266 */         dateBuffer.append(new StringBuilder().append("0").append(day).append(" ").toString());
/*     */       else {
/* 268 */         dateBuffer.append(new StringBuilder().append(day).append(" ").toString());
/*     */       }
/* 270 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(11), 2, '0')).append(":").toString());
/* 271 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(12), 2, '0')).append(":").toString());
/* 272 */       dateBuffer.append(NumberUtil.formatNumber(calendar.get(13), 2, '0'));
/*     */     }
/* 274 */     return dateBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String formatDate2NC(Date date)
/*     */   {
/* 285 */     StringBuffer dateBuffer = new StringBuffer("");
/* 286 */     if (date != null) {
/* 287 */       Calendar calendar = Calendar.getInstance();
/* 288 */       calendar.setTime(date);
/* 289 */       dateBuffer.append(new StringBuilder().append(calendar.get(1)).append("年").toString());
/* 290 */       dateBuffer.append(new StringBuilder().append(calendar.get(2) + 1).append("月").toString());
/* 291 */       dateBuffer.append(new StringBuilder().append(calendar.get(5)).append("日").toString());
/*     */     }
/* 293 */     return dateBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String formatDateTime2NC(Date date)
/*     */   {
/* 304 */     StringBuffer dateBuffer = new StringBuffer("");
/* 305 */     if (date != null) {
/* 306 */       Calendar calendar = Calendar.getInstance();
/* 307 */       calendar.setTime(date);
/* 308 */       dateBuffer.append(new StringBuilder().append(calendar.get(1)).append("年").toString());
/* 309 */       dateBuffer.append(new StringBuilder().append(calendar.get(2) + 1).append("月").toString());
/* 310 */       dateBuffer.append(new StringBuilder().append(calendar.get(5)).append("日 ").toString());
/* 311 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(11), 2, '0')).append("时").toString());
/* 312 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(12), 2, '0')).append("分").toString());
/*     */     }
/* 314 */     return dateBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String formatFullDateTime2NC(Date date)
/*     */   {
/* 325 */     StringBuffer dateBuffer = new StringBuffer("");
/* 326 */     if (date != null) {
/* 327 */       Calendar calendar = Calendar.getInstance();
/* 328 */       calendar.setTime(date);
/* 329 */       dateBuffer.append(new StringBuilder().append(calendar.get(1)).append("年").toString());
/* 330 */       dateBuffer.append(new StringBuilder().append(calendar.get(2) + 1).append("月").toString());
/* 331 */       dateBuffer.append(new StringBuilder().append(calendar.get(5)).append("日 ").toString());
/* 332 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(11), 2, '0')).append("时").toString());
/* 333 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(12), 2, '0')).append("分").toString());
/* 334 */       dateBuffer.append(new StringBuilder().append(NumberUtil.formatNumber(calendar.get(13), 2, '0')).append("秒").toString());
/*     */     }
/* 336 */     return dateBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static Date getDate(String dateStr)
/*     */   {
/* 347 */     Date date = null;
/*     */     try {
/* 349 */       if (dateStr != null) {
/* 350 */         String separator = dateStr.indexOf(47) > 0 ? "/" : "-";
/* 351 */         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(new StringBuilder().append("yyyy").append(separator).append("MM").append(separator).append("dd").toString());
/* 352 */         date = simpleDateFormat.parse(dateStr);
/*     */       }
/*     */     } catch (Exception e) {
/* 355 */       System.out.println(e.getMessage());
/*     */     }
/* 357 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date getDateTime(String dateTimeStr)
/*     */   {
/* 368 */     Date date = null;
/*     */     try {
/* 370 */       String separator = dateTimeStr.indexOf(47) > 0 ? "/" : "-";
/* 371 */       if (dateTimeStr.length() > 10) {
/* 372 */         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(new StringBuilder().append("yyyy").append(separator).append("MM").append(separator).append("dd HH:mm:ss").toString());
/* 373 */         date = simpleDateFormat.parse(dateTimeStr);
/*     */       } else {
/* 375 */         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(new StringBuilder().append("yyyy").append(separator).append("MM").append(separator).append("dd").toString());
/* 376 */         date = simpleDateFormat.parse(dateTimeStr);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 380 */       System.out.println(e.getMessage());
/*     */     }
/* 382 */     return date;
/*     */   }
/*     */ 
/*     */   public static int getYear(Date date)
/*     */   {
/* 393 */     Calendar c = Calendar.getInstance();
/* 394 */     c.setTime(date);
/* 395 */     return c.get(1);
/*     */   }
/*     */ 
/*     */   public static int getMonth(Date date)
/*     */   {
/* 406 */     Calendar c = Calendar.getInstance();
/* 407 */     c.setTime(date);
/* 408 */     return c.get(2) + 1;
/*     */   }
/*     */ 
/*     */   public static int getday(Date date)
/*     */   {
/* 419 */     Calendar c = Calendar.getInstance();
/* 420 */     c.setTime(date);
/* 421 */     return c.get(5);
/*     */   }
/*     */ 
/*     */   public static int getDiffMonth(Date fromDate, Date toDate)
/*     */   {
/* 432 */     Calendar c = Calendar.getInstance();
/* 433 */     c.setTime(fromDate);
/* 434 */     int fromYear = c.get(1);
/* 435 */     int fromMonth = c.get(2) + 1;
/* 436 */     c.setTime(toDate);
/* 437 */     int toYear = c.get(1);
/* 438 */     int toMonth = c.get(2) + 1;
/* 439 */     int monthCount = 0;
/*     */ 
/* 441 */     if (toYear == fromYear)
/* 442 */       monthCount = toMonth - fromMonth;
/* 443 */     else if (toYear - fromYear == 1)
/* 444 */       monthCount = 12 - fromMonth + toMonth;
/*     */     else {
/* 446 */       monthCount = 12 - fromMonth + 12 * (toYear - fromYear - 1) + toMonth;
/*     */     }
/* 448 */     return monthCount;
/*     */   }
/*     */ 
/*     */   public static int getDiffDays(Date fromDate, Date toDate)
/*     */   {
/* 459 */     return (int)((toDate.getTime() - fromDate.getTime()) / 86400000L);
/*     */   }
/*     */ 
/*     */   public static String getTimeStampId() {
/* 463 */     Calendar c = Calendar.getInstance();
/* 464 */     int iYear = c.get(1);
/* 465 */     int iMon = c.get(2) + 1;
/* 466 */     int iDay = c.get(5);
/* 467 */     int iHour = c.get(11);
/* 468 */     int iMin = c.get(12);
/* 469 */     int iSec = c.get(13);
/* 470 */     String s = String.valueOf(iYear);
/* 471 */     if (iMon < 10)
/* 472 */       s = new StringBuilder().append(s).append("0").toString();
/* 473 */     s = new StringBuilder().append(s).append(String.valueOf(iMon)).toString();
/* 474 */     if (iDay < 10)
/* 475 */       s = new StringBuilder().append(s).append("0").toString();
/* 476 */     s = new StringBuilder().append(s).append(String.valueOf(iDay)).toString();
/* 477 */     if (iHour < 10)
/* 478 */       s = new StringBuilder().append(s).append("0").toString();
/* 479 */     s = new StringBuilder().append(s).append(String.valueOf(iHour)).toString();
/* 480 */     if (iMin < 10)
/* 481 */       s = new StringBuilder().append(s).append("0").toString();
/* 482 */     s = new StringBuilder().append(s).append(String.valueOf(iMin)).toString();
/* 483 */     if (iSec < 10)
/* 484 */       s = new StringBuilder().append(s).append("0").toString();
/* 485 */     s = new StringBuilder().append(s).append(String.valueOf(iSec)).toString();
/* 486 */     return s;
/*     */   }
/*     */ 
/*     */   public static String getTimeStamp() {
/* 490 */     Calendar c = Calendar.getInstance();
/* 491 */     int iYear = c.get(1);
/* 492 */     int iMon = c.get(2) + 1;
/* 493 */     int iDay = c.get(5);
/* 494 */     int iHour = c.get(11);
/* 495 */     int iMin = c.get(12);
/* 496 */     int iSec = c.get(13);
/* 497 */     String s = String.valueOf(iYear);
/* 498 */     s = new StringBuilder().append(s).append("-").toString();
/* 499 */     if (iMon < 10)
/* 500 */       s = new StringBuilder().append(s).append("0").toString();
/* 501 */     s = new StringBuilder().append(s).append(String.valueOf(iMon)).toString();
/* 502 */     s = new StringBuilder().append(s).append("-").toString();
/* 503 */     if (iDay < 10)
/* 504 */       s = new StringBuilder().append(s).append("0").toString();
/* 505 */     s = new StringBuilder().append(s).append(String.valueOf(iDay)).toString();
/* 506 */     s = new StringBuilder().append(s).append(" ").toString();
/* 507 */     if (iHour < 10)
/* 508 */       s = new StringBuilder().append(s).append("0").toString();
/* 509 */     s = new StringBuilder().append(s).append(String.valueOf(iHour)).toString();
/* 510 */     s = new StringBuilder().append(s).append(":").toString();
/* 511 */     if (iMin < 10)
/* 512 */       s = new StringBuilder().append(s).append("0").toString();
/* 513 */     s = new StringBuilder().append(s).append(String.valueOf(iMin)).toString();
/* 514 */     s = new StringBuilder().append(s).append(":").toString();
/* 515 */     if (iSec < 10)
/* 516 */       s = new StringBuilder().append(s).append("0").toString();
/* 517 */     s = new StringBuilder().append(s).append(String.valueOf(iSec)).toString();
/* 518 */     return s;
/*     */   }
/*     */ 
/*     */   public static int getDayOfWeek(Date date) {
/* 522 */     Calendar calendar = Calendar.getInstance();
/* 523 */     calendar.setTime(date);
/* 524 */     int dayOfWeek = calendar.get(7);
/* 525 */     return dayOfWeek;
/*     */   }
/*     */ 
/*     */   public static int getDayOfMonth(Date date) {
/* 529 */     Calendar calendar = Calendar.getInstance();
/* 530 */     calendar.setTime(date);
/* 531 */     int dayOfMonth = calendar.get(5);
/* 532 */     return dayOfMonth;
/*     */   }
/*     */ 
/*     */   public static String getLongToDate(long time)
/*     */   {
/* 543 */     String sDate = "";
/* 544 */     Date date = new Date(time);
/* 545 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 546 */     sDate = simpleDateFormat.format(date);
/* 547 */     return sDate;
/*     */   }
/*     */ 
/*     */   public static Date switchStringToDate(String sDate, String format)
/*     */   {
/* 558 */     Date date = null;
/*     */     try {
/* 560 */       SimpleDateFormat df = new SimpleDateFormat(format);
/* 561 */       date = df.parse(sDate);
/*     */     } catch (Exception e) {
/* 563 */       e.printStackTrace();
/*     */     }
/* 565 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date strToDate(String str) {
/* 569 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
/* 570 */     Date date = null;
/*     */     try {
/* 572 */       date = sdf.parse(str);
/*     */     }
/*     */     catch (ParseException e) {
/* 575 */       e.printStackTrace();
/*     */     }
/* 577 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date switchStringToDate4Log(String sDate)
/*     */   {
/* 589 */     Date date = switchStringToDate(sDate, "yyyy-MM-dd");
/* 590 */     return date;
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate4Log()
/*     */   {
/* 599 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
/* 600 */     Date date = new Date();
/* 601 */     String time = simpleDateFormat.format(date);
/* 602 */     return time;
/*     */   }
/*     */ 
/*     */   public static Calendar switchStringToCalendar4Log(String sDate)
/*     */   {
/* 613 */     Date date = switchStringToDate4Log(sDate);
/* 614 */     Calendar c = Calendar.getInstance();
/* 615 */     c.setTime(date);
/* 616 */     return c;
/*     */   }
/*     */ 
/*     */   public static String getNDayBeforeOneDate4Log(String sDate, int n)
/*     */   {
/* 629 */     Calendar c = switchStringToCalendar4Log(sDate);
/* 630 */     if (n != 0) {
/* 631 */       c.add(5, -n);
/*     */     }
/* 633 */     int year = c.get(1);
/* 634 */     int month = c.get(2) + 1;
/* 635 */     int day = c.get(5);
/* 636 */     return new StringBuilder().append(year > 999 ? "" : "0").append(year).append("-").append(month > 9 ? "" : "0").append(month).append("-").append(day > 9 ? "" : "0").append(day).toString();
/*     */   }
/*     */ 
/*     */   public static Date getNDayAfterNow(int n)
/*     */   {
/* 646 */     Calendar c = Calendar.getInstance();
/* 647 */     int date = c.get(5) + n;
/* 648 */     int year = c.get(1);
/* 649 */     int month = c.get(2);
/* 650 */     c.set(year, month, date, 0, 0, 0);
/* 651 */     Date day = c.getTime();
/*     */ 
/* 653 */     return day;
/*     */   }
/*     */ 
/*     */   public static String switchDate2String(String pattern, Date date)
/*     */   {
/* 665 */     SimpleDateFormat format = null;
/*     */     try {
/* 667 */       format = new SimpleDateFormat(pattern);
/*     */     } catch (Exception e) {
/* 669 */       format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     }
/* 671 */     return format.format(date);
/*     */   }
/*     */ 
/*     */   public static Calendar switchStringToCalendar(String sDate)
/*     */   {
/* 682 */     Date date = switchStringToDate(sDate);
/* 683 */     Calendar c = Calendar.getInstance();
/* 684 */     c.setTime(date);
/* 685 */     return c;
/*     */   }
/*     */ 
/*     */   public static Date switchStringToDate(String sDate)
/*     */   {
/* 696 */     Date date = switchStringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
/* 697 */     return date;
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate()
/*     */   {
/* 706 */     Calendar rightNow = Calendar.getInstance();
/* 707 */     int year = rightNow.get(1);
/* 708 */     int month = rightNow.get(2) + 1;
/* 709 */     int day = rightNow.get(5);
/* 710 */     return new StringBuilder().append(year > 999 ? "" : "0").append(year).append("-").append(month > 9 ? "" : "0").append(month).append("-").append(day > 9 ? "" : "0").append(day).toString();
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate1()
/*     */   {
/* 719 */     Calendar rightNow = Calendar.getInstance();
/* 720 */     int year = rightNow.get(1);
/* 721 */     int month = rightNow.get(2) + 1;
/* 722 */     int day = rightNow.get(5);
/* 723 */     return new StringBuilder().append(year > 999 ? "" : "0").append(year).append("年").append(month > 9 ? "" : "0").append(month).append("月").append(day > 9 ? "" : "0").append(day).append("日").toString();
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate2()
/*     */   {
/* 732 */     Calendar rightNow = Calendar.getInstance();
/* 733 */     int year = rightNow.get(1);
/* 734 */     int month = rightNow.get(2) + 1;
/* 735 */     int day = rightNow.get(5);
/* 736 */     return new StringBuilder().append(year > 999 ? "" : "0").append(year).append("").append(month > 9 ? "" : "0").append(month).append("").append(day > 9 ? "" : "0").append(day).toString();
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate3()
/*     */   {
/* 745 */     Calendar rightNow = Calendar.getInstance();
/* 746 */     int year = rightNow.get(1);
/* 747 */     int month = rightNow.get(2) + 1;
/* 748 */     return new StringBuilder().append(year > 999 ? "" : "0").append(year).append("-").append(month > 9 ? "" : "0").append(month).toString();
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate4()
/*     */   {
/* 757 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
/* 758 */     Date date = new Date();
/* 759 */     String time = simpleDateFormat.format(date);
/* 760 */     return time;
/*     */   }
/*     */ 
/*     */   public static String getCurrentDate5()
/*     */   {
/* 769 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 770 */     Date date = new Date();
/* 771 */     String time = simpleDateFormat.format(date);
/* 772 */     return time;
/*     */   }
/*     */ 
/*     */   public static String getYearFromDate(String sDate)
/*     */   {
/* 783 */     Calendar c = switchStringToCalendar(sDate);
/* 784 */     int year = c.get(1);
/* 785 */     return new StringBuilder().append(year > 999 ? "" : "0").append(year).toString();
/*     */   }
/*     */ 
/*     */   public static String getMonthFromDate(String sDate)
/*     */   {
/* 796 */     Calendar c = switchStringToCalendar(sDate);
/* 797 */     int month = c.get(2) + 1;
/* 798 */     return new StringBuilder().append("").append(month < 10 ? new StringBuilder().append("0").append(month).toString() : new StringBuilder().append("").append(month).toString()).toString();
/*     */   }
/*     */ 
/*     */   public static String getDayFromDate(String sDate)
/*     */   {
/* 809 */     Calendar c = switchStringToCalendar(sDate);
/* 810 */     int day = c.get(5);
/* 811 */     return new StringBuilder().append("").append(day < 10 ? new StringBuilder().append("0").append(day).toString() : new StringBuilder().append("").append(day).toString()).toString();
/*     */   }
/*     */ 
/*     */   public static String getTimeFromDate(String sDate)
/*     */   {
/* 822 */     Calendar c = switchStringToCalendar(sDate);
/* 823 */     int hour = c.get(11);
/* 824 */     int min = c.get(12);
/* 825 */     int sec = c.get(13);
/* 826 */     return new StringBuilder().append(hour < 10 ? new StringBuilder().append("0").append(hour).toString() : new StringBuilder().append("").append(hour).toString()).append(min < 10 ? new StringBuilder().append("0").append(min).toString() : new StringBuilder().append("").append(min).toString()).append(sec < 10 ? new StringBuilder().append("0").append(sec).toString() : new StringBuilder().append("").append(sec).toString()).toString();
/*     */   }
/*     */ 
/*     */   public static String formatDuring(long mss)
/*     */   {
/* 837 */     long days = mss / 86400000L;
/* 838 */     long hours = mss % 86400000L / 3600000L;
/* 839 */     long minutes = mss % 3600000L / 60000L;
/* 840 */     long seconds = mss % 60000L / 1000L;
/* 841 */     return new StringBuilder().append(days).append(" days ").append(hours).append(" hours ").append(minutes).append(" minutes ").append(seconds).append(" seconds ").toString();
/*     */   }
/*     */ 
/*     */   public static String formatDuring(Date begin, Date end)
/*     */   {
/* 855 */     return formatDuring(end.getTime() - begin.getTime());
/*     */   }
/*     */ 
/*     */   public static String formatToGMT(Date time, String timeZone)
/*     */   {
/* 869 */     DateFormat df = DateFormat.getDateTimeInstance();
/* 870 */     TimeZone zone = TimeZone.getTimeZone(new StringBuilder().append("GMT").append(timeZone).toString());
/* 871 */     df.setTimeZone(zone);
/* 872 */     String gmtTime = df.format(time);
/* 873 */     return gmtTime;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 877 */     System.out.println(getYear(new Date()));
/* 878 */     System.out.println(getMonth(new Date()));
/* 879 */     System.out.println(getday(new Date()));
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.util.DateUtil
 * JD-Core Version:    0.6.1
 */