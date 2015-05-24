package com.simo.boss.util;

import java.util.Random;

import com.simo.boss.protocol.dto.GmateClient;

public class TestManager 
{
	private String[] testfile=null;
	private static int sign=0;
	private static String[][] user=null;
	private static String[][] termsn=null;
	private static String[][] netserv=null;
   public TestManager(String[] allfile)
   {
	   this.testfile = allfile;   
	   if(sign == 0)
	   {
		   sign =1;
		   initFile(allfile);
	   }
   }
   public GmateClient getGmateClient(String aaahost,int port)
   {
	   GmateClient gc=null;
	   String[] userpswd = this.getUserAndPswd();
	   String[] snCode = this.getSnCode();
	   String[] networkServ = this.getNetworkServ();
	   return new GmateClient(userpswd[0],"",snCode[0],snCode[1],networkServ[0],networkServ[1],aaahost,port) ;
   }
   public String[] getUserAndPswd()
   {
	   Random N = new Random();
	   int i = N.nextInt(user.length);
	   System.out.println("n="+i + "user.length"+user.length);
	   return user[i];
   }
   
   public String[] getSnCode()
   {
	   Random N = new Random();
	   int i = N.nextInt(termsn.length);
	   System.out.println("n="+i);
	   return termsn[i];
   }
   public String[] getNetworkServ()
   {
	   Random N = new Random();
	   int i =N.nextInt(netserv.length);
	   System.out.println("n="+i);
	   return netserv[i];
   }
   public static void initFile(String[] a)
   {
	   FileOperater foper1 = new FileOperater(a[0],2);
	   FileOperater foper2 = new FileOperater(a[1],2);
	   FileOperater foper3 = new FileOperater(a[2],2);
	   user = foper1.readFileToArray(Config.splitSign); 
	   termsn = foper2.readFileToArray(Config.splitSign);
	   netserv = foper3.readFileToArray(Config.splitSign);
   }
   public static void main(String[] args)
   {
	   String[] filearr = {"D:\\app\\user.txt","D:\\app\\termsn.txt","D:\\app\\network.txt"};
	   TestManager a = new TestManager(filearr);
	   GmateClient gc = a.getGmateClient("1d0.182.1.25", 88);
	   System.out.println(gc.toString());
   }
   
}
