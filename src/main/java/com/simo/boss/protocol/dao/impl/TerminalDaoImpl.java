/*    */ package com.simo.boss.protocol.dao.impl;
/*    */ 
/*    */ import com.simo.boss.core.hibernate.HibernateDao;
/*    */ import com.simo.boss.protocol.dao.TerminalDao;
/*    */ import com.simo.boss.protocol.domain.Terminal;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Repository("terminalDao")
/*    */ @Transactional
/*    */ public class TerminalDaoImpl extends HibernateDao<Terminal, Serializable>
/*    */   implements TerminalDao
/*    */ {
/*    */   public Terminal findTerminalBySn(String sn)
/*    */   {
/* 40 */     Criteria criteria = getSession().createCriteria(Terminal.class);
/* 41 */     criteria.add(Restrictions.eq("sn", sn));
/* 42 */     List list = criteria.list();
/* 43 */     if ((list != null) && (list.size() != 0)) {
/* 44 */       return (Terminal)list.get(0);
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */   public Terminal findTerminalById(long id)
/*    */   {
/* 56 */     Terminal terminal = (Terminal)getSession().get(Terminal.class, new Long(id));
/* 57 */     return terminal;
/*    */   }
/*    */ 
/*    */   public void saveOrUpdate(Terminal terminal) {
/* 61 */     getSession().saveOrUpdate(terminal);
/*    */   }
/*    */ 
/*    */   public void delete(String sn) {
/* 65 */     getSession().delete(findTerminalBySn(sn));
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.dao.impl.TerminalDaoImpl
 * JD-Core Version:    0.6.1
 */