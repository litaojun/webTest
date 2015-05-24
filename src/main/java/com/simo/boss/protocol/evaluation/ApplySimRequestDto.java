package com.simo.boss.protocol.evaluation;

import com.simo.boss.util.IoBufferUtil;
import org.apache.mina.core.buffer.IoBuffer;

public class ApplySimRequestDto
{
  private short usernameLength;
  private String userName;
  private short passwordLength;
  private byte[] password;
  private short mccLength = 3;
  private String mcc;
  private short mncLength;
  private String mnc;
  private short supportedNetwork;
  private short supportedService;

  public ApplySimRequestDto()
  {
  }

  /*public ApplySimRequestDto(byte[] body)
  {
    IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
    this.usernameLength = buffer.getUnsigned();
    if (this.usernameLength > 0) {
      byte[] usernameBytes = new byte[this.usernameLength];
      buffer.get(usernameBytes);
      this.userName = new String(usernameBytes);
    }

    this.passwordLength = buffer.getUnsigned();
    if (this.passwordLength > 0) {
      byte[] passwordBytes = new byte[this.passwordLength];
      buffer.get(passwordBytes);
      this.password = passwordBytes;
    }
    byte[] mccBytes = new byte[this.mccLength];
    buffer.get(mccBytes);
    this.mcc = new String(mccBytes);

    this.mncLength = buffer.getUnsigned();
    if (this.mncLength > 0) {
      byte[] mncBytes = new byte[this.mncLength];
      buffer.get(mncBytes);
      this.mnc = new String(mncBytes);
    }

    this.supportedNetwork = buffer.getUnsigned();
    this.supportedService = buffer.getUnsigned();
    buffer.flip();
  }*/
  public ApplySimRequestDto(String userName,byte[] password,String mcc,String mnc,short supportedNetwork,short supportedService)
  {
	  this.userName = userName;
	  this.password = password;
	  this.mcc = mcc;
	  this.mnc = mnc;
	  this.supportedNetwork = supportedNetwork;
	  this.supportedService = supportedService;
	  this.usernameLength = (short)this.userName.length();
	  this.passwordLength = (short)this.password.length;
	  this.mncLength = (short)this.mnc.length();
  }

  public short getUsernameLength() {
    return this.usernameLength;
  }
  public void setUsernameLength(short usernameLength) {
    this.usernameLength = usernameLength;
  }
  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public short getPasswordLength() {
    return this.passwordLength;
  }
  public void setPasswordLength(short passwordLength) {
    this.passwordLength = passwordLength;
  }
  public byte[] getPassword() {
    return this.password;
  }
  public void setPassword(byte[] password) {
    this.password = password;
  }
  public String getMcc() {
    return this.mcc;
  }
  public void setMcc(String mcc) {
    this.mcc = mcc;
  }
  public short getMncLength() {
    return this.mncLength;
  }
  public void setMncLength(short mncLength) {
    this.mncLength = mncLength;
  }
  public String getMnc() {
    return this.mnc;
  }
  public void setMnc(String mnc) {
    this.mnc = mnc;
  }
  public short getSupportedNetwork() {
    return this.supportedNetwork;
  }
  public void setSupportedNetwork(short supportedNetwork) {
    this.supportedNetwork = supportedNetwork;
  }

  public short getMccLength() {
    return this.mccLength;
  }

  public void setMccLength(short mccLength) {
    this.mccLength = mccLength;
  }

  public short getSupportedService() {
    return this.supportedService;
  }

  public void setSupportedService(short supportedService) {
    this.supportedService = supportedService;
  }
  public byte[] toByte()
  {
	  IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	  buffer.put((byte)this.usernameLength);
	  buffer.put(this.userName.getBytes());
	  buffer.put((byte)this.passwordLength);
	  buffer.put(this.password);
	  buffer.put(this.mcc.getBytes());
	  buffer.put((byte)this.mncLength);
	  buffer.put(this.mnc.getBytes());
	  buffer.put((byte)this.supportedNetwork);
	  buffer.put((byte)this.supportedService);
	  buffer.flip();
	  return IoBufferUtil.ioBufferToByte(buffer);
  }
}
