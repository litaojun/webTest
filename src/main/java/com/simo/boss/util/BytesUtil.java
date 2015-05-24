package com.simo.boss.util;

import java.io.PrintStream;
import java.nio.charset.Charset;

public class BytesUtil
{
  public static boolean compareBytes(byte[] src, byte[] tar)
  {
    if (src.length != tar.length) {
      return false;
    }
    for (int i = 0; i < tar.length; i++) {
      if (src[i] != tar[i]) {
        return false;
      }
    }
    return true;
  }

  public static int countStringLength(String string)
  {
    if (("".equals(string)) || (null == string))
      return 0;
    return string.getBytes(Charset.forName("utf-8")).length;
  }

  public static byte[] stringToBytes(String string) {
    if (("".equals(string)) || (null == string))
      return "".getBytes();
    return string.getBytes(Charset.forName("utf-8"));
  }

  public static String bytes2String(byte[] bytes, int beginIndex, int length)
  {
    byte[] sub = new byte[length];
    for (int i = 0; i < sub.length; i++) {
      sub[i] = bytes[(i + beginIndex)];
    }
    return new String(sub);
  }

  public static String outPutToString(byte[] bytes)
  {
    String str = "";
    if ((bytes != null) && (bytes.length > 0)) {
      str = "";
      for (int i = 0; i < bytes.length; i++) {
        str = str + " 0x" + Integer.toHexString(bytes[i] & 0xFF | 0xFFFFFF00).substring(6);
      }
    }
    return str.trim();
  }

  public static void main(String[] args) {
    byte[] body = { 97, 98, 99, 100, 1, 4, 90 };

    System.out.println(outPutToString(body));
  }
}