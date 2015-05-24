/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.aaa.service.TerminalFacade;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("delTerminalHandler")
/*    */ public class DelTerminalHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TerminalFacade terminalFacade;
/*    */ 
/*    */   public Response<String> process(byte[] bytes)
/*    */   {
/* 31 */     Request request = parseJsonReq(bytes, Map.class);
/*    */ 
/* 36 */     String sn = (String)((Map)request.getParams()).get("terminalSn");
/*    */ 
/* 38 */     this.terminalFacade.deleteTerminalBySN(sn);
/*    */ 
/* 40 */     return wrapResponse(null, request);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.DelTerminalHandler
 * JD-Core Version:    0.6.1
 */