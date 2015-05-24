package com.simo.boss.protocol.dao;

import com.simo.boss.protocol.domain.Encrypt;

public abstract interface EncryptDao
{
  public abstract void saveOrUpdateEncrypt(Encrypt paramEncrypt);

  public abstract Encrypt findById(Long paramLong);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.protocol.dao.EncryptDao
 * JD-Core Version:    0.6.1
 */