/*    */ package com.simo.boss.aaa.startup;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ConfigurableApplicationContext;
/*    */ import org.springframework.context.support.ClassPathXmlApplicationContext;
/*    */ 
/*    */ public class AaaServerMain
/*    */ {
/* 23 */   private static Logger log = LoggerFactory.getLogger(AaaServerMain.class);
/*    */   private static ConfigurableApplicationContext context;
/* 26 */   private static String TERMINAL_SERVER = "t";
/*    */ 
/* 28 */   private static String JSON_SERVER = "j";
/*    */ 
/*    */   private static ConfigurableApplicationContext getContext(String configFileName)
/*    */   {
/* 32 */     if (context == null) {
/* 33 */       context = new ClassPathXmlApplicationContext(configFileName);
/*    */     }
/* 35 */     return context;
/*    */   }
/*    */ 
/*    */   public static Object getBean(String name) {
/* 39 */     return context.getBean(name);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 44 */     String configFileName = "appContext-all.xml";
/* 45 */     if ((args != null) && (args.length > 0)) {
/* 46 */       if (args.length > 1) {
/* 47 */         log.warn("args are more than one,system will ingore the other!");
/*    */       }
/* 49 */       if (TERMINAL_SERVER.equals(args[0]))
/* 50 */         configFileName = "appContext-terminal.xml";
/* 51 */       else if (JSON_SERVER.equals(args[0])) {
/* 52 */         configFileName = "appContext-json.xml";
/*    */       }
/*    */     }
/*    */ 
/* 56 */     log.info("*************** AAA Server[load configuration from " + configFileName + "] is strating...");
/*    */ 
/* 58 */     getContext(configFileName);
/* 59 */     log.info("*************** AAA Server starts up successfully!***************");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.startup.AaaServerMain
 * JD-Core Version:    0.6.1
 */