/*    */ package com.simo.boss.aaa.service;
/*    */ 
/*    */ import com.simo.boss.aaa.request.dto.AddTerminalDto;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.protocol.domain.Terminal;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("terminalFacade")
/*    */ public class TerminalFacadeImpl
/*    */   implements TerminalFacade
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TerminalDao terminalDao;
/*    */ 
/*    */   @Transactional(propagation=Propagation.REQUIRED)
/*    */   public void deleteTerminalBySN(String sn)
/*    */   {
/* 21 */     this.terminalDao.delete(sn);
/*    */   }
/*    */ 
/*    */   @Transactional(propagation=Propagation.REQUIRED)
/*    */   public void addTerminal(AddTerminalDto reqDto)
/*    */   {
/* 27 */     Terminal t = this.terminalDao.findTerminalById(reqDto.getId());
/* 28 */     if (null == t) {
/* 29 */       t = new Terminal();
/*    */     }
/* 31 */     t.setSn(reqDto.getTerminalSn());
/* 32 */     t.setStatus(Integer.valueOf(reqDto.getStatus()));
/* 33 */     t.setCode1(reqDto.getCode1());
/* 34 */     t.setId(Long.valueOf(reqDto.getId()));
/* 35 */     this.terminalDao.saveOrUpdate(t);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.TerminalFacadeImpl
 * JD-Core Version:    0.6.1
 */