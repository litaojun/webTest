package com.simo.boss.protocol.rsp;
import org.apache.mina.core.buffer.IoBuffer;
import com.simo.boss.util.Constant;
import com.simo.boss.util.DesUtil;
import com.simo.boss.util.HexUtil;
import com.simo.boss.util.IPv4Util;
import com.simo.boss.util.IoBufferUtil;

import com.simo.boss.protocol.RspClass;

public class ApplySimRsp extends RspClass
{
	public ApplySimRsp(byte[] kdc)
	{
	   for (int i=0 ;i<8 ;i++)
	   {
		   keys[i] = kdc[i];
	   }
	}
	  private byte[] keys = new byte[8];
	  private String token;
	  private String imsi;
	  private int simId;
	  private long userId;
	  private long simPackageId;
	  private long userPackageId;
	  private String mcc;
	  private String mnc;
	  private Constant.ServiceType serviceType;
	  private Constant.NetworkType simType;
	  private String phoneNumber;
	  private String simAddress;
	  private String iccid;
	  private String smsp;
	  private String hplmn;
	  private String acc;
	  private String ad;
	  private String apnName;
	  private String apnPassword;
	  private Integer apnProxyPort;
	  private String apnProxyServer;
	  private Constant.AuthType apnAuthType;
	  private String apnAuthName;
	  private String apnAuthPassword;
	  private String apnAuthServer;
	  private String apn;
	  private Integer dataCdrSyncInterval;
	  private Integer dataCdrSyncThreshold;
	  private Integer dataCdrSaveInterval;
	  private Integer dataCdrSaveThreshold;
	  private Integer callCdrSaveInterval;
	  private Integer heartbeat;
	  private String heartbeatServerIp;
	  private Integer heartbeatServerPort;
	  private Integer heartbeatDelayTime;
	  private String cdrServerIp;
	  private Integer cdrServerPort;
	  private String apduServerIp;
	  private Integer apduServerPort;

	  public String getImsi()
	  {
	    return this.imsi;
	  }
	  public void setImsi(String imsi) {
	    this.imsi = imsi;
	  }
	  public int getSimId() {
	    return this.simId;
	  }
	  public void setSimId(int simId) {
	    this.simId = simId;
	  }
	  public Integer getHeartbeatDelayTime() {
	    return this.heartbeatDelayTime;
	  }
	  public void setHeartbeatDelayTime(Integer heartbeatDelayTime) {
	    this.heartbeatDelayTime = heartbeatDelayTime;
	  }
	  public long getSimPackageId() {
	    return this.simPackageId;
	  }
	  public void setSimPackageId(long simPackageId) {
	    this.simPackageId = simPackageId;
	  }
	  public String getMcc() {
	    return this.mcc;
	  }
	  public void setMcc(String mcc) {
	    this.mcc = mcc;
	  }
	  public String getMnc() {
	    return this.mnc;
	  }
	  public void setMnc(String mnc) {
	    this.mnc = mnc;
	  }

	  public String getPhoneNumber()
	  {
	    return this.phoneNumber;
	  }
	  public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }
	  public String getSimAddress() {
	    return this.simAddress;
	  }
	  public void setSimAddress(String simAddress) {
	    this.simAddress = simAddress;
	  }
	  public String getIccid() {
	    return this.iccid;
	  }
	  public void setIccid(String iccid) {
	    this.iccid = iccid;
	  }
	  public String getSmsp() {
	    return this.smsp;
	  }
	  public void setSmsp(String smsp) {
	    this.smsp = smsp;
	  }
	  public String getHplmn() {
	    return this.hplmn;
	  }
	  public void setHplmn(String hplmn) {
	    this.hplmn = hplmn;
	  }
	  public String getAcc() {
	    return this.acc;
	  }
	  public void setAcc(String acc) {
	    this.acc = acc;
	  }
	  public String getAd() {
	    return this.ad;
	  }
	  public void setAd(String ad) {
	    this.ad = ad;
	  }
	  public String getApnName() {
	    return this.apnName;
	  }
	  public void setApnName(String apnName) {
	    this.apnName = apnName;
	  }
	  public String getApnPassword() {
	    return this.apnPassword;
	  }
	  public void setApnPassword(String apnPassword) {
	    this.apnPassword = apnPassword;
	  }
	  public Integer getApnProxyPort() {
	    return this.apnProxyPort;
	  }
	  public void setApnProxyPort(Integer apnProxyPort) {
	    this.apnProxyPort = apnProxyPort;
	  }
	  public String getApnProxyServer() {
	    return this.apnProxyServer;
	  }
	  public void setApnProxyServer(String apnProxyServer) {
	    this.apnProxyServer = apnProxyServer;
	  }
	  public Constant.AuthType getApnAuthType() {
	    return this.apnAuthType;
	  }
	  public void setApnAuthType(Constant.AuthType apnAuthType) {
	    this.apnAuthType = apnAuthType;
	  }
	  public String getApnAuthName() {
	    return this.apnAuthName;
	  }
	  public void setApnAuthName(String apnAuthName) {
	    this.apnAuthName = apnAuthName;
	  }
	  public String getApnAuthPassword() {
	    return this.apnAuthPassword;
	  }
	  public void setApnAuthPassword(String apnAuthPassword) {
	    this.apnAuthPassword = apnAuthPassword;
	  }
	  public String getApnAuthServer() {
	    return this.apnAuthServer;
	  }
	  public void setApnAuthServer(String apnAuthServer) {
	    this.apnAuthServer = apnAuthServer;
	  }
	  public String getApn() {
	    return this.apn;
	  }
	  public void setApn(String apn) {
	    this.apn = apn;
	  }
	  public Integer getDataCdrSyncInterval() {
	    return this.dataCdrSyncInterval;
	  }
	  public void setDataCdrSyncInterval(Integer dataCdrSyncInterval) {
	    this.dataCdrSyncInterval = dataCdrSyncInterval;
	  }
	  public Integer getDataCdrSyncThreshold() {
	    return this.dataCdrSyncThreshold;
	  }
	  public void setDataCdrSyncThreshold(Integer dataCdrSyncThreshold) {
	    this.dataCdrSyncThreshold = dataCdrSyncThreshold;
	  }
	  public Integer getDataCdrSaveInterval() {
	    return this.dataCdrSaveInterval;
	  }
	  public void setDataCdrSaveInterval(Integer dataCdrSaveInterval) {
	    this.dataCdrSaveInterval = dataCdrSaveInterval;
	  }
	  public Integer getDataCdrSaveThreshold() {
	    return this.dataCdrSaveThreshold;
	  }
	  public void setDataCdrSaveThreshold(Integer dataCdrSaveThreshold) {
	    this.dataCdrSaveThreshold = dataCdrSaveThreshold;
	  }
	  public Integer getCallCdrSaveInterval() {
	    return this.callCdrSaveInterval;
	  }
	  public void setCallCdrSaveInterval(Integer callCdrSaveInterval) {
	    this.callCdrSaveInterval = callCdrSaveInterval;
	  }
	  public Integer getHeartbeat() {
	    return this.heartbeat;
	  }
	  public void setHeartbeat(Integer heartbeat) {
	    this.heartbeat = heartbeat;
	  }
	  public String getHeartbeatServerIp() {
	    return this.heartbeatServerIp;
	  }
	  public void setHeartbeatServerIp(String heartbeatServerIp) {
	    this.heartbeatServerIp = heartbeatServerIp;
	  }
	  public Integer getHeartbeatServerPort() {
	    return this.heartbeatServerPort;
	  }
	  public void setHeartbeatServerPort(Integer heartbeatServerPort) {
	    this.heartbeatServerPort = heartbeatServerPort;
	  }
	  public String getCdrServerIp() {
	    return this.cdrServerIp;
	  }
	  public void setCdrServerIp(String cdrServerIp) {
	    this.cdrServerIp = cdrServerIp;
	  }
	  public Integer getCdrServerPort() {
	    return this.cdrServerPort;
	  }
	  public void setCdrServerPort(Integer cdrServerPort) {
	    this.cdrServerPort = cdrServerPort;
	  }
	  public String getApduServerIp() {
	    return this.apduServerIp;
	  }
	  public void setApduServerIp(String apduServerIp) {
	    this.apduServerIp = apduServerIp;
	  }
	  public Integer getApduServerPort() {
	    return this.apduServerPort;
	  }
	  public void setApduServerPort(Integer apduServerPort) {
	    this.apduServerPort = apduServerPort;
	  }
	  public long getUserPackageId() {
	    return this.userPackageId;
	  }
	  public void setUserPackageId(long userPackageId) {
	    this.userPackageId = userPackageId;
	  }
	  public String getToken() {
	    return this.token;
	  }
	  public void setToken(String token) {
	    this.token = token;
	  }
	  public long getUserId() {
	    return this.userId;
	  }
	  public void setUserId(long userId) {
	    this.userId = userId;
	  }
	  public Constant.ServiceType getServiceType() {
	    return this.serviceType;
	  }
	  public void setServiceType(Constant.ServiceType serviceType) {
	    this.serviceType = serviceType;
	  }
	  public Constant.NetworkType getSimType() {
	    return this.simType;
	  }
	  public void setSimType(Constant.NetworkType simType) {
	    this.simType = simType;
	  }

	  public String toString() {
	    return "ApplySimResponse [token=" + this.token + ", imsi=" + this.imsi + ", simId=" + this.simId + ", userId=" + this.userId + ", simPackageId=" + this.simPackageId + ", userPackageId=" + this.userPackageId + ", mcc=" + this.mcc + ", mnc=" + this.mnc + ", serviceType=" + this.serviceType + ", simType=" + this.simType + ", phoneNumber=" + this.phoneNumber + ", simAddress=" + this.simAddress + ", iccid=" + this.iccid + ", smsp=" + this.smsp + ", hplmn=" + this.hplmn + ", acc=" + this.acc + ", ad=" + this.ad + ", apnName=" + this.apnName + ", apnPassword=" + this.apnPassword + ", apnProxyPort=" + this.apnProxyPort + ", apnProxyServer=" + this.apnProxyServer + ", apnAuthType=" + this.apnAuthType + ", apnAuthName=" + this.apnAuthName + ", apnAuthPassword=" + this.apnAuthPassword + ", apnAuthServer=" + this.apnAuthServer + ", apn=" + this.apn + ", dataCdrSyncInterval=" + this.dataCdrSyncInterval + ", dataCdrSyncThreshold=" + this.dataCdrSyncThreshold + ", dataCdrSaveInterval=" + this.dataCdrSaveInterval + ", dataCdrSaveThreshold=" + this.dataCdrSaveThreshold + ", callCdrSaveInterval=" + this.callCdrSaveInterval + ", heartbeat=" + this.heartbeat + ", heartbeatServerIp=" + this.heartbeatServerIp + ", heartbeatServerPort=" + this.heartbeatServerPort + ", cdrServerIp=" + this.cdrServerIp + ", cdrServerPort=" + this.cdrServerPort + ", apduServerIp=" + this.apduServerIp + ", apduServerPort=" + this.apduServerPort + "]";
	  }
	  public  RspClass IobufferToClass(byte[] bodydata)
	 {
		  short curlen = 0;
		  byte[] temp = null;
		 // byte[] data = DesUtil.createDecryptor(this.keys, bodydata);
		  byte[] data  = bodydata;
		  IoBuffer io = IoBufferUtil.byteToIoBuffer(data);
		  this.errorcode = io.getInt();
		  //System.out.println("ApplySimRsp--errorcode="+this.errorcode);
		  if(this.errorcode == 0)
		  {
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.token = new String(temp);
			  this.userId = io.getInt();
			  this.userPackageId = io.getInt();
			  short x = io.getUnsigned();
			  this.simId = (int)io.getUnsignedInt();
			  this.simPackageId = io.getUnsignedInt();
			  this.simAddress = String.valueOf(io.getUnsignedInt())+".";
			  this.simAddress = this.simAddress + String.valueOf(io.getUnsignedInt())+".";
			  this.simAddress = this.simAddress + String.valueOf(io.getUnsignedInt())+".";
			  this.simAddress = this.simAddress + String.valueOf(io.getUnsignedInt());
			  //System.out.println("simAddress="+this.simAddress+"simPackageId="+this.simPackageId+"simId="+this.simId);
			  short y = io.getUnsigned();
			  //System.out.println("x="+y);
			 // this.serviceType.value = x;
			  //curlen = io.getUnsigned();
			  curlen = io.get();
			  //System.out.println("curlen="+curlen);
			  temp = new byte[curlen];
			  io.get(temp);
			  this.iccid = HexUtil.toHexString(temp);
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.imsi =  HexUtil.toHexString(temp);
			  //System.out.println("this.iccid="+this.iccid+"imsi="+this.imsi);
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.smsp = HexUtil.toHexString(temp);
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.hplmn =  HexUtil.toHexString(temp);
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.acc =  new String(temp);
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  //System.out.println("hplmn="+this.hplmn+"smsp="+this.smsp+"acc="+this.acc);
			  this.ad =  new String(temp);
			  curlen = 3;
			  temp = new byte[curlen];
			  io.get(temp);
			  this.mcc =  new String(temp);
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.mnc =  new String(temp);
			  //System.out.println("ad="+this.ad+"mcc="+this.mcc+"mnc="+this.mnc);
			  int xcurlen = io.getUnsignedShort();
			  temp = new byte[xcurlen];
			  io.get(temp);
			  this.phoneNumber =  new String(temp);
			  xcurlen = (int)io.getUnsignedInt();
			  temp = new byte[xcurlen];
			  io.get(temp);
			  this.apn =  new String(temp);
			  
			  xcurlen = (int)io.getUnsignedInt();
			  temp = new byte[xcurlen];
			  io.get(temp);
			  this.apduServerIp =  new String(temp);
			  //System.out.println("phoneNumber="+this.phoneNumber+"apn="+this.apn+"apduServerIp="+this.apduServerIp);
			  int apnProxyPort = io.getUnsignedShort();
			  
			  int apnAuthType = (int)io.getUnsigned();
			  
			  curlen = io.get();
			  //System.out.println("apnProxyPort="+apnProxyPort+"apnAuthType="+apnAuthType+"curlen="+curlen);
			  temp = new byte[curlen];
			  io.get(temp);
			  this.apnAuthServer =  new String(temp);
			  
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.apnAuthName =  new String(temp);
			  
			  curlen = io.get();
			  temp = new byte[curlen];
			  io.get(temp);
			  this.apnAuthPassword =  new String(temp);
			  
			  this.dataCdrSyncInterval = io.getUnsignedShort();
			  this.dataCdrSyncThreshold = Integer.valueOf((int)io.getUnsignedInt());
			  this.dataCdrSaveInterval = io.getUnsignedShort();
			  this.dataCdrSaveThreshold = Integer.valueOf((int)io.getUnsignedInt());
			  this.callCdrSaveInterval = Integer.valueOf((int)io.getUnsignedShort());
			  this.heartbeat = Integer.valueOf((int)io.getUnsignedShort());
			  long hbsip = io.getUnsignedInt();
			  this.heartbeatServerIp = IPv4Util.longToIp(hbsip);
			  this.heartbeatServerPort = io.getUnsignedShort();
			  long csip = io.getUnsignedInt();
			  this.cdrServerIp = IPv4Util.longToIp(csip);
			  this.cdrServerPort = io.getUnsignedShort();
			  
			  long asip = io.getUnsignedInt();
			  this.apduServerIp = IPv4Util.longToIp(asip);
			  this.apduServerPort = io.getUnsignedShort();
			  //System.out.println( "ApplySimResponse [token=" + this.token + ", imsi=" + this.imsi + ", simId=" + this.simId + ", userId=" + this.userId + ", simPackageId=" + this.simPackageId + ", userPackageId=" + this.userPackageId + ", mcc=" + this.mcc + ", mnc=" + this.mnc + ", serviceType=" + this.serviceType + ", simType=" + this.simType + ", phoneNumber=" + this.phoneNumber + ", simAddress=" + this.simAddress + ", iccid=" + this.iccid + ", smsp=" + this.smsp + ", hplmn=" + this.hplmn + ", acc=" + this.acc + ", ad=" + this.ad + ", apnName=" + this.apnName + ", apnPassword=" + this.apnPassword + ", apnProxyPort=" + this.apnProxyPort + ", apnProxyServer=" + this.apnProxyServer + ", apnAuthType=" + this.apnAuthType + ", apnAuthName=" + this.apnAuthName + ", apnAuthPassword=" + this.apnAuthPassword + ", apnAuthServer=" + this.apnAuthServer + ", apn=" + this.apn + ", dataCdrSyncInterval=" + this.dataCdrSyncInterval + ", dataCdrSyncThreshold=" + this.dataCdrSyncThreshold + ", dataCdrSaveInterval=" + this.dataCdrSaveInterval + ", dataCdrSaveThreshold=" + this.dataCdrSaveThreshold + ", callCdrSaveInterval=" + this.callCdrSaveInterval + ", heartbeat=" + this.heartbeat + ", heartbeatServerIp=" + this.heartbeatServerIp + ", heartbeatServerPort=" + this.heartbeatServerPort + ", cdrServerIp=" + this.cdrServerIp + ", cdrServerPort=" + this.cdrServerPort + ", apduServerIp=" + this.apduServerIp + ", apduServerPort=" + this.apduServerPort + "]");
		  }
		return this;
	 }
}
