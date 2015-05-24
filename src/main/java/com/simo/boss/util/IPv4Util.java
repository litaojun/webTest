package com.simo.boss.util;

import java.io.PrintStream;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class IPv4Util
{
  private static final int INADDRSZ = 4;
  private static final String ipAddr = "192.168.1.80";

  public static String intToIp(long ipInt)
  {
    long forthIp = ipInt >> 24 & 0xFF;
    long thirdIp = ipInt >> 16 & 0xFF;
    long secondIp = ipInt >> 8 & 0xFF;
    long firstIp = ipInt & 0xFF;
    return String.valueOf(firstIp) + '.' + secondIp + '.' + thirdIp + '.' + forthIp;
  }

  public static String longToIp(long ipInt)
  {
    long firstIp = ipInt >> 24 & 0xFF;
    long secondIp = ipInt >> 16 & 0xFF;
    long thirdIp = ipInt >> 8 & 0xFF;
    long forthIp = ipInt & 0xFF;
    return String.valueOf(firstIp) + '.' + secondIp + '.' + thirdIp + '.' + forthIp;
  }

  public static byte[] ipToBytesByInet(String ipAddr)
  {
    try
    {
      return InetAddress.getByName(ipAddr).getAddress(); } catch (Exception e) {
    }
    throw new IllegalArgumentException(ipAddr + " is invalid IP");
  }

  public static byte[] ipToBytesByReg(String ipAddr)
  {
    byte[] ret = new byte[4];
    try
    {
      String[] ipArr = ipAddr.split("\\.");
      ret[0] = (byte)(Integer.parseInt(ipArr[0]) & 0xFF);
      ret[1] = (byte)(Integer.parseInt(ipArr[1]) & 0xFF);
      ret[2] = (byte)(Integer.parseInt(ipArr[2]) & 0xFF);
      ret[3] = (byte)(Integer.parseInt(ipArr[3]) & 0xFF);
      return ret; } catch (Exception e) {
    }
    throw new IllegalArgumentException(ipAddr + " is invalid IP");
  }

  public static String bytesToIp(byte[] bytes)
  {
    return new StringBuffer().append(bytes[0] & 0xFF).append('.').append(bytes[1] & 0xFF).append('.').append(bytes[2] & 0xFF).append('.').append(bytes[3] & 0xFF).toString();
  }

  public static int bytesToInt(byte[] bytes)
  {
    for (int i = 0; i < bytes.length; i++) {
      System.out.println(bytes[i]);
    }
    int addr = bytes[3] & 0xFF;
    addr |= bytes[2] << 8 & 0xFF00;
    addr |= bytes[1] << 16 & 0xFF0000;
    addr |= bytes[0] << 24 & 0xFF000000;
    return addr;
  }

  public static int ipToInt(String ipAddr)
  {
    try
    {
      return bytesToInt(ipToBytesByInet(ipAddr)); } catch (Exception e) {
    }
    throw new IllegalArgumentException(ipAddr + " is invalid IP");
  }

  public static long ipToLong(String ip)
  {
    long result = 0L;
    StringTokenizer token = new StringTokenizer(ip, ".");
    result += (Long.parseLong(token.nextToken()) << 24);
    result += (Long.parseLong(token.nextToken()) << 16);
    result += (Long.parseLong(token.nextToken()) << 8);
    result += Long.parseLong(token.nextToken());
    return result;
  }

  public static byte[] intToBytes(int ipInt)
  {
    byte[] ipAddr = new byte[4];
    ipAddr[0] = (byte)(ipInt >>> 24 & 0xFF);
    ipAddr[1] = (byte)(ipInt >>> 16 & 0xFF);
    ipAddr[2] = (byte)(ipInt >>> 8 & 0xFF);
    ipAddr[3] = (byte)(ipInt & 0xFF);
    return ipAddr;
  }

  public static int[] getIPIntScope(String ipAndMask)
  {
    String[] ipArr = ipAndMask.split("/");
    if (ipArr.length != 2) {
      throw new IllegalArgumentException("invalid ipAndMask with: " + ipAndMask);
    }

    int netMask = Integer.valueOf(ipArr[1].trim()).intValue();
    if ((netMask < 0) || (netMask > 31)) {
      throw new IllegalArgumentException("invalid ipAndMask with: " + ipAndMask);
    }

    int ipInt = ipToInt(ipArr[0]);
    int netIP = ipInt & -1 << 32 - netMask;
    int hostScope = -1 >>> netMask;
    return new int[] { netIP, netIP + hostScope };
  }

  public static String[] getIPAddrScope(String ipAndMask)
  {
    int[] ipIntArr = getIPIntScope(ipAndMask);
    return new String[] { intToIp(ipIntArr[0]), intToIp(ipIntArr[0]) };
  }

  public static int[] getIPIntScope(String ipAddr, String mask)
  {
    int netMaskInt = 0; int ipcount = 0;
    try {
      int ipInt = ipToInt(ipAddr);
      if ((null == mask) || ("".equals(mask))) {
        return new int[] { ipInt, ipInt };
      }
      netMaskInt = ipToInt(mask);
      ipcount = ipToInt("255.255.255.255") - netMaskInt;
      int netIP = ipInt & netMaskInt;
      int hostScope = netIP + ipcount;
      return new int[] { netIP, hostScope }; } catch (Exception e) {
    }
    throw new IllegalArgumentException("invalid ip scope express  ip:" + ipAddr + "  mask:" + mask);
  }

  public static String[] getIPStrScope(String ipAddr, String mask)
  {
    int[] ipIntArr = getIPIntScope(ipAddr, mask);
    return new String[] { intToIp(ipIntArr[0]), intToIp(ipIntArr[0]) };
  }

  public static long getStringIpToLong(String ip)
  {
    return Long.valueOf(ip).longValue();
  }

  public static void main(String[] args)
    throws Exception
  {
    go();
  }

  private static void go()
  {
    long ipInt = ipToLong("192.168.1.80");

    System.out.println("IP: 192.168.1.80  --> long: " + ipInt);

    int ipInt2 = 1342286016;

    System.out.println("int: " + ipInt2 + " --> IP: " + intToIp(ipInt2));
  }

  private static void oldTest()
  {
    String ipAddr = "192.168.1.80";

    byte[] bytearr = ipToBytesByInet(ipAddr);

    StringBuffer byteStr = new StringBuffer();

    for (byte b : bytearr) {
      if (byteStr.length() == 0)
        byteStr.append(b);
      else {
        byteStr.append("," + b);
      }
    }
    System.out.println("IP: " + ipAddr + " ByInet --> byte[]: [ " + byteStr + " ]");

    bytearr = ipToBytesByReg(ipAddr);
    byteStr = new StringBuffer();

    for (byte b : bytearr) {
      if (byteStr.length() == 0)
        byteStr.append(b);
      else {
        byteStr.append("," + b);
      }
    }
    System.out.println("IP: " + ipAddr + " ByReg  --> byte[]: [ " + byteStr + " ]");

    System.out.println("byte[]: " + byteStr + " --> IP: " + bytesToIp(bytearr));

    int ipInt = ipToInt(ipAddr);

    System.out.println("IP: " + ipAddr + "  --> int: " + ipInt);

    System.out.println("int: " + ipInt + " --> IP: " + intToIp(ipInt));

    String ipAndMask = "192.168.1.1/24";

    int[] ipscope = getIPIntScope(ipAndMask);
    System.out.println(ipAndMask + " --> :[ " + ipscope[0] + "," + ipscope[1] + " ]");

    System.out.println(ipAndMask + " --> :[ " + intToIp(ipscope[0]) + "" + intToIp(ipscope[1]) + " ]");

    String ipAddr1 = "192.168.1.1"; String ipMask1 = "255.255.255.0";

    int[] ipscope1 = getIPIntScope(ipAddr1, ipMask1);
    System.out.println(ipAddr1 + " " + ipMask1 + "  -->:[ " + ipscope1[0] + "," + ipscope1[1] + " ]");

    System.out.println(ipAddr1 + " " + ipMask1 + "  --> :[ " + intToIp(ipscope1[0]) + "" + intToIp(ipscope1[1]) + " ]");
  }
}
