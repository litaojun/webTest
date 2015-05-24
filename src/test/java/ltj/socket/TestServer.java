package ltj.socket;


import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import com.simo.boss.protocol.Body;
import com.simo.boss.protocol.dto.CommonDto;
import com.simo.boss.protocol.dto.GmateClient;
import com.simo.boss.protocol.evaluation.ApplySimRequestDto;
import com.simo.boss.protocol.evaluation.AuthSimMsg;
import com.simo.boss.protocol.evaluation.HeartServer;
import com.simo.boss.protocol.evaluation.Onemsg;
import com.simo.boss.protocol.evaluation.ReleaseSim;
import com.simo.boss.protocol.evaluation.TwoMsg;
import com.simo.boss.protocol.rsp.ApplySimRsp;
import com.simo.boss.protocol.rsp.AuthSimRsp;
import com.simo.boss.protocol.rsp.OneMsgRsp;
import com.simo.boss.protocol.rsp.TwoMsgRsp;
import com.simo.boss.util.BytesUtil;
import com.simo.boss.util.Config;
import com.simo.boss.util.IoBufferUtil;
import com.simo.boss.util.MDtest;
import com.simo.boss.util.DesUtil;
import java.nio.charset.Charset;
public class TestServer {
	private ClientSocket cst = null;
	public ClientSocket apducst = null;
	public ClientSocket heartser = null;
	private OneMsgRsp  omr=new OneMsgRsp();
	private TwoMsgRsp tmr=null;
	private ApplySimRsp  asr = null;
	private AuthSimRsp asrp = null;
	private HeartServer hsr = null;
	private byte[] datakeys = null;
	ReleaseSim rsim = null;
	int a = 0;
	public TestServer(ClientSocket cst)
	{
		this.cst = cst;
	}
	public int heartServerReq()
	{
		int errcode = 1;
		if(this.asr ==null || this.asr.getErrorcode() != 0)
		{
			int sign = this.applySimName();
			if(sign != 0)
			{
			   return 999;	
			}
		}
		this.heartser = new ClientSocket(this.asr.getHeartbeatServerIp(),this.asr.getHeartbeatServerPort(),null);
		this.hsr = new HeartServer(this.asr.getToken());
		byte[] body = this.hsr.toByte();
		Body data = new Body(Config.simoprotHeart,body);
		body = DesUtil.createEncryptor(this.datakeys, data.toBytes());
		CommonDto dto = new CommonDto(tmr.getTermid(),body);
		int rspcode = 999;
		 rspcode = this.heartser.sentMsg(dto);
		 if(rspcode == 0)
		 {
			 dto = this.heartser.recvMsg();
			 //System.out.println("lxen="+dto.getBodyLength());
			 data = new Body(DesUtil.createDecryptor(this.datakeys, dto.getBody()));
			 IoBuffer io = IoBufferUtil.byteToIoBuffer(data.getSubBody());
			 errcode = io.getInt();
			 //System.out.println("errcodeltj="+errcode);
			 return 0;
		 }
		return errcode;
	}
	public int authSim()
	{
		int errcode = 1;
		if(this.datakeys==null && this.asr ==null)
		{
		   	int sign = this.applySimName();
		   	if(sign != 0)
		   		return sign;
		}
		this.apducst = new ClientSocket(this.cst.getGmateClient().getApduhost(),this.cst.getGmateClient().getApduport(),null);
		AuthSimMsg asm = new AuthSimMsg(this.asr.getToken(),this.asr.getSimAddress(),(short)1,"oIgAABAoSi2ZEe7cgvoU0uTrRQN2");
		byte[] body = asm.toByte();
		Body data = new Body(Config.simoProtAuty,body);
		body = DesUtil.createEncryptor(this.datakeys, data.toBytes());
		CommonDto dto = new CommonDto(tmr.getTermid(),body);
		int rspcode = 999;
		 rspcode = this.apducst.sentMsg(dto);
		 if(rspcode == 0)
		 {
			 dto = this.apducst.recvMsg();
			 //System.out.println("lxen="+dto.getBodyLength());
			 data = new Body(DesUtil.createDecryptor(this.datakeys, dto.getBody()));
			 this.asrp=new AuthSimRsp();
			 this.asrp.IobufferToClass(data.getSubBody());
			 //System.out.println("litaojun="+this.asrp.toString());
			 return 0;
		 }
		return errcode;
	}
	public int releseSim()
	{
		int errcode = 1;
		this.rsim = new ReleaseSim(this.asr.getToken(),(byte)0);
		byte[] body = rsim.toByte();
		Body data = new Body(Config.simoProtRelese,body);
		body = DesUtil.createEncryptor(this.datakeys, data.toBytes());
		CommonDto dto = new CommonDto(tmr.getTermid(),body);
		int rspcode = 999;
		rspcode = this.cst.sentMsg(dto);
		if(rspcode == 0)
		{
			   dto = this.cst.recvMsg();
			   //System.out.println("lxen="+dto.getBodyLength());
			   data = new Body(DesUtil.createDecryptor(this.datakeys, dto.getBody()));
			   IoBuffer io = IoBufferUtil.byteToIoBuffer(data.getSubBody());
			   errcode = io.getInt();
			   io.flip();
			   //System.out.println("errcode="+errcode);
		   }
		return errcode;
	}
	public int applySimName()
	{ 
		if(this.datakeys == null)
		    this.datakeys = this.getEnc();
		//byte[] mdtPwd = MDtest.mdl(this.cst.getGmateClient().getPassword());
		byte[] mdtPwd = MDtest.mdl(this.cst.getGmateClient().getPassword());
		System.out.println(new String(mdtPwd));
	   ApplySimRequestDto asrd = new ApplySimRequestDto(this.cst.getGmateClient().getUsername(),mdtPwd,this.cst.getGmateClient().getMcc(),this.cst.getGmateClient().getMnc(),(short)2,(short)7);
	   //System.out.println("MDtest.mdl(this.cst.getGmateClient().getPassword())="+MDtest.mdl(this.cst.getGmateClient().getPassword()));
	   byte[] body = asrd.toByte();
	   Body data = new Body(Config.simoProtApply,body);
	   body = DesUtil.createEncryptor(this.datakeys, data.toBytes());
	   CommonDto dto = new CommonDto(tmr.getTermid(),body);
	   int rspcode = 999;
	   rspcode = this.cst.sentMsg(dto);
	   if(rspcode == 0)
	   {
		   dto = this.cst.recvMsg();
		   //System.out.println("lxen="+dto.getBodyLength());
		   data = new Body(DesUtil.createDecryptor(this.datakeys, dto.getBody()));
		   
		   asr=new ApplySimRsp(this.datakeys);
		   asr.IobufferToClass(data.getSubBody());
		   rspcode = asr.getErrorcode();
		   System.out.println("TestServer-applySimName-asr.token="+asr.getToken());
		   //System.out.println("litaojun="+asr.toString());
	   }
	   
	   return rspcode;
	}
	public byte[] getEnc()
	{
		int rspcode = 999;
		byte[] keys = null;
		Onemsg onemsg = new Onemsg(this.cst.getGmateClient().getTermSn());
		byte[] body = onemsg.toByte();
		Body data = new Body(Config.simoProtOne,body);
		body = data.toBytes();
		CommonDto dto = new CommonDto(Config.inikeyid,body);
		rspcode = cst.sentMsg(dto);
		if(rspcode == 0)
		{
			dto = cst.recvMsg();
			//System.out.println("dto="+dto.getVersion()+"keyid="+dto.getKeyIndex()+"bodylen="+dto.getBodyLength());
			data = new Body(dto.getBody());
		    omr.IobufferToClass(data.getSubBody());
		}
		else
			return null;
		TwoMsg twomsg = new TwoMsg(omr.getTermId(),omr.getRandomStr(),cst.getGmateClient().getCodeStr().substring(0,8),omr.getRandom());
		body = twomsg.toByte();
		data = new Body(Config.simoProtTwo,body);
		body = data.toBytes();
		dto = new CommonDto(Config.inikeyid,body);
		rspcode = cst.sentMsg(dto);
		if(rspcode == 0)
		{
			//System.out.println("rspcode == 0");
			dto = cst.recvMsg();
			data = new Body(dto.getBody());
		    tmr=new TwoMsgRsp(this.cst.getGmateClient().getCodeStr().substring(0,8));
		    tmr.IobufferToClass(data.getSubBody());
		    return tmr.getKdc();
		}
		else
		{
			//System.out.println("litaojun else");
			return null;
		}
	}
    public static void main(String[] args)
    {
    	byte keys[] = null;
    	System.out.println("sign=1111");
    	GmateClient gclient=new GmateClient(Config.termsn,"",Config.termsn,Config.code1,"460","01","192.168.1.103",19003);
    	System.out.println("sign=2222");
    	ClientSocket cst = new ClientSocket("192.168.1.103",19001,gclient);
    	TestServer ts= new TestServer(cst);
    	int sign = ts.applySimName();
    	int auth = ts.authSim();
    	ts.apducst.closeSocket();
    	int heart = ts.heartServerReq();
    	int rsp = 0;//ts.releseSim();
    	System.out.println("sign="+heart+"rsp="+rsp+"auth"+ heart);
    	//System.out.println(BytesUtil.outPutToString(keys));
    	int ss = ts.releseSim();
    	cst.closeSocket();
    	//ts.apducst.closeSocket();
    	ts.heartser.closeSocket();
    	
    }
}
