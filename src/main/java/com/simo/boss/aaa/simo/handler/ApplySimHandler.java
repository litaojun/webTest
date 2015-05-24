/*    */ package com.simo.boss.aaa.simo.handler;
/*    */ 
/*    */ import com.simo.boss.aaa.agency.handler.AgencyFactory;
/*    */ import com.simo.boss.aaa.agency.handler.ApplySimConnectHandler;
/*    */ import com.simo.boss.aaa.agency.handler.CommonAgencyHandler;
/*    */ import com.simo.boss.aaa.callback.CommonCallBack;
/*    */ import com.simo.boss.aaa.callback.CommonSimoCallBack;
/*    */ import com.simo.boss.aaa.request.dto.ApplySimRequestDto;
/*    */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*    */ import com.simo.boss.domain.dto.agency.ApplySimRequest;
/*    */ import com.simo.boss.domain.dto.agency.Request;
/*    */ import com.simo.boss.protocol.Body;
/*    */ import com.simo.boss.protocol.CommonDto;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.protocol.domain.Terminal;
/*    */ import com.simo.boss.protocol.handler.ICommonHandler;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import org.apache.commons.beanutils.BeanUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ 
/*    */ public class ApplySimHandler
/*    */   implements ICommonHandler
/*    */ {
/* 39 */   private static final Logger log = Logger.getLogger(ApplySimHandler.class);
/*    */ 
/*    */   public void process(IoSession session, CommonDto commonDto)
/*    */   {
/* 48 */     byte[] body = commonDto.getBody();
/* 49 */     Body b = new Body(body);
/* 50 */     ApplySimRequestDto dto = new ApplySimRequestDto(b.getSubBody());
/* 51 */     Request request = new Request();
/* 52 */     ApplySimRequest asim = new ApplySimRequest();
/*    */     try {
/* 54 */       BeanUtils.copyProperties(asim, dto);
/* 55 */       log.debug("======asim=====>>" + asim.toString());
/* 56 */       long keyIndex = commonDto.getKeyIndex();
/*    */ 
/* 58 */       TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/* 59 */       Terminal terminal = terminalDao.findTerminalById(keyIndex);
/* 60 */       if (terminal != null) {
/* 61 */         String sn = terminal.getSn();
/* 62 */         asim.setSn(sn);
/*    */       }
/*    */     }
/*    */     catch (IllegalAccessException e) {
/* 66 */       e.printStackTrace();
/*    */     }
/*    */     catch (InvocationTargetException e) {
/* 69 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 72 */     request.setId(1L);
/* 73 */     request.setMethod("applySimCard");
/* 74 */     request.setParams(asim);
/*    */ 
/* 76 */     CommonCallBack callback = new CommonSimoCallBack(session);
/* 77 */     CommonAgencyHandler handler = new ApplySimConnectHandler(callback, request);
/* 78 */     AgencyFactory factory = new AgencyFactory(handler);
/* 79 */     factory.handler();
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.simo.handler.ApplySimHandler
 * JD-Core Version:    0.6.1
 */