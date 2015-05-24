/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.ValidTokenRequestDto;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.exception.BusinessException;
/*    */ import com.simo.boss.protocol.dao.TokenDao;
/*    */ import com.simo.boss.protocol.domain.Token;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import java.util.HashMap;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("validTokenHandler")
/*    */ public class ValidTokenHandler extends BaseMessageHandler
/*    */ {
/* 22 */   private Logger log = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   @Autowired
/*    */   private TokenDao tokenDao;
/*    */ 
/*    */   public Response<HashMap<String, Object>> process(byte[] bytes)
/*    */   {
/* 30 */     Request request = parseJsonReq(bytes, ValidTokenRequestDto.class);
/*    */ 
/* 34 */     validReq(request);
/*    */ 
/* 36 */     ValidTokenRequestDto tokenDto = (ValidTokenRequestDto)request.getParams();
/*    */ 
/* 38 */     if (tokenDto.validRequest()) {
/* 39 */       int simPackageId = tokenDto.getSimPackageId();
/* 40 */       int userPackageId = tokenDto.getUserPackageId();
/* 41 */       long userId = tokenDto.getUserId();
/* 42 */       long terminalId = tokenDto.getTerminalId();
/* 43 */       String value = tokenDto.getToken();
/* 44 */       int queryForm = tokenDto.getQueryFrom();
/*    */ 
/* 46 */       Token t = this.tokenDao.findByToken(value);
/* 47 */       if (null == t) {
/* 48 */         throw new BusinessException(67108866, "token不存在");
/*    */       }
/*    */ 
/* 52 */       if (queryForm == 0) {
/* 53 */         if ((t.getUserId().longValue() != userId) || (t.getSimPackageId().longValue() != simPackageId) || (t.getUserPackageId().longValue() != userPackageId) || (t.getTerminalId().longValue() != terminalId))
/*    */         {
/* 57 */           this.log.debug("queryForm==0  from collection failed!!");
/* 58 */           throw new BusinessException(67108866, "token与请求数据不匹配");
/*    */         }
/*    */ 
/* 62 */         this.log.debug("queryForm==0  from collection successed!!");
/*    */       }
/* 64 */       else if (queryForm == 1) {
/* 65 */         if (t.getStatus().intValue() != 0) {
/* 66 */           throw new BusinessException(67108866, "token=>>" + value + " is nullity!!!");
/*    */         }
/*    */ 
/* 70 */         this.log.debug("queryForm==1  from apdu successed!!");
/*    */       }
/*    */       else {
/* 73 */         throw new BusinessException(67108867, "queryForm==" + queryForm + " is error!!!");
/*    */       }
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 79 */       throw new BusinessException(83886082, "request data is error!!!");
/*    */     }
/*    */ 
/* 82 */     return wrapResponse(new HashMap(), request);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.ValidTokenHandler
 * JD-Core Version:    0.6.1
 */