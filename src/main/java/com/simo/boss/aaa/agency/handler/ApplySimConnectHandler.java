/*     */ package com.simo.boss.aaa.agency.handler;
/*     */ 
/*     */ import com.simo.boss.aaa.callback.CommonCallBack;
/*     */ import com.simo.boss.aaa.request.dto.HeartBeatRequestDto;
/*     */ import com.simo.boss.common.codec.CommonJsonDto;
/*     */ import com.simo.boss.common.util.IPv4Util;
/*     */ import com.simo.boss.core.spring.factoryBean.SpringContextUtil;
/*     */ import com.simo.boss.domain.dto.agency.ApplySimResponse;
/*     */ import com.simo.boss.domain.dto.agency.Request;
/*     */ import com.simo.boss.domain.dto.agency.Response;
/*     */ import com.simo.boss.enumerate.Constant.AuthType;
/*     */ import com.simo.boss.enumerate.Constant.NetworkType;
/*     */ import com.simo.boss.enumerate.Constant.ServiceType;
/*     */ import com.simo.boss.protocol.Body;
/*     */ import com.simo.boss.protocol.CommonDto;
/*     */ import com.simo.boss.protocol.dao.TerminalDao;
/*     */ import com.simo.boss.protocol.dao.TokenDao;
/*     */ import com.simo.boss.protocol.domain.Terminal;
/*     */ import com.simo.boss.protocol.domain.Token;
/*     */ import com.simo.boss.util.HexUtil;
/*     */ import com.simo.boss.util.IoBufferUtil;
/*     */ import com.simo.boss.util.JsonUtil;
/*     */ import java.util.Date;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.mina.core.buffer.IoBuffer;
/*     */ import org.apache.mina.core.session.IoSession;
/*     */ import org.codehaus.jackson.type.TypeReference;
import com.simo.boss.util.Constant;
/*     */ 
/*     */ public class ApplySimConnectHandler<T> extends CommonAgencyHandler<T>
/*     */ {
/*  47 */   private static final Logger log = Logger.getLogger(ApplySimConnectHandler.class);
/*     */ 
/*     */   public ApplySimConnectHandler(CommonCallBack callback, T request) {
/*  50 */     setCallback(callback);
/*  51 */     setRequest(request);
/*     */   }
/*     */ 
/*     */   public void messageReceived(IoSession session, Object message)
/*     */     throws Exception
/*     */   {
/*  59 */     CommonJsonDto dto = (CommonJsonDto)message;
/*  60 */     String jsonString = new String(dto.getBody());
/*  61 */     log.debug("========get apply sim messages from agency successed=======>>>>" + jsonString);
/*     */ 
/*  63 */     Response response = (Response)JsonUtil.jsonStringToObject(new TypeReference()
/*     */     {
/*     */     }
/*     */     , jsonString);
/*     */ 
/*  65 */     CommonDto responseDto = new CommonDto();
/*  66 */     long keyIndex = ((Long)getCallback().getSession().getAttribute("keyIndex")).longValue();
/*  67 */     responseDto.setKeyIndex(keyIndex);
/*     */ 
/*  69 */     int errorCode = response.getErrorCode();
/*  70 */     IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
/*  71 */     buffer.putUnsignedInt(errorCode);
/*  72 */     byte[] responseBody = null;
/*  73 */     int heartbeat = 0;
/*  74 */     String token = "";
/*  75 */     Integer delayTime = Integer.valueOf(0);
/*  76 */     if (errorCode == 0) {
/*  77 */       ApplySimResponse applySim = (ApplySimResponse)response.getResult();
/*     */ 
/*  79 */       token = applySim.getToken();
/*  80 */       buffer.putUnsigned(token.getBytes().length);
/*  81 */       buffer.put(token.getBytes());
/*     */ 
/*  83 */       delayTime = applySim.getHeartbeatDelayTime();
/*     */ 
/*  85 */       long userId = applySim.getUserId();
/*  86 */       buffer.putUnsignedInt(userId);
/*     */ 
/*  88 */       long userPackageId = applySim.getUserPackageId();
/*  89 */       buffer.putUnsignedInt(userPackageId);
/*     */ 
/*  91 */       ServiceType userService = applySim.getServiceType();
/*  92 */       buffer.putUnsigned(userService.getValue());
/*     */ 
/*  97 */       long simId = applySim.getSimId();
/*  98 */       buffer.putUnsignedInt(simId);
/*     */ 
/* 100 */       long simPackageId = applySim.getSimPackageId();
/* 101 */       buffer.putUnsignedInt(simPackageId);
/*     */ 
/* 103 */       String address = applySim.getSimAddress();
/*     */ 
/* 105 */       long vsimAddress = 0L;
/* 106 */       long simbankAddress = 0L;
/* 107 */       long bladeLogicalAddress = 0L;
/* 108 */       long simAddress = 0L;
/* 109 */       String[] addresses = address.split("\\.");
/* 110 */       if (addresses.length == 1) {
/* 111 */         vsimAddress = Long.parseLong(addresses[0]);
/* 112 */       } else if (addresses.length == 2) {
/* 113 */         vsimAddress = Long.parseLong(addresses[0]);
/* 114 */         simbankAddress = Long.parseLong(addresses[1]);
/* 115 */       } else if (addresses.length == 3) {
/* 116 */         vsimAddress = Long.parseLong(addresses[0]);
/* 117 */         simbankAddress = Long.parseLong(addresses[1]);
/* 118 */         bladeLogicalAddress = Long.parseLong(addresses[2]);
/* 119 */       } else if (addresses.length == 4) {
/* 120 */         vsimAddress = Long.parseLong(addresses[0]);
/* 121 */         simbankAddress = Long.parseLong(addresses[1]);
/* 122 */         bladeLogicalAddress = Long.parseLong(addresses[2]);
/* 123 */         simAddress = Long.parseLong(addresses[3]);
/*     */       }
/* 125 */       buffer.putUnsignedInt(vsimAddress);
/* 126 */       buffer.putUnsignedInt(simbankAddress);
/* 127 */       buffer.putUnsignedInt(bladeLogicalAddress);
/* 128 */       buffer.putUnsignedInt(simAddress);
/*     */ 
/* 130 */       NetworkType simType = applySim.getSimType();
/* 131 */       buffer.putUnsigned(simType.getValue());
/*     */ 
/* 133 */       String iccid = applySim.getIccid();
/*     */ 
/* 136 */       buffer.putUnsigned(HexUtil.hexStringToBytes(iccid).length);
/* 137 */       buffer.put(HexUtil.hexStringToBytes(iccid));
/*     */ 
/* 139 */       String imsi = applySim.getImsi();
/*     */ 
/* 142 */       buffer.putUnsigned(HexUtil.hexStringToBytes(imsi).length);
/* 143 */       buffer.put(HexUtil.hexStringToBytes(imsi));
/*     */ 
/* 145 */       String smsp = applySim.getSmsp();
/*     */ 
/* 148 */       buffer.putUnsigned(HexUtil.hexStringToBytes(smsp).length);
/* 149 */       buffer.put(HexUtil.hexStringToBytes(smsp));
/*     */ 
/* 151 */       String hplmn = applySim.getHplmn();
/*     */ 
/* 154 */       buffer.putUnsigned(HexUtil.hexStringToBytes(hplmn).length);
/* 155 */       buffer.put(HexUtil.hexStringToBytes(hplmn));
/*     */ 
/* 157 */       String acc = applySim.getAcc();
/* 158 */       buffer.putUnsigned(acc.getBytes().length);
/* 159 */       buffer.put(acc.getBytes());
/*     */ 
/* 161 */       String ad = applySim.getAd();
/* 162 */       buffer.putUnsigned(ad.getBytes().length);
/* 163 */       buffer.put(ad.getBytes());
/*     */ 
/* 166 */       String mcc = applySim.getMcc();
/* 167 */       buffer.put(mcc.getBytes());
/*     */ 
/* 169 */       String mnc = applySim.getMnc();
/* 170 */       buffer.putUnsigned(mnc.getBytes().length);
/* 171 */       buffer.put(mnc.getBytes());
/*     */ 
/* 173 */       String number = applySim.getPhoneNumber();
/* 174 */       buffer.putUnsignedShort(number.getBytes().length);
/* 175 */       buffer.put(number.getBytes());
/*     */ 
/* 177 */       String apnName = applySim.getApnName();
/* 178 */       buffer.putUnsignedInt(apnName.getBytes().length);
/* 179 */       buffer.put(apnName.getBytes());
/*     */ 
/* 181 */       String apn = applySim.getApn();
/* 182 */       buffer.putUnsignedInt(apn.getBytes().length);
/* 183 */       buffer.put(apn.getBytes());
/*     */ 
/* 185 */       String apnProxyServer = applySim.getApnProxyServer();
/* 186 */       buffer.putUnsignedInt(apnProxyServer.getBytes().length);
/* 187 */       buffer.put(apnProxyServer.getBytes());
/*     */ 
/* 189 */       if (apnProxyServer.getBytes().length != 0) {
/* 190 */         int apnProxyPort = applySim.getApnProxyPort().intValue();
/* 191 */         buffer.putUnsignedShort(apnProxyPort);
/*     */       }
/*     */ 
/* 194 */       AuthType apnAuthType = applySim.getApnAuthType();
/* 195 */       buffer.putUnsigned(apnAuthType.getValue());
/*     */ 
/* 198 */       String apnAuthServer = applySim.getApnAuthServer();
/* 199 */       buffer.putUnsigned(apnAuthServer.getBytes().length);
/* 200 */       buffer.put(apnAuthServer.getBytes());
/*     */ 
/* 203 */       String apnAuthName = applySim.getApnAuthName();
/* 204 */       buffer.putUnsigned(apnAuthName.getBytes().length);
/* 205 */       buffer.put(apnAuthName.getBytes());
/*     */ 
/* 207 */       String apnAuthPassword = applySim.getApnAuthPassword();
/* 208 */       buffer.putUnsigned(apnAuthPassword.getBytes().length);
/* 209 */       buffer.put(apnAuthPassword.getBytes());
/*     */ 
/* 211 */       int dataCdrSyncInterval = applySim.getDataCdrSyncInterval().intValue();
/* 212 */       buffer.putUnsignedInt(dataCdrSyncInterval);
/*     */ 
/* 214 */       int dataCdrSyncThreshold = applySim.getDataCdrSyncThreshold().intValue();
/* 215 */       buffer.putUnsignedInt(dataCdrSyncThreshold);
/*     */ 
/* 217 */       int dataCdrSaveInterval = applySim.getDataCdrSaveInterval().intValue();
/* 218 */       buffer.putUnsignedInt(dataCdrSaveInterval);
/*     */ 
/* 220 */       int dataCdrSaveThreshold = applySim.getDataCdrSaveThreshold().intValue();
/* 221 */       buffer.putUnsignedInt(dataCdrSaveThreshold);
/*     */ 
/* 223 */       int callCdrSaveInterval = applySim.getCallCdrSaveInterval().intValue();
/* 224 */       buffer.putUnsignedInt(callCdrSaveInterval);
/*     */ 
/* 226 */       heartbeat = applySim.getHeartbeat().intValue();
/* 227 */       buffer.putUnsignedShort(heartbeat);
/*     */ 
/* 229 */       String heartbeatServerIp = applySim.getHeartbeatServerIp();
/* 230 */       long hbsip = IPv4Util.ipToLong(heartbeatServerIp);
/* 231 */       buffer.putUnsignedInt(hbsip);
/*     */ 
/* 233 */       int heartbeatServerPort = applySim.getHeartbeatServerPort().intValue();
/* 234 */       buffer.putUnsignedShort(heartbeatServerPort);
/*     */ 
/* 236 */       String cdrServerIp = applySim.getCdrServerIp();
/* 237 */       long csip = IPv4Util.ipToLong(cdrServerIp);
/* 238 */       buffer.putUnsignedInt(csip);
/*     */ 
/* 240 */       int cdrServerPort = applySim.getCdrServerPort().intValue();
/* 241 */       buffer.putUnsignedShort(cdrServerPort);
/*     */ 
/* 243 */       String apduServerIp = applySim.getApduServerIp();
/* 244 */       long asip = IPv4Util.ipToLong(apduServerIp);
/* 245 */       buffer.putUnsignedInt(asip);
/*     */ 
/* 247 */       int apduServerPort = applySim.getApduServerPort().intValue();
/* 248 */       buffer.putUnsignedShort(apduServerPort);
/*     */ 
/* 250 */       buffer.flip();
/*     */ 
/* 252 */       Token tokenRecord = new Token();
/* 253 */       tokenRecord.setCreateTime(new Date());
/* 254 */       tokenRecord.setSimId(simId + "");
/* 255 */       tokenRecord.setSimPackageId(Long.valueOf(simPackageId));
/* 256 */       tokenRecord.setTerminalId(Long.valueOf(keyIndex));
/* 257 */       tokenRecord.setUserId(Long.valueOf(userId));
/* 258 */       tokenRecord.setValue(token);
/* 259 */       tokenRecord.setUserPackageId(Long.valueOf(userPackageId));
/* 260 */       tokenRecord.setStatus(Integer.valueOf(0));
/* 261 */       TerminalDao terminalDao = (TerminalDao)SpringContextUtil.getBean("terminalDao");
/* 262 */       Terminal terminal = terminalDao.findTerminalById(keyIndex);
/* 263 */       String sn = terminal.getSn();
/* 264 */       tokenRecord.setSn(sn);
/*     */ 
/* 266 */       TokenDao tokenDao = (TokenDao)SpringContextUtil.getBean("tokenDao");
/*     */ 
/* 268 */       tokenDao.saveOrUpdate(tokenRecord);
/*     */ 
/* 271 */       Request requestHbDto = new Request();
/* 272 */       requestHbDto.setId(1L);
/* 273 */       requestHbDto.setMethod("applySimNotice");
/* 274 */       HeartBeatRequestDto hbDto = new HeartBeatRequestDto();
/* 275 */       hbDto.setApplyTime(new Date());
/* 276 */       hbDto.setHeartBeatInterval(Integer.valueOf(heartbeat));
/* 277 */       hbDto.setToken(token);
/* 278 */       hbDto.setDelayTime(delayTime);
/* 279 */       requestHbDto.setParams(hbDto);
/*     */ 
/* 281 */       HeartBeatHandler hbHandler = new HeartBeatHandler(requestHbDto);
/* 282 */       HeartBeatFactory factory = new HeartBeatFactory(hbHandler);
/* 283 */       factory.handler();
/*     */     } else {
/* 285 */       buffer.flip();
/*     */     }
/* 287 */     responseBody = IoBufferUtil.ioBufferToByte(buffer);
/* 288 */     Body body = new Body();
/* 289 */     body.setSubBody(responseBody);
/* 290 */     body.setOrderCode(6);
/* 291 */     body.countCheckCode();
/*     */ 
/* 293 */     responseDto.setBody(body.toBytes());
/* 294 */     responseDto.setBodyLength(body.toBytes().length);
/*     */ 
/* 296 */     getCallback().doCallBack(responseDto);
/* 297 */     log.debug("========user apply sim callBack successed=======");
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.ApplySimConnectHandler
 * JD-Core Version:    0.6.1
 */