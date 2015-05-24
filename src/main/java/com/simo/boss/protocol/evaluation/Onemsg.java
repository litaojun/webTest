package com.simo.boss.protocol.evaluation;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.util.IoBufferUtil;

public class Onemsg {
   private String sn=null;
   private int snlen=0;
   public Onemsg(String sn)
   {
	   this.sn = sn;
	   this.snlen = this.sn.getBytes().length;
   }
   public void setSn(String sn)
   {
	   this.sn = sn;
	   this.snlen = sn.length();
   }
   public byte[] toByte()
   {
	   IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	    buffer.put((byte)this.snlen);
	    buffer.put(this.sn.getBytes());
	    buffer.flip();
	    return IoBufferUtil.ioBufferToByte(buffer);
   }
   
}
