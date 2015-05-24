/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.AddTerminalDto;
/*    */ import com.simo.boss.aaa.service.TerminalFacade;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.enumerate.Constant.ParameterInvalid;
/*    */ import com.simo.boss.exception.BusinessException;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
import com.simo.boss.util.Constant;

/*    */ import java.util.HashMap;

/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("synTerminalHandler")
/*    */ public class SynTerminalHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TerminalFacade terminalFacade;
/* 24 */   private Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   public Response<HashMap<String, String>> process(byte[] bytes)
/*    */   {
/* 29 */     Request request = parseJsonReq(bytes, AddTerminalDto.class);
/*    */ 
/* 34 */     AddTerminalDto reqDto = (AddTerminalDto)request.getParams();
/* 35 */     if (!reqDto.validate()) {
/* 36 */       this.logger.debug("syn.terminal: params is invalidate!");
/* 37 */       throw new BusinessException(Constant.ParameterInvalid.invalidParams.getValue(), "both sn and code1 can't be empty!");
/*    */     }
/*    */ 
/* 42 */     this.terminalFacade.addTerminal(reqDto);
/*    */ 
/* 44 */     return wrapResponse(new HashMap(), request);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.SynTerminalHandler
 * JD-Core Version:    0.6.1
 */