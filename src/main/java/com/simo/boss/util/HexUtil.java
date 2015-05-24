package com.simo.boss.util;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.lang.StringUtils;

public class HexUtil
{
  public static byte[] toByteArray(String hexString)
  {
    if (StringUtils.isEmpty(hexString)) {
      throw new IllegalArgumentException("this hexString must not be empty");
    }
    hexString = hexString.toLowerCase();
    byte[] byteArray = new byte[hexString.length() / 2];
    int k = 0;
    for (int i = 0; i < byteArray.length; i++)
    {
      byte high = (byte)(Character.digit(hexString.charAt(k), 16) & 0xFF);
      byte low = (byte)(Character.digit(hexString.charAt(k + 1), 16) & 0xFF);
      byteArray[i] = (byte)(high << 4 | low);
      k += 2;
    }
    return byteArray;
  }

  public static String toHexString(byte[] byteArray)
  {
    if ((byteArray == null) || (byteArray.length < 1)) {
      throw new IllegalArgumentException("this byteArray must not be null or empty");
    }
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < byteArray.length; i++) {
      if ((byteArray[i] & 0xFF) < 16)
        hexString.append("0");
      hexString.append(Integer.toHexString(0xFF & byteArray[i]));
    }
    return hexString.toString().toLowerCase();
  }

  private static byte charToByte(char c)
  {
    return (byte)"0123456789ABCDEF".indexOf(c);
  }

  public static byte[] hexStringToBytes(String hexString)
  {
    if ((hexString == null) || (hexString.equals(""))) {
      return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
    }
    return d;
  }

  public static void main(String[] args)
    throws IOException
  {
    byte[] tt = toByteArray("f1ffffffffffffffffffffffff0891683108705505f0ffffff0010ff");

    byte[] tta = toByteArray("084906201949238482");

    byte[] cc = { 8, 73, 6, 32, 25, 73, 35, -124, -126 };

    String st = toHexString(cc);

    System.out.println(BytesUtil.outPutToString(tt));
    System.out.println(BytesUtil.outPutToString(tta));
  }
}