/*     */ package com.simo.boss.aaa.json.handler;
/*     */ 
/*     */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*     */ import com.simo.boss.aaa.request.dto.ValidTokenRequestDto;
/*     */ import com.simo.boss.common.codec.CommonJsonDto;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.domain.dto.agency.Request;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.protocol.dao.TokenDao;
/*     */ import com.simo.boss.protocol.domain.Token;
/*     */ import com.simo.boss.util.JsonUtil;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.codehaus.jackson.type.TypeReference;
/*     */ 
/*     */ public class ValidToken extends CommonJsonHandler
/*     */ {
/*  38 */   private static final Logger log = Logger.getLogger(ValidToken.class);
/*     */ 
/*     */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*     */   {
/*  46 */     byte[] body = commonJsonDto.getBody();
/*  47 */     String jsonString = new String(body);
/*     */ 
/*  49 */     Request request = (Request)JsonUtil.jsonStringToObject(new TypeReference()
/*     */     {
/*     */     }
/*     */     , jsonString);
/*     */ 
/*  51 */     ValidTokenRequestDto tokenDto = (ValidTokenRequestDto)request.getParams();
/*     */ 
/*  53 */     CommonJsonDto returnJsonDto = new CommonJsonDto();
/*  54 */     Response response = new Response();
/*  55 */     response.setMethod("validToken");
/*  56 */     response.setTime(new Date());
/*  57 */     response.setId(request.getId());
/*  58 */     int errorCode = 0;
/*  59 */     if (tokenDto.validRequest()) {
/*  60 */       TokenDao tokenDao = (TokenDao)SpringContextUtil.getBean("tokenDao");
/*  61 */       int simPackageId = tokenDto.getSimPackageId();
/*  62 */       int userPackageId = tokenDto.getUserPackageId();
/*  63 */       long userId = tokenDto.getUserId();
/*  64 */       long terminalId = tokenDto.getTerminalId();
/*  65 */       String value = tokenDto.getToken();
/*  66 */       int queryForm = tokenDto.getQueryFrom();
/*     */ 
/*  68 */       Token t = tokenDao.findByToken(value);
/*  69 */       if (null == t) {
/*  70 */         errorCode = 67108946;
/*  71 */         response.setErrorMsg("token不存在");
/*     */       }
/*  73 */       else if (queryForm == 0) {
/*  74 */         if ((t.getUserId().longValue() != userId) || (t.getSimPackageId().longValue() != simPackageId) || (t.getUserPackageId().longValue() != userPackageId) || (t.getTerminalId().longValue() != terminalId)) {
/*  75 */           errorCode = 67108946;
/*  76 */           response.setErrorMsg("token与请求数据不匹配");
/*  77 */           log.debug("queryForm==0  from collection failed!!");
/*     */         } else {
/*  79 */           log.debug("queryForm==0  from collection successed!!");
/*     */         }
/*  81 */       } else if (queryForm == 1) {
/*  82 */         if (t.getStatus().intValue() != 0) {
/*  83 */           errorCode = 67108946;
/*  84 */           response.setErrorMsg("token=>>" + value + " is nullity!!!");
/*     */         } else {
/*  86 */           log.debug("queryForm==1  from apdu successed!!");
/*     */         }
/*     */       } else {
/*  89 */         errorCode = 67108946;
/*  90 */         response.setErrorMsg("queryForm==" + queryForm + " is error!!!");
/*     */       }
/*     */     }
/*     */     else {
/*  94 */       errorCode = 67108946;
/*  95 */       response.setErrorMsg("request data is error!!!");
/*     */     }
/*     */ 
/*  98 */     response.setResult(new HashMap());
/*  99 */     response.setErrorCode(errorCode);
/* 100 */     String bodyJsonString = JsonUtil.objectToJsonString(response);
/* 101 */     returnJsonDto.setBody(bodyJsonString.getBytes());
/* 102 */     returnJsonDto.setLength(bodyJsonString.getBytes().length);
/*     */ 
/* 104 */     session.write(returnJsonDto);
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.ValidToken
 * JD-Core Version:    0.6.1
 */