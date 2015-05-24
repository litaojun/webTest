/*    */ package com.simo.boss.aaa.json.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*    */ import com.simo.boss.aaa.request.dto.AddTerminalDto;
/*    */ import com.simo.boss.common.codec.CommonJsonDto;
/*    */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.protocol.domain.Terminal;
/*    */ import com.simo.boss.util.JsonUtil;
/*    */ import java.util.HashMap;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.codehaus.jackson.type.TypeReference;
/*    */ 
/*    */ public class SynTerminalHandler extends CommonJsonHandler
/*    */ {
/* 36 */   private static final Logger log = Logger.getLogger(SynTerminalHandler.class);
/*    */ 
/*    */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*    */   {
/* 45 */     byte[] body = commonJsonDto.getBody();
/* 46 */     String jsonString = new String(body);
/* 47 */     Request request = (Request)JsonUtil.jsonStringToObject(new TypeReference()
/*    */     {
/*    */     }
/*    */     , jsonString);
/* 48 */     AddTerminalDto dto = (AddTerminalDto)request.getParams();
/* 49 */     if (!dto.validate()) {
/* 50 */       session.close(true);
/* 51 */       log.error("==AddTerminal error==");
/* 52 */       return;
/*    */     }
/* 54 */     TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/* 55 */     Terminal t = terminalDao.findTerminalBySn(dto.getTerminalSn());
/* 56 */     if (null == t) {
/* 57 */       t = new Terminal();
/* 58 */       t.setSn(dto.getTerminalSn());
/*    */     }
/* 60 */     t.setStatus(Integer.valueOf(dto.getStatus()));
/* 61 */     t.setCode1(dto.getCode1());
/*    */ 
/* 63 */     terminalDao.saveOrUpdate(t);
/*    */ 
/* 65 */     Response response = new Response();
/* 66 */     response.setId(request.getId());
/* 67 */     response.setErrorCode(0);
/* 68 */     response.setResult(new HashMap());
/* 69 */     response.setMethod("synTerminal");
/*    */ 
/* 71 */     CommonJsonDto responseJsonDto = new CommonJsonDto();
/* 72 */     responseJsonDto.setBody(JsonUtil.objectToJsonString(response).getBytes());
/* 73 */     responseJsonDto.setLength(JsonUtil.objectToJsonString(response).getBytes().length);
/*    */ 
/* 75 */     session.write(responseJsonDto);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.SynTerminalHandler
 * JD-Core Version:    0.6.1
 */