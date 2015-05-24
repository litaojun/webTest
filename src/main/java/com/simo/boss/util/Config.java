package com.simo.boss.util;

import org.apache.commons.codec.binary.Base64;

public  class Config {
    public static final short simoProtOne = 1;
    public static final short simoProtTwo = 2;
    public static final short simoProtApply = 3;
    public static final short simoProtRelese = 4;
    public static final short simoProtAuty = 5;
    public static final short simoprotHeart = 6;
    public static final short simoprotCdr = 7;
    public static final int inikeyid = 0;
    public static final int SUCESSCODE = 0;
    public static final int FAILCODE = 1;
    public static final short ver = 1;
    //public static final String termsn = "DEMOAYNWS39F7ltj";
    public static final String termsn = "DEMOAYLWSBFBTSvY";
    //public static final String code1="sq9Y75fK";
    public static final String code1="LayH77BS";
    public static final String username = "78069455@qq.com";
    public static final String password = "simo1234";
    public static final String splitSign = " ";
    public static void main(String[] args)
    {
    	String simaddress = "3232235887.1.25.67";
    	String[] a = simaddress.split("\\.");
		  if(a !=null)
			  System.out.println("len="+a.length);
		 byte[] apduBytes = "litaojunlitaojunfff".getBytes();
		 String x="oIgAABAoSi2ZEe7cgvoU0uTrRQN2";
	     String apdu = Base64.encodeBase64String(apduBytes);
	     byte[] xx = Base64.decodeBase64(x) ;  //.decodeBase64(x);
	     String apdu2 = new String(xx);
	     
	     
	     System.out.println("apdu ="+apdu);
	     System.out.println("apdu2="+apdu2);
	     System.out.println("x    ="+x);
	     
    }
}
