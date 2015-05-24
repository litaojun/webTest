/*     */ package com.simo.boss.aaa.service;
/*     */ 
/*     */ import com.simo.boss.aaa.request.dto.ApplySimRequestDto;
/*     */ import com.simo.boss.aaa.request.dto.HeartBeatRequestDto;
/*     */ import com.simo.boss.aaa.request.dto.ReleaseSimRequestDto;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.domain.dto.agency.ApplySimRequest;
/*     */ import com.simo.boss.domain.dto.agency.ApplySimResponse;
/*     */ import com.simo.boss.domain.dto.agency.ReleaseSimRequest;
/*     */ import com.simo.boss.domain.dto.agency.Request;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.enumerate.Constant.DefStatusType;
/*     */ import com.simo.boss.enumerate.Constant.ReleaseReason;
import com.simo.boss.enumerate.ErrorConstant;
/*     */ import com.simo.boss.enumerate.ErrorConstant.ApplySim;
/*     */ import com.simo.boss.enumerate.ErrorConstant.Terminal;
/*     */ import com.simo.boss.exception.BusinessException;
/*     */ import com.simo.boss.protocol.client.service.JsonMsgService;
/*     */ import com.simo.boss.protocol.dao.TerminalDao;
/*     */ import com.simo.boss.protocol.dao.TokenDao;
/*     */ import com.simo.boss.protocol.domain.Token;
import com.simo.boss.util.Constant;
/*     */ import com.simo.boss.util.JsonUtil;

/*     */ import java.util.Date;

/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Propagation;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("simInfoFacade")
/*     */ public class SimInfoFacadeImpl
/*     */   implements SimInfoFacade
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private TerminalDao terminalDao;
/*     */ 
/*     */   @Autowired
/*     */   private TokenDao tokenDao;
/*     */ 
/*     */   @Autowired
/*     */   private JsonMsgService jsonMsgService;
/*  48 */   private Logger log = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   @Transactional(propagation=Propagation.REQUIRED)
/*     */   public ApplySimResponse applySim(long terminalId, ApplySimRequestDto reqDto)
/*     */   {
/*  54 */     ApplySimRequest asim = new ApplySimRequest();
/*     */     try {
/*  56 */       BeanUtils.copyProperties(asim, reqDto);
/*     */     } catch (Exception e) {
/*  58 */       this.log.error("object.copy.error", e);
/*  59 */       throw new RuntimeException("objects copy occur error!");
/*     */     }
/*     */ 
/*  62 */     com.simo.boss.protocol.domain.Terminal terminal = this.terminalDao.findTerminalById(terminalId);
/*     */ 
/*  65 */     if (terminal == null) {
/*  66 */       throw new BusinessException(ErrorConstant.Terminal.UnFound.getValue(), "terminalId[" + terminalId + "] is not found!");
/*     */     }
/*     */ 
/*  72 */     asim.setSn(terminal.getSn());
/*     */ 
/*  75 */     Response agencyResp = applySimToAgency(asim);
/*     */ 
/*  78 */     ApplySimResponse applySim = (ApplySimResponse)agencyResp.getResult();
/*     */ 
/*  81 */     saveToken(terminal, applySim);
/*     */     try
/*     */     {
/*  85 */       noticeHeartbeat(applySim);
/*     */     } catch (Exception e) {
/*  87 */       this.log.warn(terminalId + " applySimNotice to heartbeat failured,sim info[" + applySim + "]");
/*     */ 
/*  90 */       this.log.error(terminalId + "applySimNotice to heartbeat failured,cause:", e);
/*     */     }
/*     */ 
/*  93 */     return applySim;
/*     */   }
/*     */ 
/*     */   @Transactional(propagation=Propagation.REQUIRED)
/*     */   public void releaseSim(ReleaseSimRequestDto reqDto, int releaseBy)
/*     */   {
/* 100 */     String value = reqDto.getToken();
/* 101 */     Token t = this.tokenDao.findByToken(value);
/* 102 */     if (null == t) {
/* 103 */       return;
/*     */     }
/*     */ 
/* 106 */     if (t.getStatus().intValue() == Constant.DefStatusType.effective.getValue()) {
/* 107 */       t.setNullityTime(new Date());
/*     */ 
/* 109 */       t.setStatus(Integer.valueOf(Constant.DefStatusType.invalidate.getValue()));
/* 110 */       t.setReleaseBy(Integer.valueOf(releaseBy));
/* 111 */       this.tokenDao.saveOrUpdate(t);
/*     */       try
/*     */       {
/* 116 */         if ((releaseBy != Constant.ReleaseReason.ByUser.getValue()) && (releaseBy != Constant.ReleaseReason.Eject.getValue()))
/*     */         {
/* 118 */           sendReleaseNoticeToAgency(reqDto);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 122 */         this.log.error("releaseSim to agency failured,", e);
/*     */       }
/*     */     } else {
/* 125 */       this.log.info("token[" + value + "] status has been released!");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sendReleaseNoticeToAgency(ReleaseSimRequestDto reqDto) {
/* 130 */     ReleaseSimRequest rsr = new ReleaseSimRequest();
/*     */     try {
/* 132 */       BeanUtils.copyProperties(rsr, reqDto);
/*     */     } catch (Exception e) {
/* 134 */       this.log.error("object.copy.error", e);
/* 135 */       throw new RuntimeException("objects copy occur error!");
/*     */     }
/*     */ 
/* 138 */     Request request = new Request();
/* 139 */     request.setId(1L);
/* 140 */     request.setMethod("releaseSimCard");
/* 141 */     request.setParams(rsr);
/*     */ 
/* 143 */     String respMsg = this.jsonMsgService.sendMessage("agency", request);
/*     */     try
/*     */     {
/* 147 */       JSONObject json = new JSONObject(respMsg);
/* 148 */       int errorCode = ((Integer)json.get("errorCode")).intValue();
/* 149 */       if (errorCode != 0) {
/* 150 */         this.log.error("releaseSim agency response:" + errorCode + json.getString("errorMsg"));
/*     */ 
/* 152 */         throw new BusinessException(errorCode, json.getString("errorMsg"));
/*     */       }
/*     */     }
/*     */     catch (JSONException e)
/*     */     {
/* 157 */       this.log.error("releaseSim agency response isn't json type!" + respMsg);
/* 158 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Response<ApplySimResponse> applySimToAgency(ApplySimRequest asim) {
/* 163 */     Response response = null;
/*     */     try {
/* 165 */       Request request = new Request();
/*     */ 
/* 167 */       request.setId(1L);
/* 168 */       request.setMethod("applySimCard");
/* 169 */       request.setParams(asim);
/*     */ 
/* 171 */       String respMsg = this.jsonMsgService.sendMessage("agency", request);
/*     */ 
/* 174 */       response = JsonUtil.jsonToResponse(respMsg, ApplySimResponse.class);
/*     */ 
/* 176 */       if (response.getErrorCode() != 0) {
/* 177 */         this.log.error("apply.sim.to.agency.response.error,response:{errorCode:" + response.getErrorCode() + ",errorMsg:" + response.getErrorMsg() + "}");
/*     */ 
/* 181 */         throw new BusinessException(ErrorConstant.ApplySim.ApplySimError.getValue(), "apply.sim.to.agency.error");
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 186 */       this.log.error("apply.sim.to.agency.error", e);
/* 187 */       throw new BusinessException(ErrorConstant.ApplySim.ApplySimError.getValue(), "apply.sim.to.agency.error");
/*     */     }
/*     */ 
/* 191 */     return response;
/*     */   }
/*     */ 
/*     */   private void noticeHeartbeat(ApplySimResponse applySim)
/*     */   {
/* 203 */     Request requestHbDto = new Request();
/* 204 */     requestHbDto.setId(1L);
/* 205 */     requestHbDto.setMethod("applySimNotice");
/* 206 */     HeartBeatRequestDto hbDto = new HeartBeatRequestDto();
/* 207 */     hbDto.setApplyTime(new Date());
/* 208 */     hbDto.setHeartBeatInterval(applySim.getHeartbeat());
/* 209 */     hbDto.setToken(applySim.getToken());
/* 210 */     hbDto.setDelayTime(applySim.getHeartbeatDelayTime());
/* 211 */     requestHbDto.setParams(hbDto);
/*     */ 
/* 213 */     this.jsonMsgService.sendMessage("heartbeat", requestHbDto);
/*     */   }
/*     */ 
/*     */   private void saveToken(com.simo.boss.protocol.domain.Terminal terminal, ApplySimResponse applySim)
/*     */   {
/* 218 */     Token tokenRecord = new Token();
/* 219 */     tokenRecord.setCreateTime(new Date());
/* 220 */     tokenRecord.setSimId(applySim.getSimId() + "");
/* 221 */     tokenRecord.setSimPackageId(Long.valueOf(applySim.getSimPackageId()));
/*     */ 
/* 223 */     tokenRecord.setTerminalId(terminal.getId());
/* 224 */     tokenRecord.setSn(terminal.getSn());
/*     */ 
/* 226 */     tokenRecord.setUserId(Long.valueOf(applySim.getUserId()));
/* 227 */     tokenRecord.setValue(applySim.getToken());
/* 228 */     tokenRecord.setUserPackageId(Long.valueOf(applySim.getUserPackageId()));
/* 229 */     tokenRecord.setStatus(Integer.valueOf(0));
/*     */ 
/* 231 */     TokenDao tokenDao = (TokenDao)SpringContextUtil.getBean("tokenDao");
/*     */ 
/* 233 */     tokenDao.saveOrUpdate(tokenRecord);
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.SimInfoFacadeImpl
 * JD-Core Version:    0.6.1
 */