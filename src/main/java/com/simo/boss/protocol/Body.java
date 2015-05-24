package com.simo.boss.protocol;

import com.simo.boss.util.IoBufferUtil;
import java.util.zip.CRC32;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

public class Body
{
  private static final Logger log = Logger.getLogger(Body.class);
  private int orderCode;
  private byte[] subBody;
  private long checkCode;

  public Body(int code,byte[] databody)
  {
	  this.orderCode = code;
	  this.subBody = databody;
	  this.setCheckCode();
  }

  public Body(byte[] body)
  {
    int bodyLength = body.length;
    IoBuffer buffer = IoBufferUtil.byteToIoBuffer(body);
    this.orderCode = buffer.getUnsignedShort();
    this.subBody = new byte[bodyLength - 6];
    buffer.get(this.subBody);
    this.checkCode = buffer.getUnsignedInt();
    buffer.flip();
  }

  public Body() {
	// TODO Auto-generated constructor stub
}



public void countCheckCode()
  {
    if ((this.orderCode == 0) || (this.subBody == null)) {
      throw new RuntimeException("信息不完整，不能生成校验码");
    }
    IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
    buffer.putShort((short)this.orderCode);
    buffer.put(this.subBody);
    buffer.flip();
    byte[] temp = IoBufferUtil.ioBufferToByte(buffer);
    CRC32 crc = new CRC32();
    crc.update(temp);
    log.debug("=========count checkCode==========>>" + crc.getValue());
    this.checkCode = crc.getValue();
  }
  public void setCheckCode()
  {
	  IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	  buffer.putShort((short)this.orderCode);
	  buffer.put(this.subBody);
	  buffer.flip();
	  byte[] temp = IoBufferUtil.ioBufferToByte(buffer);
	  CRC32 crc = new CRC32();
	  crc.update(temp);
	  log.debug("=========count checkCode==========>>" + crc.getValue());
	  this.checkCode = crc.getValue();
  }

  public byte[] toBytes() {
    IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
    buffer.putShort((short)this.orderCode);
    buffer.put(this.subBody);
    buffer.putInt((int)this.checkCode);
    buffer.flip();
    return IoBufferUtil.ioBufferToByte(buffer);
  }

  public int getOrderCode() {
    return this.orderCode;
  }
  public void setOrderCode(int orderCode) {
    this.orderCode = orderCode;
  }
  public byte[] getSubBody() {
    return this.subBody;
  }
  public void setSubBody(byte[] subBody) {
    this.subBody = subBody;
  }
  public long getCheckCode() {
    return this.checkCode;
  }
  public void setCheckCode(long checkCode) {
    this.checkCode = checkCode;
  }
}