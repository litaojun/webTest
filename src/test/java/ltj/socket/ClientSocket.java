package ltj.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket; 
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.InputStream;

import org.apache.mina.core.buffer.IoBuffer;

import com.simo.boss.protocol.dto.GmateClient;
import com.simo.boss.protocol.dto.CommonDto;
import com.simo.boss.protocol.coder.CommonProtocolEncoder;
import com.simo.boss.util.BytesUtil;
import com.simo.boss.util.Config;
import com.simo.boss.util.IoBufferUtil;

public class ClientSocket 
{
	private Socket client=null;
	private DataInputStream  in=null;
	private DataOutputStream ou =null;
	private CommonProtocolEncoder encoder = null;
	private SocketAddress address;
	int a = 0;
	int b = a;
	GmateClient gmateclit = null;
	public ClientSocket(String hostip,int hostPort,GmateClient curgmate)
	{
		
		this.gmateclit = curgmate;
		if(client == null)
			try {
				address = new  InetSocketAddress(hostip, hostPort);
				client = new Socket();
				client.connect(address);
				//new.test
				//new Socket().;
				in = new DataInputStream(client.getInputStream());
				ou = new DataOutputStream(client.getOutputStream());
				encoder = new CommonProtocolEncoder();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public GmateClient getGmateClient()
	{
		return this.gmateclit;
	}
	public int sentMsg(CommonDto cdto)
	{
		byte[] sentMsg = null;
		try {
			sentMsg = encoder.encodeByte(cdto);
			this.ou.write(sentMsg);
			this.ou.flush();
			return Config.SUCESSCODE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Config.FAILCODE;
			
		}
	}
	public void closeSocket()
	{
		try {
			this.ou.close();
			this.in.close();
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CommonDto recvMsg()
	{
		byte[] rsp = new byte[1024];
		try {
			this.in.read(rsp);
			IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true).setAutoShrink(true);
			buffer = IoBufferUtil.byteToIoBuffer(rsp);
			return this.encoder.doDecodeDto(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	/*public static void main(String[] args)
	{
		Socket client=null;
		try {
			client = new Socket("192.168.0.3",8888);
			if(client!=null)
			  {
			       InputStream in=null;
			       OutputStream ou =null;
				try {
				    	in = client.getInputStream();
					    ou = client.getOutputStream();
					 int i=0;
					 while((i=in.read())!=-1)
				     {
				       System.out.print((char)i);
				     }
				     in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Socket(InetAddress address, int port)
        //		          ����һ�����׽��ֲ�����l�ӵ�ָ�� IP ��ַ��ָ���˿ں�
		
		} */
	public static void main(String[] args)
	{
		String str = "simo1234";
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] obj = null;
			byte[] keys = str.getBytes();
		    md5.update(keys);
		    obj = md5.digest();
			System.out.println("outbyte="+BytesUtil.outPutToString(obj));
			String teststr = new String(obj);
			byte[] data = teststr.getBytes();   //Charset.forName("utf-8")
			//byte[] data = teststr.getBytes(Charset.forName("utf-8"));  
			System.out.println("outbyte="+BytesUtil.outPutToString(data));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}


