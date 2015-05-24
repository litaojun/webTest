package com.simo.boss.aaa.service;

import com.simo.boss.protocol.domain.Terminal;

public abstract interface TerminalAuthorityFacade
{
  public abstract Terminal authenticate(String paramString);

  public abstract byte[] saveEncryptKey(long paramLong, byte[] paramArrayOfByte);

  public abstract byte[] decrypt(long paramLong);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.TerminalAuthorityFacade
 * JD-Core Version:    0.6.1
 */