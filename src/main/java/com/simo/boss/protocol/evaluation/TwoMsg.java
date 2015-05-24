package com.simo.boss.protocol.evaluation;
import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.util.DesUtil;
import com.simo.boss.util.IoBufferUtil;

public class TwoMsg 
{
   private int termid=0;

   private short ranmlen = 0;

   private String randomStr="";
   private byte[] keys;
   private byte[] random = null;
   public TwoMsg(int termid,String randomstr,String code1,byte[] random)
   {
	  this.termid = termid;
	  this.randomStr = randomstr;
	  this.keys = code1.getBytes();
	  this.random = random;
   }
   public void setTermid(int id)
   {
	   this.termid = id;
   }
   public void setRandom(String str)
   {
	   this.randomStr = str;
   }
   public byte[] toByte()
   {
	  //System.out.println("TwoMsg--tobyte keyslen="+this.keys.length+"randomStrlen="+this.randomStr.getBytes().length);
	  byte[] bodydata =  DesUtil.createEncryptor(this.keys,this.random);
	  IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	  buffer.putInt(this.termid);
	  //System.out.println("bodydatalen = "+bodydata.length);
	  buffer.put((byte)bodydata.length);
	  buffer.put(bodydata);
	  buffer.flip();
	  return IoBufferUtil.ioBufferToByte(buffer);
   }
}
