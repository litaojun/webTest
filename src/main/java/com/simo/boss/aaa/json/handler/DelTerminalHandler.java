/*    */ package com.simo.boss.aaa.json.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.codehaus.jackson.type.TypeReference;
/*    */ 
/*    */ public class DelTerminalHandler extends CommonJsonHandler
/*    */ {
/*    */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*    */   {
/* 41 */     byte[] body = commonJsonDto.getBody();
/* 42 */     String jsonString = new String(body);
/* 43 */     Request request = (Request)JsonUtil.jsonStringToObject(new TypeReference()
/*    */     {
/*    */     }
/*    */     , jsonString);
/* 44 */     String sn = (String)((Map)request.getParams()).get("terminalSn");
/*    */ 
/* 46 */     TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/* 47 */     terminalDao.delete(sn);
/*    */ 
/* 49 */     Response response = new Response();
/* 50 */     response.setId(request.getId());
/* 51 */     response.setErrorCode(0);
/* 52 */     response.setResult(new HashMap());
/* 53 */     response.setMethod("delTerminal");
/*    */ 
/* 55 */     CommonJsonDto responseJsonDto = new CommonJsonDto();
/* 56 */     responseJsonDto.setBody(JsonUtil.objectToJsonString(response).getBytes());
/* 57 */     responseJsonDto.setLength(JsonUtil.objectToJsonString(response).getBytes().length);
/*    */ 
/* 59 */     session.write(responseJsonDto);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.DelTerminalHandler
 * JD-Core Version:    0.6.1
 */