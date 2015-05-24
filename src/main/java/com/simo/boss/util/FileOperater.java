package com.simo.boss.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileOperater 
{
	private FileReader fr=null;
	private BufferedReader br=null;
	private int arrlen;

	public FileOperater(String fileName,int arraylen)
	{
		this.arrlen = arraylen;
	   try {
	    	this.fr = new FileReader(fileName);
	        this.br = new BufferedReader(fr);
           }
	    catch (Exception e)
	    {
            System.err.println("File input error");
        }
	}
	public String[][] readFileToArray(String splitSign) 
	{
	    String record = null;
	    ArrayList aylist = new ArrayList();
	    int recCount = 0;
	    try
	    {
	       record = new String();
	       while ((record = br.readLine()) != null) 
	       {
	    	 if(record != null && !record.equals(""))
	    	 {
	           String[] a= record.split(splitSign);
	           aylist.add(a);
	           System.out.println("xxxx");
	    	 }
	         System.out.println(recCount + ": " + record);
	       }
	      } catch (Exception e)
		    {
	            System.err.println("File input error");
	        }
	      return this.listToarray(aylist);
	  }
	public String[][] listToarray(ArrayList alist)
	{
		if(alist!=null && alist.size() > 0)
		{
			String[][] array = new String[alist.size()][this.arrlen];
			for(int i=0;i<alist.size();i++)
			{
				String[] a = (String[])alist.get(i);
				for(int j=0;j<this.arrlen;j++)
				{
					array[i][j] = a[j]; 
				}
			}
			return array;
		}
		return null;
	}
	public static void main(String[] args)
	{
		FileOperater myfile = new FileOperater("D:\\app\\app.txt",3);
		String[][] a = myfile.readFileToArray(" ");
		if(a!=null && a.length != 0)
		{
			for(int i=0;i<a.length;i++)
			{
				for(int j=0;j<3;j++)
				{
					System.out.println("array i="+i+"j="+j+"value="+a[i][j]);
				}
			}
		}
	}
	       
}
