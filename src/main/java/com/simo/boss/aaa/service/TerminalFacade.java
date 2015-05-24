package com.simo.boss.aaa.service;

import com.simo.boss.aaa.request.dto.AddTerminalDto;

public abstract interface TerminalFacade
{
  public abstract void deleteTerminalBySN(String paramString);

  public abstract void addTerminal(AddTerminalDto paramAddTerminalDto);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.TerminalFacade
 * JD-Core Version:    0.6.1
 */