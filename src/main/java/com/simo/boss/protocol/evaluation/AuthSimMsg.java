package com.simo.boss.protocol.evaluation;

import org.apache.commons.codec.binary.Base64;
import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.util.IoBufferUtil;

public class AuthSimMsg {
	  private short tokenLength;
	  private String token;
	  private long vsimAddress;
	  private long simbankAddress;
	  private long bladeLogicalAddress;
	  private long simAddress;
	  private short simType;
	  private long apduLength;
	  private String apdu;

	  public AuthSimMsg(String token,String simaddress,short simType,String apdu)
	  {
		  this.token = token;
		  System.out.println("AuthSimMsg-AuthSimMsg-token="+this.token);
		  this.tokenLength = (short)this.token.getBytes().length;
		  //System.out.println("simaddress="+simaddress);
		  String[] a = simaddress.split("\\.");
		  this.simType = simType;
		  if(a !=null)
			  System.out.println("len="+a.length);
		  this.vsimAddress = Long.parseLong(a[0]);
		  this.simbankAddress = Long.parseLong(a[1]);
		  this.bladeLogicalAddress = Long.parseLong(a[2]);
		  this.simAddress = Long.parseLong(a[3]);
		  this.apdu = apdu;
		  this.apduLength = Base64.decodeBase64(this.apdu).length;
	  }
	  public byte[] toByte()
	  {
		  IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
		  buffer.put((byte)this.tokenLength);
		  buffer.put(this.token.getBytes());
		  buffer.putUnsignedInt(this.vsimAddress);
		  buffer.putUnsignedInt(this.simbankAddress);
		  buffer.putUnsignedInt(this.bladeLogicalAddress);
		  buffer.putUnsignedInt(this.simAddress);
		  buffer.putUnsigned(this.simType);
		  buffer.putUnsignedShort(this.apduLength);
		  //buffer.put(this.apdu.getBytes());
		  buffer.put(Base64.decodeBase64(this.apdu));
		  buffer.flip();
		  return IoBufferUtil.ioBufferToByte(buffer);
	  }
	  public AuthSimMsg(byte[] body)
	  {
	    IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
	    this.tokenLength = buffer.getUnsigned();
	    if (this.tokenLength > 0) {
	      byte[] tokenBytes = new byte[this.tokenLength];
	      buffer.get(tokenBytes);
	      this.token = new String(tokenBytes);
	    }
	    this.vsimAddress = buffer.getUnsignedInt();
	    this.simbankAddress = buffer.getUnsignedInt();
	    this.bladeLogicalAddress = buffer.getUnsignedInt();
	    this.simAddress = buffer.getUnsignedInt();
	    this.simType = buffer.getUnsigned();

	    this.apduLength = buffer.getUnsignedInt();
	    if (this.apduLength > 0L) {
	      byte[] apduBytes = new byte[(int)this.apduLength];
	      buffer.get(apduBytes);
	      this.apdu = new String(apduBytes);
	    }
	    buffer.flip();
	  }

	  public short getTokenLength() {
	    return this.tokenLength;
	  }

	  public void setTokenLength(short tokenLength) {
	    this.tokenLength = tokenLength;
	  }

	  public String getToken() {
	    return this.token;
	  }

	  public void setToken(String token) {
	    this.token = token;
	  }

	  public long getVsimAddress() {
	    return this.vsimAddress;
	  }

	  public void setVsimAddress(long vsimAddress) {
	    this.vsimAddress = vsimAddress;
	  }

	  public long getSimbankAddress() {
	    return this.simbankAddress;
	  }

	  public void setSimbankAddress(long simbankAddress) {
	    this.simbankAddress = simbankAddress;
	  }

	  public long getBladeLogicalAddress() {
	    return this.bladeLogicalAddress;
	  }

	  public void setBladeLogicalAddress(long bladeLogicalAddress) {
	    this.bladeLogicalAddress = bladeLogicalAddress;
	  }

	  public long getSimAddress() {
	    return this.simAddress;
	  }

	  public void setSimAddress(long simAddress) {
	    this.simAddress = simAddress;
	  }

	  public short getSimType() {
	    return this.simType;
	  }

	  public void setSimType(short simType) {
	    this.simType = simType;
	  }

	  public long getApduLength() {
	    return this.apduLength;
	  }

	  public void setApduLength(long apduLength) {
	    this.apduLength = apduLength;
	  }

	  public String getApdu() {
	    return this.apdu;
	  }

	  public void setApdu(String apdu) {
	    this.apdu = apdu;
	  }
}
