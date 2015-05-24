package com.simo.boss.protocol.coder;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.protocol.dto.CommonDto;
import com.simo.boss.util.BytesUtil;
import com.simo.boss.util.IoBufferUtil;

public class CommonProtocolEncoder extends ProtocolEncoderAdapter {

	//private static final Logger log = Logger.getLogger(CommonProtocolEncoder.class);
	  private final Charset charset;

	  public CommonProtocolEncoder(Charset charset)
	  {
	    this.charset = charset;
	  }

	  public CommonProtocolEncoder() {
	    this(Charset.defaultCharset());
	  }

	  public Charset getCharset() {
	    return this.charset;
	  }

	  public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
	    throws Exception
	  {
	    IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	    CommonDto dto = (CommonDto)message;

	    short version = dto.getVersion();
	    long keyIndex = dto.getKeyIndex();

	    int bodyLength = dto.getBodyLength();
	    byte[] bodyBytes = dto.getBody();
	    buffer.put((byte)version);
	    buffer.putInt((int)keyIndex);
	    buffer.putShort((short)bodyLength);
	    if ((bodyBytes != null) && (bodyBytes.length > 0)) {
	      buffer.put(bodyBytes);
	    }
	    //log.info("==session.id==>>" + session.getId() + "  **==encode==>>version==>>" + version + "==keyIndex==>>" + keyIndex + "==bodyLength==>>" + bodyLength + "==body==>>" + BytesUtil.outPutToString(bodyBytes));
	    buffer.flip();
	    out.write(buffer);
	  }
	  public byte[] encodeByte(Object message)
	    throws Exception
	  {
	    IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	    CommonDto dto = (CommonDto)message;

	    short version = dto.getVersion();
	    long keyIndex = dto.getKeyIndex();

	    int bodyLength = dto.getBodyLength();
	    byte[] bodyBytes = dto.getBody();
	    buffer.put((byte)version);
	    buffer.putInt((int)keyIndex);
	    buffer.putShort((short)bodyLength);
	    //System.out.println("bodylenth="+bodyLength);
	    if ((bodyBytes != null) && (bodyBytes.length > 0)) {
	      buffer.put(bodyBytes);
	    }
	    //log.info("==session.id==>>" + "  **==encode==>>version==>>" + version + "==keyIndex==>>" + keyIndex + "==bodyLength==>>" + bodyLength + "==body==>>" + BytesUtil.outPutToString(bodyBytes));
	    buffer.flip();
	    return IoBufferUtil.ioBufferToByte(buffer);
	  }
	  public CommonDto doDecodeDto(IoBuffer in)
	  {
	    CommonDto dto = new CommonDto();
	    if (in.remaining() > 7) 
	    {
	      in.mark();
	      short version = in.getUnsigned();
	      long keyIndex = in.getUnsignedInt();
	      int bodyLength = in.getUnsignedShort();
	      if (in.remaining() >= bodyLength) 
	      {
	        dto.setKeyIndex(keyIndex);
	        if (bodyLength > 0) 
	        {
	          byte[] bodyBytes = new byte[bodyLength];
	          in.get(bodyBytes);
	          dto.setBody(bodyBytes);
	        }
	        dto.setVersion(version);
	        dto.setBodyLength(bodyLength);

	        //log.info("==session.id==>>" +  "  **==doDecode==>>version==>>" + version + "==keyIndex==>>" + keyIndex + "==bodyLength==>>" + bodyLength + "==body==>>" + BytesUtil.outPutToString(dto.getBody()));
	        return dto;
	      }

	      in.reset();
	      return null;
	    }
	    return null;
	  }

}
