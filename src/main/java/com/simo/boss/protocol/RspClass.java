package com.simo.boss.protocol;
import com.simo.boss.util.IoBufferUtil;
import org.apache.mina.core.buffer.IoBuffer;

@SuppressWarnings("unused")
public abstract class RspClass {
    public int errorcode = 0;
    public int checkcode =0;
    public abstract RspClass IobufferToClass(byte[] bodydata);
    public int getErrorcode()
    {
    	return this.errorcode;
    }
}
