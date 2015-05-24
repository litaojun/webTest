/*    */ package com.simo.boss.aaa.simo.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.agency.handler.AgencyFactory;
/*    */ import com.simo.boss.aaa.agency.handler.CommonAgencyHandler;
/*    */ import com.simo.boss.aaa.agency.handler.ReleaseSimConnectHandler;
/*    */ import com.simo.boss.aaa.callback.CommonCallBack;
/*    */ import com.simo.boss.aaa.callback.CommonSimoCallBack;
/*    */ import com.simo.boss.aaa.request.dto.ReleaseSimRequestDto;
/*    */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*    */ import com.simo.boss.domain.dto.agency.ReleaseSimRequest;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.protocol.Body;
/*    */ import com.simo.boss.protocol.CommonDto;
/*    */ import com.simo.boss.protocol.dao.TokenDao;
/*    */ import com.simo.boss.protocol.domain.Token;
/*    */ import com.simo.boss.protocol.handler.ICommonHandler;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.beanutils.BeanUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class ReleaseSimHandler
/*    */   implements ICommonHandler
/*    */ {
/* 40 */   private static final Logger log = Logger.getLogger(ReleaseSimHandler.class);
/*    */ 
/*    */   public void process(IoSession session, CommonDto commonDto)
/*    */   {
/* 49 */     byte[] bodyBytes = commonDto.getBody();
/* 50 */     Body body = new Body(bodyBytes);
/* 51 */     ReleaseSimRequestDto dto = new ReleaseSimRequestDto(body.getSubBody());
/* 52 */     Request request = new Request();
/* 53 */     ReleaseSimRequest rsr = new ReleaseSimRequest();
/*    */     try {
/* 55 */       BeanUtils.copyProperties(rsr, dto);
/*    */     }
/*    */     catch (IllegalAccessException e) {
/* 58 */       e.printStackTrace();
/*    */     }
/*    */     catch (InvocationTargetException e) {
/* 61 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 65 */     TokenDao tokenDao = (TokenDao)SpringContextUtil.getBean("tokenDao");
/* 66 */     String value = dto.getToken();
/* 67 */     Token t = tokenDao.findByToken(value);
/* 68 */     if (null == t) {
/* 69 */       log.error("token error，release sim failed!!!");
/* 70 */       session.close(true);
/* 71 */       return;
/*    */     }
/* 73 */     if (t.getStatus().intValue() == 0) {
/* 74 */       t.setNullityTime(new Date());
/*    */ 
/* 76 */       t.setStatus(Integer.valueOf(1));
/* 77 */       t.setReleaseBy(Integer.valueOf(2));
/* 78 */       tokenDao.saveOrUpdate(t);
/*    */ 
/* 80 */       request.setId(session.getId());
/* 81 */       request.setMethod("releaseSimCard");
/* 82 */       request.setParams(rsr);
/*    */ 
/* 84 */       CommonCallBack callback = new CommonSimoCallBack(session);
/* 85 */       CommonAgencyHandler handler = new ReleaseSimConnectHandler(callback, request);
/* 86 */       AgencyFactory factory = new AgencyFactory(handler);
/* 87 */       factory.handler();
/*    */     } else {
/* 89 */       log.info("================token====>>>" + value + " is nullity!!!");
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.simo.handler.ReleaseSimHandler
 * JD-Core Version:    0.6.1
 */