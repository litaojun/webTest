/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.aaa.service.TerminalAuthorityFacade;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.codec.binary.Base64;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("queryKeyIndexHandler")
/*    */ public class QueryKeyIndexHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TerminalAuthorityFacade terminalAuthorityFacade;
/*    */ 
/*    */   public Response<Map<String, String>> process(byte[] bytes)
/*    */   {
/* 25 */     Request request = parseJsonReq(bytes, Map.class);
/*    */ 
/* 30 */     long keyIndex = Long.parseLong(((Map)request.getParams()).get("keyIndex").toString());
/*    */ 
/* 33 */     byte[] key = this.terminalAuthorityFacade.decrypt(keyIndex);
/*    */ 
/* 35 */     Map map = new HashMap();
/* 36 */     map.put("keyValue", Base64.encodeBase64String(key));
/*    */ 
/* 38 */     return wrapResponse(map, request);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.QueryKeyIndexHandler
 * JD-Core Version:    0.6.1
 */