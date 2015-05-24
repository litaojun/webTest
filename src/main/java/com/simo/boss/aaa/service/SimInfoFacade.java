package com.simo.boss.aaa.service;

import com.simo.boss.aaa.request.dto.ApplySimRequestDto;
import com.simo.boss.aaa.request.dto.ReleaseSimRequestDto;
import com.simo.boss.domain.dto.agency.ApplySimResponse;

public abstract interface SimInfoFacade
{
  public abstract ApplySimResponse applySim(long paramLong, ApplySimRequestDto paramApplySimRequestDto);

  public abstract void releaseSim(ReleaseSimRequestDto paramReleaseSimRequestDto, int paramInt);
}

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.service.SimInfoFacade
 * JD-Core Version:    0.6.1
 */