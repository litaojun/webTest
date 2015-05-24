/*    */ package com.simo.boss.aaa.service;
/*    */ 
/*    */ /*    */ import java.util.Date;

/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;

import com.simo.boss.enumerate.ErrorConstant;
import com.simo.boss.enumerate.ErrorConstant.Terminal;
/*    */ import com.simo.boss.exception.BusinessException;
/*    */ import com.simo.boss.protocol.dao.EncryptDao;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.protocol.domain.Encrypt;
/*    */ import com.simo.boss.util.DesUtil;
/*    */ 
/*    */ 
/*    */ @Service("terminalAuthorityFacade")
/*    */ public class TerminalAuthorityFacadeImpl
/*    */   implements TerminalAuthorityFacade
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private EncryptDao encryptDao;
/*    */ 
/*    */   @Autowired
/*    */   private TerminalDao terminalDao;
/*    */ 
/*    */   @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
/*    */   public com.simo.boss.protocol.domain.Terminal authenticate(String terminalSN)
/*    */   {
/* 31 */     com.simo.boss.protocol.domain.Terminal terminal = this.terminalDao.findTerminalBySn(terminalSN);
/* 32 */     if (terminal == null) {
/* 33 */       throw new BusinessException(ErrorConstant.Terminal.UnFound.getValue(), "sn[" + terminalSN + "] is not found!");
/*    */     }
/*    */ 
/* 37 */     return terminal;
/*    */   }
/*    */ 
/*    */   @Transactional(propagation=Propagation.REQUIRED)
/*    */   public byte[] saveEncryptKey(long terminalId, byte[] key)
/*    */   {
/* 44 */     Encrypt encrypt = new Encrypt();
/* 45 */     encrypt.setKeyIndex(new Long(terminalId));
/* 46 */     encrypt.setCreateTime(new Date());
/* 47 */     encrypt.setKeyEncrypt(key);
/* 48 */     this.encryptDao.saveOrUpdateEncrypt(encrypt);
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */   @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
/*    */   public byte[] decrypt(long terminalId)
/*    */   {
/* 55 */     Encrypt encrypt = this.encryptDao.findById(Long.valueOf(terminalId));
/*    */ 
/* 57 */     if (encrypt == null) {
/* 58 */       throw new BusinessException(83886080, "keyindex不存在");
/*    */     }
/*    */ 
/* 61 */     byte[] encryptBytes = encrypt.getKeyEncrypt();
/*    */ 
/* 64 */     com.simo.boss.protocol.domain.Terminal terminal = this.terminalDao.findTerminalById(terminalId);
/* 65 */     byte[] codes = terminal.getCode1().getBytes();
/*    */ 
/* 68 */     byte[] key = DesUtil.createDecryptor(codes, encryptBytes);
/* 69 */     return key;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.TerminalAuthorityFacadeImpl
 * JD-Core Version:    0.6.1
 */