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
/*    */ public class HeartBeatFactory<T>
/*    */ {
/* 28 */   private static final Logger log = Logger.getLogger(HeartBeatFactory.class);
/*    */   private HeartBeatHandler<T> handler;
/*    */ 
/*    */   public HeartBeatFactory(HeartBeatHandler<T> handler)
/*    */   {
/* 32 */     this.handler = handler;
/*    */   }
/*    */ 
/*    */   public void handler() {
/* 36 */     NioSocketConnector connector = new NioSocketConnector();
/* 37 */     connector.getSessionConfig().setReadBufferSize(10240);
/* 38 */     connector.getFilterChain().addLast("codecFilter", new ProtocolCodecFilter(new CommonJsonProtocolCodecFactory()));
/*    */ 
/* 40 */     connector.setHandler(this.handler);
/* 41 */     IoSession session = null;
/*    */     try {
/* 43 */       ConnectFuture future = connector.connect(new InetSocketAddress(System.getProperty("heartBeatServer.ip").toString(), Integer.parseInt(System.getProperty("heartBeatServer.port").toString())));
/* 44 */       future.awaitUninterruptibly();
/* 45 */       session = future.getSession();
/* 46 */       log.debug("=======HeartBeatFactory连接关闭=========");
/* 47 */       session.getConfig().setUseReadOperation(true);
/* 48 */       session.getCloseFuture().awaitUninterruptibly();
/*    */     } catch (Exception e) {
/* 50 */       e.printStackTrace();
/* 51 */       log.error("===连接heartBeatServer服务器失败!!!");
/*    */     }
/* 53 */     connector.dispose(true);
/* 54 */     log.debug("=======connector.dispose()=========");
/*    */   }
/*    */ }

/* Location:           E:\litaojun\ltj\2.1.7\boss\boss-aaa-2.1.7-bin\boss-aaa-2.1.7\lib\boss-aaa-2.1.7.jar
 * Qualified Name:     com.simo.boss.aaa.agency.handler.HeartBeatFactory
 * JD-Core Version:    0.6.1
 */