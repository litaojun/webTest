/*    */ package com.simo.boss.aaa.main;
/*    */ 
/*    */ import com.simo.boss.aaa.json.handler.DelTerminalHandler;
/*    */ import com.simo.boss.aaa.json.handler.QueryChargeHandler;
/*    */ import com.simo.boss.aaa.json.handler.QueryKeyIndex;
/*    */ import com.simo.boss.aaa.json.handler.QueryRoamCountryHandler;
/*    */ import com.simo.boss.aaa.json.handler.QueryUserBillHander;
/*    */ import com.simo.boss.aaa.json.handler.QueryUserInfoHandler;
/*    */ import com.simo.boss.aaa.json.handler.QueryUserItemHandler;
/*    */ import com.simo.boss.aaa.json.handler.ReleaseSimNoticeHandler;
/*    */ import com.simo.boss.aaa.json.handler.SynTerminalHandler;
/*    */ import com.simo.boss.aaa.json.handler.TerminalUserInfoCollectionHandler;
/*    */ import com.simo.boss.aaa.json.handler.UpdateUserInfoHandler;
/*    */ import com.simo.boss.aaa.json.handler.UserLoginHandler;
/*    */ import com.simo.boss.aaa.json.handler.UserRegistHandler;
/*    */ import com.simo.boss.aaa.json.handler.ValidToken;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public abstract class CommonJsonHandler
/*    */ {
/*    */   public static CommonJsonHandler getInstance(String method)
/*    */   {
/* 44 */     CommonJsonHandler handler = null;
/* 45 */     if ("register".equals(method))
/* 46 */       handler = new UserRegistHandler();
/* 47 */     else if ("login".equals(method))
/* 48 */       handler = new UserLoginHandler();
/* 49 */     else if ("findUserBill".equals(method))
/* 50 */       handler = new QueryUserBillHander();
/* 51 */     else if ("findRoamCountry".equals(method))
/* 52 */       handler = new QueryRoamCountryHandler();
/* 53 */     else if ("findChargeByUserItemIdAndCountry".equals(method))
/* 54 */       handler = new QueryChargeHandler();
/* 55 */     else if ("updateUser".equals(method))
/* 56 */       handler = new UpdateUserInfoHandler();
/* 57 */     else if ("showUserItem".equals(method))
/* 58 */       handler = new QueryUserItemHandler();
/* 59 */     else if ("showUser".equals(method))
/* 60 */       handler = new QueryUserInfoHandler();
/* 61 */     else if ("queryKeyIndex".equals(method))
/* 62 */       handler = new QueryKeyIndex();
/* 63 */     else if ("validToken".equals(method))
/* 64 */       handler = new ValidToken();
/* 65 */     else if ("releaseSimNotice".equals(method))
/* 66 */       handler = new ReleaseSimNoticeHandler();
/* 67 */     else if ("synTerminal".equals(method))
/* 68 */       handler = new SynTerminalHandler();
/* 69 */     else if ("delTerminal".equals(method))
/* 70 */       handler = new DelTerminalHandler();
/* 71 */     else if ("collectGmateInfo".equals(method)) {
/* 72 */       handler = new TerminalUserInfoCollectionHandler();
/*    */     }
/*    */ 
/* 75 */     return handler;
/*    */   }
/*    */ 
/*    */   public abstract void process(IoSession paramIoSession, CommonJsonDto paramCommonJsonDto);
/*    */ 
/*    */   public void exec(IoSession session, CommonJsonDto commonJsonDto) {
/* 81 */     process(session, commonJsonDto);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.main.CommonJsonHandler
 * JD-Core Version:    0.6.1
 */