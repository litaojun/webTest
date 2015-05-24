/*    */ package com.simo.boss.aaa.handler.json;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.ReleaseSimNoticeDto;
/*    */ import com.simo.boss.aaa.request.dto.ReleaseSimRequestDto;
/*    */ import com.simo.boss.aaa.service.SimInfoFacade;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.domain.dto.agency.Response;
/*    */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("releaseSimNoticeHandler")
/*    */ public class ReleaseSimNoticeHandler extends BaseMessageHandler
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SimInfoFacade simInfoFacade;
/* 24 */   private Logger logger = LoggerFactory.getLogger(getClass());
/*    */ 
/*    */   public Response<List<String>> process(byte[] bytes)
/*    */   {
/* 28 */     this.logger.debug("ReleaseSimNoticeHandler processes start...");
/*    */ 
/* 30 */     Request request = parseJsonReq(bytes, ReleaseSimNoticeDto.class);
/*    */ 
/* 34 */     validReq(request);
/*    */ 
/* 36 */     ReleaseSimNoticeDto releaseSimNoticeDto = (ReleaseSimNoticeDto)request.getParams();
/* 37 */     List returnList = releaseSimNotice(releaseSimNoticeDto);
/* 38 */     this.logger.debug("ReleaseSimNoticeHandler processes end...");
/*    */ 
/* 40 */     return wrapResponse(returnList, request);
/*    */   }
/*    */ 
/*    */   private List<String> releaseSimNotice(ReleaseSimNoticeDto releaseSimNoticeDto)
/*    */   {
/* 45 */     String[] tokenes = releaseSimNoticeDto.getToken();
/* 46 */     List returnList = new ArrayList();
/* 47 */     if ((null != tokenes) && (tokenes.length > 0))
/*    */     {
/* 50 */       ReleaseSimRequestDto reqDto = new ReleaseSimRequestDto();
/* 51 */       for (String token : tokenes) {
/* 52 */         reqDto.setReason((short)releaseSimNoticeDto.getReleaseBy());
/* 53 */         reqDto.setToken(token);
/* 54 */         reqDto.setTokenLength((short)token.getBytes().length);
/*    */         try {
/* 56 */           this.simInfoFacade.releaseSim(reqDto, releaseSimNoticeDto.getReleaseBy());
/*    */         }
/*    */         catch (Exception e) {
/* 59 */           returnList.add(token);
/*    */         }
/*    */       }
/*    */     }
/* 63 */     return returnList;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.json.ReleaseSimNoticeHandler
 * JD-Core Version:    0.6.1
 */