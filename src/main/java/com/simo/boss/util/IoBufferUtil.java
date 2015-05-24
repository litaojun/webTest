package com.simo.boss.util;

import org.apache.mina.core.buffer.IoBuffer;

public class IoBufferUtil
{
  public static IoBuffer concat(IoBuffer frontIoBuffer, IoBuffer nextIoBuffer)
  {
    IoBuffer ioBuffer = IoBuffer.allocate(1024).setAutoExpand(true);
    ioBuffer.put(frontIoBuffer).put(nextIoBuffer);
    ioBuffer.flip();
    return ioBuffer;
  }

  public static IoBuffer intToIoBuffer(int value)
  {
    IoBuffer ioBuffer = IoBuffer.allocate(4).setAutoExpand(true);
    ioBuffer.putInt(value);
    ioBuffer.flip();
    return ioBuffer;
  }

  public static IoBuffer longToIoBuffer(long value)
  {
    IoBuffer ioBuffer = IoBuffer.allocate(8).setAutoExpand(true);
    ioBuffer.putLong(value);
    ioBuffer.flip();
    return ioBuffer;
  }

  public static String byteToString(byte[] b)
  {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      stringBuffer.append((char)b[i]);
    }
    return stringBuffer.toString();
  }

  public static IoBuffer stringToIoBuffer(String str)
  {
    byte[] bt = str.getBytes();
    IoBuffer ioBuffer = IoBuffer.allocate(bt.length);
    ioBuffer.put(bt, 0, bt.length);
    ioBuffer.flip();
    return ioBuffer;
  }

  public static IoBuffer byteToIoBuffer(byte[] bt, int length)
  {
    IoBuffer ioBuffer = IoBuffer.allocate(length);
    ioBuffer.put(bt, 0, length);
    ioBuffer.flip();
    return ioBuffer;
  }

  public static IoBuffer byteToIoBuffer(byte[] bt)
  {
    int length = bt.length;
    return byteToIoBuffer(bt, length);
  }

  public static byte[] ioBufferToByte(Object message)
  {
    if (!(message instanceof IoBuffer)) {
      return null;
    }
    IoBuffer ioBuffer = (IoBuffer)message;
    byte[] b = new byte[ioBuffer.limit()];
    ioBuffer.get(b);
    return b;
  }

  public static String ioBufferToString(Object message)
  {
    if (!(message instanceof IoBuffer)) {
      return "";
    }
    IoBuffer ioBuffer = (IoBuffer)message;
    byte[] b = new byte[ioBuffer.limit()];
    ioBuffer.get(b);
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      stringBuffer.append((char)b[i]);
    }
    return stringBuffer.toString();
  }
  public static void main(String[] args)
  {
	  IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
	  byte[] x = "litaojun".getBytes();
	  buffer.put(x);
	  buffer.putInt(1);
	  buffer.flip();
	  byte[] y = new byte[x.length+4];
	  buffer.get(y);
	  for(int i=0;i<y.length;i++)
	  {
		  if(x[i] != y[i])
		  {
			  System.out.println("error"+i+"x="+x[i]+",y="+y[i]);
		  }
		  else
		  {
			  System.out.println("sucess");
		  }
	  }
  }
}