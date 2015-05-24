/*    */ package com.simo.boss.aaa.main;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ConfigurableApplicationContext;
/*    */ import org.springframework.context.support.ClassPathXmlApplicationContext;
/*    */ 
/*    */ public class AaaCommonServerMain
/*    */ {
/* 25 */   private static Logger log = LoggerFactory.getLogger(AaaCommonServerMain.class);
/*    */   private static ConfigurableApplicationContext context;
/*    */ 
/*    */   private static ConfigurableApplicationContext getContext()
/*    */   {
/* 29 */     if (context == null) {
/* 30 */       context = new ClassPathXmlApplicationContext("spring-mina-common.xml");
/*    */     }
/* 32 */     return context;
/*    */   }
/*    */ 
/*    */   public static Object getBean(String name) {
/* 36 */     return context.getBean(name);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 40 */     log.debug("===AaaCommonServerMain===正在启动服务======");
/* 41 */     getContext();
/* 42 */     log.debug("===AaaCommonServerMain===服务启动成功======");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.main.AaaCommonServerMain
 * JD-Core Version:    0.6.1
 */