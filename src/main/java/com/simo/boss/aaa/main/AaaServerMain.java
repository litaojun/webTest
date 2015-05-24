/*    */ package com.simo.boss.aaa.main;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ConfigurableApplicationContext;
/*    */ import org.springframework.context.support.ClassPathXmlApplicationContext;
/*    */ 
/*    */ public class AaaServerMain
/*    */ {
/* 24 */   private static Logger log = LoggerFactory.getLogger(AaaServerMain.class);
/*    */   private static ConfigurableApplicationContext context;
/*    */ 
/*    */   private static ConfigurableApplicationContext getContext()
/*    */   {
/* 28 */     if (context == null) {
/* 29 */       context = new ClassPathXmlApplicationContext(new String[] { "spring-mina-json.xml", "spring-mina-common.xml" });
/*    */     }
/* 31 */     return context;
/*    */   }
/*    */ 
/*    */   public static Object getBean(String name) {
/* 35 */     return context.getBean(name);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 39 */     log.debug("===AaaServerMain===正在启动服务======");
/* 40 */     getContext();
/* 41 */     log.debug("===AaaServerMain===服务启动成功======");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.main.AaaServerMain
 * JD-Core Version:    0.6.1
 */