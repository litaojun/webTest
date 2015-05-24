package com.simo.boss.protocol.rsp;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.protocol.RspClass;
import com.simo.boss.util.BytesUtil;
import com.simo.boss.util.IoBufferUtil;

public class AuthSimRsp extends RspClass {
	private String apdu;
	private short apduLen;
	public AuthSimRsp()
	{
		
	}

	@Override
	public RspClass IobufferToClass(byte[] bodydata) {
		// TODO Auto-generated method stub
		 //System.out.println("BytesUtil.outPutToString(bodydata)="+BytesUtil.outPutToString(bodydata));
		 IoBuffer io = IoBufferUtil.byteToIoBuffer(bodydata);
		 this.errorcode = io.getInt();
		 //System.out.println("this.errorcode="+this.errorcode);
		 if(this.errorcode == 0)
		 {
			  this.apduLen = (short) io.getUnsignedShort();
			  byte[] body = new byte[this.apduLen];
			  this.apdu = new String(body);
		 }
		return null;
	}
	public String toString()
	{
	   return "string apdu="+this.apdu+"apdulen="+this.apduLen;	
	}

}
