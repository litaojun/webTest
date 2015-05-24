/*     */ package com.simo.boss.aaa.handler.binary;
/*     */ 
/*     */ import com.simo.boss.aaa.request.dto.ApplySimRequestDto;
/*     */ import com.simo.boss.aaa.service.SimInfoFacade;
/*     */ import com.simo.boss.common.util.IPv4Util;
/*     */ import com.simo.boss.domain.dto.BaseResp;
/*     */ import com.simo.boss.domain.dto.agency.ApplySimResponse;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.enumerate.Constant.AuthType;
/*     */ import com.simo.boss.enumerate.Constant.NetworkType;
/*     */ import com.simo.boss.enumerate.Constant.ServiceType;
/*     */ import com.simo.boss.protocol.handler.BaseMessageHandler;
/*     */ import com.simo.boss.util.HexUtil;
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
import com.simo.boss.util.Constant;
/*     */ 
/*     */ @Component("applySimHandler")
/*     */ public class ApplySimHandler extends BaseMessageHandler
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SimInfoFacade simInfoFacade;
/*     */ 
/*     */   public Response<BaseResp> process(IoSession session, byte[] bytes)
/*     */   {
/*  43 */     ApplySimRequestDto reqDto = new ApplySimRequestDto(bytes);
/*     */ 
/*  45 */     long keyIndex = ((Long)session.getAttribute("keyIndex")).longValue();
/*     */ 
/*  48 */     ApplySimResponse applySimResponse = this.simInfoFacade.applySim(keyIndex, reqDto);
/*     */ 
/*  52 */     final byte[] responseBody = wrapSimInfo(applySimResponse);
/*     */ 
/*  54 */     Response resp = new Response();
/*  55 */     resp.setResult(new BaseResp()
/*     */     {
/*     */       public byte[] toBytes()
/*     */       {
/*  59 */         return responseBody;
/*     */       }
/*     */     });
/*  62 */     return resp;
/*     */   }
/*     */ 
/*     */   private byte[] wrapSimInfo(ApplySimResponse applySim)
/*     */   {
/*  67 */     IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*     */ 
/*  69 */     String token = applySim.getToken();
/*  70 */     buffer.putUnsigned(token.getBytes().length);
/*  71 */     buffer.put(token.getBytes());
/*     */ 
/*  73 */     long userId = applySim.getUserId();
/*  74 */     buffer.putUnsignedInt(userId);
/*     */ 
/*  76 */     long userPackageId = applySim.getUserPackageId();
/*  77 */     buffer.putUnsignedInt(userPackageId);
/*     */ 
/*  79 */     ServiceType userService = applySim.getServiceType();
/*  80 */     buffer.putUnsigned(userService.getValue());
/*     */ 
/*  86 */     long simId = applySim.getSimId();
/*  87 */     buffer.putUnsignedInt(simId);
/*     */ 
/*  89 */     long simPackageId = applySim.getSimPackageId();
/*  90 */     buffer.putUnsignedInt(simPackageId);
/*     */ 
/*  92 */     String address = applySim.getSimAddress();
/*     */ 
/*  94 */     long vsimAddress = 0L;
/*  95 */     long simbankAddress = 0L;
/*  96 */     long bladeLogicalAddress = 0L;
/*  97 */     long simAddress = 0L;
/*  98 */     String[] addresses = address.split("\\.");
/*  99 */     if (addresses.length == 1) {
/* 100 */       vsimAddress = Long.parseLong(addresses[0]);
/* 101 */     } else if (addresses.length == 2) {
/* 102 */       vsimAddress = Long.parseLong(addresses[0]);
/* 103 */       simbankAddress = Long.parseLong(addresses[1]);
/* 104 */     } else if (addresses.length == 3) {
/* 105 */       vsimAddress = Long.parseLong(addresses[0]);
/* 106 */       simbankAddress = Long.parseLong(addresses[1]);
/* 107 */       bladeLogicalAddress = Long.parseLong(addresses[2]);
/* 108 */     } else if (addresses.length == 4) {
/* 109 */       vsimAddress = Long.parseLong(addresses[0]);
/* 110 */       simbankAddress = Long.parseLong(addresses[1]);
/* 111 */       bladeLogicalAddress = Long.parseLong(addresses[2]);
/* 112 */       simAddress = Long.parseLong(addresses[3]);
/*     */     }
/* 114 */     buffer.putUnsignedInt(vsimAddress);
/* 115 */     buffer.putUnsignedInt(simbankAddress);
/* 116 */     buffer.putUnsignedInt(bladeLogicalAddress);
/* 117 */     buffer.putUnsignedInt(simAddress);
/*     */ 
/* 119 */     NetworkType simType = applySim.getSimType();
/* 120 */     buffer.putUnsigned(simType.getValue());
/*     */ 
/* 122 */     String iccid = applySim.getIccid();
/*     */ 
/* 125 */     buffer.putUnsigned(HexUtil.hexStringToBytes(iccid).length);
/* 126 */     buffer.put(HexUtil.hexStringToBytes(iccid));
/*     */ 
/* 128 */     String imsi = applySim.getImsi();
/*     */ 
/* 131 */     buffer.putUnsigned(HexUtil.hexStringToBytes(imsi).length);
/* 132 */     buffer.put(HexUtil.hexStringToBytes(imsi));
/*     */ 
/* 134 */     String smsp = applySim.getSmsp();
/*     */ 
/* 137 */     buffer.putUnsigned(HexUtil.hexStringToBytes(smsp).length);
/* 138 */     buffer.put(HexUtil.hexStringToBytes(smsp));
/*     */ 
/* 140 */     String hplmn = applySim.getHplmn();
/*     */ 
/* 143 */     buffer.putUnsigned(HexUtil.hexStringToBytes(hplmn).length);
/* 144 */     buffer.put(HexUtil.hexStringToBytes(hplmn));
/*     */ 
/* 146 */     String acc = applySim.getAcc();
/* 147 */     buffer.putUnsigned(acc.getBytes().length);
/* 148 */     buffer.put(acc.getBytes());
/*     */ 
/* 150 */     String ad = applySim.getAd();
/* 151 */     buffer.putUnsigned(ad.getBytes().length);
/* 152 */     buffer.put(ad.getBytes());
/*     */ 
/* 154 */     String mcc = applySim.getMcc();
/* 155 */     buffer.put(mcc.getBytes());
/*     */ 
/* 157 */     String mnc = applySim.getMnc();
/* 158 */     buffer.putUnsigned(mnc.getBytes().length);
/* 159 */     buffer.put(mnc.getBytes());
/*     */ 
/* 161 */     String number = applySim.getPhoneNumber();
/* 162 */     buffer.putUnsignedShort(number.getBytes().length);
/* 163 */     buffer.put(number.getBytes());
/*     */ 
/* 170 */     String apn = applySim.getApn();
/* 171 */     buffer.putUnsignedInt(apn.getBytes().length);
/* 172 */     buffer.put(apn.getBytes());
/*     */ 
/* 174 */     String apnProxyServer = applySim.getApnProxyServer();
/* 175 */     buffer.putUnsignedInt(apnProxyServer.getBytes().length);
/* 176 */     buffer.put(apnProxyServer.getBytes());
/*     */ 
/* 178 */     int apnProxyPort = applySim.getApnProxyPort().intValue();
/* 179 */     buffer.putUnsignedShort(apnProxyPort);
/*     */ 
/* 181 */     AuthType apnAuthType = applySim.getApnAuthType();
/* 182 */     buffer.putUnsigned(apnAuthType.getValue());
/*     */ 
/* 184 */     String apnAuthServer = applySim.getApnAuthServer();
/* 185 */     buffer.putUnsigned(apnAuthServer.getBytes().length);
/* 186 */     buffer.put(apnAuthServer.getBytes());
/*     */ 
/* 188 */     String apnAuthName = applySim.getApnAuthName();
/* 189 */     buffer.putUnsigned(apnAuthName.getBytes().length);
/* 190 */     buffer.put(apnAuthName.getBytes());
/*     */ 
/* 192 */     String apnAuthPassword = applySim.getApnAuthPassword();
/* 193 */     buffer.putUnsigned(apnAuthPassword.getBytes().length);
/* 194 */     buffer.put(apnAuthPassword.getBytes());
/*     */ 
/* 197 */     buffer.putUnsignedShort(applySim.getDataCdrSyncInterval().intValue());
/*     */ 
/* 199 */     buffer.putUnsignedInt(applySim.getDataCdrSyncThreshold().intValue());
/*     */ 
/* 202 */     buffer.putUnsignedShort(applySim.getDataCdrSaveInterval().intValue());
/*     */ 
/* 204 */     buffer.putUnsignedInt(applySim.getDataCdrSaveThreshold().intValue());
/*     */ 
/* 207 */     buffer.putUnsignedShort(applySim.getCallCdrSaveInterval().intValue());
/*     */ 
/* 209 */     buffer.putUnsignedShort(applySim.getHeartbeat().intValue());
/*     */ 
/* 211 */     long hbsip = IPv4Util.ipToLong(applySim.getHeartbeatServerIp());
/* 212 */     buffer.putUnsignedInt(hbsip);
/*     */ 
/* 214 */     buffer.putUnsignedShort(applySim.getHeartbeatServerPort().intValue());
/*     */ 
/* 216 */     long csip = IPv4Util.ipToLong(applySim.getCdrServerIp());
/* 217 */     buffer.putUnsignedInt(csip);
/*     */ 
/* 219 */     buffer.putUnsignedShort(applySim.getCdrServerPort().intValue());
/*     */ 
/* 221 */     long asip = IPv4Util.ipToLong(applySim.getApduServerIp());
/* 222 */     buffer.putUnsignedInt(asip);
/*     */ 
/* 224 */     buffer.putUnsignedShort(applySim.getApduServerPort().intValue());
/*     */ 
/* 226 */     buffer.flip();
/*     */ 
/* 228 */     byte[] bytes = IoBufferUtil.ioBufferToByte(buffer);
/* 229 */     buffer.free();
/* 230 */     return bytes;
/*     */   }
/*     */ 
/*     */   protected Response<BaseResp> process(byte[] bytes)
/*     */   {
/* 235 */     return null;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.handler.binary.ApplySimHandler
 * JD-Core Version:    0.6.1
 */