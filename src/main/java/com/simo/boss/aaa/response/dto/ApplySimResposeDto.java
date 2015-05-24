/*     */ package com.simo.boss.aaa.response.dto;
/*     */ 
/*     */ public class ApplySimResposeDto
/*     */ {
/*     */   private long userId;
/*     */   private long userPackage;
/*     */   private long simId;
/*     */   private long simPackage;
/*     */   private short simType;
/*     */   private short iccidLength;
/*     */   private String iccid;
/*     */   private short imsiLength;
/*     */   private String imsi;
/*     */   private short smspLength;
/*     */   private String smsp;
/*     */   private short hplmnLength;
/*     */   private String hplmn;
/*     */   private short accLength;
/*     */   private String acc;
/*     */   private short adLength;
/*     */   private String ad;
/*  38 */   private short mccLength = 3;
/*     */   private String mcc;
/*     */   private short mncLength;
/*     */   private String mnc;
/*     */   private int numberLength;
/*     */   private String number;
/*     */   private long apnNameLength;
/*     */   private String apnName;
/*     */   private long apnLength;
/*     */   private String apn;
/*     */   private long apnProxyServerLength;
/*     */   private String apnProxyServer;
/*     */   private int apnProxyPort;
/*     */   private short apnAuthType;
/*     */   private short apnAuthServerLength;
/*     */   private String apnAuthServer;
/*     */   private short apnAuthNameLength;
/*     */   private String apnAuthName;
/*     */   private short apnAuthPasswordLength;
/*     */   private String apnAuthPassword;
/*     */   private long dataAvailable;
/*     */   private long cdrSyncInterval;
/*     */   private long cdrSyncThreshold;
/*     */   private long cdrSaveInterval;
/*     */   private long cdrSaveThreshold;
/*     */   private int heartbeat;
/*     */ 
/*     */   public long getUserId()
/*     */   {
/*  67 */     return this.userId;
/*     */   }
/*     */   public void setUserId(long userId) {
/*  70 */     this.userId = userId;
/*     */   }
/*     */   public long getUserPackage() {
/*  73 */     return this.userPackage;
/*     */   }
/*     */   public void setUserPackage(long userPackage) {
/*  76 */     this.userPackage = userPackage;
/*     */   }
/*     */   public long getSimId() {
/*  79 */     return this.simId;
/*     */   }
/*     */   public void setSimId(long simId) {
/*  82 */     this.simId = simId;
/*     */   }
/*     */   public long getSimPackage() {
/*  85 */     return this.simPackage;
/*     */   }
/*     */   public void setSimPackage(long simPackage) {
/*  88 */     this.simPackage = simPackage;
/*     */   }
/*     */   public int getNumberLength() {
/*  91 */     return this.numberLength;
/*     */   }
/*     */   public void setNumberLength(int numberLength) {
/*  94 */     this.numberLength = numberLength;
/*     */   }
/*     */   public String getNumber() {
/*  97 */     return this.number;
/*     */   }
/*     */   public void setNumber(String number) {
/* 100 */     this.number = number;
/*     */   }
/*     */   public long getApnNameLength() {
/* 103 */     return this.apnNameLength;
/*     */   }
/*     */   public void setApnNameLength(long apnNameLength) {
/* 106 */     this.apnNameLength = apnNameLength;
/*     */   }
/*     */   public String getApnName() {
/* 109 */     return this.apnName;
/*     */   }
/*     */   public void setApnName(String apnName) {
/* 112 */     this.apnName = apnName;
/*     */   }
/*     */   public long getApnLength() {
/* 115 */     return this.apnLength;
/*     */   }
/*     */   public void setApnLength(long apnLength) {
/* 118 */     this.apnLength = apnLength;
/*     */   }
/*     */   public String getApn() {
/* 121 */     return this.apn;
/*     */   }
/*     */   public void setApn(String apn) {
/* 124 */     this.apn = apn;
/*     */   }
/*     */   public short getSimType() {
/* 127 */     return this.simType;
/*     */   }
/*     */   public void setSimType(short simType) {
/* 130 */     this.simType = simType;
/*     */   }
/*     */   public short getIccidLength() {
/* 133 */     return this.iccidLength;
/*     */   }
/*     */   public void setIccidLength(short iccidLength) {
/* 136 */     this.iccidLength = iccidLength;
/*     */   }
/*     */   public String getIccid() {
/* 139 */     return this.iccid;
/*     */   }
/*     */   public void setIccid(String iccid) {
/* 142 */     this.iccid = iccid;
/*     */   }
/*     */   public short getImsiLength() {
/* 145 */     return this.imsiLength;
/*     */   }
/*     */   public void setImsiLength(short imsiLength) {
/* 148 */     this.imsiLength = imsiLength;
/*     */   }
/*     */   public String getImsi() {
/* 151 */     return this.imsi;
/*     */   }
/*     */   public void setImsi(String imsi) {
/* 154 */     this.imsi = imsi;
/*     */   }
/*     */   public short getSmspLength() {
/* 157 */     return this.smspLength;
/*     */   }
/*     */   public void setSmspLength(short smspLength) {
/* 160 */     this.smspLength = smspLength;
/*     */   }
/*     */   public String getSmsp() {
/* 163 */     return this.smsp;
/*     */   }
/*     */   public void setSmsp(String smsp) {
/* 166 */     this.smsp = smsp;
/*     */   }
/*     */   public short getHplmnLength() {
/* 169 */     return this.hplmnLength;
/*     */   }
/*     */   public void setHplmnLength(short hplmnLength) {
/* 172 */     this.hplmnLength = hplmnLength;
/*     */   }
/*     */   public String getHplmn() {
/* 175 */     return this.hplmn;
/*     */   }
/*     */   public void setHplmn(String hplmn) {
/* 178 */     this.hplmn = hplmn;
/*     */   }
/*     */   public short getAccLength() {
/* 181 */     return this.accLength;
/*     */   }
/*     */   public void setAccLength(short accLength) {
/* 184 */     this.accLength = accLength;
/*     */   }
/*     */   public String getAcc() {
/* 187 */     return this.acc;
/*     */   }
/*     */   public void setAcc(String acc) {
/* 190 */     this.acc = acc;
/*     */   }
/*     */   public short getAdLength() {
/* 193 */     return this.adLength;
/*     */   }
/*     */   public void setAdLength(short adLength) {
/* 196 */     this.adLength = adLength;
/*     */   }
/*     */   public String getAd() {
/* 199 */     return this.ad;
/*     */   }
/*     */   public void setAd(String ad) {
/* 202 */     this.ad = ad;
/*     */   }
/*     */   public short getMccLength() {
/* 205 */     return this.mccLength;
/*     */   }
/*     */   public void setMccLength(short mccLength) {
/* 208 */     this.mccLength = mccLength;
/*     */   }
/*     */   public String getMcc() {
/* 211 */     return this.mcc;
/*     */   }
/*     */   public void setMcc(String mcc) {
/* 214 */     this.mcc = mcc;
/*     */   }
/*     */   public short getMncLength() {
/* 217 */     return this.mncLength;
/*     */   }
/*     */   public void setMncLength(short mncLength) {
/* 220 */     this.mncLength = mncLength;
/*     */   }
/*     */   public String getMnc() {
/* 223 */     return this.mnc;
/*     */   }
/*     */   public void setMnc(String mnc) {
/* 226 */     this.mnc = mnc;
/*     */   }
/*     */   public long getApnProxyServerLength() {
/* 229 */     return this.apnProxyServerLength;
/*     */   }
/*     */   public void setApnProxyServerLength(long apnProxyServerLength) {
/* 232 */     this.apnProxyServerLength = apnProxyServerLength;
/*     */   }
/*     */   public String getApnProxyServer() {
/* 235 */     return this.apnProxyServer;
/*     */   }
/*     */   public void setApnProxyServer(String apnProxyServer) {
/* 238 */     this.apnProxyServer = apnProxyServer;
/*     */   }
/*     */   public int getApnProxyPort() {
/* 241 */     return this.apnProxyPort;
/*     */   }
/*     */   public void setApnProxyPort(int apnProxyPort) {
/* 244 */     this.apnProxyPort = apnProxyPort;
/*     */   }
/*     */   public short getApnAuthType() {
/* 247 */     return this.apnAuthType;
/*     */   }
/*     */   public void setApnAuthType(short apnAuthType) {
/* 250 */     this.apnAuthType = apnAuthType;
/*     */   }
/*     */   public short getApnAuthServerLength() {
/* 253 */     return this.apnAuthServerLength;
/*     */   }
/*     */   public void setApnAuthServerLength(short apnAuthServerLength) {
/* 256 */     this.apnAuthServerLength = apnAuthServerLength;
/*     */   }
/*     */   public String getApnAuthServer() {
/* 259 */     return this.apnAuthServer;
/*     */   }
/*     */   public void setApnAuthServer(String apnAuthServer) {
/* 262 */     this.apnAuthServer = apnAuthServer;
/*     */   }
/*     */   public short getApnAuthNameLength() {
/* 265 */     return this.apnAuthNameLength;
/*     */   }
/*     */   public void setApnAuthNameLength(short apnAuthNameLength) {
/* 268 */     this.apnAuthNameLength = apnAuthNameLength;
/*     */   }
/*     */   public String getApnAuthName() {
/* 271 */     return this.apnAuthName;
/*     */   }
/*     */   public void setApnAuthName(String apnAuthName) {
/* 274 */     this.apnAuthName = apnAuthName;
/*     */   }
/*     */   public short getApnAuthPasswordLength() {
/* 277 */     return this.apnAuthPasswordLength;
/*     */   }
/*     */   public void setApnAuthPasswordLength(short apnAuthPasswordLength) {
/* 280 */     this.apnAuthPasswordLength = apnAuthPasswordLength;
/*     */   }
/*     */   public String getApnAuthPassword() {
/* 283 */     return this.apnAuthPassword;
/*     */   }
/*     */   public void setApnAuthPassword(String apnAuthPassword) {
/* 286 */     this.apnAuthPassword = apnAuthPassword;
/*     */   }
/*     */   public long getDataAvailable() {
/* 289 */     return this.dataAvailable;
/*     */   }
/*     */   public void setDataAvailable(long dataAvailable) {
/* 292 */     this.dataAvailable = dataAvailable;
/*     */   }
/*     */   public long getCdrSyncInterval() {
/* 295 */     return this.cdrSyncInterval;
/*     */   }
/*     */   public void setCdrSyncInterval(long cdrSyncInterval) {
/* 298 */     this.cdrSyncInterval = cdrSyncInterval;
/*     */   }
/*     */   public long getCdrSyncThreshold() {
/* 301 */     return this.cdrSyncThreshold;
/*     */   }
/*     */   public void setCdrSyncThreshold(long cdrSyncThreshold) {
/* 304 */     this.cdrSyncThreshold = cdrSyncThreshold;
/*     */   }
/*     */   public long getCdrSaveInterval() {
/* 307 */     return this.cdrSaveInterval;
/*     */   }
/*     */   public void setCdrSaveInterval(long cdrSaveInterval) {
/* 310 */     this.cdrSaveInterval = cdrSaveInterval;
/*     */   }
/*     */   public long getCdrSaveThreshold() {
/* 313 */     return this.cdrSaveThreshold;
/*     */   }
/*     */   public void setCdrSaveThreshold(long cdrSaveThreshold) {
/* 316 */     this.cdrSaveThreshold = cdrSaveThreshold;
/*     */   }
/*     */   public int getHeartbeat() {
/* 319 */     return this.heartbeat;
/*     */   }
/*     */   public void setHeartbeat(int heartbeat) {
/* 322 */     this.heartbeat = heartbeat;
/*     */   }
/*     */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.response.dto.ApplySimResposeDto
 * JD-Core Version:    0.6.1
 */