/*    */ package com.simo.boss.aaa.callback;
/*    */ 
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public abstract class CommonCallBack
/*    */ {
/*    */   private IoSession session;
/*    */ 
/*    */   public abstract void doCallBack(Object paramObject);
/*    */ 
/*    */   public IoSession getSession()
/*    */   {
/* 27 */     return this.session;
/*    */   }
/*    */ 
/*    */   public void setSession(IoSession session) {
/* 31 */     this.session = session;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.callback.CommonCallBack
 * JD-Core Version:    0.6.1
 */