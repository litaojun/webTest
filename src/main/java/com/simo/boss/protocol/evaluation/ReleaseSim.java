package com.simo.boss.protocol.evaluation;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.util.IoBufferUtil;

public class ReleaseSim 
{
   private String tokent = null;
   private byte tokentLen = 0;
   private byte reason = 0;
   public ReleaseSim()
   {
   }
   public ReleaseSim(String tok,byte reason)
   {
	   this.tokent = tok;
	   this.tokentLen = (byte)this.tokent.getBytes().length;
	   this.reason = reason;
   }
   public byte[] toByte()
   {
	   IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	   buffer.put(this.tokentLen);
	   buffer.put(this.tokent.getBytes());
	   buffer.put(this.reason);
	   buffer.flip();
	   return IoBufferUtil.ioBufferToByte(buffer);
   }
}
