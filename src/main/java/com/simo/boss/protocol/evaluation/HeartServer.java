package com.simo.boss.protocol.evaluation;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.util.IoBufferUtil;

public class HeartServer {
	  String token =null;
	  byte tokenlen=0;
      public HeartServer(String token)
      {
    	  this.token = token;
    	  this.tokenlen = (byte)this.token.getBytes().length;
      }
      public byte[] toByte()
      {
    	  IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
    	  buffer.putUnsigned(this.tokenlen);
    	  buffer.put(this.token.getBytes());
    	  buffer.flip();
  	      return IoBufferUtil.ioBufferToByte(buffer);
      }
}
