/*    */ package com.simo.boss.protocol.dao.impl;
/*    */ 
/*    */ import com.simo.boss.core.hibernate.HibernateDao;
/*    */ import com.simo.boss.protocol.dao.TokenDao;
/*    */ import com.simo.boss.protocol.domain.Token;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Repository("tokenDao")
/*    */ @Transactional
/*    */ public class TokenDaoImpl extends HibernateDao<Token, Serializable>
/*    */   implements TokenDao
/*    */ {
/*    */   public void saveOrUpdate(Token token)
/*    */   {
/* 39 */     getSession().saveOrUpdate(token);
/*    */   }
/*    */ 
/*    */   public Token findByToken(String token)
/*    */   {
/* 50 */     Criteria criteria = getSession().createCriteria(Token.class);
/* 51 */     criteria.add(Restrictions.eq("value", token));
/* 52 */     List list = criteria.list();
/* 53 */     if ((null != list) && (list.size() != 0)) {
/* 54 */       return (Token)list.get(0);
/*    */     }
/* 56 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.dao.impl.TokenDaoImpl
 * JD-Core Version:    0.6.1
 */