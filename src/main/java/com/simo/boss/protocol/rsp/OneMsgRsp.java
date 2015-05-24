package com.simo.boss.protocol.rsp;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.protocol.RspClass;
import com.simo.boss.util.BytesUtil;
import com.simo.boss.util.IoBufferUtil;

public class OneMsgRsp  extends RspClass{
	private int termid = 0;
	private byte randomlen = 0;
	private String randomStr = null;
	private byte[] random = null;
	public OneMsgRsp(int tid,byte randlen,String randStr)
	{
		this.termid = tid;
		this.randomlen = randlen;
		this.randomStr = randStr;
	}
	public OneMsgRsp()
	{
	}
	public int getTermId()
	{
		return this.termid;
	}
	public String getRandomStr()
	{
		return this.randomStr;
	}
	public short getRandomlen()
	{
		return this.randomlen;
	}
	public byte[] getRandom()
	{
		return this.random;
	}
	public  RspClass IobufferToClass(byte[] bodydata)
	{
		 //System.out.println("BytesUtil.outPutToString(bodydata)="+BytesUtil.outPutToString(bodydata));
		IoBuffer io = IoBufferUtil.byteToIoBuffer(bodydata);
		this.errorcode = io.getInt();
		//System.out.println("this.errorcode="+this.errorcode);
		if(this.errorcode == 0)
		{
			this.termid = io.getInt();
			this.randomlen = io.get();
			this.random = new byte[this.randomlen];
			io.get(this.random);
			//System.out.println("BytesUtil.outPutToString(a)="+BytesUtil.outPutToString(a));
			//this.randomStr = new String(a);
			this.randomStr = BytesUtil.bytes2String(this.random, 0, this.random.length);
			//System.out.println("BytesUtil.outPutToString(randomStr)="+BytesUtil.outPutToString(BytesUtil.stringToBytes(this.randomStr)));
			//System.out.println("len="+this.randomlen+"errorcode = "+this.errorcode+"termid= "+this.termid+"randomStr="+this.randomStr+",len="+BytesUtil.stringToBytes(this.randomStr).length);
		    //this.checkcode = io.getInt();
		}
		io.flip();
		return this;
	}
	public static void main(String[] args)
	{
		String a = "litaojun12345678";
		byte[] b = a.getBytes();
		System.out.println("a="+BytesUtil.outPutToString(b));
		String c = new String(b);
		System.out.println("a="+BytesUtil.outPutToString(c.getBytes()));
		
	}
}
