package com.simo.boss.protocol.rsp;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.protocol.RspClass;
import com.simo.boss.util.DesUtil;
import com.simo.boss.util.IoBufferUtil;

public class TwoMsgRsp  extends RspClass{
	private int termid;
	private String code1;
	private byte kdcrandLen = 0;
	private byte[] kdc = null;
	private short year = 0;
	private byte month = 0;
	private byte day = 0;
	private byte hources = 0;
	private byte ms = 0;
	private byte sencods = 0;
	public TwoMsgRsp(String code1)
	{
		this.code1 = code1;
	}
	public  RspClass IobufferToClass(byte[] bodydata)
	{
		IoBuffer io = IoBufferUtil.byteToIoBuffer(bodydata);
		this.errorcode = io.getInt();
		this.termid = io.getInt();
		this.kdcrandLen = io.get();
		byte[] keys = new byte[this.kdcrandLen];
		io.get(keys);
		kdc = DesUtil.createDecryptor(this.code1.substring(0, 8).getBytes(),keys);
		this.kdcrandLen = (byte)kdc.length;
		this.year = io.getShort();
		this.month = io.get();
		this.day = io.get();
		this.hources = io.get();
		this.ms= io.get();
		this.sencods = io.get();
		io.flip();
		return this;
	}
	public byte[] getKdc()
	{
		return this.kdc;
	}
	public int getTermid()
	{
		return this.termid;
	}
}
