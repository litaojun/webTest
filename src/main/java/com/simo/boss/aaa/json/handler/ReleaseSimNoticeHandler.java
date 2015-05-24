/*     */ package com.simo.boss.aaa.json.handler;
/*     */ 
/*     */ import com.simo.boss.aaa.agency.handler.AgencyFactory;
/*     */ import com.simo.boss.aaa.agency.handler.CommonAgencyHandler;
/*     */ import com.simo.boss.aaa.agency.handler.ReleaseSimConnectHandler;
/*     */ import com.simo.boss.aaa.callback.CommonCallBack;
/*     */ import com.simo.boss.aaa.callback.CommonSimoCallBack;
/*     */ import com.simo.boss.aaa.main.CommonJsonHandler;
/*     */ import com.simo.boss.aaa.request.dto.ReleaseSimNoticeDto;
/*     */ import com.simo.boss.common.codec.CommonJsonDto;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.domain.dto.agency.ReleaseSimRequest;
/*     */ import com.simo.boss.domain.dto.agency.Request;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.protocol.dao.TokenDao;
/*     */ import com.simo.boss.protocol.domain.Token;
/*     */ import com.simo.boss.util.JsonUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.codehaus.jackson.type.TypeReference;
/*     */ 
/*     */ public class ReleaseSimNoticeHandler extends CommonJsonHandler
/*     */ {
/*  43 */   private static final Logger log = Logger.getLogger(ReleaseSimNoticeHandler.class);
/*     */ 
/*     */   public void process(IoSession session, CommonJsonDto commonJsonDto)
/*     */   {
/*  50 */     byte[] body = commonJsonDto.getBody();
/*  51 */     String jsonString = new String(body);
/*  52 */     log.debug("=============receive messages from ReleaseSimNotice successed==============" + jsonString);
/*  53 */     Request request = (Request)JsonUtil.jsonStringToObject(new TypeReference()
/*     */     {
/*     */     }
/*     */     , jsonString);
/*  54 */     ReleaseSimNoticeDto releaseSimNoticeDto = (ReleaseSimNoticeDto)request.getParams();
/*  55 */     int releaseBy = releaseSimNoticeDto.getReleaseBy();
/*     */ 
/*  57 */     String[] tokenes = releaseSimNoticeDto.getToken();
/*     */ 
/*  59 */     log.debug("========releaseBy=====>>>" + releaseBy + "========tokenes.length=====>>>" + tokenes.length);
/*  60 */     if (((releaseBy != 1) && (releaseBy != 3)) || (null == tokenes) || (tokenes.length < 1)) {
/*  61 */       log.error("============session.close(true)======ReleaseSimNoticeHandler error=========");
/*  62 */       session.close(true);
/*  63 */       return;
/*     */     }
/*  65 */     int errorCode = 0;
/*  66 */     String errorMsg = "";
/*     */ 
/*  68 */     CommonCallBack callback = new CommonSimoCallBack(session);
/*  69 */     CommonAgencyHandler handler = new ReleaseSimConnectHandler();
/*  70 */     AgencyFactory factory = new AgencyFactory();
/*     */ 
/*  72 */     List returnList = new ArrayList();
/*  73 */     TokenDao tokenDao = (TokenDao)SpringContextUtil.getBean("tokenDao");
/*  74 */     for (int i = 0; i < tokenes.length; i++) {
/*  75 */       String token = tokenes[i];
/*  76 */       Token tokenBean = tokenDao.findByToken(token);
/*  77 */       if (null != tokenBean) {
/*  78 */         if (tokenBean.getStatus().intValue() == 0) {
/*  79 */           tokenBean.setNullityTime(new Date());
/*     */ 
/*  81 */           tokenBean.setStatus(Integer.valueOf(1));
/*  82 */           tokenBean.setReleaseBy(Integer.valueOf(releaseBy));
/*     */           try {
/*  84 */             tokenDao.saveOrUpdate(tokenBean);
/*     */           }
/*     */           catch (Exception e) {
/*  87 */             e.printStackTrace();
/*  88 */             returnList.add(token);
/*  89 */             errorCode++;
/*  90 */             errorMsg = "error Count " + errorCode;
/*  91 */             continue;
/*     */           }
/*     */ 
/*  94 */           if (releaseBy == 3) {
/*  95 */             Request requestReleaseSim = new Request();
/*  96 */             ReleaseSimRequest rsr = new ReleaseSimRequest();
/*  97 */             rsr.setReason((short)6);
/*  98 */             rsr.setToken(token);
/*  99 */             requestReleaseSim.setId(session.getId());
/* 100 */             requestReleaseSim.setMethod("releaseSimCard");
/* 101 */             requestReleaseSim.setParams(rsr);
/*     */ 
/* 103 */             handler.setCallback(callback);
/* 104 */             handler.setRequest(requestReleaseSim);
/* 105 */             factory.setHandler(handler);
/* 106 */             factory.handler();
/*     */           }
/*     */         }
/*     */         else {
/* 110 */           log.error("===========ReleaseSimNoticeHandler============token====" + token + " is nullity!!!");
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 115 */         log.error("===========ReleaseSimNoticeHandler============token====" + token + " is not exist!!!");
/*     */       }
/*     */     }
/*     */ 
/* 119 */     CommonJsonDto returnJsonDto = new CommonJsonDto();
/* 120 */     Response response = new Response();
/* 121 */     response.setMethod("releaseSimNotice");
/* 122 */     response.setId(request.getId());
/* 123 */     response.setTime(new Date());
/* 124 */     response.setErrorCode(errorCode);
/* 125 */     response.setErrorMsg(errorMsg);
/* 126 */     if (returnList.size() > 0) {
/* 127 */       response.setResult(returnList);
/*     */     }
/* 129 */     String bodyJsonString = JsonUtil.objectToJsonString(response);
/* 130 */     returnJsonDto.setBody(bodyJsonString.getBytes());
/* 131 */     returnJsonDto.setLength(bodyJsonString.getBytes().length);
/*     */ 
/* 133 */     session.write(returnJsonDto);
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.json.handler.ReleaseSimNoticeHandler
 * JD-Core Version:    0.6.1
 */