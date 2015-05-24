/*    */ package com.simo.boss.protocol.dao.impl;
/*    */ 
/*    */ import com.simo.boss.core.hibernate.HibernateDao;
/*    */ import com.simo.boss.protocol.dao.EncryptDao;
/*    */ import com.simo.boss.protocol.domain.Encrypt;
/*    */ import java.io.Serializable;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Repository("encryptDao")
/*    */ @Transactional
/*    */ public class EncryptDaoImpl extends HibernateDao<Encrypt, Serializable>
/*    */   implements EncryptDao
/*    */ {
/*    */   public void saveOrUpdateEncrypt(Encrypt encrypt)
/*    */   {
/* 37 */     getSession().saveOrUpdate(encrypt);
/*    */   }
/*    */ 
/*    */   public Encrypt findById(Long id)
/*    */   {
/* 47 */     return (Encrypt)getSession().get(Encrypt.class, id);
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.dao.impl.EncryptDaoImpl
 * JD-Core Version:    0.6.1
 */