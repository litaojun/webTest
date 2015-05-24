/*    */ package com.simo.boss.aaa.main;
/*    */ 
/*    */ import com.simo.boss.aaa.simo.handler.ApplySimHandler;
/*    */ import com.simo.boss.aaa.simo.handler.QueryUserItemHandler;
/*    */ import com.simo.boss.aaa.simo.handler.ReleaseSimHandler;
/*    */ import com.simo.boss.protocol.CommonDto;
/*    */ import com.simo.boss.protocol.handler.ICommonHandler;
/*    */ import com.simo.boss.protocol.handler.TerminalIdentificationHandler;
/*    */ import com.simo.boss.protocol.handler.ValidateRandomHandler;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class CommonHandler
/*    */ {
/* 29 */   private ICommonHandler handler = null;
/*    */ 
/*    */   public CommonHandler(int orderCode) {
/* 32 */     switch (orderCode) {
/*    */     case 1:
/* 34 */       this.handler = new TerminalIdentificationHandler();
/* 35 */       break;
/*    */     case 3:
/* 37 */       this.handler = new ValidateRandomHandler();
/* 38 */       break;
/*    */     case 5:
/* 40 */       this.handler = new ApplySimHandler();
/* 41 */       break;
/*    */     case 7:
/* 43 */       this.handler = new ReleaseSimHandler();
/* 44 */       break;
/*    */     case 13:
/* 46 */       this.handler = new QueryUserItemHandler();
/* 47 */       break;
/*    */     case 2:
/*    */     case 4:
/*    */     case 6:
/*    */     case 8:
/*    */     case 9:
/*    */     case 10:
/*    */     case 11:
/*    */     case 12:
/*    */     default:
/* 49 */       this.handler = null;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void exec(IoSession session, CommonDto commonDto)
/*    */   {
/* 55 */     this.handler.process(session, commonDto);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.main.CommonHandler
 * JD-Core Version:    0.6.1
 */