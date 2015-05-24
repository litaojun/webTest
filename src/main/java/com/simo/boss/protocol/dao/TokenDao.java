package com.simo.boss.protocol.dao;

import com.simo.boss.protocol.domain.Token;

public abstract interface TokenDao
{
  public abstract void saveOrUpdate(Token paramToken);

  public abstract Token findByToken(String paramString);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.dao.TokenDao
 * JD-Core Version:    0.6.1
 */