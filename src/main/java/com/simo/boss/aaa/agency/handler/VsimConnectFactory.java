/*    */ package com.simo.boss.aaa.agency.handler;
/*    */ 
/*    */ import com.simo.boss.common.codec.CommonJsonProtocolCodecFactory;
/*    */ import java.net.InetSocketAddress;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
/*    */ import org.apache.mina.core.future.CloseFuture;
/*    */ import org.apache.mina.core.future.ConnectFuture;
/*    */ import org.apache.mina.core.session.IoSession;
/*    */ import org.apache.mina.core.session.IoSessionConfig;
/*    */ import org.apache.mina.filter.codec.ProtocolCodecFilter;
/*    */ import org.apache.mina.transport.socket.SocketSessionConfig;
/*    */ import org.apache.mina.transport.socket.nio.NioSocketConnector;
/*    */ 
/*    */ public class VsimConnectFactory<T>
/*    */ {
/* 28 */   private static final Logger log = Logger.getLogger(VsimConnectFactory.class);
/*    */   private CommonVsimHandler<T> handler;
/*    */ 
/*    */   public VsimConnectFactory(CommonVsimHandler<T> handler)
/*    */   {
/* 32 */     this.handler = handler;
/*    */   }
/*    */ 
/*    */   public void handler() throws Exception {
/* 36 */     NioSocketConnector connector = new NioSocketConnector();
/* 37 */     connector.getSessionConfig().setReadBufferSize(10240);
/* 38 */     connector.getFilterChain().addLast("codecFilter", new ProtocolCodecFilter(new CommonJsonProtocolCodecFactory()));
/*    */ 
/* 40 */     connector.setHandler(this.handler);
/* 41 */     IoSession session = null;
/*    */     try {
/* 43 */       ConnectFuture future = connector.connect(new InetSocketAddress(System.getProperty("vsimServer.ip").toString(), Integer.parseInt(System.getProperty("vsimServer.port").toString())));
/*    */ 
/* 45 */       future.awaitUninterruptibly();
/*    */ 
/* 47 */       session = future.getSession();
/* 48 */       log.debug("=======VsimConnectFactory连接关闭=========");
/* 49 */       session.getConfig().setUseReadOperation(true);
/*    */ 
/* 51 */       session.getConfig().setWriteTimeout(10000);
/* 52 */       session.getCloseFuture().awaitUninterruptibly();
/*    */     } catch (Exception e) {
/* 54 */       e.printStackTrace();
/* 55 */       log.error("===连接Vsim服务器失败!!!");
/* 56 */       throw new Exception("连接Vsim服务器失败!");
/*    */     }
/* 58 */     connector.dispose(true);
/* 59 */     log.debug("=======connector.dispose()=========");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.VsimConnectFactory
 * JD-Core Version:    0.6.1
 */