package com.simo.boss.util;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import java.security.*;
import java.security.spec.*;
import java.util.Collection;

import org.apache.logging.log4j.core.helpers.FileUtils;

//import org.apache.logging.log4j.core.helpers.FileUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class MDtest{
public MDtest()
{
	
}
public  static String MD5(String s){
    char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    try {
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
		byte byte0 = md[i];
		str[k++] = hexDigits[byte0 >>> 4 & 0xf];
		str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
		}
   catch (Exception e){
     return null;
     }
}
public static String mdls(String str) 
{
	try
	{
		BASE64Encoder enc=new BASE64Encoder();
		BASE64Decoder dec=new BASE64Decoder();
		byte[] obj=null;
		try {
			obj = dec.decodeBuffer(str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        MessageDigest md5 = MessageDigest.getInstance("MD5");
	    md5.update(obj);
	    obj = md5.digest();
	    //System.out.println("outbyte="+BytesUtil.outPutToString(obj));
	    //String strrst =new String(obj);
	    String strrst = enc.encode(obj);
	    //System.out.println("strrst="+strrst);
	    //String strrst = BytesUtil.bytes2String(obj, 0, obj.length);
	   // byte[] x = strrst.getBytes();
	    byte[] x=null;
		try {
			x = dec.decodeBuffer(strrst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //byte[] x= BytesUtil.stringToBytes(strrst);
	    //System.out.println("xxx="+BytesUtil.compareBytes(obj, x));
	    //System.out.println("outbyte="+BytesUtil.outPutToString(x));
        return strrst; 
    }
	catch(NoSuchAlgorithmException e) 
	{
	         e.printStackTrace();
	         return null;
	}
}
public static byte[] mdl(String str) 
{
	try
	{
		byte[] obj=null;
		obj = str.getBytes();
		System.out.println(new String(obj));
		//obj = str.getBytes(Charset.forName("ISO-8859-1"));
        MessageDigest md5 = MessageDigest.getInstance("MD5");
	    md5.update(obj);
	    obj = md5.digest();
	    //System.out.println("outbyte="+BytesUtil.outPutToString(obj));
	    String strrst =new String(obj);
	    
	    //String strrst = BytesUtil.bytes2String(obj, 0, obj.length);
	   // byte[] x = strrst.getBytes();
	    //byte[] x= BytesUtil.stringToBytes(strrst);
	   // System.out.println("xxx="+BytesUtil.compareBytes(obj, x));
	    //System.out.println("outbyte="+BytesUtil.outPutToString(x));
	    System.out.println(obj+BytesUtil.outPutToString(obj)+strrst);
        return obj; 
    }
	catch(NoSuchAlgorithmException e) 
	{
	         e.printStackTrace();
	         return null;
	}
}
public static String mdlS(String password) {
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    byte[] md5Bytes = md5.digest(password.getBytes());

    StringBuffer hexValue = new StringBuffer();

    for (int i = 0; i < md5Bytes.length; i++) {
      int val = md5Bytes[i] & 0xFF;
      if (val < 16)
        hexValue.append("0");
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString();
  }
public static void main(String[] args)
{
	MDtest.mdl("");
//	String srcDirPath = "D:\\dev\\workspace\\masdev\\mas\\src"; 
//
//	String utf8DirPath = "D:\\UTF8\\src"; 
//	        
//
//	Collection javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true); 
//	        
//	for (File javaGbkFile : javaGbkFileCol) { 
//
//	      String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length()); 
//
//	      FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));        
//	}
}
}