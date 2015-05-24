package com.simo.boss.protocol.dto;

public class GmateClient {

	/**
	 * @param args
	 */
	private String termSn=null;
	private String username;
	private String password;
	private String codeStr;
	private String mcc;
	private String mnc;
	private String apduhost;
	private int apduport;
	public GmateClient(String username,String password,String termsn,String codeStr,String mcc,String mnc,String apduhost,int apduport)
	{
		this.termSn = termsn;
		this.username = username;
		this.password = password;
		this.codeStr = codeStr;
		this.mcc = mcc;
		this.mnc = mnc;
		this.apduhost = apduhost;
		this.apduport = apduport;
	}
	public String getApduhost()
	{
		return this.apduhost;
	}
	public int getApduport()
	{
		return this.apduport;
	}
	public String getMcc()
	{
		return this.mcc;
	}
	public String getMnc()
	{
		return this.mnc;
	}
	public void setTermSn(String termSn)
	{
		this.termSn = termSn;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setCodeStr(String codeStr)
	{
		this.codeStr = codeStr;
	}
	public String getTermSn()
	{
		return this.termSn;
	}
	public String getUsername()
	{
		return this.username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getCodeStr()
	{
		return this.codeStr;
	}
	public String toString()
	{
		return "this.username="+this.username +"this.password=" + this.password+ "this.termSn=" + this.termSn+ "this.codeStr=" + this.codeStr +"this.mcc="+ this.mcc+ "this.mnc=" + this.mnc+  "this.apduhost=" + this.apduhost ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
