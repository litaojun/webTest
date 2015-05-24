package com.simo.boss.protocol.dao;

import com.simo.boss.protocol.domain.Terminal;

public abstract interface TerminalDao
{
  public abstract Terminal findTerminalBySn(String paramString);

  public abstract Terminal findTerminalById(long paramLong);

  public abstract void saveOrUpdate(Terminal paramTerminal);

  public abstract void delete(String paramString);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.dao.TerminalDao
 * JD-Core Version:    0.6.1
 */