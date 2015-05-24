package com.simo.boss.protocol.dto;

import com.simo.boss.protocol.Body;
import com.simo.boss.util.Config;
import com.simo.boss.util.DesUtil;
import com.simo.boss.util.IoBufferUtil;
import java.util.zip.CRC32;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

@SuppressWarnings("unused")
public class CommonDto
{
  private static final Logger log = Logger.getLogger(CommonDto.class);

  private short version = 1;
  private long keyIndex;
  private int bodyLength;
  private byte[] body;

  public  CommonDto()
  {

  }
  public  CommonDto(long keyIndex,byte[] data)
  {
    this.setKeyIndex(keyIndex);
    this.setBody(data);
    this.setBodyLength(data.length);
  }
  public short getVersion()
  {
    return this.version;
  }
  public void setVersion(short version) {
    this.version = version;
  }
  public int getBodyLength() {
    return this.bodyLength;
  }
  public void setBodyLength(int bodyLength) {
    this.bodyLength = bodyLength;
  }
  public byte[] getBody() {
    return this.body;
  }
  public void setBody(byte[] body) {
    this.body = body;
  }

  public long getKeyIndex() {
    return this.keyIndex;
  }
  public void setKeyIndex(long keyIndex) {
    this.keyIndex = keyIndex;
  }

  public void decDecryptBody(byte[] key)
  {
    if (this.keyIndex > 0L)
    {
      byte[] temp = DesUtil.createDecryptor(key, this.body);
      setBodyLength(temp.length);
      setBody(temp);
      log.info("=====解密成功======");
    }
  }

  public void decEncryptBody(byte[] key)
  {
    if (this.keyIndex > 0L)
    {
      byte[] enBody = DesUtil.createEncryptor(key, this.body);
      setBody(enBody);
      setBodyLength(enBody.length);
      log.info("=====加密成功======");
    }
  }

  public boolean validateCheckCode()
  {
    boolean flag = false;
    IoBuffer buffer = IoBufferUtil.byteToIoBuffer(this.body);
    byte[] temp = new byte[this.bodyLength - 4];
    buffer.get(temp);
    long checkCode = buffer.getUnsignedInt();
    buffer.flip();
    CRC32 crc = new CRC32();
    crc.update(temp);
    long value = crc.getValue();
    log.debug("========CRC32计算出的校验码========>>" + value);
    log.debug("========消息中传递的校验码========>>" + checkCode);
    if (value == checkCode) {
      flag = true;
    }
    return flag;
  }

  public static CommonDto newInstanceCommonDto(int errorCode, int orderCode, long keyIndex)
  {
    CommonDto responseCommonDto = new CommonDto(1,null);
    responseCommonDto.setKeyIndex(keyIndex);
    Body body = new Body(null);
    body.setOrderCode(orderCode);
    IoBuffer buffer = IoBuffer.allocate(10).setAutoExpand(true);
    buffer.putUnsignedInt(errorCode);
    buffer.flip();
    body.setSubBody(IoBufferUtil.ioBufferToByte(buffer));
    body.countCheckCode();
    responseCommonDto.setBody(body.toBytes());
    responseCommonDto.setBodyLength(body.toBytes().length);
    return responseCommonDto;
  }
}
